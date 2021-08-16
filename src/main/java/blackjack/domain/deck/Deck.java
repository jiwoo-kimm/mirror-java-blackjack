package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static blackjack.exception.ExceptionMessage.EMPTY_DECK;

public class Deck {
    public static final int TOTAL_CARD_COUNT = 52;

    private final Deque<Card> cards;

    public Deck(DeckGenerator deckGenerator) {
        this.cards = deckGenerator.generateDeck();
    }

    public List<Card> drawCards(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> drawCard())
                .collect(Collectors.toList());
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_DECK);
        }

        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
