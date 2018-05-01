package com.company;

import javax.swing.*;
import java.awt.*;

public class window extends JFrame {

    static int width = 1300;
    static int height = 700;

    JLabel background = new JLabel();

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));
    Wizard wiz = new Wizard();

    public window() {

        setSize(width, height);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);


        background.setIcon(imgRescaler(FJap, width, height));
        background.setBounds(0, 0, width, height);

        add(wiz.icon);
        add(background);


    }

    ImageIcon imgRescaler(ImageIcon img, int w, int h) {

        Image tempImg = img.getImage();
        ImageIcon tempFinal = new ImageIcon(tempImg.getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return tempFinal;

    }


}
