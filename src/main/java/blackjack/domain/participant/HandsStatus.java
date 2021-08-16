package blackjack.domain.participant;

import static blackjack.domain.prize.PrizeResults.BLACKJACK_RANK;

public enum HandsStatus {
    BUST,
    BLACKJACK,
    HOLD;

    public static HandsStatus of(Participant participant) {
        if (participant.sumRank() > BLACKJACK_RANK) {
            return BUST;
        }

        if (participant.isBlackjack()) {
            return BLACKJACK;
        }

        return HOLD;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }
}
