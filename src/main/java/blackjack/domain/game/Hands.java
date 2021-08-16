package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Hands {
    private final List<Card> hands;

    public Hands(List<Card> cards) {
        this.hands = new ArrayList<>(cards);
    }

    public void add(List<Card> card) {
        this.hands.addAll(card);
    }

    public void add(Card card) {
        this.hands.add(card);
    }

    public int size() {
        return hands.size();
    }

    public int sumRanks() {
        int sumExceptAceCards = calculateSumExceptAceCards();
        int aceCardCount = countAceCards();

        if (aceCardCount > 0) {
            return AceCard.softOrHardSum(sumExceptAceCards, aceCardCount);
        }
        return sumExceptAceCards;
    }

    private int calculateSumExceptAceCards() {
        return hands.stream()
                .filter(((Predicate<? super Card>) AceCard.class::isInstance).negate())
                .mapToInt(Card::getRank)
                .sum();
    }

    public int countAceCards() {
        return (int) hands.stream()
                .filter(AceCard.class::isInstance)
                .count();
    }

    public int countMajorCards() {
        return (int) hands.stream()
                .filter(Card::majorCard)
                .count();
    }

    public Card getFirstHand() {
        return hands.get(0);
    }

    public List<Card> getHands() {
        return new ArrayList<>(hands);
    }
}
