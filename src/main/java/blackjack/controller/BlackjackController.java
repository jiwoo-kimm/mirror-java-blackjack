package blackjack.controller;

import blackjack.domain.game.DeckGenerator;
import blackjack.domain.game.RandomDeckGenerator;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    private BlackjackService blackjackService;

    public void run() {
        try {
            setUp();
            initialDeal();
            deal();
            gameResult();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void setUp() {
        List<String> playerNames = InputView.getPlayers();
        DeckGenerator deckGenerator = new RandomDeckGenerator();
        blackjackService = new BlackjackService(playerNames, deckGenerator);
    }

    private void initialDeal() {
        ParticipantsDto initialParticipants = blackjackService.getParticipants();
        OutputView.printInitialDeal(initialParticipants);
    }

    private void deal() {
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

        PrizeResults prizeResults = blackjackService.calculatePrizeResults();
        OutputView.printPrizeResults(prizeResults);
    }
}