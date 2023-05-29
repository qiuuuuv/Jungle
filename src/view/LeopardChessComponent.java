//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

import model.PlayerColor;

public class LeopardChessComponent extends ChessComponent {
    private PlayerColor owner;
    private boolean selected;

    public LeopardChessComponent(PlayerColor owner, int size) {
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon pic = new ImageIcon("src/resources/rl.png");
        if (owner == PlayerColor.BLUE){
            pic = new ImageIcon("src/resources/bl.png");
        }
        Image image = pic.getImage();
        pic = new ImageIcon(image.getScaledInstance(this.getWidth(), this.getWidth(),Image.SCALE_SMOOTH));
        JLabel label = new JLabel(pic);
        label.setSize(this.getWidth(), this.getWidth());
        //bgLabel.setLocation(0, 0);
        add(label);
    }
}
