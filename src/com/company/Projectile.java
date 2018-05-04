package com.company;

import javax.swing.*;
import java.awt.*;

public class Projectile extends JLabel {

    ImageIcon RShot = new ImageIcon(getClass().getResource("RShot.gif"));
    ImageIcon LShot = new ImageIcon(getClass().getResource("LShot.gif"));

    ImageIcon[][] allShot = new ImageIcon[3][3];

    public Projectile(JLabel icon, int facing) {

        setShots();
        setIcon(allShot[0][facing]);
        setBounds(icon.getX() + 30, Math.round(icon.getY() + icon.getHeight() / 2), RShot.getIconWidth(), RShot.getIconHeight());
        setOpaque(false);

    }

    public Projectile(JLabel icon, int facing, int additionalHeight) {

        setShots();
        setIcon(allShot[0][facing]);
        setBounds(icon.getX() + 30, Math.round(icon.getY() + icon.getHeight() / 2) + additionalHeight, RShot.getIconWidth(), RShot.getIconHeight());
        setOpaque(false);

    }

    void moveHorizon(int m) {

        setLocation(getLocation().x + m, getY());

    }

    void setShots() {

        allShot[0][0] = RShot;
        allShot[0][2] = LShot;


    }


}
