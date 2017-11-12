
public interface IPetriNamedElements extends IPetriElements {
	public void SetName(String name);
	String GetName();
	public void SetPosition(int x,int y);
    public IPetriPosition GetPosition();

}
