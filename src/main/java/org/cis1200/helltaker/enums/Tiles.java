package org.cis1200.helltaker.enums;

public enum Tiles implements GameObjs {
    WALL("W", "wall"),
    FLOOR("f", "floor"),
    SPIKE("S", "spike"),
    ALT_SPIKE_ACTIVE("A", "spike"),
    ALT_SPIKE_INACTIVE("a", "alt_spike_inactive"),
    GOAL("g", "goal"),
    KEY("k", "key"),
    ;

    private final String symbol;
    private final String imageName;

    Tiles(String symbol, String imageName) {
        this.symbol = symbol;
        this.imageName = imageName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getImageName() {
        return imageName;
    }
}
