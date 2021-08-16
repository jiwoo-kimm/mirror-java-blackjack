package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final List<String> VALID_HIT_OR_STAND_INPUTS = Arrays.asList("y", "n");
    private static final String INVALID_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 다시 확인해 주십시오.";
    private static final String INVALID_HIT_OR_STAND_MESSAGE = "예는 y, 아니오는 n 을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> getPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = getUserInput();
        validatePlayersNullOrEmpty(input);

        List<String> players = parsePlayerNames(input);
        validatePlayersSize(players);

        return players;
    }

    private static List<String> parsePlayerNames(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void validatePlayersSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYERS_NAME_MESSAGE);
        }
    }

    private static void validatePlayersNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYERS_NAME_MESSAGE);
        }
    }

    public static boolean hit(String playerName) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String input = getUserInput();
        validateHit(input);

        return input.equals("y");
    }

    private static void validateHit(String input) {
        if (!VALID_HIT_OR_STAND_INPUTS.contains(input)) {
            throw new IllegalArgumentException(INVALID_HIT_OR_STAND_MESSAGE);
        }
    }

    private static String getUserInput() {
        return scanner.nextLine();
    }
}
