package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Player {

    int commonFloor = 45;

    boolean[][] allBoolMove = new boolean[2][6];
    boolean[][] whileBoolMove = new boolean[2][6];


    JLabel icon = new JLabel();

    Timer movementTimer;
    Timer jumpTimer;
    Timer stopTimer;

    int jumpHeight = 33;
    int jumpSpeed = 3;
    int moveSpeed;
    int count = 0;


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

        //boolean for recording which action is performed
        for (boolean[] x : whileBoolMove) {

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
            jumpTimer.start();

        });

        //sets the movement hit
        addKeyBinder(RootPane, KeyEvent.VK_J, "Hit", e -> {

            set(1, 3, allPic);
            whileBoolMove[1][3] = true;
            stopTimer.start();

        });

        //sets the movement kick
        addKeyBinder(RootPane, KeyEvent.VK_K, "Kick", e -> {

            set(1, 3, allPic);
            whileBoolMove[1][3] = true;
            stopTimer.start();

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


        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), "Pressed " + id);
        im.put(KeyStroke.getKeyStroke(KeyCode, 0, true), "Released " + id);

        ap.put("Pressed " + id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerP.actionPerformed(e);
            }
        });

        ap.put("Released " + id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerR.actionPerformed(e);
            }
        });


    }

    void moveAct(int delay, ActionListener actionListener) {

        movementTimer = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });
    }

    void jumpAct(int delay, ActionListener actionListener) {

        jumpTimer = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });

    }

    void stopAct(int delay, ActionListener actionListener) {

        stopTimer = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });

    }

    void reset(int w, int h, ImageIcon[][] allPic) {

        allBoolMove[w][h] = false;


        if (!Arrays.asList(allBoolMove).contains(true)) {

            icon.setIcon(allPic[0][0]);

        }


    }

    void set(int w, int h, ImageIcon[][] allPic) {

        allBoolMove[w][h] = true;
        icon.setSize(allPic[w][h].getIconWidth(),allPic[w][h].getIconHeight());
        icon.setIcon(allPic[w][h]);


    }


}
