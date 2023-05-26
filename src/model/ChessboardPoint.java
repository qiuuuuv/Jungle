//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public class ChessboardPoint {
    private final int row;
    private final int col;

    public ChessboardPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int hashCode() {
        return this.row + this.col;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            ChessboardPoint temp = (ChessboardPoint) obj;
            return temp.getRow() == this.row && temp.getCol() == this.col;
        }
    }

    public String toString() {
        return "(" + this.row + "," + this.col + ") on the chessboard is clicked!";
    }
}
