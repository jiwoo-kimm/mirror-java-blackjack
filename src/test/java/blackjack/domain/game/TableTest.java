package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.fixture.TestDeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;
import static blackjack.domain.game.Table.INITIAL_DEAL_COUNT;
import static blackjack.domain.fixture.TestDeckGenerator.TEST_DECK_INITIAL_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

class TableTest {

    @Test
    @DisplayName("딜러와 플레이어에게 초기 카드 배분한다.")
    void create_table() {
        // given
        Table table = generateTestTable();
        int participantCount = 3;

        // when
        List<Player> players = table.getPlayers();
        Dealer dealer = table.getDealer();

        // then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT);
        assertThat(dealer.getCards().get(0)).isEqualTo(ACE_1);
        assertThat(dealer.getCards().get(1)).isEqualTo(CARD_2);

        players.forEach(player ->
                assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT));
        assertThat(table.getDeckSize())
                .isEqualTo(TEST_DECK_INITIAL_SIZE - INITIAL_DEAL_COUNT * participantCount);
    }

    @Test
    @DisplayName("플레이어에게 추가 카드를 한 장 배분한다.")
    void hit_player() {
        //given
        Table table = generateTestTable();
        Player player = table.getPlayers().get(0);

        //when
        table.hit(player);
        List<Card> cards = player.getCards();

        //then
        assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT + 1);
        assertThat(cards.get(0)).isEqualTo(CARD_K);
        assertThat(cards.get(1)).isEqualTo(CARD_3);
        assertThat(cards.get(2)).isEqualTo(CARD_J);
    }

    @Test
    @DisplayName("딜러에게 추가 카드를 한 장 배분한다.")
    void hit_dealer() {
        //given
        Table table = generateTestTable();
        Dealer dealer = table.getDealer();

        //when
        table.hit(dealer);
        List<Card> cards = dealer.getCards();

        //then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT + 1);
        assertThat(cards.get(2)).isEqualTo(CARD_J);
    }

    @Test
    @DisplayName("딜러 카드 합이 17 이상이 될 때까지 추가 카드를 배분한다.")
    void final_deal() {
        //given
        Table table = generateTestTable();

        //when
        table.finalDeal();
        Dealer dealer = table.getDealer();

        //then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT + 2);
        assertThat(dealer.getCards().get(2)).isEqualTo(CARD_J);
        assertThat(dealer.getCards().get(3)).isEqualTo(CARD_5);
    }

    private Table generateTestTable() {
        List<String> playerNames = generatePlayerNames();
        DeckGenerator deckGenerator = new TestDeckGenerator();
        return new Table(playerNames, deckGenerator);
    }

    private List<String> generatePlayerNames() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("pobi");
        playerNames.add("jason");
        return playerNames;
    }
}
