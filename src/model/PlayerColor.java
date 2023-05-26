//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.awt.Color;

public enum PlayerColor {
    BLUE(Color.BLUE),
    RED(Color.RED);

    private final Color color;

    PlayerColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
