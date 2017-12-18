package Release;

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
	int x6;
	int y6;
	int factor;
	double d;
	double e;
	double doubleX3;
	double doubleY3;
	double doubleX4;
	double doubleY4;
	double doubleX5;
	double doubleY5;
	double doubleX6;
	double doubleY6;
	
	public Pfeildreieck(int x12, int y12, int x22, int y22, int elemsizefactor) {
		this.x1 = x12;
		this.y1 = y12;
		this.x2 = x22;
		this.y2 = y22;
		this.factor=elemsizefactor;
	}

	public Polygon Pfeilspitze() {
		calcTrianglePoints();
		int xPoints[] = {x3, x5, x6};
		int yPoints[]= {y3, y5, y6};
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
		
		if (Math.abs(x1-x2)<Math.abs(y1-y2)) {
		    if (y1<y2) {
		        doubleY3 = (double)y2-factor/2;
		        doubleX3 = (doubleY3-b)/a;
		    }
		    else if (y1>y2) {
                doubleY3 = (double)y2+factor/2;
                doubleX3 = (doubleY3-b)/a;		        
		    }
		}
		else if (Math.abs(x1-x2)>Math.abs(y1-y2)) {
		    if (x1<x2) {
		        doubleX3 = (double)x2-factor/2;
		        doubleY3 = a*doubleX3 + b;
		    }
		    else if (x1>x2) {
		        doubleX3 = (double)x2+factor/2;
		        doubleY3 = a*doubleX3 + b;
		    }
		}
		
			if (x1<x2) {
				//x2=x2-factor/2;
				if (y1<y2) {
					//y2=y2-factor/2;
					doubleX4 = (doubleX3 - distB);
					doubleY4 = a*doubleX4 + b;
					doubleX5 = doubleX4 + distF;
					doubleY5 = doubleY4 - (1/a*distF);
					doubleX6 = doubleX4 - distF;
					doubleY6 = doubleY4 + (1/a*distF);
				}
				else if (y1>y2) {
					//y2=y2+factor/2;
				    doubleX4 = (doubleX3 - distA);
                    doubleY4 = a*doubleX4 + b;
                    doubleX5 = doubleX4 + distE;
                    doubleY5 = doubleY4 - (1/a*distE);
                    doubleX6 = doubleX4 - distE;
                    doubleY6 = doubleY4 + (1/a*distE);
                    }
				else if (y1==y2) {
					doubleX4 = (doubleX3 - factor);
					doubleY4 = doubleY3;
					doubleX5 = doubleX4;
					doubleY5 = doubleY4 - (factor/2);
					doubleX6 = doubleX4;
					doubleY6 = doubleY4 + (factor/2);
				}
			}
			else if (x1>x2) {
				//x2=x2+factor/2;
				if (y1<y2) {
					//y2=y2-factor/2;
                    doubleX4 = (doubleX3 + distA);
                    doubleY4 = a*doubleX4 + b;
                    doubleX5 = doubleX4 + distE;
                    doubleY5 = doubleY4 - (1/a*distE);
                    doubleX6 = doubleX4 - distE;
                    doubleY6 = doubleY4 + (1/a*distE);
				}
				else if (y1>y2) {
					//y2=y2+factor/2;
					doubleX4 = (doubleX3 + distB);
					doubleY4 = a*doubleX4 + b;
					doubleX5 = doubleX4 + distF;
					doubleY5 = doubleY4 - (1/a*distF);
					doubleX6 = doubleX4 - distF;
					doubleY6 = doubleY4 + (1/a*distF);

				}
				else if (y1==y2) {
					doubleX4 = (doubleX3 + factor);
					doubleY4 = doubleY3;
					doubleX5 = doubleX4;
					doubleY5 = doubleY4 - (factor/2);
					doubleX6 = doubleX4;
					doubleY6 = doubleY4 + (factor/2);
				}
			}
			else if (x1==x2) {
				if (y1<y2) {
					doubleX4 = doubleX3;
					doubleY4 = doubleY3 - factor;
					doubleX5 = doubleX4 + factor/2;
					doubleY5 = doubleY4;
					doubleX6 = doubleX4 - factor/2;
					doubleY6 = doubleY4;
				}
				else if (y1>y2) {
					doubleX4 = doubleX3;
					doubleY4 = doubleY3 + factor;
					doubleX5 = doubleX4 + factor/2;
					doubleY5 = doubleY4;
					doubleX6 = doubleX4 - factor/2;
					doubleY6 = doubleY4;

				}
				else if (y1==y2) {
					doubleX4 = doubleX3;
					doubleY4 = doubleY3;
					doubleX5 = doubleX4;
					doubleY5 = doubleY4;
					doubleX6 = doubleX4;
					doubleY6 = doubleY4;
				}
			}
            x3 = (int)Math.round(doubleX3);
            y3 = (int)Math.round(doubleY3);
            x4 = (int)Math.round(doubleX4);
            y4 = (int)Math.round(doubleY4);
			x5 = (int)Math.round(doubleX5);
			y5 = (int)Math.round(doubleY5);
			x6 = (int)Math.round(doubleX6);
			y6 = (int)Math.round(doubleY6);
			}


}
