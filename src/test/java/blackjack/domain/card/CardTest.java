package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static blackjack.domain.card.Card.MAJOR_CARD_RANK;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("중복되지 않는 숫자 카드를 생성한다.")
    void generate_number_card(int rank) {
        //given, when
        Card card = new Card(Suit.DIAMOND, rank);

        //then
        assertThat(card.getRank()).isEqualTo(rank);
    }

    @ParameterizedTest
    @ValueSource(strings = {"K", "Q", "J"})
    @DisplayName("10을 의미하는 K, Q, J 카드를 생성한다.")
    void generate_major_card(String signature) {
        //given, when
        Card card = new Card(Suit.DIAMOND, signature);

        //then
        assertThat(card.getRank()).isEqualTo(MAJOR_CARD_RANK);
        assertThat(card.majorCard()).isTrue();
    }

    @Test
    @DisplayName("1 또는 11을 의미하는 A 카드를 생성한다.")
    void generate_ace_card() {
        //given, when
        Card card = new AceCard(Suit.DIAMOND);

        //then
        assertThat(card.getRank()).isEqualTo(AceCard.ACE_CARD_DEFAULT_RANK);
        assertThat(card).isInstanceOf(AceCard.class);
    }
}
