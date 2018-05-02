package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class World extends JFrame {

    static int width = 1300;
    static int height = 700;

    JLabel background = new JLabel();

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));
    Player wiz = new Wizard(getRootPane());

    public World() {

        setSize(width, height);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);


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
