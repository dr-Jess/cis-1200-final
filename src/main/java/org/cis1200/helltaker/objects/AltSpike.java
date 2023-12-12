package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;
import org.cis1200.helltaker.enums.Tiles;

public class AltSpike implements Tile {
    private boolean active;

    public AltSpike(boolean active) {
        this.active = active;
    }

    @Override
    public void nextTurn() {
        active = !active;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Action objectOnTile(Item i) {
        if (i instanceof Player) {
            if (active) {
                return Action.REMOVETURN;
            }
        } else if (i instanceof Enemy) {
            if (active) {
                return Action.BREAK;
            }
        }
        return Action.NONE;
    }

    public Tiles getEnum() {
        if (active) {
            return Tiles.ALT_SPIKE_ACTIVE;
        } else {
            return Tiles.ALT_SPIKE_INACTIVE;
        }
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
