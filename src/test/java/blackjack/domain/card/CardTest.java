package blackjack.domain.card;

import blackjack.domain.deck.Deck;
import blackjack.exception.NoSuchCardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {
    @Test
    @DisplayName("카드 52장을 캐싱한다")
    void card_cache() {
        // given, when
        Collection<Card> cards = Card.getAll();
        int size = cards.size();

        // then
        assertThat(size).isEqualTo(Deck.TOTAL_CARD_COUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("suit와 rank가 주어지면 해당 카드를 반환한다.")
    void get_with_suit_and_rank(int rank) {
        // given
        Suit suit = Suit.DIAMOND;

        // when
        Card card = Card.of(suit, rank);

        // then
        assertThat(card.getRank()).isEqualTo(rank);
        assertThat(card.getSuit()).isEqualTo(suit);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "K", "Q", "J"})
    @DisplayName("suit와 symbol이 주어지면 해당 카드를 반환한다.")
    void get_with_suit_and_rank(String symbol) {
        // given
        Suit suit = Suit.DIAMOND;

        // when
        Card card = Card.of(suit, symbol);

        // then
        assertThat(card.getSymbol()).isEqualTo(symbol);
        assertThat(card.getSuit()).isEqualTo(suit);
    }

    @Test
    @DisplayName("동일한 key가 주어지면 항상 동일한 카드를 반환한다.")
    void get_same_card() {
        // given
        Suit suit = Suit.HEART;
        int rank = 2;

        // when
        Card first = Card.of(suit, rank);
        Card second = Card.of(suit, rank);

        // then
        assertThat(first).isSameAs(second);
    }

    @Test
    @DisplayName("없는 카드를 요구하면 예외를 던진다.")
    void throw_exception_on_no_such_card() {
        // given
        Suit suit = Suit.SPADE;
        int rank = 0;

        // when, then
        assertThrows(NoSuchCardException.class, () -> {
            Card.of(suit, rank);
        });
    }

    @ParameterizedTest
    @CsvSource({"J, true", "A, false", "2, false"})
    @DisplayName("메이저 카드인지 확인한다.")
    void is_major_card(String signature, boolean expected) {
        // given
        Card card = Card.of(Suit.SPADE, signature);

        // when
        boolean isMajorCard = card.isMajorCard();

        // then
        assertThat(isMajorCard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"J, false", "A, true", "2, false"})
    @DisplayName("에이스 카드인지 확인한다.")
    void is_ace_card(String signature, boolean expected) {
        // given
        Card card = Card.of(Suit.SPADE, signature);

        // when
        boolean isAceCard = card.isAceCard();

        // then
        assertThat(isAceCard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"J, true", "A, false", "2, true"})
    @DisplayName("에이스 카드가 아닌지 확인한다.")
    void is_not_ace_card(String signature, boolean expected) {
        // given
        Card card = Card.of(Suit.SPADE, signature);

        // when
        boolean isNotAceCard = card.isNotAceCard();

        // then
        assertThat(isNotAceCard).isEqualTo(expected);
    }
}
