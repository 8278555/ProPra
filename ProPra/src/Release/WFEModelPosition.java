package Release;

public class WFEModelPosition implements IPetriPosition{
    int x;
    int y;
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    public IPetriPosition getPosition() {
    	return this;
    }
    public int getPositionx() {
    	return x;
    }
    public int getPositiony() {
    	return y;
    }
    public String toString() {
    	return Integer.toString(x) + Integer.toString(y);
    }
}
