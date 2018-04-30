package com.company;

import javax.swing.*;

public class window extends JFrame {

    static int width = 1300;
    static int height = 700;

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));
    Wizard wiz = new Wizard();

    public window(){

        setSize(width,height);
        setLayout(null);
        getContentPane().set
       // setUndecorated(true);




        add(wiz.icon);




    }




}
