import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class MyPNMLParser extends PNMLParser{

    public static void main(final String[] args) {
        if (args.length >= 0) {
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
        }
    }

    /**
     * Dies ist eine Referenz zum Java Datei Objekt.
     */
    private File           pnmlDatei;

    /**
     * Dies ist eine Referenz zum XML Parser. Diese Referenz wird durch die
     * Methode parse() initialisiert.
     */
    private XMLEventReader xmlParser = null;

    /**
     * Diese Variable dient als Zwischenspeicher für die ID des zuletzt gefundenen Elements.
     */
    private String         lastId    = null;

    /**
     * Dieses Flag zeigt an, ob der Parser gerade innerhalb eines Token Elements liest.
     */
    private boolean        isToken   = false;

    /**
     * Dieses Flag zeigt an, ob der Parser gerade innerhalb eines Name Elements liest.
     */
    private boolean        isName    = false;

    /**
     * Dieses Flag zeigt an, ob der Parser gerade innerhalb eines Value Elements liest.
     */
    private boolean        isValue   = false;

    /**
     * Dieser Konstruktor erstellt einen neuen Parser für PNML Dateien,
     * dem die PNML Datei als Java {@link File} übergeben wird.
     * 
     * @param pnml
     *      Java {@link File} Objekt der PNML Datei
     */

    private WFEModelNet netz;
	
	public MyPNMLParser(final File pnml) {
		super(pnml);
		WFEModelNet netz = new WFEModelNet("Testname");
		this.pnmlDatei = pnml;
		
	}










    /**
     * Diese Methode kann überschrieben werden, um geladene Transitionen zu erstellen.
     * 
     * @param id
     *      Identifikationstext der Transition
     */
    public void newTransition(final String id) {
        //netz.addTransition(id);
    	IPetriNamedElements id1 = new WFEModelTransition();
        id1.SetID(id);
    	System.out.println("Transition mit id " + id1 + " wurde gefunden.");
    	System.out.println(id1.toString());
    }

    /**
     * Diese Methode kann überschrieben werden, um geladene Stellen zu erstellen.
     * 
     * @param id
     *      Identifikationstext der Stelle
     */
    public void newPlace(final String id) {
        IPetriNamedElements stelle = new WFEModelPlace();
        stelle.SetID(id);
        System.out.println("Stelle mit id " + id + " wurde gefunden.");
        System.out.println(stelle.toString());
    }

    /**
     * Diese Methode kann überschrieben werden, um geladene Kanten zu erstellen.
     * 
     * @param id
     *      Identifikationstext der Kante
     * @param source
     *      Identifikationstext des Startelements der Kante
     * @param target
     *      Identifikationstext des Endelements der Kante     
     */
    public void newArc(final String id, final String source, final String target) {
        IArc kante = new WFEModelArc();
        kante.SetID(id);
        kante.SetSource(source);
        kante.SetTarget(target);
    	System.out.println("Kante mit id " + id + " von " + source + " nach "
                + target + " wurde gefunden.");
    	System.out.println(kante.toString());
    }

    /**
     * Diese Methode kann überschrieben werden, um die Positionen der geladenen
     * Elemente zu aktualisieren.
     * 
     * @param id
     *      Identifikationstext des Elements
     * @param x
     *      x Position des Elements
     * @param y
     *      y Position des Elements
     */
    public void setPosition(final String id, final String x, final String y) {
        System.out.println("Setze die Position des Elements " + id + " auf ("
                + x + ", " + y + ")");
    }

    /**
     * Diese Methode kann überschrieben werden, um den Beschriftungstext der geladenen
     * Elemente zu aktualisieren.
     * 
     * @param id
     *      Identifikationstext des Elements
     * @param name
     *      Beschriftungstext des Elements
     */
    public void setName(final String id, final String name) {
        System.out.println("Setze den Namen des Elements " + id + " auf "
                + name);
    }

    /**
     * Diese Methode kann überschrieben werden, um die Markierung der geladenen
     * Elemente zu aktualisieren.
     * 
     * @param id
     *      Identifikationstext des Elements
     * @param marking
     *      Markierung des Elements
     */
    public void setMarking(final String id, final String marking) {
        System.out.println("Setze die Markierung des Elements " + id + " auf "
                + marking);
    }

}
