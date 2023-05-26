//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import model.PlayerColor;

public class TrapChessComponent extends JComponent {
    private PlayerColor owner;

    public TrapChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.setSize(size / 2, size / 2);
        this.setLocation(0, 0);
        this.setVisible(true);
    }
}
