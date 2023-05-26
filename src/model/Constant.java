//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public enum Constant {
    CHESSBOARD_ROW_SIZE(9),
    CHESSBOARD_COL_SIZE(7);

    private final int num;

    private Constant(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }
}
