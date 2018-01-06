/**
 * 
 */
package Testobjekte;

import java.util.ArrayList;

import Release.*;

/**
 * @author bibabuzzel
 *
 */
public class NetzTester {
	WFEModelNet petrinetz;
	WFEPanel panel;
	boolean validNet;
	private ArrayList<IPetriElements> lonelyElements;
	
	
	public NetzTester() {
		
	}

	public boolean isValidNet(WFEModelNet petrinetz, WFEPanel panel) {
		checkNet(petrinetz, panel);
		return validNet;
	}

	public void setValidNet(boolean validNet) {
		this.validNet = validNet;
	}

	private void checkNet(WFEModelNet petrinetz, WFEPanel panel) {
		lonelyElements = new ArrayList<IPetriElements>(petrinetz.petriElements);
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IArc) {
				IArc currArc = (IArc) petrinetz.petriElements.get(i);
				boolean containsElems = true;
				int j = 0;				
				while (containsElems) {
					containsElems = false;
					for (int n = 0; n < lonelyElements.size(); n++) {
						if (lonelyElements.get(n) instanceof IPetriNamedElements) {
							containsElems = true;
						}
					}
					if (containsElems == true) {
						if (lonelyElements.get(j) instanceof IPetriNamedElements) {
							IPetriNamedElements currElem = (IPetriNamedElements) lonelyElements.get(j);
							if (currElem.GetID().equals(currArc.GetSource())) {
								lonelyElements.remove(currElem);
							}
							else if (currElem.GetID().equals(currArc.GetTarget())) {
								lonelyElements.remove(currElem);
							}
							for (int l = 0; l < lonelyElements.size(); l++) {
								System.out.println("Inhalt lonelyElems an Pos "+l+": "+lonelyElements.get(l).GetID());
								System.out.println(containsElems);
							}
						}
					}
					for (int l = 0; l < lonelyElements.size(); l++) {
						System.out.println("Inhalt lonelyElems an Pos "+l+": "+lonelyElements.get(l).GetID());
						System.out.println(containsElems);
					}
				}
				
			}
		}
		for (int k = 0; k < lonelyElements.size(); k++) {
			if (lonelyElements.get(k) instanceof IPetriNamedElements) {
				panel.setInvalidReasonMessage("Nicht alle Netzelemente (z.B. Element " + lonelyElements.get(k).GetID() + ") auf einem Pfad von Anfangs- zu Endstelle");
			}
			else {
				panel.setInvalidReasonMessage(null);
			}
		}
	}
}
