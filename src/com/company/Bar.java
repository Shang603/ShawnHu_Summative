package com.company;

import javax.swing.*;
import java.awt.*;

public class Bar extends JLabel {

    final int RHP_XMOVE = 6;
    final int LHP_XMOVE = 7;
    final int HP_Y = 16;
    final int HP_WIDTH = 300;
    final int HP_HEIGHT = 34;

    ImageIcon RBar = new ImageIcon(getClass().getResource("RBar.png"));
    ImageIcon LBar = new ImageIcon(getClass().getResource("LBar.png"));

    ImageIcon RWizFace = new ImageIcon(getClass().getResource("RWizFace.png"));
    ImageIcon LWizFace = new ImageIcon(getClass().getResource("LWizFace.png"));

    ImageIcon RobFace = new ImageIcon(getClass().getResource("RobFace.png"));

    JLabel mugshot = new JLabel();
    JLabel hp = new JLabel();

    public Bar(int whichPlayerNum, boolean[] whichPlayer) {


        setOpaque(false);

        if (whichPlayerNum == 1) {

            setBounds(0, 0, RBar.getIconWidth(), RBar.getIconHeight());
            setIcon(RBar);


            if (whichPlayer[0]) {

                mugshot.setBounds(0, 0, RWizFace.getIconWidth(), RWizFace.getIconHeight());
                mugshot.setIcon(RWizFace);


            } else if (whichPlayer[1]) {


                mugshot.setBounds(0, 0, RobFace.getIconWidth(), RobFace.getIconHeight());
                mugshot.setIcon(RobFace);

            }

            mugshot.setOpaque(false);

            hp.setBackground(Color.green);
            hp.setBounds(mugshot.getWidth() + RHP_XMOVE, HP_Y, HP_WIDTH, HP_HEIGHT);
            hp.setOpaque(true);


        } else if (whichPlayerNum == 2) {

            setBounds(FightClub.width - LBar.getIconWidth(), 0, LBar.getIconWidth(), LBar.getIconHeight());
            setIcon(LBar);

            if (whichPlayer[0]) {

                mugshot.setBounds(0, 0, LWizFace.getIconWidth(), LWizFace.getIconHeight());
                mugshot.setIcon(LWizFace);


            } else if (whichPlayer[1]) {


                mugshot.setBounds(getWidth() - RobFace.getIconWidth(), 0, RobFace.getIconWidth(), RobFace.getIconHeight());
                mugshot.setIcon(RobFace);

            }

            mugshot.setOpaque(false);

            hp.setBackground(Color.green);
            hp.setBounds(LHP_XMOVE, HP_Y, HP_WIDTH, HP_HEIGHT);
            hp.setOpaque(true);

        }

        add(hp);
        add(mugshot);

    }


}
