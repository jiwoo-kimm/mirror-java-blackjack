package blackjack.domain.prize;

import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.HandsStatus;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class PrizeResults {
    public static final int BLACKJACK_RANK = 21;

    private final DealerPrize dealerPrize;
    private final PlayersPrize playersPrize;

    public PrizeResults(Dealer dealer, List<Player> players) {
        this.dealerPrize = new DealerPrize();
        this.playersPrize = new PlayersPrize(findPrizes(dealer, players));
    }

    public PrizeResults(Table table) {
        this(table.getDealer(), table.getPlayers());
    }

    private List<PlayerPrize> findPrizes(Dealer dealer, List<Player> players) {
        List<PlayerPrize> playersPrize = new ArrayList<>();
        for (Player player : players) {
            PlayerPrize playerPrize = findPrize(dealer, player);
            playersPrize.add(playerPrize);
        }
        return playersPrize;
    }

    private PlayerPrize findPrize(Dealer dealer, Player player) {
        final HandsStatus dealerStatus = HandsStatus.of(dealer);
        final HandsStatus playerStatus = HandsStatus.of(player);

        if (dealerStatus.isBust() || playerStatus.isBust()) {
            Prize prize = prizeOnBust(dealerStatus, playerStatus);
            return new PlayerPrize(player.getName(), prize);
        }

        if (dealerStatus.isBlackjack() || playerStatus.isBlackjack()) {
            Prize prize = prizeOnBlackjack(dealerStatus, playerStatus);
            return new PlayerPrize(player.getName(), prize);
        }

        Prize prize = prizeOnHold(dealer, player);
        return new PlayerPrize(player.getName(), prize);
    }

    private Prize prizeOnBust(HandsStatus dealerStatus, HandsStatus playerStatus) {
        if (dealerStatus.isBust() && playerStatus.isBust()) {
            dealerPrize.incrementLoseCount();
            return Prize.LOSE;
        }
        if (dealerStatus.isBust()) {
            dealerPrize.incrementLoseCount();
            return Prize.WIN;
        }
        dealerPrize.incrementWinCount();
        return Prize.LOSE;
    }

    private Prize prizeOnBlackjack(HandsStatus dealerStatus, HandsStatus playerStatus) {
        if (dealerStatus.isBlackjack() && playerStatus.isBlackjack()) {
            dealerPrize.incrementWinCount();
            return Prize.WIN;
        }
        if (dealerStatus.isBlackjack()) {
            dealerPrize.incrementWinCount();
            return Prize.LOSE;
        }
        dealerPrize.incrementLoseCount();
        return Prize.WIN;
    }

    private Prize prizeOnHold(Dealer dealer, Player player) {
        int dealerGapAmount = Math.abs(BLACKJACK_RANK - dealer.sumRank());
        int playerGapAmount = Math.abs(BLACKJACK_RANK - player.sumRank());

        if (dealerGapAmount == playerGapAmount) {
            dealerPrize.incrementTieCount();
            return Prize.TIE;
        }
        if (dealerGapAmount < playerGapAmount) {
            dealerPrize.incrementWinCount();
            return Prize.LOSE;
        }
        dealerPrize.incrementLoseCount();
        return Prize.WIN;
    }

    public DealerPrize getDealerPrize() {
        return dealerPrize;
    }

    public PlayersPrize getPlayersPrize() {
        return playersPrize;
    }
}
