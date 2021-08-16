package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.fixture.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandsTest {
    @Test
    @DisplayName("에이스 카드가 없을 때 전체 카드 합을 계산한다.")
    void sum_hands_without_ace_card() {
        //given
        List<Card> cards = Arrays.asList(CARD_K, CARD_2);
        Hands hands = new Hands(cards);

        //when
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나 있을 때 전체 카드 합을 계산한다.")
    void sum_hands_with_one_ace_card() {
        //given
        List<Card> cards = Arrays.asList(CARD_2, CARD_K, ACE_1);
        Hands hands = new Hands(cards);

        //when
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(13);
    }

    @ParameterizedTest
    @MethodSource({"generateHandsParams"})
    @DisplayName("에이스 카드 개수와 나머지 카드 합이 주어지면 에이스 카드 합을 반환한다.")
    void several_ace_cards(int sumExceptAceCards, int aceCardCount, int exceptedAceSum) {
        // given
        Hands hands = new Hands(Collections.emptyList());

        // when
        int cardRankSum = hands.sumAceCards(sumExceptAceCards, aceCardCount);

        // then
        assertThat(cardRankSum).isEqualTo(exceptedAceSum);
    }

    private static Stream<Arguments> generateHandsParams() {
        return Stream.of(
                Arguments.of(9, 2, 12),
                Arguments.of(19, 2, 2),
                Arguments.of(20, 2, 2),
                Arguments.of(8, 3, 13),
                Arguments.of(18, 3, 3),
                Arguments.of(19, 3, 3),
                Arguments.of(7, 4, 14),
                Arguments.of(17, 4, 4),
                Arguments.of(18, 4, 4)
        );
    }
}
