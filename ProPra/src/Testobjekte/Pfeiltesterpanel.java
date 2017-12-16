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
	int factor;
	double d;
	double e;
	double doubleX3;
	double doubleY3;
	double doubleX4;
	double doubleY4;
	double doubleX5;
	double doubleY5;

		
	public Pfeiltesterpanel() {
		x1 = 300;
		y1 = 300;
		x2 = 300;
		y2 = 100;
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
		int xPoints[] = {x2, x4, x5};
		int yPoints[]= {y2, y4, y5};
		g2.fillPolygon(xPoints, yPoints, 3);

		
	}
	public void calcTrianglePoints() {

		double alpha = 90-(Math.sqrt((90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90))*(90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90)))%90);
		double sinalpha = Math.abs(Math.sin(Math.toRadians(alpha)));
		double cosdelta = Math.abs(Math.cos(Math.toRadians(90-alpha)));
		double distB = Math.sqrt((factor*factor)-((sinalpha)*factor*(sinalpha)*factor));
		double distF = (cosdelta)*(factor/2);
		double a = (double)(y2-y1)/(double)(x2-x1);
		double b = (double)y2-(a*(double)x2);
		
			if (x1<x2) {
				if (y1<y2) {
					doubleX3 = ((double)x2 - distB);
					doubleY3 = a*doubleX3 + b;
					doubleX4 = doubleX3 + distF;
					doubleY4 = doubleY3 - (1/a*distF);
					doubleX5 = doubleX3 - distF;
					doubleY5 = doubleY3 + (1/a*distF);
				}
				else if (y1>y2) {
					doubleX3 = ((double)x2 - distB);
					doubleY3 = a*doubleX3 + b;
					doubleX4 = doubleX3 + distF;
					doubleY4 = doubleY3 - (1/a*distF);
					doubleX5 = doubleX3 - distF;
					doubleY5 = doubleY3 + (1/a*distF);
				}
				else if (y1==y2) {
					doubleX3 = ((double)x2 - factor);
					doubleY3 = (double)y2;
					doubleX4 = doubleX3;
					doubleY4 = doubleY3 - (factor/2);
					doubleX5 = doubleX3;
					doubleY5 = doubleY3 + (factor/2);
				}
			}
			else if (x1>x2) {
				if (y1<y2) {
					doubleX3 = ((double)x2 + distB);
					doubleY3 = a*doubleX3 + b;
					doubleX4 = doubleX3 + distF;
					doubleY4 = doubleY3 - (1/a*distF);
					doubleX5 = doubleX3 - distF;
					doubleY5 = doubleY3 + (1/a*distF);
				}
				else if (y1>y2) {
					doubleX3 = ((double)x2 + distB);
					doubleY3 = a*doubleX3 + b;
					doubleX4 = doubleX3 + distF;
					doubleY4 = doubleY3 - (1/a*distF);
					doubleX5 = doubleX3 - distF;
					doubleY5 = doubleY3 + (1/a*distF);

				}
				else if (y1==y2) {
					doubleX3 = ((double)x2 + factor);
					doubleY3 = (double)y2;
					doubleX4 = doubleX3;
					doubleY4 = doubleY3 - (factor/2);
					doubleX5 = doubleX3;
					doubleY5 = doubleY3 + (factor/2);
				}
			}
			else if (x1==x2) {
				if (y1<y2) {
					doubleX3 = (double)x2;
					doubleY3 = (double)y2 - factor;
					doubleX4 = doubleX3 + factor/2;
					doubleY4 = doubleY3;
					doubleX5 = doubleX3 - factor/2;
					doubleY5 = doubleY3;
				}
				else if (y1>y2) {
					doubleX3 = (double)x2;
					doubleY3 = (double)y2 + factor;
					doubleX4 = doubleX3 + factor/2;
					doubleY4 = doubleY3;
					doubleX5 = doubleX3 - factor/2;
					doubleY5 = doubleY3;

				}
				else if (y1==y2) {
					doubleX3 = (double)x2;
					doubleY3 = (double)y2;
					doubleX4 = doubleX3;
					doubleY4 = doubleY3;
					doubleX5 = doubleX3;
					doubleY5 = doubleY3;
				}
			}
			x3 = (int)Math.round(doubleX3);
			y3 = (int)Math.round(doubleY3);
			x4 = (int)Math.round(doubleX4);
			y4 = (int)Math.round(doubleY4);
			x5 = (int)Math.round(doubleX5);
			y5 = (int)Math.round(doubleY5);
			}
}
