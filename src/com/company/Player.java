package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Player {

    int commonFloor = 30;

    boolean[][] allBoolMove = new boolean[2][6];


    JLabel icon = new JLabel();

    Timer movement;
    Timer jump;
    Timer stop;

    int moveSpeed;
    int intiJump = 20;


    Player() {

        setUp();

    }

    void setUp() {

        //boolean for recording which button is pressed
        for (boolean[] x : allBoolMove) {

            for (boolean a : x) {

                a = false;

            }

        }

    }

    //sets all the keybinding for player 1
    void setKeyBindingP1(JComponent RootPane, ImageIcon[][] allPic) {

        //TODO: make the exit button into gui, not escape
        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "Exit", e -> {

            System.exit(0);

        });

        //sets the movement right
        addKeyBinder(RootPane, KeyEvent.VK_D, "MoveRight", e -> {

            set(1, 2, allPic);


        }, e -> {

            reset(1, 2, allPic);

        });

        //sets the movement left
        addKeyBinder(RootPane, KeyEvent.VK_A, "MoveLeft", e -> {

            set(1, 0, allPic);

        }, e -> {

            reset(1, 0, allPic);


        });

        //sets the movement up
        addKeyBinder(RootPane, KeyEvent.VK_W, "Jump", e -> {

            set(0, 1, allPic);
            jump.start();

        }, e -> {

            reset(0, 1, allPic);


        });


    }

    void addKeyBinder(JComponent comp, int KeyCode, String id, ActionListener actionListener) {

        InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), "Pressed Once " + id);

        ap.put("Pressed Once " + id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        });


    }

    void addKeyBinder(JComponent comp, int KeyCode, String id, ActionListener actionListenerP, ActionListener actionListenerR) {

        InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();


        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), "Pressed " + KeyCode);
        im.put(KeyStroke.getKeyStroke(KeyCode, 0, true), "Released " + KeyCode);

        ap.put("Pressed " + KeyCode, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerP.actionPerformed(e);
            }
        });

        ap.put("Released " + KeyCode, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerR.actionPerformed(e);
            }
        });


    }

    void moveAct(int delay, ActionListener actionListener) {

        movement = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });
    }

    void jumpAct(int delay, ActionListener actionListener) {

        jump = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });

    }

    void stopAct(int delay, ActionListener actionListener) {

        stop = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });

    }

    void reset(int w, int h, ImageIcon[][] allPic) {

        allBoolMove[w][h] = false;
        icon.setIcon(allPic[0][0]);


    }

    void set(int w, int h, ImageIcon[][] allPic) {

        allBoolMove[w][h] = true;
        icon.setIcon(allPic[w][h]);


    }


}
