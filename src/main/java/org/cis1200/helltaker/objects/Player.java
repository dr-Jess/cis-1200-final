package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Collision;
import org.cis1200.helltaker.enums.Items;

public class Player implements Item {
    private int keys = 0;

    public Player(int keys) {
        this.keys = keys;
    }

    @Override
    public void nextTurn() {
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Collision collide(Player p, Tile t, Item i) {
        throw new UnsupportedOperationException();
    }

    public int getKeys() {
        return keys;
    }

    public void addKey() {
        keys++;
    }

    public void removeKey() {
        if (keys <= 0) {
            throw new IllegalStateException("Player has no keys");
        }
        keys--;
    }

    public Items getEnum() {
        return Items.PLAYER;
    }

    @Override
    public String toString() {
        return getEnum().getSymbol();
    }
}
