
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
	public void SetName(String name) {
		this.name.setname(name);
	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return this.GetName();
	}

	@Override
	public void SetPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.graphics.SetPosition(x, y);
	}

	@Override
	public IPetriPosition GetPosition() {
		// TODO Auto-generated method stub
		return this.graphics.GetPosition();
	}


	
//	public String toString() {
//		return ("Das ist die Transition " + name + " mit der id " + id + " und der Position x:" + positionx + " y:" + positiony);
//	}
}
