package com.company;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        JPanel jPanel = new JPanel();

	    LabFrame myFrame = new LabFrame();
        myFrame.setVisible(true);

	    MyBalls myBalls = new MyBalls(jPanel, myFrame);
	    myFrame.getContentPane().add(myBalls);
	    Thread newThread = new Thread(myBalls);


        MyBalls myBalls2 = new MyBalls(jPanel, myFrame);
        myFrame.getContentPane().add(myBalls2);
        Thread newThread2 = new Thread(myBalls2);

        newThread.start();
        newThread2.start();
    }
}

class LabFrame extends JFrame{
    LabFrame(){
        super("Лабораторная работа №3.3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,400);
    }
}

class MyBalls extends JComponent implements Runnable{
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
    }

    @Override
    public void run() {
        while (true) {
            this.x = this.x + 1;
            try {
                sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
