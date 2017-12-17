package Testobjekte;

import java.awt.Polygon;

public class Pfeildreieck {

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
	
	public Pfeildreieck(int x12, int y12, int x22, int y22, int elemsizefactor) {
		this.x1 = x12;
		this.y1 = y12;
		this.x2 = x22;
		this.y2 = y22;
		this.factor=elemsizefactor;
	}

	public Polygon Pfeilspitze() {
		calcTrianglePoints();
		int xPoints[] = {x2, x4, x5};
		int yPoints[]= {y2, y4, y5};
		System.out.println("Pfeilx1: " + x1);
        System.out.println("Pfeily1: " + y1);
        System.out.println("Pfeilx2: " + x2);
		System.out.println("Pfeily2: " + y2);
		Polygon p = new Polygon(xPoints, yPoints, 3);
		return p;
	}
	
	public void calcTrianglePoints() {

		double alpha = 90-(Math.sqrt((90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90))*(90-(Math.toDegrees(Math.atan2((double)(y1-y2), (x1-x2)))%90)))%90);
        double sinalpha = Math.abs(Math.sin(Math.toRadians(alpha)));
        double cosalpha = Math.abs(Math.cos(Math.toRadians(alpha)));
        double sindelta = Math.abs(Math.sin(Math.toRadians(90-alpha)));
        double cosdelta = Math.abs(Math.cos(Math.toRadians(90-alpha)));
        double distA = (sinalpha)*factor;
        double distB = (cosalpha)*factor;
        double distE = (sindelta)*(factor/2);
        double distF = (cosdelta)*(factor/2);
		double a = (double)(y2-y1)/(double)(x2-x1);
		double b = (double)y2-(a*(double)x2);
		
			if (x1<x2) {
				//x2=x2-factor/2;
				if (y1<y2) {
					//y2=y2-factor/2;
					doubleX3 = ((double)x2 - distB);
					doubleY3 = a*doubleX3 + b;
					doubleX4 = doubleX3 + distF;
					doubleY4 = doubleY3 - (1/a*distF);
					doubleX5 = doubleX3 - distF;
					doubleY5 = doubleY3 + (1/a*distF);
				}
				else if (y1>y2) {
					//y2=y2+factor/2;
				    doubleX3 = ((double)x2 - distA);
                    doubleY3 = a*doubleX3 + b;
                    doubleX4 = doubleX3 + distE;
                    doubleY4 = doubleY3 - (1/a*distE);
                    doubleX5 = doubleX3 - distE;
                    doubleY5 = doubleY3 + (1/a*distE);
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
				//x2=x2+factor/2;
				if (y1<y2) {
					//y2=y2-factor/2;
                    doubleX3 = ((double)x2 + distA);
                    doubleY3 = a*doubleX3 + b;
                    doubleX4 = doubleX3 + distE;
                    doubleY4 = doubleY3 - (1/a*distE);
                    doubleX5 = doubleX3 - distE;
                    doubleY5 = doubleY3 + (1/a*distE);
				}
				else if (y1>y2) {
					//y2=y2+factor/2;
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
