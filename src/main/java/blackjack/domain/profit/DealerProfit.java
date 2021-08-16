package blackjack.domain.profit;

public class DealerProfit {
    private final int profit;

    public DealerProfit(int tableTotalBetAmount, int playersTotalPrizeAmount) {
        this.profit = tableTotalBetAmount - playersTotalPrizeAmount;
    }

    public int getProfit() {
        return profit;
    }
}
