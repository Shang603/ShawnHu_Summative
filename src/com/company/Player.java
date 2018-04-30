package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player {

    int[][] P1Keys = new int[2][6];
    int[][] P2Keys = new int[2][6];
    JLabel icon = new JLabel();


    Player() {



    }

    void setKeys() {

        P1Keys[0][0] = KeyEvent.VK_Q;
        P1Keys[0][1] = KeyEvent.VK_W;
        P1Keys[1][2] = KeyEvent.VK_D;
        P1Keys[1][0] = KeyEvent.VK_A;
        P1Keys[0][3] = KeyEvent.VK_U;
        P1Keys[1][3] = KeyEvent.VK_J;
        P1Keys[1][4] = KeyEvent.VK_K;
        P1Keys[1][5] = KeyEvent.VK_L;

        P2Keys[0][0] = KeyEvent.VK_DOWN;
        P2Keys[0][1] = KeyEvent.VK_UP;
        P2Keys[1][2] = KeyEvent.VK_RIGHT;
        P2Keys[1][0] = KeyEvent.VK_LEFT;
        P2Keys[0][3] = KeyEvent.VK_4;
        P2Keys[1][3] = KeyEvent.VK_1;
        P2Keys[1][4] = KeyEvent.VK_2;
        P2Keys[1][5] = KeyEvent.VK_3;

    }


}
