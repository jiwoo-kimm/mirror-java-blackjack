package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.*;

public class RandomDeckGenerator implements DeckGenerator {

    @Override
    public Deque<Card> generateDeck() {
        List<Card> cards = new ArrayList<>(Card.getAll());
        Collections.shuffle(cards);

        return new ArrayDeque<>(cards);
    }
}
