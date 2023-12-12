package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.Action;

public interface Tile extends GameObj {
    /**
     * @param i The item that is on top of the file after @nextTurn() has been
     *          called
     * @return An enum representing the action to take on the item.
     */
    Action objectOnTile(Item i);
}
