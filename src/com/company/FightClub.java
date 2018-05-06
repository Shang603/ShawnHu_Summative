package com.company;

import javax.swing.*;
import java.awt.*;


public class FightClub extends JFrame {

    static int width = 1300;
    static int height = 700;

    final int HITBACK = 20;

    JLabel background = new JLabel();
    Timer direction;
    Timer collision;

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));

    Player P1 = new Wizard(getRootPane(), 1);
    Player P2 = new Robot(getRootPane(), 2);

    public FightClub() {

        setSize(width, height);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);


        direction = new Timer(20, e -> {

            if (P1.icon.getX() >= P2.icon.getX() + P2.icon.getWidth()) {

                P1.facing = 2;
                P2.facing = 0;

            } else if (P2.icon.getX() >= P1.icon.getX() + P1.icon.getWidth()) {

                P1.facing = 0;
                P2.facing = 2;

            }

        });

        collision = new Timer(20, e -> {


            if (hitProjectile(P2, P1)) {

                P1.setBack(HITBACK);

            }

            if (hitProjectile(P1, P2)) {

                P2.setBack(HITBACK);

            }



        });


        background.setIcon(imgRescaler(FJap, width, height));
        background.setBounds(0, 0, width, height);


        add(P1.hpMagic);
        add(P1.icon);
        add(P2.icon);
        add(background);


        collision.start();
        direction.start();


    }


    //resize images to correct size
    ImageIcon imgRescaler(ImageIcon img, int w, int h) {

        //complete magic here
        Image tempImg = img.getImage();
        ImageIcon tempFinal = new ImageIcon(tempImg.getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return tempFinal;

    }

    boolean hitProjectile(Player a, Player b) {

        for (Projectile x : a.allBulltes) {

            if (x.getBounds().intersects(b.icon.getBounds())) {

                x.setExplosion();
                x.face = -2;
                x.explosionTimer.start();
                return true;

            }


        }

        return false;


    }


}
