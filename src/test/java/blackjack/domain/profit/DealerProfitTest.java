package blackjack.domain.profit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerProfitTest {

    @ParameterizedTest
    @CsvSource({"20, 10", "10, 10", "10, 20"})
    @DisplayName("총 배팅금액과 플레이어들의 총 수익이 주어지면 딜러의 최종 수익을 계산한다.")
    void generate(int totalBetAmount, int playersTotalPrizeAmount) {
        // given, when
        DealerProfit dealerProfit = new DealerProfit(totalBetAmount, playersTotalPrizeAmount);
        int dealerPrizeAmount = dealerProfit.getProfit();

        // then
        assertThat(dealerPrizeAmount).isEqualTo(totalBetAmount - playersTotalPrizeAmount);
    }
}
