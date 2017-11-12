
public class WFEModelTransition implements IPetriNamedElements {
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
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setName(String name) {
		this.name.setname(name);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name.getname();
	}

	@Override
	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.graphics.SetPosition(x, y);
	}

	@Override
	public IPetriPosition getPosition() {
		// TODO Auto-generated method stub
		return this.graphics.GetPosition();
	}
	public int getPositionx() {
		return this.graphics.getPositionx();
	}
	public int getPositiony() {
		return this.graphics.getPositiony();
	}


	
//	public String toString() {
//		return ("Das ist die Transition " + name + " mit der id " + id + " und der Position x:" + positionx + " y:" + positiony);
//	}
}
