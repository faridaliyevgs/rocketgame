package org.example;

import org.example.EkranPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class GameWindow extends JFrame {

    public GameWindow(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow("OyunPenceresi");
        gw.setResizable(false);
        gw.setFocusable(false);
        gw.setSize(800, 600);
        gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EkranPanel ep = new EkranPanel();
        ep.requestFocus();
        ep.addKeyListener(ep);
        ep.setFocusable(true);
        ep.setFocusTraversalKeysEnabled(true);
        gw.add(ep);
        gw.setVisible(true);

    }
}
