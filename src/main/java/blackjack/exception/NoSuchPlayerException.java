package blackjack.exception;

import java.util.NoSuchElementException;

public class NoSuchPlayerException extends NoSuchElementException {
    private static final String NO_SUCH_PLAYER = "매칭되는 플레이어가 없습니다.";

    public NoSuchPlayerException(String playerName) {
        super(NO_SUCH_PLAYER + " playerName=" + playerName);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
