package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    int commonFloor = 45;

    boolean atTop = false;
    boolean emergencyStop = false;
    boolean[][] allBoolMove = new boolean[2][6];

    ImageIcon[][] allPic = new ImageIcon[4][6];

    ArrayList<Projectile> allBulltes = new ArrayList<>();

    JLabel icon = new JLabel();

    Timer movementTimer;
    Timer jumpTimer;
    Timer stopTimer;
    Timer bulletTimer;

    int jumpHeight = 33;
    int jumpSpeed = 3;
    int moveSpeed;
    int count = 0;
    int spinDown = 80;
    int lightUp = 600;
    int projectSpeed;
    int projectStart = 0;


    Player() {

        stopMoving();

    }

    //sets all the keybinders for player 1
    void setKeyBindingP1(JComponent RootPane) {

        //TODO: make the exit button into gui, not escape
        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "Exit", e -> {

            System.exit(0);

        });

        //sets the movement block
        addKeyBinder(RootPane, KeyEvent.VK_S, "Block", e -> {

            if (!isAttacking()) {

                stopMoving();
                set(1, 1);

            }


        }, e -> {

                reset(1, 1);

        });

        //sets the movement right
        addKeyBinder(RootPane, KeyEvent.VK_D, "MoveRight", e -> {
            if (!isAttacking()) {

                set(1, 2);

            }


        }, e -> {

            if (!isAttacking()) {

                reset(1, 2);

            }

        });

        //sets the movement left
        addKeyBinder(RootPane, KeyEvent.VK_A, "MoveLeft", e -> {
            if (!isAttacking()) {

                set(1, 0);

            }

        }, e -> {

            if (!isAttacking()) {

                reset(1, 0);

            }


        });

        //sets the movement up
        addKeyBinder(RootPane, KeyEvent.VK_W, "Jump", e -> {

            if (!isAttacking()) {

                set(0, 1);
                jumpTimer.start();

            }

        });

        //sets the movement hit
        addKeyBinder(RootPane, KeyEvent.VK_J, "Hit", e -> {

            if (!isAttacking()) {

                set(1, 3);
                stopTimer.start();

            }

        });

        //sets the movement kick
        addKeyBinder(RootPane, KeyEvent.VK_K, "Kick", e -> {

            if (!isAttacking() && !atTop) {

                icon.setLocation(icon.getLocation().x, icon.getY() + spinDown);
                set(1, 4);
                stopTimer.start();

            }

        });

        //sets the movement shoot
        addKeyBinder(RootPane, KeyEvent.VK_L, "Shoot", e -> {

            if (!isAttacking()) {

                set(1, 5);

                allBulltes.add(new Projectile(icon));

                for (int i = projectStart; i < allBulltes.size(); i++) {

                    Main.frame.add(allBulltes.get(i),0);

                }

                ++projectStart;
                bulletTimer.start();
                stopTimer.start();

            }

        });

        //sets the movement super
        addKeyBinder(RootPane, KeyEvent.VK_U, "Lightning", e -> {

            if (!isAttacking() && !isJumping()) {

                set(0, 3);
                emergencyStop = true;
                icon.setLocation(icon.getLocation().x, World.height - allPic[0][0].getIconHeight() - commonFloor - lightUp);
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

    void bulletAct(int delay, ActionListener actionListener) {

        bulletTimer = new Timer(delay, e -> {

            actionListener.actionPerformed(e);

        });

    }

    void reset(int w, int h) {

        allBoolMove[w][h] = false;
        allPic[w][h].getImage().flush();
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

    void set(int w, int h) {

        allBoolMove[w][h] = true;
        icon.setSize(allPic[w][h].getIconWidth(), allPic[w][h].getIconHeight());
        icon.setIcon(allPic[w][h]);


    }

    void setLocGround() {

        icon.setLocation(icon.getX(), World.height - allPic[0][0].getIconHeight() - commonFloor);

    }

    void stopMoving() {

        //boolean for recording which button is pressed
        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 6; j++) {

                allBoolMove[i][j] = false;

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

        if (allBoolMove[0][3]) {

            return true;

        }

        if (allBoolMove[1][3]) {

            return true;

        }

        if (allBoolMove[1][4]) {

            return true;

        }

        if (allBoolMove[1][5]) {

            return true;

        }

        return false;

    }

    boolean isJumping() {

        return allBoolMove[0][1];

    }

    boolean isWalking() {

        if (!allBoolMove[1][0] && !allBoolMove[1][2]) {

            return false;

        }

        return true;

    }


}
