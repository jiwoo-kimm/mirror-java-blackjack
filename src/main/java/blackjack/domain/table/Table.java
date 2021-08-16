package blackjack.domain.table;

import blackjack.domain.card.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.profit.ParticipantsProfit;
import blackjack.dto.PlayerInput;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Table {
    public static final int INITIAL_DEAL_COUNT = 2;
    public static final int BLACKJACK_RANK = 21;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;
    private final BettingTable bettingTable;

    public Table(List<PlayerInput> playerInputs, DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        this.dealer = new Dealer(deck.drawCards(INITIAL_DEAL_COUNT));
        this.players = Collections.unmodifiableList(generatePlayers(playerInputs));
        this.bettingTable = new BettingTable(playerInputs);
    }

    private List<Player> generatePlayers(List<PlayerInput> playerInputs) {
        return playerInputs.stream()
                .map(playerInput -> new Player(playerInput.getPlayerName(), deck.drawCards(INITIAL_DEAL_COUNT)))
                .collect(Collectors.toList());
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }

    public void hit(Participant participant) {
        Card card = deck.drawCard();
        participant.take(card);
    }

    public void finalDeal() {
        while (dealer.needMoreCard()) {
            hit(dealer);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public ParticipantsProfit calculateParticipantsProfit() {
        PlayersPrize prizes = new PlayersPrize(players, dealer);
        return new ParticipantsProfit(bettingTable, prizes);
    }
}
