package blackjack.domain.fixture;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;

public class TestCard {

    public static final Card ACE_1 = new AceCard(Suit.CLUB);
    public static final Card ACE_2 = new AceCard(Suit.DIAMOND);
    public static final Card ACE_3 = new AceCard(Suit.HEART);
    public static final Card ACE_4 = new AceCard(Suit.SPADE);

    public static final Card CARD_2 = new Card(Suit.SPADE, 2);
    public static final Card CARD_3 = new Card(Suit.SPADE, 3);
    public static final Card CARD_4 = new Card(Suit.SPADE, 4);
    public static final Card CARD_5 = new Card(Suit.SPADE, 5);
    public static final Card CARD_6 = new Card(Suit.SPADE, 6);
    public static final Card CARD_7 = new Card(Suit.SPADE, 7);
    public static final Card CARD_8 = new Card(Suit.SPADE, 8);
    public static final Card CARD_9 = new Card(Suit.SPADE, 9);
    public static final Card CARD_K = new Card(Suit.SPADE, "K");
    public static final Card CARD_J = new Card(Suit.SPADE, "J");
    public static final Card CARD_Q = new Card(Suit.SPADE, "Q");
}
