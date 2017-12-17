package Release;
import java.io.File;
public class MyPNMLParser extends PNMLParser{



    /**
     * Dies ist eine Referenz zum Java Datei Objekt.
     */
    private File pnmlDatei;
    WFEModelNet petrinetz;
    
    /**
     * Dieser Konstruktor erstellt einen neuen Parser für PNML Dateien,
     * dem die PNML Datei als Java {@link File} übergeben wird.
     * 
     * @param pnml
     *      Java {@link File} Objekt der PNML Datei
     */

	
	public MyPNMLParser(final File pnml, WFEModelNet petrinetz) {
		super(pnml);
		this.pnmlDatei = pnml;
		this.petrinetz = petrinetz;
	}




    /**
     * Diese Methode kann überschrieben werden, um geladene Transitionen zu erstellen.
     * 
     * @param id
     *      Identifikationstext der Transition
     */
    @Override
    public void newTransition(final String id) {
        petrinetz.addTransition(id);
    }

    /**
     * Diese Methode kann überschrieben werden, um geladene Stellen zu erstellen.
     * 
     * @param id
     *      Identifikationstext der Stelle
     */
    @Override
    public void newPlace(final String id) {
        petrinetz.addPlace(id);
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
    @Override
    public void newArc(final String id, final String source, final String target) {
        petrinetz.addArc(id, source, target);
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
    @Override
    public void setPosition(final String id, final String x, final String y) {
        petrinetz.setPosition(id, x, y);
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
    @Override
    public void setName(final String id, final String name) {
    	petrinetz.setName(id, name);
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
    @Override
    public void setMarking(final String id, final String marking) {
    	petrinetz.setMarking(id, marking);
    }

}
