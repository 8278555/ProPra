
public class WFEModelNet {
	WFEModelArc[] kanten;
	WFEModelTransition[] transitionen;
	WFEModelPlace[] stellen;
	String filename;
	int i;
	String id;
	WFEModelNet(String filename) {
		this.filename=filename;
	}
	
	public void addTransition(String id) {
		//TBD
		//Wenn array nicht da, erstellen Größe 1, id eintragen
		//Wenn array da, prüfen ob keine leere stelle vorhanden, dann vergrößern
		//In erste freie stelle ID eintragen
		this.id = id;
		transitionen = new WFEModelTransition[15];

				//transitionen[1].id=id;
				transitionen[0].SetID(id);;
				System.out.println(kanten[i].id);
				System.out.println("Hello World");
			}
		
	
	public void addPlace(String id) {
		//TBD
		//Wenn array nicht da, erstellen Größe 1, id eintragen
		//Wenn array da, prüfen ob keine leere stelle vorhanden, dann vergrößern
		//In erste freie stelle ID eintragen
	}
	public void addArc(String id) {
		//TBD
		//Wenn array nicht da, erstellen Größe 1, id eintragen
		//Wenn array da, prüfen ob keine leere stelle vorhanden, dann vergrößern
		//In erste freie stelle ID eintragen
	}
}
