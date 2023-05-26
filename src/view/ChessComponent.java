package view;

import model.PlayerColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ChessComponent extends JComponent{
    private PlayerColor owner;
    private boolean selected;

    public ChessComponent() {
    }

    public ChessComponent(PlayerColor owner, boolean selected) {
        this.owner = owner;
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {}


    public PlayerColor getOwner() {
        return owner;
    }


    public void setOwner(PlayerColor owner) {
        this.owner = owner;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
