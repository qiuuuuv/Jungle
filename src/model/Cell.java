//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.io.Serializable;

public class Cell implements Serializable {
    private ChessPiece piece;

    public Cell() {
    }

    public ChessPiece getPiece() {
        return this.piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }

}

