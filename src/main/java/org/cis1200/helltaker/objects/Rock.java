package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Collision;
import org.cis1200.helltaker.enums.Items;

public class Rock implements Item {
    @Override
    public void nextTurn() {

    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Collision collide(Player p, Tile t, Item i) {
        if (((i == null) || (!i.isSolid())) && !t.isSolid()) {
            return Collision.SLIDE;
        }
        return Collision.STOP;
    }

    public Items getEnum() {
        return Items.ROCK;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
