package blackjack.domain.table;

import blackjack.dto.PlayerInput;
import blackjack.exception.NoSuchPlayerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BettingTableTest {

    @Test
    @DisplayName("플레이어의 이름이 주어지면 베팅 금액을 반환한다.")
    void get_bet_amount() {
        // given
        String playerName = "a";
        int expectedBetAmount = 100;
        BettingTable bettingTable = new BettingTable(Collections.singletonList(
                new PlayerInput(playerName, expectedBetAmount)
        ));

        // when
        int betAmount = bettingTable.getBetAmount(playerName);

        // then
        assertThat(betAmount).isEqualTo(expectedBetAmount);
    }

    @Test
    @DisplayName("존재하지 않는 플레이어의 이름이 주어지면 예외를 던진다.")
    void throw_exception_on_no_such_player() {
        // given
        BettingTable bettingTable = new BettingTable(Arrays.asList(
                new PlayerInput("a", 100),
                new PlayerInput("b", 200),
                new PlayerInput("c", 300))
        );
        String playerName = "no_such_player";

        // when, then
        assertThrows(NoSuchPlayerException.class, () -> {
            bettingTable.getBetAmount(playerName);
        });
    }

    @Test
    @DisplayName("전체 플레이어의 배팅 금액 합을 구한다.")
    void total_bet_amount() {
        // given
        BettingTable bettingTable = new BettingTable(Arrays.asList(
                new PlayerInput("a", 100),
                new PlayerInput("b", 200),
                new PlayerInput("c", 300))
        );
        int expectedBetAmount = 100 + 200 + 300;

        // when
        int totalBetAmount = bettingTable.calculateTotalBetAmount();

        // then
        assertThat(totalBetAmount).isEqualTo(expectedBetAmount);
    }
}