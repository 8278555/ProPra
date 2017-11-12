
public interface IPetriNamedElements extends IPetriElements {
	public void setName(String name);
	String getName();
	public void setPosition(int x,int y);
    public IPetriPosition getPosition();
    public int getPositionx();
    public int getPositiony();
}
