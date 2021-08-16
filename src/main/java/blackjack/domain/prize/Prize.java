package blackjack.domain.prize;

public enum Prize {
    WIN("승"),
    LOSE("패"),
    TIE("무");

    private final String title;

    Prize(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
