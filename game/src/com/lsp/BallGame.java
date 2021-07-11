package com.lsp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BallGame extends JFrame {

    Image desk = Toolkit.getDefaultToolkit().getImage("D:/code/ball_game/game/resources/balltable.jpg");

    Image ball = Toolkit.getDefaultToolkit().getImage("D:/code/ball_game/game/resources/ball1.png");

    Image offScreenImage = null;
    double x = 200;

    double y = 200;

    double speed = 20;
    double degree = Math.PI/3;

    double startx;
    double starty;
    double endx;
    double endy;

    public void launch() {
        this.setVisible(true);
        this.setSize(800, 501);
        this.setLocation(100, 200);
        this.setTitle("ball_game");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == 1) {
                    startx = x + 10;
                    starty = y + 10;
                    endx = e.getX();
                    endy = e.getY();

                    double angle = getAngle(startx, starty, endx, endy);
                    System.out.println(angle);
                }
            }
        });

        while (true) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        if (offScreenImage == null) {
            offScreenImage = this.createImage(800,501);

        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(Color.darkGray);
        gImage.fillRect(0,0,800,501);
        gImage.drawImage(desk, 20, 50, null);
        gImage.drawImage(ball, (int) x, (int) y, null);
        gImage.setColor(Color.red);
        gImage.drawLine((int)startx,(int)starty,(int)endx,(int)endy);
        g.drawImage(offScreenImage, 0, 0, null);

        if (speed > 0) {
            speed -= 0.15;
        }
        x += speed*Math.cos(degree);
        y += speed * Math.sin(degree);

        if (y < 80 | y > 400) {
            degree = -degree;
        }
        if (x < 50 | x > 750) {
            degree = Math.PI - degree;
        }
    }

    public double getAngle(double startx, double starty, double endx, double endy) {
        double tempx = endx - startx;
        double tempy = endy - starty;
        double z = Math.sqrt(tempx * tempx + tempy * tempy);
        double angle = (float) (Math.asin(Math.abs(tempy) / z));
        if (tempx > 0 && tempy < 0) {
            angle = 6.28 - angle;
        } else if (tempx < 0 &&tempy <0){
            angle = 3.14 + angle;
        } else if (tempx < 0 && tempy > 0) {
            angle = 3.14 - angle;
        }
        return angle;

    }
    public static void main(String[] args) {
        BallGame ballGame = new BallGame();
        ballGame.launch();
    }

}



