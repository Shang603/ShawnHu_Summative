package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class World extends JFrame {

    static int width = 1300;
    static int height = 700;

    JLabel background = new JLabel();

    ImageIcon FJap = new ImageIcon(getClass().getResource("japanese town.gif"));
    Wizard wiz = new Wizard();

    public World() {

        setSize(width, height);
        setLayout(null);
            setUndecorated(true);
        setLocationRelativeTo(null);


        background.setIcon(imgRescaler(FJap, width, height));
        background.setBounds(0, 0, width, height);

        add(wiz.icon);
        add(background);

        addKeyBinding(this.getRootPane(), KeyEvent.VK_D, "dick", e -> {

            wiz.icon.setLocation(wiz.icon.getLocation().x+);

        });

    }

    ImageIcon imgRescaler(ImageIcon img, int w, int h) {

        Image tempImg = img.getImage();
        ImageIcon tempFinal = new ImageIcon(tempImg.getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return tempFinal;

    }

    void addKeyBinding(JComponent comp, int KeyCode, String id, ActionListener actionListener) {

        InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), id);

        ap.put(id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actionListener.actionPerformed(e);

            }
        });


    }


}
