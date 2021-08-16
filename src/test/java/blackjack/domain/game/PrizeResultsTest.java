package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.DealerPrize;
import blackjack.domain.prize.PlayerPrize;
import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.prize.PrizeResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.fixture.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class PrizeResultsTest {

    @ParameterizedTest
    @MethodSource("generateCompareHands")
    @DisplayName("딜러와 플레이어의 카드 합을 비교한다.")
    void compare_rank_sum(Dealer dealer, List<Player> players,
                          int winCount, int loseCount, int tieCount,
                          List<String> playersResults) {
        //given, when
        PrizeResults prizeResults = new PrizeResults(dealer, players);
        DealerPrize dealerPrize = prizeResults.getDealerPrize();
        PlayersPrize playersPrize = prizeResults.getPlayersPrize();

        //then
        assertThat(dealerPrize.getWinCount()).isEqualTo(winCount);
        assertThat(dealerPrize.getLoseCount()).isEqualTo(loseCount);
        assertThat(dealerPrize.getTieCount()).isEqualTo(tieCount);

        for (int i = 0; i < players.size(); i++) {
            PlayerPrize playerPrize = playersPrize.getPlayerPrizes().get(i);
            assertThat(playerPrize.getPrize().getTitle()).isEqualTo(playersResults.get(i));
        }
    }

    private static List<Player> generatePlayers() {
        return Arrays.asList(
                new Player("1", Arrays.asList(CARD_2, CARD_8, CARD_6)),   // 16
                new Player("2", Arrays.asList(CARD_K, CARD_7)),           // 17
                new Player("3", Arrays.asList(CARD_3, CARD_Q, CARD_6)),   // 19
                new Player("4", Arrays.asList(CARD_3, CARD_Q, CARD_6)),   // 19
                new Player("블랙잭", Arrays.asList(CARD_Q, ACE_1)),        // blackjack
                new Player("5", Arrays.asList(CARD_Q, CARD_8, CARD_9))    // bust
        );
    }

    private static Dealer generateDealer() { // 19
        return new Dealer(Arrays.asList(CARD_9, CARD_K));
    }

    private static Dealer generateWinDealer() { // 21
        return new Dealer(Arrays.asList(CARD_6, CARD_7, CARD_8));
    }

    private static Dealer generateBlackjackDealer() { // 21
        return new Dealer(Arrays.asList(ACE_3, CARD_Q));
    }

    private static Dealer generateBustDealer() { // 23
        return new Dealer(Arrays.asList(CARD_6, CARD_7, CARD_Q));
    }

    private static Stream<Arguments> generateCompareHands() {
        return Stream.of(
                Arguments.of(generateDealer(), generatePlayers(), 3, 1, 2, Arrays.asList("패", "패", "무", "무", "승", "패")),
                Arguments.of(generateWinDealer(), generatePlayers(), 5, 1, 0, Arrays.asList("패", "패", "패", "패", "승", "패")),
                Arguments.of(generateBlackjackDealer(), generatePlayers(), 6, 0, 0, Arrays.asList("패", "패", "패", "패", "승", "패")),
                Arguments.of(generateBustDealer(), generatePlayers(), 0, 6, 0, Arrays.asList("승", "승", "승", "승", "승", "패"))
        );
    }
}
