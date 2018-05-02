package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;

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

    boolean j = false;


    Wizard(JComponent RootPane) {

        setWiz();
        setMoveSpeed(8);

        icon.setIcon(RNormWizStat);
        icon.setBounds(0, World.height - RNormWizStat.getIconHeight() - commonFloor, RNormWizStat.getIconWidth(), RNormWizStat.getIconHeight());

        //method for what to do in move timer from super class
        moveAct(10, e -> {

//            //in charge of changing the animation of the players
//            for (int i = 0; i < 2; i++) {
//
//                for (int j = 0; j < 6; j++) {
//
//                    if (allBoolMove[i][j]) {
//
//                        icon.setIcon(allPic[i][j]);
//
//                    }
//
//                }
//
//            }

            //if both A and D are pressed, the image icon changes to stat
            if (allBoolMove[1][2] && allBoolMove[1][0]) {

                icon.setIcon(allPic[0][0]);

            }

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

        //method for what to do in jump timer from super class
        jumpAct(20, e -> {

            if (intiJump == 0){

                j= true;

            }

            if (!j) {

                icon.setLocation(icon.getX(), icon.getLocation().y - intiJump);
                --intiJump;

            } else {

                icon.setLocation(icon.getX(), icon.getLocation().y + intiJump);
                ++intiJump;

            }

            if (icon.getLocation().y == World.height - RNormWizStat.getIconHeight() - commonFloor) {

                j = false;
                intiJump = 20;
                jump.stop();

            }



        });


        setKeyBindingP1(RootPane, allPic);

        movement.start();

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

    //sets the movement speed
    void setMoveSpeed(int s) {

        moveSpeed = s;

    }


    //makes wizard move horizontally
    void moveHorizon(int m) {

        icon.setLocation(icon.getLocation().x + m, icon.getY());

    }


}
