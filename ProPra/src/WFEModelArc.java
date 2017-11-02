
public class WFEModelArc implements IArc {
	String id;
	String source;
	String target;
	
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
	public void SetSource(String source) {
		// TODO Auto-generated method stub
		this.source=source;
	}

	@Override
	public String GetSource() {
		// TODO Auto-generated method stub
		return source;
	}

	@Override
	public void SetTarget(String target) {
		// TODO Auto-generated method stub
		this.target=target;
	}

	@Override
	public String GetTarget() {
		// TODO Auto-generated method stub
		return target;
	}
	@Override
    public String toString() {
		return ("Das ist die Kante " + id + " mit der Quelle:" + source + " und dem Ziel:" + target);
	}

	
}
