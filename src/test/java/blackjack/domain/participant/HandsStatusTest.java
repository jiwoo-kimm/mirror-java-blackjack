package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.fixture.TestCard.*;
import static blackjack.domain.participant.HandsStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandsStatusTest {

    @Test
    @DisplayName("블랙잭인 것을 확인한다.")
    void blackjack() {
        // given
        List<Card> blackjackHands = Arrays.asList(ACE_1, CARD_Q);
        Participant participant = new Dealer(blackjackHands);

        // when
        HandsStatus blackjack = HandsStatus.of(participant);

        // then
        assertThat(blackjack).isEqualTo(BLACKJACK);
    }

    @Test
    @DisplayName("버스트인 것을 확인한다.")
    void bust() {
        // given
        List<Card> bustHands = Arrays.asList(CARD_K, CARD_Q, CARD_J);
        Participant participant = new Dealer(bustHands);

        // when
        HandsStatus bust = HandsStatus.of(participant);

        // then
        assertThat(bust).isEqualTo(BUST);
    }

    @Test
    @DisplayName("홀드인 것을 확인한다.")
    void hold() {
        // given
        List<Card> holdHands = Arrays.asList(CARD_K, CARD_Q);
        Participant participant = new Dealer(holdHands);

        // when
        HandsStatus hold = HandsStatus.of(participant);

        // then
        assertThat(hold).isEqualTo(HOLD);
    }
}