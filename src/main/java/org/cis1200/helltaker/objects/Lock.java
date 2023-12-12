package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Collision;
import org.cis1200.helltaker.enums.Items;

public class Lock implements Item {
    @Override
    public void nextTurn() {

    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Collision collide(Player p, Tile t, Item i) {
        if (p.getKeys() > 0) {
            p.removeKey();
            return Collision.UNLOCK;
        }
        return Collision.STOP;
    }

    public Items getEnum() {
        return Items.LOCK;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
