package blackjack.domain.prize;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.fixture.TestCards.*;
import static org.assertj.core.api.Assertions.assertThat;

class PrizeTest {

    @ParameterizedTest
    @MethodSource("generateDealerAndPlayerCards")
    @DisplayName("딜러와 플레이어가 주어지면 플레이어의 상을 찾는다.")
    void find_player_prize(List<Card> dealerCards, List<Card> playerCards, Prize expectedPrize) {
        // given
        Dealer dealer = new Dealer(dealerCards);
        Player player = new Player("name", playerCards);

        // when
        Prize prize = Prize.of(player, dealer);

        // then
        assertThat(prize).isEqualTo(expectedPrize);
    }

    private static Stream<Arguments> generateDealerAndPlayerCards() {
        return Stream.of(
                Arguments.of(blackjackCards(), blackjackCards(), Prize.TIE),
                Arguments.of(blackjackCards(), anyHoldCards(), Prize.LOSE),

                Arguments.of(winCards(), blackjackCards(), Prize.BLACKJACK),
                Arguments.of(bustCards(), blackjackCards(), Prize.BLACKJACK),
                Arguments.of(anyHoldCards(), blackjackCards(), Prize.BLACKJACK),

                Arguments.of(winCards(), bustCards(), Prize.LOSE),
                Arguments.of(bustCards(), bustCards(), Prize.LOSE),
                Arguments.of(anyHoldCards(), bustCards(), Prize.LOSE),

                Arguments.of(winCards(), winCards(), Prize.TIE),
                Arguments.of(bustCards(), winCards(), Prize.WIN),
                Arguments.of(anyHoldCards(), winCards(), Prize.WIN),

                Arguments.of(winCards(), anyHoldCards(), Prize.LOSE),
                Arguments.of(bustCards(), anyHoldCards(), Prize.WIN),

                Arguments.of(holdCardsForWin(), holdCardsForWin(), Prize.TIE),
                Arguments.of(holdCardsForLose(), holdCardsForWin(), Prize.WIN),
                Arguments.of(holdCardsForWin(), holdCardsForLose(), Prize.LOSE)
        );
    }
}