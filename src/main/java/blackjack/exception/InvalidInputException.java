package blackjack.exception;

import static blackjack.dto.PlayerInput.MINIMUM_BET_AMOUNT;

public class InvalidInputException extends RuntimeException {

    private static final String EMPTY_INPUT_MESSAGE = "입력값이 없습니다. 다시 확인해 주세요.";
    private static final String INVALID_BET_AMOUNT_MESSAGE = "베팅 금액은 " + MINIMUM_BET_AMOUNT + " 이상의 정수로 입력해 주세요.";
    private static final String DUPLICATE_PLAYER_NAME_MESSAGE = "참여자 이름은 중복될 수 없습니다.";
    private static final String INVALID_HIT_OR_STAND_MESSAGE = "예는 y, 아니오는 n 을 입력해 주세요.";

    public static final InvalidInputException EMPTY_INPUT = new InvalidInputException(EMPTY_INPUT_MESSAGE);
    public static final InvalidInputException NON_INTEGER_BET_AMOUNT = new InvalidInputException(INVALID_BET_AMOUNT_MESSAGE);
    public static final InvalidInputException NEGATIVE_BET_AMOUNT = new InvalidInputException(INVALID_BET_AMOUNT_MESSAGE);
    public static final InvalidInputException DUPLICATE_PLAYER_NAME = new InvalidInputException(DUPLICATE_PLAYER_NAME_MESSAGE);
    public static final InvalidInputException INVALID_HIT_OR_STAND = new InvalidInputException(INVALID_HIT_OR_STAND_MESSAGE);

    private InvalidInputException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}