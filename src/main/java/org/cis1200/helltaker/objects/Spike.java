package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;
import org.cis1200.helltaker.enums.Tiles;

public class Spike implements Tile {

    @Override
    public void nextTurn() {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Action objectOnTile(Item i) {
        if (i instanceof Player) {
            return Action.REMOVETURN;
        } else if (i instanceof Enemy) {
            return Action.BREAK;
        }
        return Action.NONE;
    }

    public Tiles getEnum() {
        return Tiles.SPIKE;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
