package com.company;

import javax.swing.*;

public class Bar extends JLabel {

    ImageIcon RBar = new ImageIcon(getClass().getResource("RBar.png"));
    ImageIcon LBar = new ImageIcon(getClass().getResource("LBar.png"));

    ImageIcon RWizFace = new ImageIcon(getClass().getResource("RWizFace.png"));
    ImageIcon LWizFace = new ImageIcon(getClass().getResource("LWizFace.png"));

    JLabel mugshot = new JLabel();

    public Bar() {

        setBounds(0,0,RBar.getIconWidth(),RBar.getIconHeight());
        setIcon(RBar);
        setOpaque(false);

        mugshot.setBounds(0,0,RBar.getIconWidth(),RBar.getIconHeight());
        mugshot.setIcon(RWizFace);
        mugshot.setOpaque(false);

        add(mugshot);

    }


}
