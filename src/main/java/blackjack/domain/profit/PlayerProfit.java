package blackjack.domain.profit;

import blackjack.domain.prize.Prize;

public class PlayerProfit {
    private final String playerName;
    private final int receiveAmount;
    private final int profit;

    public PlayerProfit(String playerName, int betAmount, Prize prize) {
        this.playerName = playerName;
        this.receiveAmount = (int) (betAmount * prize.getDividendRate());
        this.profit = this.receiveAmount - betAmount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getReceiveAmount() {
        return receiveAmount;
    }

    public int getProfit() {
        return profit;
    }
}
