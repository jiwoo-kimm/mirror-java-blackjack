package blackjack.domain.card;

public class AceCard extends Card {

    private static final int HARD_HAND = 1;
    private static final int SOFT_HAND = 11;
    private static final String SIGNATURE = "A";
    public static final int ACE_CARD_DEFAULT_RANK = HARD_HAND;

    public AceCard(Suit suit) {
        super(suit, ACE_CARD_DEFAULT_RANK, SIGNATURE);
    }

    public static int softOrHardSum(int sumExceptAceCards, int aceCardCount) {
        if (softAvailable(sumExceptAceCards, aceCardCount)) {
            return softSum(sumExceptAceCards, aceCardCount);
        }
        return hardSum(sumExceptAceCards, aceCardCount);
    }

    private static boolean softAvailable(int sumExceptAceCards, int aceCardCount) {
        int threshold = SOFT_HAND - aceCardCount;
        return sumExceptAceCards <= threshold;
    }

    private static int softSum(int sumExceptAceCards, int aceCardCount) {
        int hardHandCount = aceCardCount - 1;
        return sumExceptAceCards + (hardHandCount * HARD_HAND + SOFT_HAND);
    }

    private static int hardSum(int sumExceptAceCards, int aceCardCount) {
        return sumExceptAceCards + (aceCardCount * HARD_HAND);
    }

}
