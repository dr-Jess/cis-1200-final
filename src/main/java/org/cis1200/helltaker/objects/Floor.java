package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;
import org.cis1200.helltaker.enums.Tiles;

public class Floor implements Tile {

    @Override
    public void nextTurn() {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Action objectOnTile(Item i) {
        return Action.NONE;
    }

    public Tiles getEnum() {
        return Tiles.FLOOR;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
