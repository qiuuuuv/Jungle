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
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import model.PlayerColor;

public class WolfChessComponent extends ChessComponent {
    private PlayerColor owner;
    private boolean selected;

    public WolfChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        this.setSize(size / 2, size / 2);
        this.setLocation(0, 0);
        this.setVisible(true);
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("楷体", 0, this.getWidth() / 2);
        g2.setFont(font);
        g2.setColor(this.owner.getColor());
        g2.drawString("狼", this.getWidth() / 4, this.getHeight() * 5 / 8);

        if (this.isSelected()) {
            if (isSelected()) { // Highlights the model if selected.
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 255, 255, 150));
                Rectangle2D Rectangle = new Rectangle2D.Double(
                        0, 0, this.getWidth() , this.getHeight());
                g2d.fill(Rectangle);
            }
        }
    }
}
