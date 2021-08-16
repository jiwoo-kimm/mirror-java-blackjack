package blackjack.domain.prize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersPrize {
    private final List<PlayerPrize> playerPrizes;

    public PlayersPrize(List<PlayerPrize> playerPrizes) {
        this.playerPrizes = Collections.unmodifiableList(new ArrayList<>(playerPrizes));
    }

    public List<PlayerPrize> getPlayerPrizes() {
        return playerPrizes;
    }
}
