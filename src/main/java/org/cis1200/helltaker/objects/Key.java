package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;
import org.cis1200.helltaker.enums.Tiles;

public class Key implements Tile {
    private boolean collected = false;

    @Override
    public void nextTurn() {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Action objectOnTile(Item i) {
        if (i instanceof Player && !collected) {
            collected = true;
            return Action.ADDKEY;
        }
        return Action.NONE;
    }

    public Tiles getEnum() {
        return Tiles.KEY;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
