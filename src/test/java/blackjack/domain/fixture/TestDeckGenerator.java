package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.game.DeckGenerator;

import java.util.ArrayDeque;
import java.util.Deque;

import static blackjack.domain.fixture.TestCard.*;

public class TestDeckGenerator implements DeckGenerator {

    public static final int TEST_DECK_INITIAL_SIZE = 10;

    @Override
    public Deque<Card> generateCards() {
        Deque<Card> cards = new ArrayDeque<>();

        cards.offerLast(ACE_1);
        cards.offerLast(CARD_2);
        cards.offerLast(CARD_K);
        cards.offerLast(CARD_3);
        cards.offerLast(CARD_Q);
        cards.offerLast(CARD_4);
        cards.offerLast(CARD_J);
        cards.offerLast(CARD_5);
        cards.offerLast(CARD_6);
        cards.offerLast(CARD_7);

        return cards;
    }
}