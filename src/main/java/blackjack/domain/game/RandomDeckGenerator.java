package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomDeckGenerator implements DeckGenerator {

    @Override
    public Deque<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(generateNumberCards(suit));
            cards.addAll(generateMajorCards(suit));
            cards.add(new AceCard(suit));
        }

        Collections.shuffle(cards);

        return new ArrayDeque<>(cards);
    }

    private List<Card> generateMajorCards(Suit suit) {
        return MAJOR_SIGNATURES.stream()
                .map(signature -> new Card(suit, signature))
                .collect(Collectors.toList());
    }

    private List<Card> generateNumberCards(Suit suit) {
        return IntStream.rangeClosed(MIN_NUMBER_RANK, MAX_NUMBER_RANK)
                .mapToObj(signature -> new Card(suit, signature))
                .collect(Collectors.toList());
    }
}
