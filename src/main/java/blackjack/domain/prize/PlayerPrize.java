package blackjack.domain.prize;

public class PlayerPrize {
    private final String playerName;
    private final Prize prize;

    public PlayerPrize(String playerName, Prize prize) {
        this.playerName = playerName;
        this.prize = prize;
    }

    public String getPrizeTitle() {
        return prize.getTitle();
    }

    public Prize getPrize() {
        return prize;
    }

    public String getPlayerName() {
        return playerName;
    }
}
