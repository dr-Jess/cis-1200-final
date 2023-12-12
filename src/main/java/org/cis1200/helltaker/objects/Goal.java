package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;
import org.cis1200.helltaker.enums.Tiles;

public class Goal implements Tile {

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
            return Action.ENDGAME;
        }
        return Action.NONE;
    }

    public Tiles getEnum() {
        return Tiles.GOAL;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
