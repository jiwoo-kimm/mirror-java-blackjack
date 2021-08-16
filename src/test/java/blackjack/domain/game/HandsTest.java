package blackjack.domain.game;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandsTest {
    @Test
    @DisplayName("에이스 카드가 없을 때 전체 카드 합을 계산한다.")
    void sum_hands_without_ace_card() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_2);
        cards.add(CARD_K);

        //when
        Hands hands = new Hands(cards);
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나 있을 때 전체 카드 합을 계산한다.")
    void sum_hands_with_one_ace_card() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_2);
        cards.add(CARD_K);
        cards.add(ACE_1);

        //when
        Hands hands = new Hands(cards);
        int sum = hands.sumRanks();

        //then
        assertThat(sum).isEqualTo(13);
    }
}
