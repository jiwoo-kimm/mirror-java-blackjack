package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<Card> cards;
    private final int rankSum;

    public PlayerDto(Player player) {
        this.name = player.getName();
        this.cards = Collections.unmodifiableList(new ArrayList<>(player.getCards()));
        this.rankSum = player.sumRank();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getRankSum() {
        return rankSum;
    }
}
