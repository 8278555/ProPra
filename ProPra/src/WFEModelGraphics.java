
public class WFEModelGraphics {
    WFEModelPosition position;
    public WFEModelGraphics() {
        position = new WFEModelPosition();
    }
    public void SetPosition(int x, int y) {
        position.setPosition(x, y);
    }
    public IPetriPosition  GetPosition() {
        return position.getPosition();
    }
    public int getPositionx() {
    	return position.getPositionx();
    }
    public int getPositiony() {
    	return position.getPositiony();
    }
}
