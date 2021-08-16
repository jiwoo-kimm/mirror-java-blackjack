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

class DealerTest {

    @Test
    @DisplayName("딜러 객체를 생성한다.")
    void create() {
        //given
        List<Card> cards = generateHitHands();

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.countHands()).isEqualTo(INITIAL_DEAL_COUNT);
    }

    @Test
    @DisplayName("오픈할 카드만 반환한다.")
    void get_face_up_card() {
        //given
        Card firstCard = CARD_3;
        List<Card> cards = Arrays.asList(firstCard, CARD_Q);

        // when
        Dealer dealer = new Dealer(cards);
        Card faceUpCard = dealer.getFaceUpCard();

        //then
        assertThat(faceUpCard.getRank()).isEqualTo(firstCard.getRank());
    }

    @Test
    @DisplayName("카드 합이 16 이하인 경우 true를 리턴한다.")
    void final_deal_on_hit() {
        //given
        List<Card> cards = generateHitHands();

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.hit()).isTrue();
    }

    @Test
    @DisplayName("카드 합이 16 초과인 경우 false를 리턴한다.")
    void final_deal_on_stand() {
        //given
        List<Card> cards = generateStandHands();

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.hit()).isFalse();
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void is_blackjack() {
        // given
        List<Card> blackjackCards = new ArrayList<>();
        blackjackCards.add(ACE_1);
        blackjackCards.add(CARD_K);
        Dealer dealer = new Dealer(blackjackCards);

        // when
        boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

    @Test
    @DisplayName("카드를 여러 장 받는다.")
    void take_card() {
        //given
        List<Card> initialCards = generateStandHands();
        List<Card> additionalCards = Arrays.asList(CARD_3, CARD_Q);
        int handSize = initialCards.size() + additionalCards.size();
        Dealer dealer = new Dealer(initialCards);

        //when
        dealer.take(additionalCards);

        // then
        assertThat(dealer.countHands()).isEqualTo(handSize);
    }

    private List<Card> generateHitHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_2);
        cards.add(CARD_Q);
        return cards;
    }

    private List<Card> generateStandHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_J);
        cards.add(CARD_K);
        return cards;
    }
}
