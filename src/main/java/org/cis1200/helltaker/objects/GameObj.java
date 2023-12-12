package org.cis1200.helltaker.objects;

import org.cis1200.helltaker.enums.GameObjs;

public interface GameObj extends Cloneable {
    void nextTurn();

    boolean isSolid();

    GameObjs getEnum();
}
