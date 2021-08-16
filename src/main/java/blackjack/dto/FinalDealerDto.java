package blackjack.dto;

import blackjack.domain.participant.Dealer;

public class FinalDealerDto extends DealerDto {
    private final int rankSum;

    public FinalDealerDto(Dealer dealer) {
        super(dealer.getCards());
        this.rankSum = dealer.sumRank();
    }

    public int getRankSum() {
        return rankSum;
    }
}
