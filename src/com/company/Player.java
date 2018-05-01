package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Player {

    int[][] P1Keys = new int[2][6];
    int[][] P2Keys = new int[2][6];
    JLabel icon = new JLabel();
    Timer movement;


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

    void addKeyBindingP(JComponent comp, int KeyCode, String id, ActionListener actionListenerP,ActionListener actionListenerR) {

        InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();
//
//        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), id);
//
//        ap.put(id, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                actionListener.actionPerformed(e);
//
//            }
//        });

        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), "pressed");
        im.put(KeyStroke.getKeyStroke(KeyCode, 0, true), "released");

        ap.put("pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerP.actionPerformed(e);
            }
        });

        ap.put("released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerR.actionPerformed(e);
            }
        });


    }

    void act(int delay,ActionListener actionListener) {

        movement = new Timer(delay,e->{

            actionListener.actionPerformed(e);

        });
    }


}
