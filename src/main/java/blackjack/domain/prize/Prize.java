package blackjack.domain.prize;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.HandsStatus;
import blackjack.domain.participant.Player;

import static blackjack.domain.table.Table.BLACKJACK_RANK;

public enum Prize {
    BLACKJACK(2.5),
    WIN(2),
    TIE(1),
    LOSE(0);

    private final double dividendRate;

    Prize(double dividendRate) {
        this.dividendRate = dividendRate;
    }

    public static Prize of(Player player, Dealer dealer) {
        HandsStatus dealerStatus = HandsStatus.of(dealer);
        HandsStatus playerStatus = HandsStatus.of(player);

        if (dealerStatus.isBlackjack()) {
            return findPlayerPrizeOnDealerBlackjack(playerStatus);
        }
        if (playerStatus.isBlackjack()) {
            return BLACKJACK;
        }
        if (playerStatus.isBust()) {
            return LOSE;
        }
        if (dealerStatus.isBust()) {
            return WIN;
        }
        return findPlayerPrizeOnHold(player, dealer);
    }

    private static Prize findPlayerPrizeOnDealerBlackjack(HandsStatus playerStatus) {
        if (playerStatus.isBlackjack()) {
            return TIE;
        }
        return LOSE;
    }

    private static Prize findPlayerPrizeOnHold(Player player, Dealer dealer) {
        int dealerGapAmount = BLACKJACK_RANK - dealer.sumRank();
        int playerGapAmount = BLACKJACK_RANK - player.sumRank();

        if (dealerGapAmount == playerGapAmount) {
            return TIE;
        }
        if (dealerGapAmount < playerGapAmount) {
            return LOSE;
        }
        return WIN;
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
