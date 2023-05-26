//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import controller.GameController;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

import model.*;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

public class ChessboardComponent extends JComponent {
    public final CellComponent[][] gridComponents;
    private final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell;
    private final Set<ChessboardPoint> TrapCell;
    private final Set<ChessboardPoint> DenCell;
    public GameController gameController;
    public Chessboard chessboard;
    public JLabel statusLabel;
    public JLabel timeLabel;

    public int getCHESS_SIZE() {
        return CHESS_SIZE;
    }

    public Set<ChessboardPoint> getRiverCell() {
        return riverCell;
    }

    public Set<ChessboardPoint> getTrapCell() {
        return TrapCell;
    }

    public Set<ChessboardPoint> getDenCell() {
        return DenCell;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public CellComponent[][] getGridComponents() {
        return gridComponents;
    }

    public ChessboardComponent(JLabel statusLabel, JLabel timeLabel, Chessboard chessboard, int chessSize) {
        this.chessboard = chessboard;
        this.statusLabel = statusLabel;
        this.timeLabel = timeLabel;
        this.gridComponents = new CellComponent[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];
        this.riverCell = new HashSet<>();
        this.TrapCell = new HashSet<>();
        this.DenCell = new HashSet<>();
        this.CHESS_SIZE = chessSize;
        int width = this.CHESS_SIZE * 7;
        int height = this.CHESS_SIZE * 9;
        this.enableEvents(16L);
        this.setLayout((LayoutManager) null);
        this.setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, this.CHESS_SIZE);
        this.initiateGridComponents();
    }

    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Elephant")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new ElephantChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Lion")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new LionChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Tiger")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new TigerChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Leopard")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new LeopardChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Wolf")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new WolfChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Dog")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new DogChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Cat")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new CatChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Rat")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new RatChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("River")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new RiverChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Trap")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new TrapChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getName().equals("Den")) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    this.gridComponents[i][j].add(new DenChessComponent(chessPiece.getOwner(), this.CHESS_SIZE));
                }
            }
        }
    }

    public void initiateGridComponents() {
        this.riverCell.add(new ChessboardPoint(3, 1));
        this.riverCell.add(new ChessboardPoint(3, 2));
        this.riverCell.add(new ChessboardPoint(4, 1));
        this.riverCell.add(new ChessboardPoint(4, 2));
        this.riverCell.add(new ChessboardPoint(5, 1));
        this.riverCell.add(new ChessboardPoint(5, 2));
        this.riverCell.add(new ChessboardPoint(3, 4));
        this.riverCell.add(new ChessboardPoint(3, 5));
        this.riverCell.add(new ChessboardPoint(4, 4));
        this.riverCell.add(new ChessboardPoint(4, 5));
        this.riverCell.add(new ChessboardPoint(5, 4));
        this.riverCell.add(new ChessboardPoint(5, 5));

        this.TrapCell.add(new ChessboardPoint(0, 2));
        this.TrapCell.add(new ChessboardPoint(1, 3));
        this.TrapCell.add(new ChessboardPoint(0, 4));
        this.TrapCell.add(new ChessboardPoint(8, 2));
        this.TrapCell.add(new ChessboardPoint(7, 3));
        this.TrapCell.add(new ChessboardPoint(8, 4));

        this.DenCell.add(new ChessboardPoint(0, 3));
        this.DenCell.add(new ChessboardPoint(8, 3));


        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); ++i) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); ++j) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (this.riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, this.calculatePoint(i, j), this.CHESS_SIZE);
                    this.add(cell);
                } else if (this.TrapCell.contains(temp)) {
                    cell = new CellComponent(Color.ORANGE, this.calculatePoint(i, j), this.CHESS_SIZE);
                    this.add(cell);
                } else if (this.DenCell.contains(temp)) {
                    cell = new CellComponent(Color.YELLOW, this.calculatePoint(i, j), this.CHESS_SIZE);
                    this.add(cell);
                } else {
                    cell = new CellComponent(new Color(96, 194, 73), this.calculatePoint(i, j), this.CHESS_SIZE);
                    this.add(cell);
                }
                this.gridComponents[i][j] = cell;
            }
        }

    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

//    public void setChessComponentAtGrid(ChessboardPoint point, ElephantChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }
//
//
//    public void setChessComponentAtGrid(ChessboardPoint point, LionChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }
//
//    public void setChessComponentAtGrid(ChessboardPoint point, TigerChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }
//
//    public void setChessComponentAtGrid(ChessboardPoint point, LeopardChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }
//
//    public void setChessComponentAtGrid(ChessboardPoint point, WolfChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }
//
//    public void setChessComponentAtGrid(ChessboardPoint point, DogChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessComponent chess) {
        this.getGridComponentAt(point).add(chess);
    }

//    public void setChessComponentAtGrid(ChessboardPoint point, RatChessComponent chess) {
//        this.getGridComponentAt(point).add(chess);
//    }

//    public ElephantChessComponent removeChessComponentAtGridElephant(ChessboardPoint point) {
//        ElephantChessComponent chess = (ElephantChessComponent) this.getGridComponentAt(point).getComponents()[0];
//        this.getGridComponentAt(point).removeAll();
//        this.getGridComponentAt(point).revalidate();
//        chess.setSelected(false);
//        return chess;
//    }
//
//    public LionChessComponent removeChessComponentAtGridLion(ChessboardPoint point) {
//        LionChessComponent chess = (LionChessComponent) this.getGridComponentAt(point).getComponents()[0];
//        this.getGridComponentAt(point).removeAll();
//        this.getGridComponentAt(point).revalidate();
//        chess.setSelected(false);
//        return chess;
//    }
//
//    public TigerChessComponent removeChessComponentAtGridTiger(ChessboardPoint point) {
//        TigerChessComponent chess = (TigerChessComponent) this.getGridComponentAt(point).getComponents()[0];
//        this.getGridComponentAt(point).removeAll();
//        this.getGridComponentAt(point).revalidate();
//        chess.setSelected(false);
//        return chess;
//    }
//
//    public LeopardChessComponent removeChessComponentAtGridLeopard(ChessboardPoint point) {
//        LeopardChessComponent chess = (LeopardChessComponent) this.getGridComponentAt(point).getComponents()[0];
//        this.getGridComponentAt(point).removeAll();
//        this.getGridComponentAt(point).revalidate();
//        chess.setSelected(false);
//        return chess;
//    }
//
//    public WolfChessComponent removeChessComponentAtGridWolf(ChessboardPoint point) {
//        WolfChessComponent chess = (WolfChessComponent) this.getGridComponentAt(point).getComponents()[0];
//        this.getGridComponentAt(point).removeAll();
//        this.getGridComponentAt(point).revalidate();
//        chess.setSelected(false);
//        return chess;
//    }
//
//    public DogChessComponent removeChessComponentAtGridDog(ChessboardPoint point) {
//        DogChessComponent chess = (DogChessComponent) this.getGridComponentAt(point).getComponents()[0];
//        this.getGridComponentAt(point).removeAll();
//        this.getGridComponentAt(point).revalidate();
//        chess.setSelected(false);
//        return chess;
//    }

    public ChessComponent removeChessComponentAtGrid(ChessboardPoint point) {
        ChessComponent chess = (ChessComponent) this.getGridComponentAt(point).getComponents()[0];
        this.getGridComponentAt(point).removeAll();
        this.getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    public RatChessComponent removeChessComponentAtGridRat(ChessboardPoint point) {
        RatChessComponent chess = (RatChessComponent) this.getGridComponentAt(point).getComponents()[0];
        this.getGridComponentAt(point).removeAll();
        this.getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }


    private CellComponent getGridComponentAt(ChessboardPoint point) {
        return this.gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        int var10001 = point.y / this.CHESS_SIZE;
        System.out.println("[" + var10001 + ", " + point.x / this.CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y / this.CHESS_SIZE, point.x / this.CHESS_SIZE);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * this.CHESS_SIZE, row * this.CHESS_SIZE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void processMouseEvent(MouseEvent e) {
        if (e.getID() == 501) {
            JComponent clickedComponent = (JComponent) this.getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                this.gameController.onPlayerClickCell(this.getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (ChessComponent) clickedComponent.getComponents()[0]);

//                if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Elephant")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (ElephantChessComponent) clickedComponent.getComponents()[0]);
//                } else if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Lion")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (LionChessComponent) clickedComponent.getComponents()[0]);
//                } else if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Tiger")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (TigerChessComponent) clickedComponent.getComponents()[0]);
//                } else if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Leopard")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (LeopardChessComponent) clickedComponent.getComponents()[0]);
//                } else if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Wolf")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (WolfChessComponent) clickedComponent.getComponents()[0]);
//                } else if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Dog")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (DogChessComponent) clickedComponent.getComponents()[0]);
//                }  else if (this.getGameController().getModel().getGrid()[y][x].getPiece().getName().equals("Rat")) {
//                    this.gameController.onPlayerClickChessPiece(this.getChessboardPoint(e.getPoint()), (RatChessComponent) clickedComponent.getComponents()[0]);
//                }
            }
        }

    }

    public void removeChessComponent() {
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                try {
                    gridComponents[i][j].remove(0);
                } catch (Exception ignored) {
                }
            }
        }

    }

//    public void removeChessComponent(ChessboardPoint point) {
//        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
//            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
//                if()
//            }
//        }
//    }
}
