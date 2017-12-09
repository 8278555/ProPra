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
	double doubleX3;
	double doubleY3;
	double doubleX4;
	double doubleY4;
	double doubleX5;
	double doubleY5;
	int factor;
	double d;
	double e;

		
	public Pfeiltesterpanel() {
		x1 = 0;
		y1 = 0;
		x2 = 300;
		y2 = 300;
		factor = 20;
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawLine(g2);
    }

	public void drawLine(Graphics2D g2) {
		g2.drawLine(x1, y1, x2, y2);
		calcTrianglePoints();
		g2.drawLine(x3, y3, x4, y4);
		g2.drawLine(x4, y4, x2, y2);
		
	}
	public void calcTrianglePoints() {
//		m1 = Math.abs((double)y1-(double)y2);
//		m2 = Math.abs((double)x1-(double)x2);
		double alpha = 90-(Math.sqrt((90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90))*(90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90)))%90);
		double sinalpha = Math.abs(Math.sin(alpha));
		double delta = 90-alpha;
		double sindelta = Math.abs(Math.sin(delta));
		double distA = (sinalpha)*factor;
		double distB = Math.sqrt((factor*factor)-(distA*distA));
		double distE = sindelta*(factor/2);
		double distF = Math.sqrt(((factor/2)*(factor/2))-(distE*distE));
		double a = (double)(y2-y1)/(double)(x2-x1);
		double b = (double)y2-(a*(double)x2);
		
		if (alpha<=45) {
			if (x1<x2) {			
				doubleX3 = ((double)x2 - distB);
				doubleX4 = doubleX3 + distF;
				doubleX5 = doubleX3 - distF;
			}
			else if (x1>x2) {
				doubleX3 = ((double)x2 + distB);
				doubleX4 = doubleX3 - distF;
				doubleX5 = doubleX3 + distF;

			}
			else if (x1==x2) {
				doubleX3 = x2;
				a=1;
				b=-20;
			}
			d = (doubleY3-(double)y2)/(doubleX3-(double)x2);
			e = doubleY3 - (d*doubleX3);
			x3 = (int)(Math.round(doubleX3));
			y3 = (int)(Math.round(a*doubleX3+b));
			x4 = (int)(Math.round(doubleX4));
			y4 = (int)(Math.round(doubleX3-(doubleX4-doubleX3)));
//			y4 = (int)(Math.round(doubleY3+distE));
//			y4=283;
			x5 = (int)(Math.round(doubleX5));
			y5 = (int)(Math.round(d*doubleX5+e));
		}
		
		if (alpha>45) {
			if (y1<y2) {			
				doubleY3 = ((double)y2 - distB);
				doubleY4 = doubleY3 + distF;
				doubleY5 = doubleY3 - distF;
			}
			else if (y1>y2) {
				doubleY3 = ((double)y2 + distB);
				doubleY4 = doubleY3 - distF;
				doubleY5 = doubleY3 + distF;
			}
			else if (y1==y2) {
				y3 = y2;
				a=1;
				b=20;
			}
			d = (doubleY3-(double)y2)/(doubleX3-(double)x2);
			e = doubleY3 - (d*doubleX3);
			x3 = (int)(Math.round((doubleY3-b)/a));
			y3 = (int)(Math.round(doubleY3));
			x4 = (int)(Math.round((doubleY4-e)/d));
			y4 = (int)(Math.round(doubleY4));
			x5 = (int)(Math.round((doubleY5-e)/d));
			y5 = (int)(Math.round(doubleY5));
		}
//		System.out.println("X1: " + x1);
//		System.out.println("Y1: " + y1);
//		System.out.println("X2: " + x2);
//		System.out.println("Y2: " + y2);
		System.out.println("X3: " + x3);
		System.out.println("Y3: " + y3);
		System.out.println("X4: " + x4);
		System.out.println("Y4: " + y4);
//		System.out.println("X5: " + x5);
//		System.out.println("Y5: " + y5);
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("D: " + d);
		System.out.println("E: " + e);
		System.out.println("Delta: " + delta);
		System.out.println("Alpha: " + alpha);
		System.out.println("DistE: " + distE);
		System.out.println("DistF: " + distF);
	}
}
