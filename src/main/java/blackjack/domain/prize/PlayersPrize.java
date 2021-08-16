package blackjack.domain.prize;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.NoSuchPlayerException;

import java.util.*;

public class PlayersPrize {
    private final Map<String, Prize> prizes;

    public PlayersPrize(List<Player> players, Dealer dealer) {
        Map<String, Prize> map = new HashMap<>();
        players.forEach(player -> {
            Prize prize = Prize.of(player, dealer);
            map.put(player.getName(), prize);
        });

        this.prizes = Collections.unmodifiableMap(map);
    }

    public Collection<String> getPlayerNames() {
        return prizes.keySet();
    }

    public Prize getPrize(String playerName) {
        Prize prize = prizes.get(playerName);

        if (prize == null) {
            throw new NoSuchPlayerException(playerName);
        }

        return prize;
    }
}
