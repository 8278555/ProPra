import java.util.ArrayList;

public class WFEModelNet {
	WFEModelArc kante;
	WFEModelTransition transition;
	WFEModelPlace stelle;
	ArrayList<IPetriElements> petriElements;
	String filename;
	int i;
	//String id;
	WFEModelNet(String filename) {
		petriElements = new ArrayList<IPetriElements>();
	}
	
	public int getListSize() {
		return petriElements.size();
	}
		
	public void addTransition(String id) {
		transition = new WFEModelTransition(id);
		transition.SetID(id);
		petriElements.add(transition);
		}
		
	
	public void addPlace(String id) {
		stelle = new WFEModelPlace(id);
		petriElements.add(stelle);
	}
	public void addArc(String id, String source, String target) {
		kante = new WFEModelArc();
		kante.SetID(id);
		kante.SetSource(source);
		kante.SetTarget(target);
		petriElements.add(kante);
	}
	public void setPosition(String id, String x, String y) {
		for (i=0; i<(petriElements.size()); i++) {
			if (id == (petriElements.get(i).GetID())) {
				IPetriNamedElements possetter = (IPetriNamedElements) petriElements.get(i);
				possetter.setPosition(Integer.parseInt(x),Integer.parseInt(y));
			}
		}		
	}
	public void setName(String id, String name) {
		for (i=0; i<(petriElements.size()); i++) {
			if (id == (petriElements.get(i).GetID())) {
				IPetriNamedElements namesetter = (IPetriNamedElements) petriElements.get(i);
				namesetter.setName(name);		
			}
		}		
	}
	
	public void setMarking(String id, String marking) {
		//ToDo
		for (i=0; i<(petriElements.size()); i++) {
			if (id == (petriElements.get(i).GetID())) {
				IPlace placesetter = (IPlace) petriElements.get(i);
				placesetter.SetToken(marking);
			}
		}
	}
}
