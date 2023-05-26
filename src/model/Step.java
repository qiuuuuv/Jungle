package model;

public class Step {
    public ChessboardPoint src;
    public ChessboardPoint dest;
    public PlayerColor color;
    public boolean result;


    public Step() {
    }

    public Step(ChessboardPoint src, ChessboardPoint dest, PlayerColor color, boolean result) {
        this.src = src;
        this.dest = dest;
        this.color = color;
        this.result=result;
    }


    public String toString() {
        return String.format("%-4s: ( %d , %d )->( %d , %d )\n", color.toString(), src.getRow() + 1, src.getCol() + 1, dest.getRow() + 1, dest.getCol() + 1);
    }
}
