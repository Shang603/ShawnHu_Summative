package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    int commonFloor = 45;

    boolean atTop = false;
    boolean emergencyStop = false;
    boolean[][] allBoolMove = new boolean[2][6];
    boolean[] whichPlayer = new boolean[3];

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

    int RobKickDistance = 100;
    int RobShootDistance = 100;

    int projectSpeed;
    int projectStart = 0;
    int facing = 0;

    final int JUMP_HEIGHT = 33;
    final int ROB_SHOOT = -40;


    Player() {

        stopMoving();

    }


    //sets the movementTimer speed
    void setMoveSpeed(int s) {

        moveSpeed = s;

    }

    //sets the movementTimer speed
    void setProjectSpeed(int s) {

        projectSpeed = s;

    }

    //makes wizard move horizontally
    void moveHorizon(int m) {

        icon.setLocation(icon.getLocation().x + m, icon.getY());

    }

    void outOfBounds() {

        if (icon.getX() + icon.getWidth() >= FightClub.width) {

            icon.setLocation(FightClub.width - icon.getWidth(), icon.getY());

        } else if (icon.getX() <= 0) {

            icon.setLocation(0, icon.getY());

        }

    }

    //sets all the keybinders for player 1
    void setKeyBindingP1(JComponent RootPane) {

        //TODO: make the exit button into gui, not escape
        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "P1Exit", e -> {

            System.exit(0);

        });

        //sets the movement block
        addKeyBinder(RootPane, KeyEvent.VK_S, "P1Block", e -> {

            if (!isAttacking() && !isJumping()) {

                stopMoving();
                set(1, 1);

            }


        }, e -> {

            reset(1, 1);

        });

        //sets the movement right
        addKeyBinder(RootPane, KeyEvent.VK_D, "P1MoveRight", e -> {
            if (!isAttacking()) {

                set(1, 2);

            }


        }, e -> {

            if (!isAttacking()) {

                reset(1, 2);

            }

        });

        //sets the movement left
        addKeyBinder(RootPane, KeyEvent.VK_A, "P1MoveLeft", e -> {
            if (!isAttacking()) {

                set(1, 0);

            }

        }, e -> {

            if (!isAttacking()) {

                reset(1, 0);

            }


        });

        //sets the movement up
        addKeyBinder(RootPane, KeyEvent.VK_W, "P1Jump", e -> {

            if (!isAttacking()) {

                set(0, 1);
                jumpTimer.start();

            }

        });

        //sets the movement hit
        addKeyBinder(RootPane, KeyEvent.VK_F, "P1Hit", e -> {

            if (!isAttacking()) {

                set(1, 3);
                stopTimer.start();

            }

        });

        //sets the movement kick
        addKeyBinder(RootPane, KeyEvent.VK_G, "P1Kick", e -> {

            if (!isAttacking() && !atTop) {

                icon.setLocation(icon.getLocation().x, icon.getY() + spinDown);
                set(1, 4);
                stopTimer.start();

            }

        });

        //sets the movement shoot
        addKeyBinder(RootPane, KeyEvent.VK_H, "P1Shoot", e -> {

            if (!isAttacking()) {

                set(1, 5);

                bulletCreation();

                for (int i = projectStart; i < allBulltes.size(); i++) {

                    Main.frame.add(allBulltes.get(i), 0);

                }

                ++projectStart;
                bulletTimer.start();
                stopTimer.start();

            }

        });

        //sets the movement super
        addKeyBinder(RootPane, KeyEvent.VK_R, "P1Lightning", e -> {

            if (!isAttacking() && !isJumping()) {

                set(0, 3);
                emergencyStop = true;
                icon.setLocation(icon.getLocation().x, FightClub.height - allPic[0][0].getIconHeight() - commonFloor - lightUp);
                stopTimer.start();

            }

        });


    }

    //sets all the keybinders for player 2
    void setKeyBindingP2(JComponent RootPane) {

        //TODO: make the exit button into gui, not escape
        addKeyBinder(RootPane, KeyEvent.VK_ESCAPE, "Exit", e -> {

            System.exit(0);

        });
//
//        //sets the movement block
//        addKeyBinder(RootPane, KeyEvent.VK_S, "Block", e -> {
//
//            if (!isAttacking()) {
//
//                stopMoving();
//                set(1, 1);
//
//            }
//
//
//        }, e -> {
//
//            reset(1, 1);
//
//        });
//
        //sets the movement right
        addKeyBinder(RootPane, KeyEvent.VK_RIGHT, "P2MoveRight", e -> {
            if (!isAttacking()) {

                set(1, 2);

            }


        }, e -> {

            if (!isAttacking()) {

                reset(1, 2);

            }

        });

        //sets the movement left
        addKeyBinder(RootPane, KeyEvent.VK_LEFT, "P2MoveLeft", e -> {
            if (!isAttacking()) {

                set(1, 0);

            }

        }, e -> {

            if (!isAttacking()) {

                reset(1, 0);

            }


        });

        //sets the movement up
        addKeyBinder(RootPane, KeyEvent.VK_UP, "P2Jump", e -> {

            if (!isAttacking()) {

                set(0, 1);
                jumpTimer.start();

            }

        });

        //sets the movement hit
        addKeyBinder(RootPane, KeyEvent.VK_J, "P2Hit", e -> {

            if (!isAttacking()) {

                set(1, 3);
                stopTimer.start();

            }

        });

        //sets the movement kick
        addKeyBinder(RootPane, KeyEvent.VK_K, "Kick", e -> {

            if (!isAttacking() && !atTop) {

                if (whichPlayer[0]) {

                    icon.setLocation(icon.getLocation().x, icon.getY() + spinDown);

                } else if (whichPlayer[1]) {

                    if (facing == 2) {

                        icon.setLocation(icon.getLocation().x - RobKickDistance, icon.getY());

                    } else if (facing == 0) {

                        icon.setLocation(icon.getLocation().x + RobKickDistance, icon.getY());

                    }

                }

                set(1, 4);
                stopTimer.start();

            }

        });

        //sets the movement shoot
        addKeyBinder(RootPane, KeyEvent.VK_L, "Shoot", e -> {

            if (!isAttacking()) {

                set(1, 5);

                if (whichPlayer[1]) {

                    if (facing == 2) {

                        icon.setLocation(icon.getLocation().x - RobShootDistance, icon.getY());

                    } else if (facing == 0) {

                        icon.setLocation(icon.getLocation().x + RobShootDistance, icon.getY());

                    }

                }

                bulletCreation();

                for (int i = projectStart; i < allBulltes.size(); i++) {

                    Main.frame.add(allBulltes.get(i), 0);

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
                if (whichPlayer[0]) {

                    emergencyStop = true;
                    icon.setLocation(icon.getLocation().x, FightClub.height - allPic[0][0].getIconHeight() - commonFloor - lightUp);

                }
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

    void set(int w, int h) {

        allBoolMove[w][h] = true;

        if (facing == 0) {

            icon.setSize(allPic[w][h].getIconWidth(), allPic[w][h].getIconHeight());
            icon.setIcon(allPic[w][h]);

        } else if (facing == 2) {

            icon.setSize(allPic[w + facing][h].getIconWidth(), allPic[w + facing][h].getIconHeight());
            icon.setIcon(allPic[w + facing][h]);

        }


    }

    void reset(int w, int h) {

        allBoolMove[w][h] = false;
        count = 0;

        if (facing == 0) {

            allPic[w][h].getImage().flush();

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

        } else if (facing == 2) {

            allPic[w + facing][h].getImage().flush();

            if (isAllBoolFalse(allBoolMove)) {

                icon.setIcon(allPic[facing][0]);
                icon.setSize(allPic[facing][0].getIconWidth(), allPic[facing][0].getIconHeight());

            }

            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < 6; j++) {

                    if (allBoolMove[i][j]) {

                        icon.setIcon(allPic[i + facing][j]);
                        icon.setSize(allPic[i + facing][j].getIconWidth(), allPic[i + facing][j].getIconHeight());

                    }

                }

            }

        }


    }

    void setLocGround() {

        icon.setLocation(icon.getX(), FightClub.height - allPic[0][0].getIconHeight() - commonFloor);

    }

    void stopMoving() {

        //boolean for recording which button is pressed
        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 6; j++) {

                allBoolMove[i][j] = false;

            }

        }


    }

    void bulletCreation() {

        if (whichPlayer[0]) {

            allBulltes.add(new Projectile(icon, facing));

        } else if (whichPlayer[1]) {

            allBulltes.add(new Projectile(icon, facing, ROB_SHOOT));

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
