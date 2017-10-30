import java.io.File;

public class Testklasse {

    public static void main(final String[] args) {
    	/**    if (args.length >= 0) {
            File pnmlDatei = new File("/home/bibabuzzel/ProPra/Beispiele/Beispiel-01.pnml");
            if (pnmlDatei.exists()) {
                PNMLParser pnmlParser = new MyPNMLParser(pnmlDatei);
                pnmlParser.initParser();
                pnmlParser.parse();
            } else {
                System.err.println("Die Datei " + pnmlDatei.getAbsolutePath()
                        + " wurde nicht gefunden!");
            }
        } else {
            System.out.println("Bitte eine Datei als Parameter angeben!");
        }*/
    	String id = "S5";
    	WFEModelNet netz = new WFEModelNet("Testname");
    	netz.addTransition(id);
    }
	}


