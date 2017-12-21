package Release;

public class WFEModelPosition implements IPetriPosition{
    int x;
    int y;
    @Override
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    @Override
    public IPetriPosition getPosition() {
    	return this;
    }
    @Override
    public int getPositionx() {
    	return x;
    }
    @Override
    public int getPositiony() {
    	return y;
    }
    @Override
    public String toString() {
    	return Integer.toString(x) + Integer.toString(y);
    }
}
