package blackjack.dto;

import blackjack.exception.InvalidInputException;

public class PlayerInput {
    public static final int MINIMUM_BET_AMOUNT = 1;

    private final String playerName;
    private final int betAmount;

    public PlayerInput(String playerName, int betAmount) {
        validateBetAmount(betAmount);

        this.playerName = playerName;
        this.betAmount = betAmount;
    }

    private void validateBetAmount(int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT) {
            throw InvalidInputException.NEGATIVE_BET_AMOUNT;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
