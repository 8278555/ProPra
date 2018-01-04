package Release;

public class WFEModelTransition implements ITransition {
	public String id;
	WFEModelName name;
	WFEModelGraphics graphics;

	public WFEModelTransition(String id){
		this.id=id;
		name = new WFEModelName();
		graphics = new WFEModelGraphics();
	}
	
	public void SetID(String id) {
		this.id=id;
	}

	@Override
	public String GetID() {
		return id;
	}

	@Override
	public void setName(String name) {
		this.name.setname(name);
	}

	@Override
	public String getName() {
		return this.name.getname();
	}

	@Override
	public void setPosition(int x, int y) {
		this.graphics.SetPosition(x, y);
	}

	@Override
	public IPetriPosition getPosition() {
		return this.graphics.GetPosition();
	}
	public int getPositionx() {
		return this.graphics.getPositionx();
	}
	public int getPositiony() {
		return this.graphics.getPositiony();
	}	
}
