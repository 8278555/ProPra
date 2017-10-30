
public class WFEModelPlace implements IPlace {
	String id;
	String name;
	int token;
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
	public void SetToken(int token) {
		this.token=token;

	}

	@Override
	public int GetToken() {
		
		return token;
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
		return ("Das ist die Stelle " + name + " mit der id " + id + ", dem Token " + token + " und der Position x:" + positionx + " y:" + positiony);
	}

}
