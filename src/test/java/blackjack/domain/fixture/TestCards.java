package blackjack.domain.fixture;

import blackjack.domain.card.Card;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;

public class TestCards {
    public static List<Card> blackjackCards() {
        return Arrays.asList(ACE_3, CARD_Q);
    }

    public static List<Card> winCards() {
        return Arrays.asList(CARD_6, CARD_7, CARD_8);
    }

    public static List<Card> bustCards() {
        return Arrays.asList(CARD_6, CARD_7, CARD_Q);
    }

    public static List<Card> anyHoldCards() {
        return Arrays.asList(CARD_9, CARD_Q);
    }

    public static List<Card> holdCardsForWin() {
        return Arrays.asList(CARD_9, CARD_K);
    }

    public static List<Card> holdCardsForLose() {
        return Arrays.asList(CARD_8, CARD_9);
    }
}
