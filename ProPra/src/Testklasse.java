import java.io.File;

public class Testklasse {

    public static void main(final String[] args) {
    	    if (args.length >= 0) {
            //File pnmlDatei = new File("/home/bibabuzzel/ProPra/Beispiele/Beispiel-01.pnml");
            File pnmlDatei = new File("C:\\Users\\daniel.brenner\\Documents\\Uni\\2017_2018_ws_aufgabenstellung\\Beispiele\\Beispiel-03.pnml");
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
    	}
    	/**
    	String id = "S5";
    	WFEModelNet petrinetz = new WFEModelNet("Testname");
    	netz.addTransition(id);
    	*/
    }
}


