package Testobjekte;
import java.io.File;
import Release.*;

public class Fenstertester {
	
	public Fenstertester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
        File pnmlDatei = new File("/home/bibabuzzel/ProPra/Beispiele/Beispiel-0001.pnml");
        //File pnmlDatei = new File("C:\\Users\\daniel.brenner\\Documents\\Uni\\2017_2018_ws_aufgabenstellung\\Beispiele\\Beispiel-01.pnml");
        if (pnmlDatei.exists()) {
        	final WFEModelNet petrinetz = new WFEModelNet();
            PNMLParser pnmlParser = new MyPNMLParser(pnmlDatei, petrinetz);
            pnmlParser.initParser();
            pnmlParser.parse();
        	WFEPanel testpanel = new WFEPanel(petrinetz);
    		WFEMainWindow testfenster = new WFEMainWindow("Fenstertext", testpanel, petrinetz);
    		testfenster.setVisible(true);
    	
        } 
        else {
        	WFEMainWindow testfenster = new WFEMainWindow("Daniel Brenner, Mat-Nr: 8278555");
        	testfenster.setVisible(true);
//            System.err.println("Die Datei " + pnmlDatei.getAbsolutePath()
//                    + " wurde nicht gefunden!");
        }
		
	}

}
