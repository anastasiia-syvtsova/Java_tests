package de.uniba.wiai.dsg.ajp.assignment3;

public enum MovieQuality {
    HD("HD"),
    FOUR_K("4K");
    private final String quality;

    MovieQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return quality;
    }
}
