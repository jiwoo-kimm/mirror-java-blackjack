package blackjack.service;

import blackjack.domain.game.DeckGenerator;
import blackjack.domain.game.RandomDeckGenerator;
import blackjack.domain.game.Table;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.prize.PrizeResults;
import blackjack.dto.DealerDto;
import blackjack.dto.FinalDealerDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class BlackjackService {
    private final Table table;

    public BlackjackService(List<String> playerNames, DeckGenerator deckGenerator) {
        this.table = new Table(playerNames, deckGenerator);
    }

    public ParticipantsDto getParticipants() {
        DealerDto dealerDto = generateDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();
        return new ParticipantsDto(dealerDto, playersDto);
    }

    public ParticipantsDto getFinalParticipants() {
        DealerDto finalDealerDto = generateFinalDealerDto();
        List<PlayerDto> playersDto = generatePlayerDto();
        return new ParticipantsDto(finalDealerDto, playersDto);
    }

    private DealerDto generateDealerDto() {
        Dealer dealer = table.getDealer();
        return new DealerDto(dealer.getFaceUpCard());
    }

    private FinalDealerDto generateFinalDealerDto() {
        Dealer dealer = table.getDealer();
        return new FinalDealerDto(dealer);
    }

    private List<PlayerDto> generatePlayerDto() {
        List<PlayerDto> playersDto = new ArrayList<>();
        table.getPlayers().forEach(player -> playersDto.add(new PlayerDto(player)));
        return playersDto;
    }

    public PlayerDto hit(Player player) {
        table.hit(player);
        return new PlayerDto(player);
    }

    public void dealDealer() {
        table.finalDeal();
    }

    public PrizeResults calculatePrizeResults() {
        return new PrizeResults(table);
    }

    public List<Player> getPlayers() {
        return table.getPlayers();
    }
}
