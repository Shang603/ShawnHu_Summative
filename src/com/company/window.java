package com.company;

import javax.swing.*;

public class window extends JFrame {

    static int width = 1300;
    static int height = 700;

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));
    Wizard wiz = new Wizard();
    String hugeMonster = "hi";

    public window(){

        setSize(width,height);
        setLayout(null);
        setUndecorated(true);
        add(wiz.icon);




    }




}
