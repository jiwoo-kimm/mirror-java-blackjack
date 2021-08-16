package blackjack.domain.profit;

import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.table.BettingTable;

import java.util.List;

public class ParticipantsProfit {
    private final DealerProfit dealerProfit;
    private final PlayersProfit playersProfit;

    public ParticipantsProfit(BettingTable bettingTable, PlayersPrize prizes) {
        this.playersProfit = new PlayersProfit(bettingTable, prizes);
        this.dealerProfit = new DealerProfit(bettingTable.calculateTotalBetAmount(), playersProfit.calculateTotalAmount());
    }

    public int getDealerProfit() {
        return dealerProfit.getProfit();
    }

    public List<PlayerProfit> getPlayersProfit() {
        return playersProfit.getPlayerPrizes();
    }
}
