package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Deck {
    public static final int TOTAL_CARD_COUNT = 52;

    private final Deque<Card> cards;

    public Deck(DeckGenerator deckGenerator) {
        this.cards = deckGenerator.generateCards();
    }

    public List<Card> drawCards(int count) {
        List<Card> deals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            deals.add(drawCard());
        }
        return deals;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException("남아있는 카드가 없습니다.");
        }

        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
