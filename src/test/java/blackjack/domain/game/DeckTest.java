package blackjack.domain.game;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static blackjack.domain.game.Deck.TOTAL_CARD_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeckTest {

    @Test
    @DisplayName("총 52장의 카드를 갖는다.")
    void generator() {
        //given
        DeckGenerator deckGenerator = new RandomDeckGenerator();
        Deck deck = new Deck(deckGenerator);

        //when
        int size = deck.size();

        //then
        assertThat(size).isEqualTo(TOTAL_CARD_COUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 51, 52})
    @DisplayName("카드를 주어진 개수만큼 반환한다.")
    void draw_cards(int cardCount) {
        //given
        DeckGenerator deckGenerator = new RandomDeckGenerator();
        Deck deck = new Deck(deckGenerator);

        //when
        List<Card> cards = deck.drawCards(cardCount);

        //then
        assertThat(cards.size()).isEqualTo(cardCount);
    }

    @Test
    @DisplayName("남은 카드가 없을 때 예외를 던진다.")
    void draw_cards_out_of_index_exception() {
        //given
        DeckGenerator deckGenerator = new RandomDeckGenerator();
        Deck deck = new Deck(deckGenerator);
        int cardCount = TOTAL_CARD_COUNT + 1;

        //when, then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            deck.drawCards(cardCount);
        });
    }
}
