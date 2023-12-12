package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Collision;

public interface Item extends GameObj {
    /**
     * @param p The player kicking the item.
     * @param t The tile behind the item.
     * @param i The item behind the item. Null if no item.
     * @return An enum representing the action to take on the item.
     * @throws IllegalArgumentException        if p or t are null
     * @throws UnsupportedOperationException() if kickable() is false.
     */
    public Collision collide(Player p, Tile t, Item i);
}
