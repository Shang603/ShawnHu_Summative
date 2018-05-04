package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Wizard extends Player {

    ImageIcon RNormWizStat = new ImageIcon(getClass().getResource("R_Norm_Wiz_Stat_v1.gif"));
    ImageIcon RNormWizWalk = new ImageIcon(getClass().getResource("R_Norm_Wiz_Walk_v1.gif"));
    ImageIcon RNormWizBlock = new ImageIcon(getClass().getResource("RBlock.gif"));
    ImageIcon RNormWizJump = new ImageIcon(getClass().getResource("R_Norm_Wiz_Jump_v2.gif"));
    ImageIcon RNormWizPunch = new ImageIcon(getClass().getResource("R_Norm_Wiz_Punch_v3.gif"));
    ImageIcon RNormWizShot = new ImageIcon(getClass().getResource("R_Norm_Wiz_Shot_v1.gif"));
    ImageIcon RNormWizSpin = new ImageIcon(getClass().getResource("R_Norm_Wiz_SPin_v1.gif"));
    ImageIcon RNormWizLight = new ImageIcon(getClass().getResource("R_Norm_Wiz_Lightning_v4.gif"));

    ImageIcon LNormWizStat = new ImageIcon(getClass().getResource("L_Norm_Wiz_Stat_v1.gif"));
    ImageIcon LNormWizWalk = new ImageIcon(getClass().getResource("L_Norm_Wiz_Walk_v1.gif"));
    ImageIcon LNormWizBlock = new ImageIcon(getClass().getResource("LBlock.gif"));
    ImageIcon LNormWizJump = new ImageIcon(getClass().getResource("L_Norm_Wiz_Jump_v2.gif"));
    ImageIcon LNormWizPunch = new ImageIcon(getClass().getResource("L_Norm_Wiz_Punch_v3.gif"));
    ImageIcon LNormWizShot = new ImageIcon(getClass().getResource("L_Norm_Wiz_Shot_v1.gif"));
    ImageIcon LNormWizSpin = new ImageIcon(getClass().getResource("L_Norm_Wiz_SPin_v1.gif"));
    ImageIcon LNormWizLight = new ImageIcon(getClass().getResource("L_Norm_Wiz_Lightning_v4.gif"));

    




    Wizard(JComponent RootPane, int whichPlayer) {

        setWiz();
        setMoveSpeed(8);
        setProjectSpeed(20);
        setKeyBindingP1(RootPane);//TODO: setKeyBindingP1 is for P1, adjust later to accommodate for P2 listener

        icon.setIcon(RNormWizStat);
        icon.setBounds(0, World.height - RNormWizStat.getIconHeight() - commonFloor, RNormWizStat.getIconWidth(), RNormWizStat.getIconHeight());



        //method for what to do in move timer from super class
        moveAct(10, e -> {

            outOfBounds();
            //if pressed D
            if (allBoolMove[1][2]) {

                //move right
                moveHorizon(moveSpeed);
                //     set(1,2,allPic);

            }

            //if press A
            if (allBoolMove[1][0]) {

                //move left
                moveHorizon(-moveSpeed);
                //   set(1,0,allPic);

            }


        });

        //method for what to do in jumpTimer timer from super class
        jumpAct(20, e -> {

            if (jumpHeight == 0) {

                atTop = true;

            }

            if (!atTop) {

                icon.setLocation(icon.getX(), icon.getLocation().y - jumpHeight);
                jumpHeight -= jumpSpeed;

            } else {

                icon.setLocation(icon.getX(), icon.getLocation().y + jumpHeight);
                jumpHeight += jumpSpeed;

            }

            if (emergencyStop) {

                atTop = false;
                jumpHeight = 33;
                emergencyStop = false;
                jumpTimer.stop();


            }

            if (icon.getLocation().y >= World.height - icon.getHeight() - commonFloor) {

                atTop = false;
                reset(0, 1);
                jumpHeight = 33;
                jumpTimer.stop();

            }


        });

        stopAct(10, e -> {

            if (allBoolMove[1][3] && count == 18) {

                stopMoving();
                reset(1, 3);
                stopTimer.stop();

            } else if (allBoolMove[1][4] && count == 18) {

                icon.setLocation(icon.getLocation().x, icon.getY() - spinDown);
                stopMoving();
                reset(1, 4);
                stopTimer.stop();

            } else if (allBoolMove[1][5] && count == 25) {

                stopMoving();
                reset(1, 5);
                stopTimer.stop();

            } else if (allBoolMove[0][3] && count == 30) {

                stopMoving();
                reset(0, 3);
                setLocGround();
                stopTimer.stop();

            }

            count++;

        });

        bulletAct(20, e -> {

            for (int i = 0; i < allBulltes.size(); i++) {

                allBulltes.get(i).moveHorizon(20);
                if (allBulltes.get(i).getX() + allBulltes.get(i).getWidth() >= World.width) {

                    Main.frame.remove(allBulltes.get(i));


                }

            }



        });


        movementTimer.start();

    }

    //setup pics
    void setWiz() {

        //setting pics
        allPic[0][0] = RNormWizStat;
        allPic[0][1] = RNormWizJump;
        allPic[1][0] = LNormWizWalk;
        allPic[1][1] = RNormWizBlock;
        allPic[1][2] = RNormWizWalk;
        allPic[0][3] = RNormWizLight;
        allPic[1][3] = RNormWizPunch;
        allPic[1][4] = RNormWizSpin;
        allPic[1][5] = RNormWizShot;

        allPic[2][0] = LNormWizStat;
        allPic[2][1] = LNormWizJump;
        allPic[3][0] = LNormWizWalk;
        allPic[3][1] = LNormWizBlock;
        allPic[3][2] = RNormWizWalk;
        allPic[2][3] = LNormWizLight;
        allPic[3][3] = LNormWizPunch;
        allPic[3][4] = LNormWizSpin;
        allPic[3][5] = LNormWizShot;

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

        if (icon.getX() + icon.getWidth() >= World.width) {

            icon.setLocation(World.width - icon.getWidth(), icon.getY());

        } else if (icon.getX() <= 0) {

            icon.setLocation(0, icon.getY());

        }

    }


}
