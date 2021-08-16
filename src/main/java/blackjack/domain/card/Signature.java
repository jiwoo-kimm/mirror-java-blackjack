package blackjack.domain.card;

public enum Signature {
    ACE("A", 11),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    NUMBER2("2", 2),
    NUMBER3("3", 3),
    NUMBER4("4", 4),
    NUMBER5("5", 5),
    NUMBER6("6", 6),
    NUMBER7("7", 7),
    NUMBER8("8", 8),
    NUMBER9("9", 9),
    NUMBER10("10", 10);

    private static final int HARD_RANK_OF_ACE = 1;
    private static final int SOFT_RANK_OF_ACE = 11;

    private final String symbol;
    private final int rank;

    Signature(String name, int rank) {
        this.symbol = name;
        this.rank = rank;
    }

    public boolean isMajor() {
        return this == JACK || this == QUEEN || this == KING;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getHardRank() {
        if (this != ACE) {
            throw new UnsupportedOperationException(String.format("Hard rank는 ACE만 보유하고 있습니다. CARD=%s", this.name()));
        }
        return HARD_RANK_OF_ACE;
    }

    public int getSoftRank() {
        if (this != ACE) {
            throw new UnsupportedOperationException(String.format("Soft rank는 ACE만 보유하고 있습니다. CARD=%s", this.name()));
        }
        return SOFT_RANK_OF_ACE;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getRank() {
        return rank;
    }
}