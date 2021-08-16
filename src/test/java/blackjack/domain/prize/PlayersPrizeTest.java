package blackjack.domain.prize;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.NoSuchPlayerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static blackjack.domain.fixture.TestCards.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayersPrizeTest {
    @Test
    @DisplayName("플레이어 리스트와 딜러가 주어지면 플레이어들의 승패결과를 반환한다.")
    void generate() {
        // given
        List<Player> players = Arrays.asList(
                new Player("a", anyHoldCards()),
                new Player("b", blackjackCards())
        );
        Dealer dealer = new Dealer(winCards());

        // when
        PlayersPrize playersPrize = new PlayersPrize(players, dealer);

        // then
        assertThat(playersPrize.getPrize("a")).isEqualTo(Prize.LOSE);
        assertThat(playersPrize.getPrize("b")).isEqualTo(Prize.BLACKJACK);
    }

    @Test
    @DisplayName("승패결과의 key로 플레이어 이름을 반환한다.")
    void get_player_names() {
        // given
        List<Player> players = Arrays.asList(
                new Player("a", anyHoldCards()),
                new Player("b", blackjackCards())
        );
        Dealer dealer = new Dealer(winCards());
        PlayersPrize playersPrize = new PlayersPrize(players, dealer);

        // when
        Collection<String> playerNames = playersPrize.getPlayerNames();

        // then
        assertThat(playerNames).hasSize(2)
                .containsExactlyInAnyOrder("a", "b");
    }

    @Test
    @DisplayName("존재하지 않는 플레이어의 이름이 주어지면 예외를 던진다.")
    void throw_exception_on_no_such_player() {
        // given
        List<Player> players = Arrays.asList(
                new Player("a", anyHoldCards()),
                new Player("b", blackjackCards())
        );
        Dealer dealer = new Dealer(winCards());
        PlayersPrize playersPrize = new PlayersPrize(players, dealer);
        String playerName = "no_such_player";

        // when, then
        assertThrows(NoSuchPlayerException.class, () -> {
            playersPrize.getPrize(playerName);
        });
    }
}