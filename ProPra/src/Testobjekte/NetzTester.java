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
	private boolean noSingleElements;
	private boolean correctStartPlace;
	//private boolean noStartPlace;
	private boolean correctEndPlace;
	//private boolean noEndPlace;
	private boolean validNet;
	private boolean transitionAsStart;
	private boolean transitionAsEnd;
	String fehlermeldung;
	private ArrayList<IPetriElements> lonelyElements;
	private ArrayList<String> sourceCandidates;
	private ArrayList<String> endCandidates;
	
	
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
		fehlermeldung = "";
		lonelyElements = new ArrayList<IPetriElements>(petrinetz.petriElements);
		boolean containsElems = true;
		checkSingleElements(petrinetz, panel, containsElems);
		checkStartPlaces(petrinetz, panel, containsElems);
		checkEndPlaces(petrinetz, panel, containsElems);
		checkFinal(panel);
	}

	/**
	 * @param petrinetz
	 * @param panel
	 * @param containsElems
	 */
	private void checkSingleElements(WFEModelNet petrinetz, WFEPanel panel, boolean containsElems) {
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IArc) {
				IArc currArc = (IArc) petrinetz.petriElements.get(i);
				int j = 0;				
				while (containsElems && (j < lonelyElements.size())) {
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
							else {j++;}
						}
						else if (lonelyElements.get(j) instanceof IArc) {j++;}
					}
				}
				
			}
		}
		if (containsElems) {
			for (int k = 0; k < lonelyElements.size(); k++) {
				if (lonelyElements.get(k) instanceof IPetriNamedElements) {
					noSingleElements = false;
				}

			}
		}
		else {
			noSingleElements = true;
		}
	}

	private void checkStartPlaces(WFEModelNet petrinetz, WFEPanel panel, boolean containsElems) {
		sourceCandidates = new ArrayList<String>();
		String candidate = "";
		String sourcePlace = "";
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IArc) {
				IArc currArc = (IArc) petrinetz.petriElements.get(i);
				candidate = currArc.GetSource();
				sourceCandidates.add(candidate);
				for (int j = 0; j < petrinetz.getListSize();j++) {
					if (petrinetz.petriElements.get(j) instanceof IArc) {
						IArc controlArc = (IArc) petrinetz.petriElements.get(j);
						if (controlArc.GetTarget().equals(candidate)){
							sourceCandidates.remove(candidate);
							candidate = "";
						}
					}
				}
				if (sourcePlace == "") {
					if (candidate != "") {
						sourcePlace = candidate;						
					}
				}
			}			
		}
		for (int r = 0; r < petrinetz.getListSize(); r++) {
			System.out.println("SrcCand Anz :"+sourceCandidates.size());
			if ((sourceCandidates.size()==1)&&((petrinetz.petriElements.get(r) instanceof IPlace)&&(petrinetz.petriElements.get(r).GetID().equals(sourceCandidates.get(0))))) {
				correctStartPlace = true;
				transitionAsStart = false;
			}
			else if ((sourceCandidates.size()==1)&&((petrinetz.petriElements.get(r) instanceof ITransition)&&(petrinetz.petriElements.get(r).GetID().equals(sourceCandidates.get(0))))) {
				correctStartPlace = true;
				transitionAsStart = true;
			}
			else {
				for (int t = 0; t < sourceCandidates.size(); t++) {
					if ((sourceCandidates.size()>1)&&((petrinetz.petriElements.get(r) instanceof ITransition)&&(petrinetz.petriElements.get(r).GetID().equals(sourceCandidates.get(t))))) {
						correctStartPlace = false;
						transitionAsStart = true;
					}
					else if ((sourceCandidates.size()>1)&&((!(petrinetz.petriElements.get(r) instanceof ITransition))&&(petrinetz.petriElements.get(r).GetID().equals(sourceCandidates.get(t))))) {
						correctStartPlace = false;
						transitionAsStart = false;
					}
				}
			}
		};
	}

	private void checkEndPlaces(WFEModelNet petrinetz, WFEPanel panel, boolean containsElems) {
		endCandidates = new ArrayList<String>();
		String candidate = "";
		String endPlace = "";
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IArc) {
				IArc currArc = (IArc) petrinetz.petriElements.get(i);
				candidate = currArc.GetTarget();
				endCandidates.add(candidate);
				for (int j = 0; j < petrinetz.getListSize();j++) {
					if (petrinetz.petriElements.get(j) instanceof IArc) {
						IArc controlArc = (IArc) petrinetz.petriElements.get(j);
						if (controlArc.GetSource().equals(candidate)){
							endCandidates.remove(candidate);
							candidate = "";
						}
					}
				}
				if (endPlace == "") {
					if (candidate != "") {
						endPlace = candidate;						
					}
				}
			}			
		}
		for (int r = 0; r < petrinetz.getListSize(); r++) {
			System.out.println("EndCand Anz :"+endCandidates.size());
			if ((endCandidates.size()==1)&&((petrinetz.petriElements.get(r) instanceof IPlace)&&(petrinetz.petriElements.get(r).GetID().equals(endCandidates.get(0))))) {
				correctEndPlace = true;
				transitionAsEnd = false;
			}
			else if ((endCandidates.size()==1)&&((petrinetz.petriElements.get(r) instanceof ITransition)&&(petrinetz.petriElements.get(r).GetID().equals(endCandidates.get(0))))) {
				correctEndPlace = true;
				transitionAsEnd = true;
			}
			else {
				for (int t = 0; t < endCandidates.size(); t++) {
					if ((endCandidates.size()>1)&&((petrinetz.petriElements.get(r) instanceof ITransition)&&(petrinetz.petriElements.get(r).GetID().equals(endCandidates.get(t))))) {
						correctEndPlace = false;
						transitionAsEnd = true;
					}
					else if ((endCandidates.size()>1)&&((!(petrinetz.petriElements.get(r) instanceof ITransition))&&(petrinetz.petriElements.get(r).GetID().equals(endCandidates.get(t))))) {
						correctEndPlace = false;
						transitionAsEnd = false;
					}
				}
			}
		};
	}

	
	private void checkFinal(WFEPanel panel) {
		fehlermeldung = "";
		if ((noSingleElements==false)||((correctStartPlace == false)&&(transitionAsStart == true))||((correctEndPlace == false)&&(transitionAsEnd == true))) {
			fehlermeldung = fehlermeldung+" Nicht alle Netzelemente auf einem Pfad von Anfangs- zu Endstelle!";
			panel.setStartPlace(null);
			panel.setEndPlace(null);
		}
		if ((correctStartPlace==false)&&(transitionAsStart==false)) {
			fehlermeldung = fehlermeldung+" Zu viele Anfangsstellen!";
			panel.setStartPlace(null);
		}
		if ((correctEndPlace==false)&&(transitionAsEnd==false)) {
			fehlermeldung = fehlermeldung+" Zu viele Endstellen!";
			panel.setEndPlace(null);
		}
		if ((noSingleElements==true)&&((correctStartPlace == true)&&(transitionAsStart == true))) {
			fehlermeldung = fehlermeldung+" Keine Anfangsstelle!";
			panel.setStartPlace(null);
		}
		if ((noSingleElements==true)&&((correctEndPlace == true)&&(transitionAsEnd == true))) {
			fehlermeldung = fehlermeldung+" Keine Endstelle!";
			panel.setEndPlace(null);
		}
		if ((noSingleElements==true)&&(correctStartPlace==true)&&(transitionAsStart==false)&&(correctEndPlace==true)&&(transitionAsEnd==false)) {
			panel.setInvalidReasonMessage(null);
			panel.setStartPlace(sourceCandidates.get(0));
			panel.setEndPlace(endCandidates.get(0));
		}
		else {
			panel.setInvalidReasonMessage(fehlermeldung);
		}
	}


}
