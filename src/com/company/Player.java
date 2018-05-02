package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Player {

    int[][] P1Keys = new int[2][6];
    int commonFloor = 45;

    boolean atTop = false;
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
    int spinDown = 80;


    Player() {

        setUp();
   //     icon.setOpaque(true);

    }

    void setUp() {

        P1Keys[0][0] = KeyEvent.VK_Q;
        P1Keys[0][1] = KeyEvent.VK_W;
        P1Keys[1][2] = KeyEvent.VK_D;
        P1Keys[1][0] = KeyEvent.VK_A;
        P1Keys[0][3] = KeyEvent.VK_U;
        P1Keys[1][3] = KeyEvent.VK_J;
        P1Keys[1][4] = KeyEvent.VK_K;
        P1Keys[1][5] = KeyEvent.VK_L;

        stopMoving();
    }

    //sets all the keybinders for player 1
    void setKeyBindingP1(JComponent RootPane, ImageIcon[][] allPic) {

        //TODO: make the exit button into gui, not escape
        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "Exit", e -> {

            System.exit(0);

        });

        //sets the movement right
        addKeyBinder(RootPane, KeyEvent.VK_D, "MoveRight", e -> {
            if (!whileBoolMove[1][4]) {

                set(1, 2, allPic);

            }


        }, e -> {

            if (!whileBoolMove[1][4]) {

                reset(1, 2, allPic);

            }

        });

        //sets the movement left
        addKeyBinder(RootPane, KeyEvent.VK_A, "MoveLeft", e -> {
            if (!whileBoolMove[1][4]) {

                set(1, 0, allPic);

            }

        }, e -> {

            if (!whileBoolMove[1][4]) {

                reset(1, 0, allPic);

            }


        });

        //sets the movement up
        addKeyBinder(RootPane, KeyEvent.VK_W, "Jump", e -> {

            set(0, 1, allPic);
            jumpTimer.start();

        });

        //sets the movement hit
        addKeyBinder(RootPane, KeyEvent.VK_J, "Hit", e -> {

            if (!isAttacking()) {

                set(1, 3, allPic);
                whileBoolMove[1][3] = true;
                stopTimer.start();

            }

        });

        //sets the movement kick
        addKeyBinder(RootPane, KeyEvent.VK_K, "Kick", e -> {

            if (!isAttacking() && !atTop) {

                icon.setLocation(icon.getLocation().x, icon.getY() + spinDown);
                set(1, 4, allPic);
                whileBoolMove[1][4] = true;
                stopTimer.start();

            }

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
        whileBoolMove[w][h] = false;
        count = 0;

        if (isAllBoolFalse(allBoolMove)) {

            icon.setIcon(allPic[0][0]);
            icon.setSize(allPic[0][0].getIconWidth(), allPic[0][0].getIconHeight());

        }

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 6; j++) {

                if (allBoolMove[i][j]) {

                    icon.setIcon(allPic[i][j]);
                    icon.setSize(allPic[i][j].getIconWidth(), allPic[i][j].getIconHeight());

                }

            }

        }


    }

    void set(int w, int h, ImageIcon[][] allPic) {

        allBoolMove[w][h] = true;
        icon.setSize(allPic[w][h].getIconWidth(), allPic[w][h].getIconHeight());
        icon.setIcon(allPic[w][h]);


    }

    void setLocGround(ImageIcon allPic[][]) {

        icon.setLocation(icon.getX(), World.height - allPic[0][0].getIconHeight() - commonFloor);

    }

    void stopMoving(){

        //boolean for recording which button is pressed
        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 6; j++) {

                allBoolMove[i][j] = false;

            }

        }

        //boolean for recording which action is performed
        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 6; j++) {

                whileBoolMove[i][j] = false;

            }

        }



    }

    boolean isAllBoolFalse(boolean[][] t) {

        for (boolean[] a : t) {

            for (boolean x : a) {

                if (x) {

                    return false;

                }

            }

        }

        return true;

    }

    boolean isAttacking() {

        if (whileBoolMove[0][3]) {

            return true;

        }

        if (whileBoolMove[1][3]) {

            return true;

        }

        if (whileBoolMove[1][4]) {

            return true;

        }

        if (whileBoolMove[1][5]) {

            return true;

        }

        return false;

    }




}
