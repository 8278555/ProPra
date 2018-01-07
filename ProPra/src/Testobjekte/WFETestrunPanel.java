package Testobjekte;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import Release.*;

public class WFETestrunPanel extends JPanel implements IWFESituationView {

    private IPetriNamedElements sourcepoint;
    private IPetriNamedElements destpoint;
    private WFEModelNet petrinetz;
    private int elemsizefactor;
    private ArrayList<IPlace> allPlaces = new ArrayList<IPlace>();
    private ArrayList<ITransition> allTransitions =  new ArrayList<ITransition>();
    private ArrayList<IPlace> affectedPlaces = new ArrayList<IPlace>();
    private ArrayList<ITransition> affectedTransitions =  new ArrayList<ITransition>();
    private ArrayList<IPlace> markedPlaces = new ArrayList<IPlace>();
    private ArrayList<ITransition> activeTransitions =  new ArrayList<ITransition>();
    private ArrayList<IArc> allArcs = new ArrayList<IArc>();
    int chooseFrameWidth;
    int chooseFrameHeight;
    private String startPlace;
    private String endPlace;
//    private ArrayList<PetriNode> petriBaum = new ArrayList<PetriNode>();
    
    public WFETestrunPanel() {
    	
    }
    
	public WFETestrunPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
		elemsizefactor = 20;
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IPlace) {
				allPlaces.add((IPlace) petrinetz.petriElements.get(i));
			}
		}
		for (int j = 0; j < petrinetz.getListSize(); j++) {
			if (petrinetz.petriElements.get(j) instanceof ITransition) {
				allTransitions.add((ITransition) petrinetz.petriElements.get(j));
			}
		}
		for (int k = 0; k < petrinetz.getListSize(); k++) {
			if (petrinetz.petriElements.get(k) instanceof IArc) {
				allArcs.add((IArc) petrinetz.petriElements.get(k));
			}
		}
		this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1) {
                	//transitionSchalten (evt);
                	for (int i = 0; i < petrinetz.getListSize();i++) {
    					if (petrinetz.petriElements.get(i) instanceof ITransition) {
    						ITransition transition = (ITransition) petrinetz.petriElements.get(i);
    						if (evt.getX() >= transition.getPositionx() && evt.getX() <= (transition.getPositionx()+elemsizefactor)&& evt.getY() >= transition.getPositiony() && evt.getY() <= (transition.getPositiony()+elemsizefactor)) {
    							if (activeTransitions.contains(transition)) {
    								transitionSchalten(transition);
    							};
    							
    						}
    					}
    				}
               	//getClickedElement(evt);
                	System.out.println(evt);
                    /*/JOptionPane.showMessageDialog(null, 
                    "Pressed Linksklick auf X: " + e.getX() + " Y: " + e.getY(),
                    "MouseButton", 
                    JOptionPane.PLAIN_MESSAGE);/*/
                }
                if(evt.getButton() == MouseEvent.BUTTON2) {
                /*      JOptionPane.showMessageDialog(null, 
                              "Mittelklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);*/
                }
            	if(evt.getButton() == MouseEvent.BUTTON3) {
            		transitionActive();
                	/*JOptionPane.showMessageDialog(null, 
                              "Rechtsklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);*/
                }

            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
 
		});
		//transitionActive();
	
	}
	

	public void setPetrinetz(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
	}

	public void setElemsizefactor(int newsize) {
		elemsizefactor = newsize;
		refresh();
	}
	
	public int getElemsizefactor() {
		return elemsizefactor;
	}

	/**
	 * @return the startPlace
	 */
	public String getStartPlace() {
		return startPlace;
	}

	/**
	 * @param startPlace the startPlace to set
	 */
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
		markedPlaces.add(getCurrentPlace(startPlace));
		
	}

	/**
	 * @return the endPlace
	 */
	public String getEndPlace() {
		return endPlace;
	}

	/**
	 * @param endPlace the endPlace to set
	 */
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	
	

	private IPlace getCurrentPlace(String id) {
		IPlace currElem = null;
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IPlace) {
				IPlace candidate = (IPlace)petrinetz.petriElements.get(i);
				if (candidate.GetID().equals(id)){
					currElem = candidate;
				}
			}
		}
		return currElem;
	}
	private ITransition getCurrentTransition(String id) {
		ITransition currElem = null;
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof ITransition) {
				ITransition candidate = (ITransition)petrinetz.petriElements.get(i);
				if (candidate.GetID().equals(id)){
					currElem = candidate;
				}
			}
		}
		return currElem;
	}
	private IArc getCurrentArc(String id) {
		IArc currElem = null;
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IArc) {
				IArc candidate = (IArc)petrinetz.petriElements.get(i);
				if (candidate.GetID().equals(id)){
					currElem = candidate;
				}
			}
		}
		return currElem;
	}
	private IPetriNamedElements getCurrentElement(String id) {
		IPetriNamedElements currElem = null;
		for (int i = 0; i < petrinetz.getListSize(); i++) {
			if (petrinetz.petriElements.get(i) instanceof IPetriNamedElements) {
				IPetriNamedElements candidate = (IPetriNamedElements)petrinetz.petriElements.get(i);
				if (candidate.GetID().equals(id)){
					currElem = candidate;
				}
			}
		}
		return currElem;
	}
	
	private void transitionActive() {	
		if (!(endPlace.equals(startPlace))) {
			for (int h = 0; h < markedPlaces.size(); h++) {
				IPlace currElem = markedPlaces.get(h);
				//System.out.println("markedPlace in transActive: "+currElem.GetID());
				ArrayList<IArc> choosenArcs = new ArrayList<IArc>();
				ArrayList<ITransition> choosenTransitions =  new ArrayList<ITransition>();
				IArc currArc = null;
				for (int i = 0; i < allArcs.size(); i++) {
					if (allArcs.get(i).GetSource().equals(currElem.GetID())) {
						choosenArcs.add(allArcs.get(i));
					}
					for (int j = 0; j < choosenArcs.size(); j++) {
						currArc = choosenArcs.get(j);
						for (int k = 0; k < allTransitions.size(); k++) {
							if (allTransitions.get(k).GetID().equals(currArc.GetTarget())) {
								choosenTransitions.add(getCurrentTransition(currArc.GetTarget()));
							}
							for (int l = 0; l < choosenTransitions.size(); l++) {
								activeTransitions.add(choosenTransitions.get(l));
								for (int m = 0; m < allArcs.size(); m++) {
									if (choosenTransitions.get(l).GetID().equals(allArcs.get(m).GetTarget())) {
										if (!(getCurrentPlace(allArcs.get(m).GetSource()).GetToken().equals("1"))){
											activeTransitions.remove(choosenTransitions.get(l));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void transitionSchalten(ITransition transition) {
		while (activeTransitions.contains(transition)) {
			activeTransitions.remove(transition);
		}
		System.out.println("Act size: "+activeTransitions.size());
		ArrayList<IArc> choosenArcs = new ArrayList<IArc>();
		ArrayList<IPlace> choosenPlaces =  new ArrayList<IPlace>();
		IArc currArc = null;
		for (int i = 0; i < allArcs.size(); i++) {
			if (allArcs.get(i).GetSource().equals(transition.GetID())){
				for (int j = 0; j < allPlaces.size(); j++) {
					if (allArcs.get(i).GetTarget().equals(allPlaces.get(j).GetID())) {
						markedPlaces.add(allPlaces.get(j));
					}
				}
			}
			else if (allArcs.get(i).GetTarget().equals(transition.GetID())) {
				choosenPlaces.add(getCurrentPlace(allArcs.get(i).GetSource()));
			}
			for (int j = 0; j < allArcs.size(); j++) {
				currArc = allArcs.get(j);
				for (int k = 0; k < choosenPlaces.size(); k++) {
					//System.out.println("Aktive Stelle: "+choosenPlaces.get(k).GetID());
					if (currArc.GetSource().equals(choosenPlaces.get(k).GetID())) {
						if ((activeTransitions.contains(getCurrentTransition(currArc.GetTarget())))) {
							markedPlaces.remove(choosenPlaces.get(k));
							choosenPlaces.get(k).SetToken("0");
						}
					}
				}
			}
		}
		
		
		refresh();
	}
	
    protected void drawPlace(Graphics g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			if (markedPlaces.contains(stelle)) {
					g.setColor(Color.ORANGE);
					g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
					g.setColor(Color.BLACK);
					g.drawOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
					stelle.SetToken("1");
					g.drawString("Marke: "+stelle.GetToken(), stelle.getPositionx()+elemsizefactor+10, stelle.getPositiony()+5);
    				if (stelle.getName() != null) {
        				g.drawString(stelle.getName(), stelle.getPositionx(), stelle.getPositiony()+elemsizefactor+15);
    				}
    			}
    			else {
    				g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    				if (stelle.getName() != null) {
        				g.drawString(stelle.getName(), stelle.getPositionx(), stelle.getPositiony()+elemsizefactor+15);
    				}
    			}
    		}
    	}
    }
    protected void drawTransition(Graphics g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
    			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
    			if (activeTransitions.contains(transition)){
    				System.out.println(transition.GetID());
    				g.setColor(Color.GREEN);
    				g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    				g.setColor(Color.BLACK);
    				g.drawRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    				if (transition.getName() != null) {
        				g.drawString(transition.getName(), transition.getPositionx(), transition.getPositiony()+elemsizefactor+15);
    				}
    			}
    			else {
    				g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    				if (transition.getName() != null) {
        				g.drawString(transition.getName(), transition.getPositionx(), transition.getPositiony()+elemsizefactor+15);    				
    				}
    			}
    				
    		}
    	}
    }
    protected void drawArc(Graphics g) {
        for (int i = 0; i< petrinetz.getListSize(); i++) {
            if (petrinetz.petriElements.get(i) instanceof WFEModelArc) {
                WFEModelArc kante = (WFEModelArc) petrinetz.petriElements.get(i);
                for (int j = 0; j< petrinetz.getListSize(); j++) {
                    IPetriElements currElem = petrinetz.petriElements.get(j);
                    if (currElem.GetID().equals(kante.GetSource())) {
                        sourcepoint = (IPetriNamedElements) currElem;
                    }
                    else {
                        if (currElem.GetID().equals(kante.GetTarget())) {
                            destpoint = (IPetriNamedElements) currElem;
                        }
                    }
                }
                g.drawLine((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2));
                Pfeildreieck d = new Pfeildreieck((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2), elemsizefactor);
                g.fillPolygon(d.Pfeilspitze());
            }
        }
    }

    public void refresh() {
    	//transitionActive();
    	repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawArc(g);
        drawPlace(g);
        drawTransition(g);
    }	    
}
