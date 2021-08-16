package blackjack.domain.prize;

public class DealerPrize {
    private static final int INITIAL_COUNT = 0;

    private int winCount;
    private int loseCount;
    private int tieCount;

    public DealerPrize() {
        this.winCount = INITIAL_COUNT;
        this.loseCount = INITIAL_COUNT;
        this.tieCount = INITIAL_COUNT;
    }

    public void incrementWinCount() {
        winCount++;
    }

    public void incrementLoseCount() {
        loseCount++;
    }

    public void incrementTieCount() {
        tieCount++;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getTieCount() {
        return tieCount;
    }
}
