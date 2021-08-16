package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;
import static blackjack.domain.game.Table.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체를 생성한다.")
    void create() {
        //given
        String name = "pobi";
        List<Card> cards = generateCards();

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
        String name = "name";
        List<Card> initialCards = generateCards();
        List<Card> additionalCards = Arrays.asList(CARD_3, CARD_Q);
        int handSize = initialCards.size() + additionalCards.size();
        Player player = new Player(name, initialCards);

        //when
        player.take(additionalCards);

        // then
        assertThat(player.countHands()).isEqualTo(handSize);
    }

    @Test
    @DisplayName("카드를 추가로 받지 않았는지 확인한다.")
    void never_hit() {
        //given
        Player neverHitPlayer = new Player("name", generateCards());
        Player hitPlayer = new Player("name", generateCards());

        //when
        hitPlayer.take(generateCards());

        //then
        assertThat(neverHitPlayer.neverHit()).isTrue();
        assertThat(hitPlayer.neverHit()).isFalse();
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void is_blackjack() {
        // given
        List<Card> blackjackCards = new ArrayList<>();
        blackjackCards.add(ACE_1);
        blackjackCards.add(CARD_K);
        Player player = new Player("name", blackjackCards);

        // when
        boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_8);
        cards.add(CARD_9);
        return cards;
    }
}
