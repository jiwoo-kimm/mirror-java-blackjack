package blackjack.domain.participant;

import static blackjack.domain.table.Table.BLACKJACK_RANK;

public enum HandsStatus {
    BLACKJACK,
    BUST,
    HOLD;

    public static HandsStatus of(Participant participant) {
        if (participant.isBlackjack()) {
            return BLACKJACK;
        }

        if (participant.sumRank() > BLACKJACK_RANK) {
            return BUST;
        }

        return HOLD;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isBust() {
        return this == BUST;
    }
}
