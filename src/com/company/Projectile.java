package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Projectile extends JLabel {

    ImageIcon RShot = new ImageIcon(getClass().getResource("RShot.gif"));
    ImageIcon LShot = new ImageIcon(getClass().getResource("LShot.gif"));
    ImageIcon RExplosion = new ImageIcon(getClass().getResource("RExplosion.gif"));
    ImageIcon[][] allShot = new ImageIcon[3][3];

    int face = -1;
    int count;

    Timer explosionTimer;

    public Projectile(JLabel icon, int facing) {

        setShots();
        setIcon(allShot[0][facing]);
        setBounds(icon.getX() + 30, Math.round(icon.getY() + icon.getHeight() / 2), RShot.getIconWidth(), RShot.getIconHeight());
        setOpaque(false);

        explosionAct(20, e -> {

            if (count == 4) {

                count = 0;
                RExplosion.getImage().flush();
                remove();
                explosionTimer.stop();

            }

            count++;

        });

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

    void explosionAct(int delay, ActionListener actionListener) {

        explosionTimer = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });

    }

    void remove() {

        setLocation(200,2000);

    }


}
