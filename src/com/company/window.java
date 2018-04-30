package com.company;

import javax.swing.*;

public class window extends JFrame {

    static int width = 1300;
    static int height = 700;

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));
    Wizard wiz = new Wizard();
<<<<<<< HEAD
    String nani = "hi";
=======
    String dickhead = "hi";
>>>>>>> parent of 1312747... just a test

    public window(){

        setSize(width,height);
        setLayout(null);
        setUndecorated(true);
        add(wiz.icon);



    }




}
