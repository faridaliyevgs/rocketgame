package org.example;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Ates {
    private int x;
    private int y;

    public Ates(int x) {
    }

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}

public class EkranPanel extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(2, this);

    private  int kecen_vaxt = 0;
    private  int atilan_ates = 0;
    private BufferedImage img;
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    private int atesdirY = 6;
    private  int topX = 0;
    private  int topdirX = 2;
    public boolean controller(){
        for (Ates ates : atesler) {
            if ( new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX, 0, 20, 20))){
                return true;
            }


        }
        return false;
    }
    public EkranPanel()  {
        try {
            img = ImageIO.read(new FileImageInputStream(new File("rocket.png.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(Color.BLACK);
        timer.start();

    }

    private  int roketX = -33;
    private  int dirSpaceX = 30;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        kecen_vaxt += 2;
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20,20);
        g.drawImage(img, roketX, 460, img.getWidth()/3, img.getHeight()/3, this);
        for(Ates ates : atesler){
            if(ates.getY()<0){
                atesler.remove(ates);
            }

        }
        g.setColor(Color.BLUE);
        for(Ates ates : atesler){
            g.fillRect(ates.getX(), ates.getY(), 10,20);
        }
        if(controller()){
            timer.stop();
            String msg = "Qalib geldiniz...\n"+
                    "Serf olunan vaxt:"+kecen_vaxt+"\n Acilan ates sayi:"+atilan_ates;
            JOptionPane.showMessageDialog(this, msg);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Ates ates : atesler){
            ates.setY(ates.getY() - atesdirY);
        }
        topX += topdirX;
        if (topX >= 750) {
            topdirX = -topdirX;
        }
        if (topX <= 0) {
            topdirX = -topdirX;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            if (roketX <= -33) {
                roketX = 0;
            } else {
                roketX -= dirSpaceX;
            }
        } else if (c == KeyEvent.VK_RIGHT) {
            if (roketX >= 720) {
                roketX = 720;
            } else {
                roketX += dirSpaceX;
            }
        } else if (c == KeyEvent.VK_CONTROL) {
            atesler.add(new Ates(roketX + 45, 430));
            atilan_ates++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
