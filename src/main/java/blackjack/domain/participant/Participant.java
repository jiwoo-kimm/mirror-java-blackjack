package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Hands;

import java.util.List;

public abstract class Participant {
    protected final Hands hands;

    protected Participant(List<Card> cards) {
        this.hands = new Hands(cards);
    }

    public void take(List<Card> cards) {
        hands.add(cards);
    }

    public void take(Card card) {
        hands.add(card);
    }

    public int sumRank() {
        return hands.sumRanks();
    }

    public boolean isBlackjack() {
        return hands.countAceCards() == 1 && hands.countMajorCards() == 1;
    }

    public int countHands() {
        return hands.size();
    }

    public List<Card> getCards() {
        return hands.getHands();
    }
}