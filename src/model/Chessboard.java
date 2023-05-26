//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import javax.swing.*;
import java.util.ArrayList;

public class Chessboard {
    private final Cell[][] grid;
    public ChessboardPoint[][] point;
    public ArrayList<Step> steps = new ArrayList<>();

    public int getBlueDeadSize() {
        return blueDead.size();
    }

    public int getRedDeadSize() {
        return redDead.size();
    }

    public ArrayList<ChessPiece> blueDead = new ArrayList<>();
    public ArrayList<ChessPiece> redDead = new ArrayList<>();


    public Chessboard() {
        this.grid = new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];
        this.point = new ChessboardPoint[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];

        this.initGrid();
        this.initPieces();

    }

    public void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                this.grid[i][j] = new Cell();
            }
        }
    }

    public void initPieces() {
        this.grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant", 8));
        this.grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant", 8));
        this.grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion", 7));
        this.grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion", 7));
        this.grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6));
        this.grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6));
        this.grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5));
        this.grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5));
        this.grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4));
        this.grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4));
        this.grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3));
        this.grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3));
        this.grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2));
        this.grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2));
        this.grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Rat", 1));
        this.grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat", 1));
    }

    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return this.getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return this.grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    public void removeChess(ChessboardPoint point) {
        this.getGridAt(point).removePiece();
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = this.getChessPieceAt(point);
        this.getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        this.getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!this.isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        } else {
            this.setChessPiece(dest, this.removeChessPiece(src));
            Step step = new Step(src, dest, grid[dest.getRow()][dest.getCol()].getPiece().getOwner(), false);
            this.steps.add(step);

        }
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!this.isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        } else {
            steps.add(new Step(src, dest, this.getChessPieceOwner(src), true));
            removeChessPiece(dest);
            this.setChessPiece(dest, this.removeChessPiece(src));
        }
    }



    public Cell[][] getGrid() {
        return this.grid;
    }

    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return this.getGridAt(point).getPiece().getOwner();
    }

    public boolean isRiver(ChessboardPoint point) {
        return point.getRow() >= 3 && point.getRow() <= 5 &&
                (point.getCol() == 1 || point.getCol() == 2 || point.getCol() == 4 || point.getCol() == 5);
    }

    public boolean isOwnDen(ChessboardPoint src, ChessboardPoint dest) {
        if (this.getChessPieceOwner(src).equals(PlayerColor.BLUE) && (dest.getRow() == 8 && dest.getCol() == 3)) {
            return true;
        }
        return this.getChessPieceOwner(src).equals(PlayerColor.RED) && (dest.getRow() == 0 && dest.getCol() == 3);
    }

    // if a rat is in the river, while a tiger tries to jump over it, it's illegal
    public boolean ifJump(ChessboardPoint src, ChessboardPoint dest) {
        boolean m = false;
        ChessboardPoint a = new ChessboardPoint(src.getRow(), src.getCol() + 1);
        ChessboardPoint b = new ChessboardPoint(src.getRow(), src.getCol() + 2);
        ChessboardPoint c = new ChessboardPoint(src.getRow(), src.getCol() - 2);
        ChessboardPoint d = new ChessboardPoint(src.getRow(), src.getCol() - 1);
        ChessboardPoint e = new ChessboardPoint(src.getRow() + 1, src.getCol());
        ChessboardPoint f = new ChessboardPoint(src.getRow() + 2, src.getCol());
        ChessboardPoint g = new ChessboardPoint(src.getRow() + 3, src.getCol());
        ChessboardPoint h = new ChessboardPoint(src.getRow() - 1, src.getCol());
        ChessboardPoint i = new ChessboardPoint(src.getRow() - 2, src.getCol());
        ChessboardPoint j = new ChessboardPoint(src.getRow() - 3, src.getCol());
        if (src.getRow() >= 3 && src.getRow() <= 5 && (src.getCol() == 0 || src.getCol() == 3 || src.getCol() == 6) && ((dest.getCol() == src.getCol() + 3 && this.getChessPieceAt(a) == null && this.getChessPieceAt(b) == null) || (dest.getCol() == src.getCol() - 3 && this.getChessPieceAt(c) == null && this.getChessPieceAt(d) == null)) && dest.getRow() == src.getRow()) {
            m = true;
        }
        if ((src.getRow() == 2 || src.getRow() == 6) && (src.getCol() == 1 || src.getCol() == 2 || src.getCol() == 4 || src.getCol() == 5) && ((dest.getRow() == src.getRow() + 4 && this.getChessPieceAt(e) == null && this.getChessPieceAt(f) == null && this.getChessPieceAt(g) == null) || (dest.getRow() == src.getRow() - 4 && this.getChessPieceAt(h) == null && this.getChessPieceAt(i) == null && this.getChessPieceAt(j) == null)) && src.getCol() == dest.getCol()) {
            m = true;
        }
        return m;
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        boolean boo = true;
        if (isOwnDen(src, dest)) {
            return false;
        }
        if (this.getChessPieceAt(src) != null) {
            if (this.getChessPieceAt(dest) != null) {
                return false;
            } else {
                boolean bo1 = calculateDistance(src, dest) == 1;
                boolean bo2 = !isRiver(dest);
                switch (this.getChessPieceAt(src).getName()) {
                    case "Elephant", "Cat", "Dog", "Wolf", "Leopard" -> boo = bo1 && bo2;
                    case "Lion", "Tiger" -> boo = (bo1 && bo2) || ifJump(src, dest);
                    case "Rat" -> boo = bo1;
                }
            }
        } else {
            boo = false;
        }
        return boo;
    }

    public boolean isValidMoveForCapture(ChessboardPoint src, ChessboardPoint dest) {
        boolean boo = true;
        if (isOwnDen(src, dest)) {
            return false;
        }
        if (this.getChessPieceAt(src) != null) {
            boolean bo1 = calculateDistance(src, dest) == 1;
            boolean bo2 = !isRiver(dest);
            switch (this.getChessPieceAt(src).getName()) {
                case "Elephant", "Leopard", "Wolf", "Dog", "Cat" -> boo = bo1 && bo2;
                case "Lion", "Tiger" -> boo = (bo1 && bo2) || ifJump(src, dest);
                case "Rat" -> boo = bo1;
            }
        } else {
            boo = false;
        }
        return boo;
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        if (this.getChessPieceAt(dest) != null && this.getChessPieceAt(src) != null && this.isValidMoveForCapture(src, dest)&&this.getChessPieceAt(src).getOwner()!=this.getChessPieceAt(dest).getOwner()) {
            boolean boo = true;
            ChessPiece eater = this.grid[src.getRow()][src.getCol()].getPiece();
            ChessPiece dead = this.grid[dest.getRow()][dest.getCol()].getPiece();
                if (this.isRiver(src)) {
                    boo = false;
                }
                if (eater.getName().equals("Elephant") && dead.getName().equals("Rat")&&!this.isOpponentTrap(dest,dead.getOwner())) {
                    boo = false;
                }
            if(eater.getRank()< dead.getRank()&&!(eater.getName().equals("Rat")&&dead.getName().equals("Elephant"))&&!this.isOpponentTrap(dest,dead.getOwner())){return false;}
            return boo;
        }
        return false;
    }

    private boolean isOpponentTrap(ChessboardPoint po, PlayerColor color) {
        boolean boo;
        if (color == PlayerColor.BLUE) {
            boo = (po.getRow() == 1 && po.getCol() == 3)
                    || (po.getRow() == 0 && po.getCol() == 2)
                    || (po.getRow() == 0 && po.getCol() == 4);
        } else {
            boo = (po.getRow() == 7 && po.getCol() == 3)
                    || (po.getRow() == 8 && po.getCol() == 2)
                    || (po.getRow() == 8 && po.getCol() == 4);
        }
        return boo;
    }
}
