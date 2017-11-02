
public class WFEModelPlace implements IPlace {
	String id;
	WFEModelName name;
	WFEModelInitialMarking initialmarking;
	WFEModelGraphics graphics;
	
	public WFEModelPlace(String id) {
	    this.id = id;
	    name = new WFEModelName();
	    initialmarking = new WFEModelInitialMarking();
	    graphics = new WFEModelGraphics();
	}
	
	@Override
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
		return this.name.getname();
	}

    public void SetToken(String token) {
		this.initialmarking.settoken(token);
	}

	@Override
	public String GetToken() {		
		return this.initialmarking.gettoken();
	}

	@Override
	public void SetPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.graphics.SetPosition(x, y);;
	}

	@Override
	public int GetPosition() {
		// TODO Auto-generated method stub
		return this.graphics.GetPosition();
	}

//	public String toString() {
//		return ("Das ist die Stelle " + name + " mit der id " + id + ", dem Token " + token + " und der Position x:" + positionx + " y:" + positiony);
//	}

}
