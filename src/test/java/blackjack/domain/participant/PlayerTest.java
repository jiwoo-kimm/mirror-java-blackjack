package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;
import static blackjack.domain.table.Table.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체를 생성한다.")
    void create_() {
        //given
        String name = "name";
        List<Card> cards = Arrays.asList(CARD_10, CARD_K);

        //when
        Player player = new Player(name, cards);

        //then
        assertThat(player.getName()).isEqualTo(name);
        assertThat(player.countHands()).isEqualTo(INITIAL_DEAL_COUNT);
    }

    @Test
    @DisplayName("카드를 여러 장 받는다.")
    void take_card() {
        //given
        List<Card> initialCards = Arrays.asList(CARD_2, CARD_5);
        Player player = new Player("name", initialCards);
        List<Card> additionalCards = Arrays.asList(CARD_3, CARD_Q);
        int handSize = initialCards.size() + additionalCards.size();

        //when
        player.take(additionalCards);

        // then
        assertThat(player.countHands()).isEqualTo(handSize);
    }

    @Test
    @DisplayName("카드를 추가로 받지 않았는지 확인한다.")
    void never_hit() {
        //given
        Player neverHitPlayer = new Player("name", Arrays.asList(CARD_8, CARD_K));
        Player hitPlayer = new Player("name", Arrays.asList(CARD_3, CARD_J));

        //when
        hitPlayer.take(CARD_9);

        //then
        assertThat(neverHitPlayer.neverHit()).isTrue();
        assertThat(hitPlayer.neverHit()).isFalse();
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void is_blackjack() {
        // given
        List<Card> blackjackCards = Arrays.asList(CARD_K, ACE_1);
        Player player = new Player("name", blackjackCards);

        // when
        boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }
}
