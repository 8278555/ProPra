
public class WFEModelTransition implements IPetriNamedElements {
	public String id;
	String name;
	int positionx;
	int positiony;

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
		this.name=name;

	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void SetPositionx(int x) {
		// TODO Auto-generated method stub
		this.positionx=x;
	}

	@Override
	public int GetPositionx() {
		// TODO Auto-generated method stub
		return positionx;
	}

	@Override
	public void SetPositiony(int y) {
		// TODO Auto-generated method stub
		this.positiony=y;
	}

	@Override
	public int GetPositiony() {
		// TODO Auto-generated method stub
		return positiony;
	}
	
	public String toString() {
		return ("Das ist die Transition " + name + " mit der id " + id + " und der Position x:" + positionx + " y:" + positiony);
	}
}
