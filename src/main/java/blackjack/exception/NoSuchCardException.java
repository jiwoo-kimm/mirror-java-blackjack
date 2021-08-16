package blackjack.exception;

import java.util.NoSuchElementException;

public class NoSuchCardException extends NoSuchElementException {
    private static final String NO_SUCH_CARD = "매칭되는 카드가 없습니다.";

    public NoSuchCardException(String key) {
        super(NO_SUCH_CARD + " key=" + key);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
