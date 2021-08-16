package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Signature.ACE;

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
            return sumAceCards(sumExceptAceCards, aceCardCount) + sumExceptAceCards;
        }

        return sumExceptAceCards;
    }

    private int calculateSumExceptAceCards() {
        return hands.stream()
                .filter(Card::isNotAceCard)
                .mapToInt(Card::getRank)
                .sum();
    }

    private int countAceCards() {
        return (int) hands.stream()
                .filter(Card::isAceCard)
                .count();
    }

    protected int sumAceCards(int sumExceptAceCards, int aceCardCount) {
        if (softRankAvailable(sumExceptAceCards, aceCardCount)) {
            return ACE.getSoftRank() + (aceCardCount - 1) * ACE.getHardRank();
        }
        return aceCardCount * ACE.getHardRank();
    }

    private boolean softRankAvailable(int sumExceptAceCards, int aceCardCount) {
        int threshold = ACE.getSoftRank() - aceCardCount;
        return sumExceptAceCards <= threshold;
    }

    public boolean hasOneAceCard() {
        return countAceCards() == 1;
    }

    public boolean hasOneMajorCard() {
        return countMajorCards() == 1;
    }

    private int countMajorCards() {
        return (int) hands.stream()
                .filter(Card::isMajorCard)
                .count();
    }

    public Card getFirstHand() {
        return hands.get(0);
    }

    public List<Card> getHands() {
        return new ArrayList<>(hands);
    }
}
