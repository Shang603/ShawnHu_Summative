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


    Robot() {





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
