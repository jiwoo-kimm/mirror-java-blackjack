package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.prize.DealerPrize;
import blackjack.domain.prize.PlayersPrize;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.DealerDto;
import blackjack.dto.FinalDealerDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final int MAX_DEALER_CARDS_COUNT = 3;
    private static final String PARTICIPANTS_HANDS = "%s 카드: %s";
    private static final String PARTICIPANTS_FINAL_HANDS = "%n%s - 결과: %d";

    private OutputView() {
    }

    public static void printInitialDeal(ParticipantsDto participants) {
        printDealMessage(participants);

        System.out.println(generateDealerHandsMessage(participants.getDealerDto()));
        participants.getPlayers()
                .forEach(player -> System.out.println(generatePlayerHandsMessage(player)));
    }

    private static void printDealMessage(ParticipantsDto participants) {
        List<String> names = participants.getPlayers().stream()
                .map(PlayerDto::getName)
                .collect(Collectors.toList());
        System.out.printf("%n딜러와 %s에게 2장의 카드를 나누었습니다.%n", String.join(", ", names));
    }

    private static String generateDealerHandsMessage(DealerDto dto) {
        return String.format(PARTICIPANTS_HANDS, dto.getName(), printCards(dto.getCards()));
    }

    private static String generatePlayerHandsMessage(PlayerDto dto) {
        return String.format(PARTICIPANTS_HANDS, dto.getName(), printCards(dto.getCards()));
    }

    public static void printPlayerHandsMessage(PlayerDto dto) {
        System.out.print(generatePlayerHandsMessage(dto));
    }

    private static String printCards(List<Card> cards) {
        List<String> cardSignatures = cards.stream()
                .map(card -> card.getSignature() + card.getSuit().getName())
                .collect(Collectors.toList());
        return String.join(", ", cardSignatures);
    }

    public static void printFinalHands(ParticipantsDto participants) {
        System.out.println();
        printFinalDealMessage(participants.getDealerDto());

        printFinalDealerHands((FinalDealerDto) participants.getDealerDto());
        printFinalPlayersHands(participants.getPlayers());
        System.out.println();
    }

    private static void printFinalDealMessage(DealerDto dealer) {
        if (dealer.getCards().size() == MAX_DEALER_CARDS_COUNT) {
            System.out.println("딜러는 16 이하라 한 장의 카드를 더 받았습니다.");
        }
    }

    private static void printFinalDealerHands(FinalDealerDto finalDealerDto) {
        System.out.printf(PARTICIPANTS_FINAL_HANDS,
                generateDealerHandsMessage(finalDealerDto),
                finalDealerDto.getRankSum());
    }

    private static void printFinalPlayersHands(List<PlayerDto> players) {
        players.forEach(player ->
                System.out.printf(PARTICIPANTS_FINAL_HANDS,
                        generatePlayerHandsMessage(player),
                        player.getRankSum()));
    }

    public static void printPrizeResults(PrizeResults prizeResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerPrize(prizeResults.getDealerPrize());
        printPlayersPrize(prizeResults.getPlayersPrize());
    }

    private static void printDealerPrize(DealerPrize dealerPrize) {
        System.out.printf("딜러: %d승 %d패 %d무%n", dealerPrize.getWinCount(), dealerPrize.getLoseCount(), dealerPrize.getTieCount());
    }

    private static void printPlayersPrize(PlayersPrize playersPrize) {
        playersPrize.getPlayerPrizes().forEach(playerPrize ->
                System.out.printf("%s: %s%n", playerPrize.getPlayerName(), playerPrize.getPrizeTitle()));
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}