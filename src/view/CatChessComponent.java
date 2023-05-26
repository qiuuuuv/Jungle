//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

import model.PlayerColor;

public class CatChessComponent extends ChessComponent {
    private PlayerColor owner;
    private boolean selected;

    public CatChessComponent(PlayerColor owner, int size) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon pic = new ImageIcon("resource/chess/rc.png");
        if (owner == PlayerColor.BLUE) {
            pic = new ImageIcon("resource/chess/bc.png");
        }
        Image image = pic.getImage();
        pic = new ImageIcon(image.getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_SMOOTH));
        JLabel label = new JLabel(pic);
        label.setSize(this.getWidth(), this.getWidth());
        //bgLabel.setLocation(0, 0);
        add(label);

        if (this.isSelected()) {
            if (isSelected()) { // Highlights the model if selected.
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(96, 164, 73));
                Rectangle2D Rectangle = new Rectangle2D.Double(
                        0, 0, this.getWidth(), this.getHeight());
                g2d.fill(Rectangle);
            }
        }
    }
}
