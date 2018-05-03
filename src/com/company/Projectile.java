package com.company;

import javax.swing.*;
import java.awt.*;

public class Projectile extends JLabel {

    ImageIcon RShot = new ImageIcon(getClass().getResource("RShot.gif"));


    public Projectile(JLabel icon) {

        setIcon(RShot);
        setBounds(icon.getX() + 30, Math.round(icon.getY() + icon.getHeight() / 2), RShot.getIconWidth(), RShot.getIconHeight());
        setOpaque(false);

    }

    void moveHorizon(int m) {

        setLocation(getLocation().x + m, getY());

    }

    //resized images to correct size
    ImageIcon imgRescaler(ImageIcon img, int w, int h) {

        //complete magic here
        Image tempImg = img.getImage();
        ImageIcon tempFinal = new ImageIcon(tempImg.getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return tempFinal;

    }


}
