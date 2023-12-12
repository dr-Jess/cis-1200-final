package org.cis1200.helltaker.enums;

public enum Items implements GameObjs {
    PLAYER("P", "player"),
    ROCK("R", "rock"),
    ENEMY("E", "enemy"),
    LOCK("L", "lock"),
    ;

    private final String symbol;
    private final String imageName;

    Items(String symbol, String imageName) {
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
