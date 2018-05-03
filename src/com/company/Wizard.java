package com.company;

import javax.swing.*;

public class Wizard extends Player {

    ImageIcon RNormWizStat = new ImageIcon(getClass().getResource("R_Norm_Wiz_Stat_v1.gif"));
    ImageIcon RNormWizWalk = new ImageIcon(getClass().getResource("R_Norm_Wiz_Walk_v1.gif"));
    ImageIcon RNormWizJump = new ImageIcon(getClass().getResource("R_Norm_Wiz_Jump_v2.gif"));
    ImageIcon RNormWizPunch = new ImageIcon(getClass().getResource("R_Norm_Wiz_Punch_v3.gif"));
    ImageIcon RNormWizShot = new ImageIcon(getClass().getResource("R_Norm_Wiz_Shot_v1.gif"));
    ImageIcon RNormWizSpin = new ImageIcon(getClass().getResource("R_Norm_Wiz_SPin_v1.gif"));
    ImageIcon RNormWizLight = new ImageIcon(getClass().getResource("R_Norm_Wiz_Lightning_v4.gif"));

    ImageIcon LNormWizStat = new ImageIcon(getClass().getResource("L_Norm_Wiz_Stat_v1.gif"));
    ImageIcon LNormWizWalk = new ImageIcon(getClass().getResource("L_Norm_Wiz_Walk_v1.gif"));
    ImageIcon LNormWizJump = new ImageIcon(getClass().getResource("L_Norm_Wiz_Jump_v2.gif"));
    ImageIcon LNormWizPunch = new ImageIcon(getClass().getResource("L_Norm_Wiz_Punch_v3.gif"));
    ImageIcon LNormWizShot = new ImageIcon(getClass().getResource("L_Norm_Wiz_Shot_v1.gif"));
    ImageIcon LNormWizSpin = new ImageIcon(getClass().getResource("L_Norm_Wiz_SPin_v1.gif"));
    ImageIcon LNormWizLight = new ImageIcon(getClass().getResource("L_Norm_Wiz_Lightning_v4.gif"));

    ImageIcon[][] allPic = new ImageIcon[4][6];


    Wizard(JComponent RootPane) {

        setWiz();
        setMoveSpeed(8);

        icon.setIcon(RNormWizStat);
        icon.setBounds(0, World.height - RNormWizStat.getIconHeight() - commonFloor, RNormWizStat.getIconWidth(), RNormWizStat.getIconHeight());

        //method for what to do in move timer from super class
        moveAct(10, e -> {

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

                System.out.println(icon.getY());
                atTop = false;
                reset(0, 1, allPic);
                jumpHeight = 33;
                jumpTimer.stop();

            }


        });

        stopAct(10, e -> {

            if (whileBoolMove[1][3] && count == 18) {

                stopMoving();
                reset(1, 3, allPic);
                stopTimer.stop();

            } else if (whileBoolMove[1][4] && count == 18) {

                stopMoving();
                reset(1, 4, allPic);
                icon.setLocation(icon.getLocation().x, icon.getY() - spinDown);
                stopTimer.stop();

            } else if (whileBoolMove[1][5] && count == 50) {

                System.out.println(true);
                stopMoving();
                reset(1, 5, allPic);
                stopTimer.stop();

            } else if (whileBoolMove[0][3] && count == 30) {

                stopMoving();
                reset(0, 3, allPic);
                setLocGround(allPic);
                stopTimer.stop();

            }

            count++;

        });


        setKeyBindingP1(RootPane, allPic);

        movementTimer.start();

    }

    //setup pics
    void setWiz() {

        //setting pics
        allPic[0][0] = RNormWizStat;
        allPic[0][1] = RNormWizJump;
        allPic[1][0] = LNormWizWalk;
        allPic[1][2] = RNormWizWalk;
        allPic[0][3] = RNormWizLight;
        allPic[1][3] = RNormWizPunch;
        allPic[1][4] = RNormWizSpin;
        allPic[1][5] = RNormWizShot;

        allPic[2][0] = LNormWizStat;
        allPic[2][1] = LNormWizJump;
        allPic[3][0] = LNormWizWalk;
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

    //makes wizard move horizontally
    void moveHorizon(int m) {

        icon.setLocation(icon.getLocation().x + m, icon.getY());

    }

    //overloaded method for resetting animation and movements once keys are let go


}
