package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    public Card getFaceUpCard() {
        return hands.getFirstHand();
    }

    public boolean hit() {
        return sumRank() <= HIT_THRESHOLD;
    }
}
