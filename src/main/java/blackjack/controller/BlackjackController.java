package blackjack.controller;

import blackjack.domain.game.DeckGenerator;
import blackjack.domain.game.RandomDeckGenerator;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.ParticipantsPrize;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerInput;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.NoSuchElementException;

import static blackjack.exception.ExceptionMessage.INVALID_BET_AMOUNT_MESSAGE;
import static blackjack.exception.ExceptionMessage.INVALID_CARD_KEY_MESSAGE;

public class BlackjackController {

    private BlackjackService blackjackService;

    public void run() {
        try {
            setUp();
            initialDeal();
            deal();
            gameResult();
        } catch (NumberFormatException e) {
            OutputView.printError(INVALID_BET_AMOUNT_MESSAGE);
        } catch (NoSuchElementException e) {
            OutputView.printError(INVALID_CARD_KEY_MESSAGE);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void setUp() {
        List<PlayerInput> playerInputs = InputView.getPlayersInput();
        DeckGenerator deckGenerator = new RandomDeckGenerator();
        blackjackService = new BlackjackService(playerInputs, deckGenerator);
    }

    private void initialDeal() {
        ParticipantsDto initialParticipants = blackjackService.getParticipants();
        OutputView.printInitialDeal(initialParticipants);
    }

    private void deal() {
        if (blackjackService.isDealerBlackjack()) {
            OutputView.printDealerBlackjack();
            return;
        }

        blackjackService.getPlayers().forEach(this::hitOrStand);
        blackjackService.dealDealer();
    }

    private void hitOrStand(Player player) {
        while (InputView.hit(player.getName())) {
            PlayerDto playerDto = blackjackService.hit(player);
            OutputView.printPlayerHandsMessage(playerDto);
        }

        if (player.neverHit()) {
            OutputView.printPlayerHandsMessage(new PlayerDto(player));
        }
    }

    private void gameResult() {
        ParticipantsDto finalParticipants = blackjackService.getFinalParticipants();
        OutputView.printFinalHands(finalParticipants);

        ParticipantsPrize participantsPrize = blackjackService.getPrizeResults();
        OutputView.printPrizeResults(participantsPrize);
    }
}