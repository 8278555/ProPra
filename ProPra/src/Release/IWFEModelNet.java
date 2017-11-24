package Release;

public interface IWFEModelNet {
	
	public int getListSize();
	public void addTransition(String id);
	public void addPlace(String id);
	public void addArc(String id, String source, String target);
	public void setPosition(String id, String x, String y);
	public void setName(String id, String name);
	public void setMarking(String id, String marking);
}
