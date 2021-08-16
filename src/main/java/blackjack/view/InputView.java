package blackjack.view;

import blackjack.dto.PlayerInput;
import blackjack.exception.InvalidInputException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Collection<String> VALID_HIT_OR_STAND_INPUTS = Arrays.asList("y", "n");

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<PlayerInput> getPlayersInput() {
        List<String> playerNames = getPlayerNames();

        return playerNames.stream()
                .map(playerName -> new PlayerInput(playerName, getBetAmountInput(playerName)))
                .collect(Collectors.toList());
    }

    private static List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateInputNullOrEmpty(input);

        List<String> playerNames = parsePlayerNames(input);
        validatePlayerNames(playerNames);
        return playerNames;
    }

    private static void validateInputNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw InvalidInputException.EMPTY_INPUT;
        }
    }

    private static List<String> parsePlayerNames(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw InvalidInputException.EMPTY_INPUT;
        }

        int givenCount = playerNames.size();
        int distinctCount = (int) playerNames.stream().distinct().count();
        if (givenCount != distinctCount) {
            throw InvalidInputException.DUPLICATE_PLAYER_NAME;
        }
    }

    private static int getBetAmountInput(String betAmountInput) {
        System.out.printf("%n%s의 배팅 금액은?%n", betAmountInput);
        String input = scanner.nextLine();
        validateInputNullOrEmpty(input);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw InvalidInputException.NON_INTEGER_BET_AMOUNT;
        }
    }

    public static boolean hit(String playerName) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String input = scanner.nextLine();
        validateInputNullOrEmpty(input);

        if (!VALID_HIT_OR_STAND_INPUTS.contains(input)) {
            throw InvalidInputException.INVALID_HIT_OR_STAND;
        }

        return input.equals("y");
    }
}
