//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package listener;

import model.ChessboardPoint;
import view.*;

public interface GameListener {
    void onPlayerClickCell(ChessboardPoint var1, CellComponent var2);

    void onPlayerClickChessPiece(ChessboardPoint var1, ChessComponent var2);
}
