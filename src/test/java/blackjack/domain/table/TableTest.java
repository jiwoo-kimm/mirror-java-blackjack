package blackjack.domain.table;

import blackjack.domain.deck.TestDeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.table.Table.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class TableTest {

    @Test
    @DisplayName("딜러와 플레이어에게 초기 카드를 배분한다.")
    void create_table() {
        //given
        List<PlayerInput> playerInputs = Arrays.asList(
                new PlayerInput("name1", 10),
                new PlayerInput("name2", 20)
        );
        Table table = new Table(playerInputs, new TestDeckGenerator());

        // when
        List<Player> players = table.getPlayers();
        Dealer dealer = table.getDealer();

        // then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT);
        players.forEach(player ->
                assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT));
    }

    @Test
    @DisplayName("플레이어에게 추가 카드를 한 장 배분한다.")
    void hit_player() {
        //given
        List<PlayerInput> playerInputs = Collections.singletonList(
                new PlayerInput("name", 100)
        );
        Table table = new Table(playerInputs, new TestDeckGenerator());
        Player player = table.getPlayers().get(0);

        //when
        table.hit(player);

        //then
        assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT + 1);
    }

    @Test
    @DisplayName("딜러에게 추가 카드를 한 장 배분한다.")
    void hit_dealer() {
        //given
        List<PlayerInput> playerInputs = Collections.singletonList(
                new PlayerInput("name", 100)
        );
        Table table = new Table(playerInputs, new TestDeckGenerator());
        Dealer dealer = table.getDealer();

        //when
        table.hit(dealer);

        //then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT + 1);
    }

    @Test
    @DisplayName("딜러 카드 합이 17 이상이 될 때까지 추가 카드를 배분한다.")
    void final_deal() {
        //given
        List<PlayerInput> playerInputs = Collections.singletonList(
                new PlayerInput("name", 100)
        );
        Table table = new Table(playerInputs, new TestDeckGenerator());

        //when
        table.finalDeal();
        Dealer dealer = table.getDealer();

        //then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT + 2);
    }

    @Test
    @DisplayName("딜러가 블랙잭인지 확인한다.")
    void is_dealer_blackjack() {
        //given
        List<PlayerInput> playerInputs = Collections.emptyList();
        TestDeckGenerator deckGenerator = new TestDeckGenerator();  // ACE,2,K
        Table table = new Table(playerInputs, deckGenerator);

        // when
        boolean isBlackjack = table.isDealerBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }
}
