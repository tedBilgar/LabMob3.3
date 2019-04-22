package com.company;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        JPanel jPanel = new JPanel();

	    LabFrame myFrame = new LabFrame();
        myFrame.setVisible(true);

        myFrame.getContentPane().add(jPanel);

        for(int i=0;i<3;i++) {
            new MyBalls(jPanel, myFrame).start();
        }
    }
}

class LabFrame extends JFrame{
    LabFrame(){
        super("Лабораторная работа №3.3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,400);
    }
}

class MyBalls extends Thread{
    private JPanel panel;
    private int x;
    private int y;
    private int xDir=1;
    private int yDir=1;
    private int windowWidth;
    private int windowHeight;

    public MyBalls(JPanel panel, LabFrame labFrame) {
        this.panel = panel;
        this.windowWidth = labFrame.getWidth();
        this.windowHeight = labFrame.getHeight();
        this.x = 0+ (int) (Math.random() * this.windowWidth);
        this.y = 0+ (int) (Math.random() * this.windowHeight);
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(this.x,this.y,50,50);
        try {
            sleep(10);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        g.setColor(panel.getBackground());
        g.fillOval(this.x,this.y,50,50);
    }

    @Override
    public void run() {
        while (true) {
            if (this.x >= windowWidth - 50) xDir = -1;
            if(this.x <= 0 + 50) xDir = 1;
            if(this.y >= windowHeight - 50) yDir = -1;
            if(this.y <= 0 + 50) yDir = 1;

            this.x = this.x + xDir;
            this.y = this.y + yDir;
            paint(panel.getGraphics());
        }
    }
}
