package blackjack.dto;

import blackjack.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerInputTest {

    @Test
    @DisplayName("베팅 금액이 1 미만의 숫자일 때 예외를 던진다.")
    void invalid_bet_amount() {
        // given
        int betAmount = 0;
        String name = "name";

        // when, then
        assertThrows(InvalidInputException.class, () -> {
            new PlayerInput(name, betAmount);
        });
    }

    @Test
    @DisplayName("베팅 금액이 1 이상의 숫자일 때 객체를 생성한다.")
    void generate() {
        // given
        int betAmount = 1;
        String name = "name";

        // when
        PlayerInput playerInput = new PlayerInput(name, betAmount);

        // then
        assertThat(playerInput.getPlayerName()).isEqualTo(name);
        assertThat(playerInput.getBetAmount()).isEqualTo(betAmount);

    }
}
