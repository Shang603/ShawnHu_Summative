package com.company;

import javax.swing.*;
import java.awt.*;

public class Projectile extends JLabel {

    ImageIcon RShot = new ImageIcon(getClass().getResource("RShot.gif"));

    public Projectile() {

        RShot = imgRescaler(RShot, 125, 50);
        setIcon(RShot);
        setBounds(0, 0, RShot.getIconWidth(), RShot.getIconHeight());
        setOpaque(false);

    }

    //resized images to correct size
    ImageIcon imgRescaler(ImageIcon img, int w, int h) {

        //complete magic here
        Image tempImg = img.getImage();
        ImageIcon tempFinal = new ImageIcon(tempImg.getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return tempFinal;

    }


}
