//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JPanel;

public class CellComponent extends JPanel {
    private Color background;

    public CellComponent(Color background, Point location, int size) {
        this.setLayout(new GridLayout(1, 1));
        this.setLocation(location);
        this.setSize(size, size);
        this.background = background;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(this.background);
        g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
    }
}
