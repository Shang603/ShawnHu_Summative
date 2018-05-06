package com.company;

import javax.swing.*;

public class Bar extends JLabel {

    ImageIcon RBar = new ImageIcon(getClass().getResource("RBar.png"));
    ImageIcon LBar = new ImageIcon(getClass().getResource("LBar.png"));

    ImageIcon RWizFace = new ImageIcon(getClass().getResource("RWizFace.png"));
    ImageIcon LWizFace = new ImageIcon(getClass().getResource("LWizFace.png"));

    ImageIcon RobFace = new ImageIcon(getClass().getResource("RobFace.png"));

    JLabel mugshot = new JLabel();

    public Bar(int whichPlayerNum, boolean[] whichPlayer) {


        setOpaque(false);

        if (whichPlayerNum == 1) {

            setBounds(0, 0, RBar.getIconWidth(), RBar.getIconHeight());
            setIcon(RBar);

            mugshot.setBounds(0, 0, RWizFace.getIconWidth(), RWizFace.getIconHeight());
            mugshot.setIcon(RWizFace);
            mugshot.setOpaque(false);

        } else if (whichPlayerNum == 2) {

            setBounds(FightClub.width - LBar.getIconWidth(), 0, LBar.getIconWidth(), LBar.getIconHeight());
            setIcon(RBar);

            mugshot.setBounds(0, 0, RobFace.getIconWidth(), RobFace.getIconHeight());
            mugshot.setIcon(RobFace);
            mugshot.setOpaque(false);

        }

        add(mugshot);

    }


}
