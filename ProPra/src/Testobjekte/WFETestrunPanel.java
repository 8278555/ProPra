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
    public ArrayList<IPetriElements> toModifyElements = new ArrayList<IPetriElements>();
    public ArrayList<IArc> markedArcs = new ArrayList<IArc>();
    int chooseFrameWidth;
    int chooseFrameHeight;
    private String startPlace;
    private String endPlace;
    
    
	public WFETestrunPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
		elemsizefactor = 20;
		this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                	System.out.println(e);
                    /*/JOptionPane.showMessageDialog(null, 
                    "Pressed Linksklick auf X: " + e.getX() + " Y: " + e.getY(),
                    "MouseButton", 
                    JOptionPane.PLAIN_MESSAGE);/*/
                }
                if(e.getButton() == MouseEvent.BUTTON2) {
                /*      JOptionPane.showMessageDialog(null, 
                              "Mittelklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);*/
                }
            	if(e.getButton() == MouseEvent.BUTTON3) {
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


   
    protected void drawPlace(Graphics g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			if ((stelle.GetID().equals(startPlace))||(stelle.GetID().equals(endPlace))) {
					g.setColor(Color.ORANGE);
					g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
					g.setColor(Color.BLACK);
					g.drawOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
					if (stelle.GetID().equals(startPlace)) {
						stelle.SetToken("1");
						g.drawString("Marke: "+stelle.GetToken(), stelle.getPositionx()+elemsizefactor+15, stelle.getPositiony());
					}
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
    			if (toModifyElements.size()!=0) {
    				for (int k = 0; k<toModifyElements.size();k++) {
    					if (transition.equals(toModifyElements.get(k))) {
    						g.setColor(Color.RED);
    						g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    						g.setColor(Color.BLACK);
    						g.drawRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    	    				if (transition.getName() != null) {
    	        				g.drawString(transition.getName(), transition.getPositionx(), transition.getPositiony()+elemsizefactor+15);
    	    				}

    						break;
    					}
    					else {
    						g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    	    				if (transition.getName() != null) {
    	        				g.drawString(transition.getName(), transition.getPositionx(), transition.getPositiony()+elemsizefactor+15);
    	    				}
    					}
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
                if (markedArcs.size()!=0) {
                	for (int k = 0; k<markedArcs.size();k++) {
                		if (kante.equals(markedArcs.get(k))) {
                			g.setColor(Color.RED);
                			g.drawLine((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2));
                            Pfeildreieck d = new Pfeildreieck((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2), elemsizefactor);
                            g.fillPolygon(d.Pfeilspitze());
                            g.setColor(Color.BLACK);
                            g.drawPolygon(d.Pfeilspitze());
                            break;
                		}
                		else {
                            g.drawLine((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2));
                            Pfeildreieck d = new Pfeildreieck((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2), elemsizefactor);
                            g.fillPolygon(d.Pfeilspitze());           

                		}
                	}
                }
                else {
                	g.drawLine((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2));
                    Pfeildreieck d = new Pfeildreieck((sourcepoint.getPositionx()+elemsizefactor/2), (sourcepoint.getPositiony()+elemsizefactor/2), (destpoint.getPositionx()+elemsizefactor/2), (destpoint.getPositiony()+elemsizefactor/2), elemsizefactor);
                    g.fillPolygon(d.Pfeilspitze());
                }
            }
        }
    }

    public void refresh() {
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
