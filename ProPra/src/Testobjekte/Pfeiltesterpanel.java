package Testobjekte;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Pfeiltesterpanel extends JPanel {

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
	double m1;
	double m2;
	
		
	public Pfeiltesterpanel() {
		x1 = 10;
		y1 = 10;
		x2 = 10;
		y2 = 300;
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
		m1 = (double)Math.abs(y1-y2);
		m2 = (double)Math.abs(x1-x2);
		double alphahelp = 90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90);
		double alpha = Math.sqrt(alphahelp*alphahelp);
		double sinalpha;
		if (Math.sin(alpha)<0) {
			sinalpha = (Math.sin(alpha) * (-1));
		}
		else {sinalpha = Math.sin(alpha);}
		double a = (sinalpha)*50;
		double b = (Math.cos(alpha))*100;
		double coeff = (m1 / m2);
		
//		double betahelp1 = Math.toDegrees(Math.atan2((double)(x1-x2), (y1-y2)))%90;
//		double beta1 = Math.sqrt(betahelp1*betahelp1);
//		double a1 = (Math.cos(beta1))*10;
//		double b1 = (Math.cos(90-beta1)*10);
		
		System.out.println("Alpha: " + alpha);
		System.out.println("Sin Alpha: " + sinalpha);
		System.out.println("A: " + a);
		System.out.println("B: " + b);
		System.out.println("Y-Dist M1: " + m1);
		System.out.println("X-Dist M2: " + m2);
		System.out.println("Coeff: " + coeff);
		
		if (x1<x2) {
			x3 = (int)Math.round((double)x2 - a);
		}
		else if (x1>x2) {
			x3 = (int)Math.round((double)x2 + a);
		}
		else if (x1==x2) {
			x3 = x2;
		}

		
		if (y1<y2) {
			y3 = (int)Math.round((double)x3 * coeff);
		}
		else if (y1>y2) {
			y3 = (x3 * (int)Math.round(coeff));
		}
		else if (y1==y2) {
			y3 = (x3 * (int)Math.round(coeff));
		}
		System.out.println("X3:" + x3);
		System.out.println("Y3:" + y3);

		
	}
}
