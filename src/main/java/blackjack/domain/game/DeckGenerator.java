package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public interface DeckGenerator {
    int MIN_NUMBER_RANK = 2;
    int MAX_NUMBER_RANK = 10;
    List<String> MAJOR_SIGNATURES = Arrays.asList("K", "Q", "J");

    Deque<Card> generateCards();
}
