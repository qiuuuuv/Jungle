//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public class ChessPiece {
    private PlayerColor owner;
    private String name;
    private int rank;

    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
    }

    public boolean canCapture(ChessPiece target) {
        // todo list
        return false;
    }

    public String getName() {
        return this.name;
    }

    public PlayerColor getOwner() {
        return this.owner;
    }

    public int getRank() {
        return rank;
    }
}
