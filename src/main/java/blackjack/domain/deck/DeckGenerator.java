package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.Deque;

public interface DeckGenerator {
    Deque<Card> generateDeck();
}
