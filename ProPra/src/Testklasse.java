import java.io.File;

public class Testklasse {

    public static void main(final String[] args) {
        if (args.length >= 0) {
            //ToDo Try-Catch wegen File/Path not found
            // File pnmlOutDatei = new File("/home/bibabuzzel/ProPra/Beispiele/Eigene/Beispiel-03.pnml");
            File pnmlOutDatei = new File("C:\\Users\\daniel.brenner\\Documents\\Uni\\2017_2018_ws_aufgabenstellung\\Beispiele\\Eigene\\Beispiel-03.pnml");
            if (pnmlOutDatei.exists() == false) {
            	PNMLWriter pnmlWriter = new PNMLWriter(pnmlOutDatei);
            	final WFEModelNet petrinetz = new WFEModelNet("Testname");
            	petrinetz.addTransition("transition1");
            	petrinetz.addTransition("transition2");
            	petrinetz.addPlace("place1");
            	petrinetz.addPlace("place2");
            	petrinetz.addArc("arc1", "transition1", "place1");
            	petrinetz.addArc("arc2", "place1", "transition2");
            	petrinetz.addArc("arc3", "transition2", "place2");
            	petrinetz.addArc("arc4", "place2", "transition1");
            	petrinetz.setPosition("transition1", "200", "200");
            	petrinetz.setPosition("transition2", "200", "400");
            	petrinetz.setPosition("place1", "100", "300");
            	petrinetz.setPosition("place2", "300", "300");
            	petrinetz.setName("transition1", "Transition A");
            	petrinetz.setName("transition2", "Transition B");
            	petrinetz.setName("place1", "Stelle 1");
            	petrinetz.setName("place2", "Stelle 2");
            	petrinetz.setMarking("place1", "1");
            	petrinetz.setMarking("place2", "0");
            	
            	pnmlWriter.startXMLDocument();
            	

            	
            	for (int i = 0; i< petrinetz.getListSize(); i++) {
            		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
            			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
            			pnmlWriter.addTransition(transition.GetID(), transition.getName(), Integer.toString(transition.getPositionx()), Integer.toString(transition.getPositiony()));
            		}
            		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
            			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
            			pnmlWriter.addPlace(stelle.GetID(), stelle.getName(), Integer.toString(stelle.getPositionx()), Integer.toString(stelle.getPositiony()), stelle.GetToken());
            		}
            		if (petrinetz.petriElements.get(i) instanceof WFEModelArc) {
            			WFEModelArc kante = (WFEModelArc) petrinetz.petriElements.get(i);
            			pnmlWriter.addArc(kante.GetID(), kante.GetSource(), kante.GetTarget());
            		}
            	}
            	
                /**
            	pnmlWriter
                        .addTransition("transition1", "Transition A", "200", "200");
                pnmlWriter
                        .addTransition("transition2", "Transition B", "200", "400");

                pnmlWriter.addPlace("place1", "Stelle 1", "100", "300", "1");
                pnmlWriter.addPlace("place2", "Stelle 2", "300", "300", "0");

                pnmlWriter.addArc("arc1", "transition1", "place1");
                pnmlWriter.addArc("arc2", "place1", "transition2");
                pnmlWriter.addArc("arc3", "transition2", "place2");
                pnmlWriter.addArc("arc4", "place2", "transition1");
                
                */

                pnmlWriter.finishXMLDocument();

            } else {
            	System.out.println("Datei bereits vorhanden!");
            	}
        	} else {
            System.out.println("Bitte eine Datei als Parameter angeben!");
        }
    	
    		/**  if (args.length >= 0) {
            File pnmlDatei = new File("/home/bibabuzzel/ProPra/Beispiele/Beispiel-01.pnml");
            //File pnmlDatei = new File("C:\\Users\\daniel.brenner\\Documents\\Uni\\2017_2018_ws_aufgabenstellung\\Beispiele\\Beispiel-03.pnml");
            if (pnmlDatei.exists()) {
            	final WFEModelNet petrinetz = new WFEModelNet("Testname");
                PNMLParser pnmlParser = new MyPNMLParser(pnmlDatei, petrinetz);
                pnmlParser.initParser();
                pnmlParser.parse();
            	for (int i = 0; i<petrinetz.petriElements.size()-1;i++) {
            		IPetriElements test = petrinetz.petriElements.get(i);
            		System.out.println(test.toString());
            	}
            } 
            else {
                System.err.println("Die Datei " + pnmlDatei.getAbsolutePath()
                        + " wurde nicht gefunden!");
            }
    	} 
    	    else {
            System.out.println("Bitte eine Datei als Parameter angeben!");
    	}*/

    
    	/**
    	String id = "S5";
    	WFEModelNet petrinetz = new WFEModelNet("Testname");
    	netz.addTransition(id);
    	*/
    }
}


