package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AceCardTest {

    @ParameterizedTest
    @MethodSource({"generateHands"})
    @DisplayName("에이스 카드 개수와 나머지 카드 합이 주어지면 1 또는 11 값을 반환한다.")
    void several_ace_cards(int sumExceptAceCards, int aceCardCount, int exceptedRankSum) {
        //given, when
        int cardRankSum = AceCard.softOrHardSum(sumExceptAceCards, aceCardCount);

        // then
        assertThat(cardRankSum).isEqualTo(exceptedRankSum);
    }

    private static Stream<Arguments> generateHands() {
        return Stream.of(
                Arguments.of(9, 2, 21),
                Arguments.of(19, 2, 21),
                Arguments.of(20, 2, 22),
                Arguments.of(8, 3, 21),
                Arguments.of(18, 3, 21),
                Arguments.of(19, 3, 22),
                Arguments.of(7, 4, 21),
                Arguments.of(17, 4, 21),
                Arguments.of(18, 4, 22)
        );
    }
}