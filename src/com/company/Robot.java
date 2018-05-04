package com.company;

import javax.swing.*;

public class Robot extends Player {

    ImageIcon RNormRobStat = new ImageIcon(getClass().getResource("R_Norm_Rob_Stat_v2.gif"));
    ImageIcon RNormRobWalk = new ImageIcon(getClass().getResource("R_Norm_Rob_Walk_v2.gif"));
    ImageIcon RNormRobJump = new ImageIcon(getClass().getResource("R_Norm_Rob_Jump_v1.gif"));
    ImageIcon RNormRobPunch = new ImageIcon(getClass().getResource("R_Norm_Rob_Punch_v2.gif"));
    ImageIcon RNormRobShot = new ImageIcon(getClass().getResource("R_Norm_Rob_Gun_v2.gif"));
    ImageIcon RNormRobKick = new ImageIcon(getClass().getResource("R_Norm_Rob_Kick_v2.gif"));
    ImageIcon RNormRobSlam = new ImageIcon(getClass().getResource("R_Norm_Rob_Ground_Slam_v2.gif"));

    ImageIcon LNormRobStat = new ImageIcon(getClass().getResource("L_Norm_Rob_Stat_v2.gif"));
    ImageIcon LNormRobWalk = new ImageIcon(getClass().getResource("L_Norm_Rob_Walk_v2.gif"));
    ImageIcon LNormRobJump = new ImageIcon(getClass().getResource("L_Norm_Rob_Jump_v1.gif"));
    ImageIcon LNormRobPunch = new ImageIcon(getClass().getResource("L_Norm_Rob_Punch_v2.gif"));
    ImageIcon LNormRobShot = new ImageIcon(getClass().getResource("L_Norm_Rob_Gun_v2.gif"));
    ImageIcon LNormRobKick = new ImageIcon(getClass().getResource("L_Norm_Rob_Kick_v2.gif"));
    ImageIcon LNormRobSlam = new ImageIcon(getClass().getResource("L_Norm_Rob_Ground_Slam_v2.gif"));

    final int JUMP_HEIGHT = 33;

    Robot(JComponent RootPane, int whichPlayer) {

        setRob();
        setMoveSpeed(8);
        setProjectSpeed(20);

        if (whichPlayer == 1) {

            setKeyBindingP1(RootPane);//TODO: setKeyBindingP1 is for P1, adjust later to accommodate for P2 listener

        } if (whichPlayer == 2) {

            setKeyBindingP2(RootPane);//TODO: setKeyBindingP1 is for P1, adjust later to accommodate for P2 listener

        }

        icon.setIcon(RNormRobStat);
        icon.setBounds(400, World.height - RNormRobStat.getIconHeight() - commonFloor, RNormRobStat.getIconWidth(), RNormRobStat.getIconHeight());



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
                jumpHeight = JUMP_HEIGHT;
                emergencyStop = false;
                jumpTimer.stop();


            }

            if (icon.getLocation().y >= World.height - icon.getHeight() - commonFloor) {

                atTop = false;
                reset(0, 1);
                jumpHeight = JUMP_HEIGHT;
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

    void setRob() {

        allPic[0][0] = RNormRobStat;
        allPic[0][1] = RNormRobJump;
        allPic[1][0] = LNormRobWalk;
        allPic[1][2] = RNormRobWalk;
        allPic[0][3] = RNormRobSlam;
        allPic[1][3] = RNormRobPunch;
        allPic[1][4] = RNormRobKick;
        allPic[1][5] = RNormRobShot;

        allPic[2][0] = LNormRobStat;
        allPic[2][1] = LNormRobJump;
        allPic[3][0] = LNormRobWalk;
        allPic[3][2] = RNormRobWalk;
        allPic[2][3] = LNormRobSlam;
        allPic[3][3] = LNormRobPunch;
        allPic[3][4] = LNormRobKick;
        allPic[3][5] = LNormRobShot;

    }



}
