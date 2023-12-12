package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;
import org.cis1200.helltaker.enums.Tiles;

public class Wall implements Tile {
    @Override
    public void nextTurn() {

    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Action objectOnTile(Item i) {
        throw new UnsupportedOperationException();
    }

    public Tiles getEnum() {
        return Tiles.WALL;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
