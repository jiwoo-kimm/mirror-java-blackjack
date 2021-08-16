package blackjack.controller;

import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.deck.RandomDeckGenerator;
import blackjack.domain.participant.Player;
import blackjack.domain.profit.ParticipantsProfit;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerInput;
import blackjack.exception.InvalidInputException;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.NoSuchElementException;

public class BlackjackController {

    private BlackjackService blackjackService;

    public void run() {
        try {
            setUp();
            initialDeal();
            deal();
            gameResult();
        } catch (InvalidInputException | NoSuchElementException | IndexOutOfBoundsException e) {
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

        ParticipantsProfit participantsProfit = blackjackService.getProfitResults();
        OutputView.printPrizeResults(participantsProfit);
    }
}