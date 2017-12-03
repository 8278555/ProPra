package Testobjekte;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Pfeiltesterpanel_bak extends JPanel {

	int x1;
	int y1;
	int x2;
	int y2;
	int x3;
	int y3;
	int x4;
	int y4;
	int x5;
	int y5;
	int m1;
	int m2;
	
		
	public Pfeiltesterpanel_bak() {
		x1 = 10;
		y1 = 10;
		x2 = 300;
		y2 = 100;
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawLine(g2);
    }

	public void drawLine(Graphics2D g2) {
		g2.drawLine(x1, y1, x2, y2);
		calcTrianglePoints();
//		g2.drawLine(x4, y4, x5, y5);
		g2.drawLine(x3, y3, 490, 400);
		//g2.drawArc(x2, y2, 100, 100, 45, 270);
		
		
	}
	public void calcTrianglePoints() {
		m1 = Math.abs(y1-y2);
		m2 = Math.abs(x1-x2);
		double alphahelp = 90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90);
		double neah = Math.toDegrees(Math.atan((m1/m2)));
		double alpha = Math.sqrt(alphahelp*alphahelp);
		double a = (Math.sin(alpha))*20;
		double b = (Math.cos(alpha))*20;
		double coeff = m1/m2;
		
//		double betahelp1 = Math.toDegrees(Math.atan2((double)(x1-x2), (y1-y2)))%90;
//		double beta1 = Math.sqrt(betahelp1*betahelp1);
//		double a1 = (Math.cos(beta1))*10;
//		double b1 = (Math.cos(90-beta1)*10);
		
		System.out.println("Alpha: " + alpha);
		System.out.println("A: " + a);
		System.out.println("B: " + b);
		System.out.println("Y-Dist M1: " + m1);
		System.out.println("X-Dist M2: " + m2);
		
		if (x1<x2) {
			x3 = (x2 - (int)Math.round(a));
		}
		else if (x1>x2) {
			x3 = (x2 + (int)Math.round(a));
		}
		else if (x1==x2) {
			x3 = x2;
		}
		if (y1<y2) {
			y3 = (y2 - (int)Math.round(a*coeff));
		}
		else if (y1>y2) {
			y3 = (y2 + (int)Math.round(a*coeff));
		}
		else if (y1==y2) {
			y3 = y2;
		}
		System.out.println("X3:" + x3);
		System.out.println("Y3:" + y3);

		
	}
}
