package blackjack.service;

import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.profit.ParticipantsProfit;
import blackjack.domain.table.Table;
import blackjack.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackService {
    private final Table table;

    public BlackjackService(List<PlayerInput> playerInputs, DeckGenerator deckGenerator) {
        this.table = new Table(playerInputs, deckGenerator);
    }

    public ParticipantsDto getParticipants() {
        DealerDto dealerDto = generateDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();
        return new ParticipantsDto(dealerDto, playersDto);
    }

    private DealerDto generateDealerDto() {
        Dealer dealer = table.getDealer();
        return new DealerDto(dealer.getFaceUpCard());
    }

    private List<PlayerDto> generatePlayerDto() {
        return table.getPlayers().stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());
    }

    public boolean isDealerBlackjack() {
        return table.isDealerBlackjack();
    }

    public PlayerDto hit(Player player) {
        table.hit(player);
        return new PlayerDto(player);
    }

    public void dealDealer() {
        table.finalDeal();
    }

    public ParticipantsDto getFinalParticipants() {
        DealerDto finalDealerDto = generateFinalDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();
        return new ParticipantsDto(finalDealerDto, playersDto);
    }

    private FinalDealerDto generateFinalDealerDto() {
        Dealer dealer = table.getDealer();
        return new FinalDealerDto(dealer);
    }

    public ParticipantsProfit getProfitResults() {
        return table.calculateParticipantsProfit();
    }

    public List<Player> getPlayers() {
        return table.getPlayers();
    }
}
