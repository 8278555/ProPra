package Release;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class WFEPanel extends JPanel implements IWFESituationView {

    private IPetriNamedElements sourcepoint;
    private IPetriNamedElements destpoint;
    private WFEModelNet petrinetz;
    private int xstart;
    private int ystart;
    private int xend;
    private int yend;
    private int elemsizefactor;
    public ArrayList<IPetriElements> toModifyElements = new ArrayList<IPetriElements>();
    public ArrayList<IArc> markedArcs = new ArrayList<IArc>();
    private boolean singleelemclicked;
    private boolean verschiebemodus;
    private boolean arcAddSrcMode;
    private boolean arcAddDestMode;
    private boolean newArcDestPlace;
    private boolean newArcDestTransition;
    private boolean netValid;
    private String newSrcId;
    private String newDestId;
    private String newArcId;
    int chooseFrameWidth;
    int chooseFrameHeight;
    private int b1 = MouseEvent.BUTTON1_DOWN_MASK;
    private String invalidReasonMessage;
    private NetzTester Validator;
    private String startPlace;
    private String endPlace;
    
    
	public WFEPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
		elemsizefactor = 20;
		Validator = new NetzTester();
		this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
            	if(e.getButton() == MouseEvent.BUTTON1) {
            		panelReleased(petrinetz, e);
            	}
            	if(e.getButton() == MouseEvent.BUTTON3) {
            		refresh();
            	}
            }

			/**
			 * @param petrinetz
			 * @param e
			 */
            
            @Override
            public void mousePressed(MouseEvent e) {
            	if(e.getButton() == MouseEvent.BUTTON1) {
            		panelPressed(e, petrinetz);
            	}
            	if(e.getButton() == MouseEvent.BUTTON3) {
            		panelRightButtonMenu(e, petrinetz);

                }
            }

			/**
			 * @param e
			 * @param petrinetz
			 */
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                	panelClicked(e);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
             }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
 
		});
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            @Override
            public void mouseDragged(MouseEvent e) {
            	if (e.getModifiersEx()==b1) {
            		panelDragged(e);
            	}            	
            }
        });
        
        this.addKeyListener(new KeyListener() {
        	
 			@Override
 			public void keyTyped(KeyEvent e) { 				 	
 			}
 			
 			@Override
 			public void keyReleased(KeyEvent e) {
 			}
 			
 			@Override
 			public void keyPressed(KeyEvent e) {
 				System.out.println(e.getKeyCode());
 				deleteElements(e);
 			}
 		});


	}
	
	/**
	* Override the isFocusTraversable method and return true
	*/
	public boolean isFocusTraversable ( ) {
		return true ;
		}

	/**
	 * @return the arcAddMode
	 */
	public boolean isArcAddMode() {
		return arcAddSrcMode;
	}

	/**
	 * @param arcAddMode the arcAddMode to set
	 */
	public void setArcAddMode(boolean arcAddMode) {
		this.arcAddSrcMode = arcAddMode;
	}
	
	/**
	 * @return the newArcId
	 */
	public String getNewArcId() {
		return newArcId;
	}

	/**
	 * @param newArcId the newArcId to set
	 */
	public void setNewArcId(String newArcId) {
		this.newArcId = newArcId;
	}

	public void setElemsizefactor(int newsize) {
		elemsizefactor = newsize;
		refresh();
	}
	
	public int getElemsizefactor() {
		return elemsizefactor;
	}

	/**
	 * @return the invalidReasonMessage
	 */
	public String getInvalidReasonMessage() {
		return invalidReasonMessage;
	}

	/**
	 * @param invalidReasonMessage the invalidReasonMessage to set
	 */
	public void setInvalidReasonMessage(String invalidReasonMessage) {
		this.invalidReasonMessage = invalidReasonMessage;
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

	/**
	 * @return the netValid
	 */
	public boolean isNetValid() {
		return netValid;
	}

	/**
	 * @param netValid the netValid to set
	 */
	public void setNetValid(boolean netValid) {
		this.netValid = netValid;
	}

	private void panelPressed(MouseEvent e, WFEModelNet petrinetz) {
		xstart = e.getX();
        ystart = e.getY();
		xend = e.getX();
        yend = e.getY();
        singleelemclicked = false;
        for (int k = 0; k < petrinetz.getListSize(); k++) {
            if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                if (xend >= currElem.getPositionx() && xend <= (currElem.getPositionx()+elemsizefactor)&& yend >= currElem.getPositiony() && yend <= (currElem.getPositiony()+elemsizefactor)) {
                    if (toModifyElements.isEmpty()) {
                    	toModifyElements.add(currElem);
                    }                       	
                    verschiebemodus = true;
                    if (toModifyElements.size() == 1) {singleelemclicked = true;}
                    refresh();
                    break;
                }
            }
            else if (petrinetz.petriElements.get(k) instanceof IArc) {
            	IArc currElem = (IArc) petrinetz.petriElements.get(k);
            	String arcSrcElemId = currElem.GetSource();
            	String arcDestElemId = currElem.GetTarget();
            	int pathStartX = 0;
            	int pathStartY = 0;
            	int pathEndX = 0;
            	int pathEndY = 0;
            	for (int p = 0; p < petrinetz.getListSize(); p++) {
            		if (petrinetz.petriElements.get(p) instanceof IPetriNamedElements) {
            			IPetriNamedElements candidate1 = (IPetriNamedElements) petrinetz.petriElements.get(p);
            			if (candidate1.GetID().equals(arcSrcElemId)) {
            				pathStartX = candidate1.getPositionx();
            				pathStartY = candidate1.getPositiony();
            			}
            			else if (candidate1.GetID().equals(arcDestElemId)) {
            				pathEndX = candidate1.getPositionx();
            				pathEndY = candidate1.getPositiony();
            			}
            		}
            	}
            	if (shortestDistance((pathStartX+elemsizefactor/2), (pathStartY+elemsizefactor/2), (pathEndX+elemsizefactor/2), (pathEndY+elemsizefactor/2), e.getX(), e.getY()) < 6) {
            		markedArcs.add(currElem);
            	}
            }
        }
	}

	private void panelReleased(WFEModelNet petrinetz, MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
    		if (verschiebemodus == false) {
				for (int k = 0; k< petrinetz.getListSize(); k++) {
                    if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                    	IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                        if (xend <= currElem.getPositionx() && e.getX() >= (currElem.getPositionx()+elemsizefactor)&& yend <= currElem.getPositiony() && e.getY() >= (currElem.getPositiony()+elemsizefactor)) {
                            toModifyElements.add(currElem);
                        	verschiebemodus = true;
                        }
                    }

                }
			}
			else if (xstart != e.getX() || ystart != e.getY() || singleelemclicked == false){
				verschiebemodus = false;
				toModifyElements.clear();
				markedArcs.clear();
			}
    		xend = 0;
			yend = 0;
			chooseFrameWidth = 0;
			chooseFrameHeight = 0;
			int maxX = 0;
			int maxY = 0;
			// Mindestens benötigtePanelgröße ermitteln 
			for (int k = 0; k< petrinetz.getListSize(); k++) {
                if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                	IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                    if (currElem.getPositionx() > maxX) {maxX = currElem.getPositionx();}
                    if (currElem.getPositiony() > maxY) {maxY = currElem.getPositiony();}
                }
            }
			// Panelgröße anpassen. Hiermit wird auch das Scrollpanel angepasst.
			setPreferredSize(new Dimension ((maxX+elemsizefactor+20), (maxY+elemsizefactor+20)));
			revalidate();
			refresh();
          }
	}

    private void panelClicked(MouseEvent e) {
    	if (arcAddSrcMode == true) {
    		for (int k = 0; k< petrinetz.getListSize(); k++) {
                if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                    IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                    if ((currElem.getPositionx()<=e.getX())&&(currElem.getPositionx()+elemsizefactor>=e.getX())&&(currElem.getPositiony()<=e.getY())&&(currElem.getPositiony()+elemsizefactor>=e.getY())) {
                    	newSrcId = currElem.GetID();
                    	if (currElem instanceof IPlace) {
                    		newArcDestPlace = true;
                    		newArcDestTransition = false;
                    	}
                    	else if (currElem instanceof ITransition) {
                    		newArcDestTransition = true;
                    		newArcDestPlace = false;
                    	}
                    	arcAddSrcMode = false;
                    	arcAddDestMode = true;
                    	break;
                    }
                }
    		}    
    	}
    	else if (arcAddDestMode == true) {
    		for (int k = 0; k< petrinetz.getListSize(); k++) {
                if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                    IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                    if ((currElem.getPositionx()<=e.getX())&&(currElem.getPositionx()+elemsizefactor>=e.getX())&&(currElem.getPositiony()<=e.getY())&&(currElem.getPositiony()+elemsizefactor>=e.getY())) {
                    	newDestId = currElem.GetID();
                    	if (((newArcDestPlace == true)&&(currElem instanceof ITransition))||((newArcDestTransition == true)&&(currElem instanceof IPlace))) {
                    		petrinetz.addArc(newArcId, newSrcId, newDestId);
                        	arcAddDestMode = false;
                    		newArcDestTransition = false;
                    		newArcDestPlace = false;
                    		toModifyElements.clear();
                        	refresh();
                        	break;
                    	}
                    	else {
                    		JOptionPane.showMessageDialog(null, 
                                    "Eine Kante darf nur von einer Stelle zu einer Transition oder umgekehrt führen",
                                    "Warnung", 
                                    JOptionPane.PLAIN_MESSAGE);
                    		newArcDestTransition = false;
                    		newArcDestPlace = false;
                    		arcAddDestMode = false;
                    		toModifyElements.clear();
                    		refresh();
                    	}
                    }
                }
    		}    

    	}
    	else {}
    }

	public void panelRightButtonMenu(MouseEvent e, WFEModelNet petrinetz) {
    	RightButtonPopUp menu = new RightButtonPopUp(e, petrinetz, this);
        menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
    private void panelDragged(MouseEvent e) {
        IPetriNamedElements choosenElem;
        if (toModifyElements.size()==0) {        
        	chooseFrameWidth = e.getX()-xend;
        	chooseFrameHeight = e.getY()-yend;
        	refresh();
        }
        else {
        	for (int p = 0; p<toModifyElements.size();p++) {
        		if (toModifyElements.get(p) instanceof IPetriNamedElements) {
        			choosenElem = (IPetriNamedElements) toModifyElements.get(p);
                    for (int l = 0; l< petrinetz.getListSize(); l++) {
                        if (petrinetz.petriElements.get(l) instanceof IPetriNamedElements) {
                            IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(l);
                            if (currElem.GetID().equals(choosenElem.GetID())) {
                            	int newx = currElem.getPositionx() + (e.getX() - xend);
                            	int newy = currElem.getPositiony() + (e.getY() - yend);
                            	currElem.setPosition((newx), (newy));
                                refresh();
                            }
                        }
                    }
        		}
            }
        	xend = e.getX();
        	yend = e.getY();
        }    	
    }
    
    private double shortestDistance(int pathStartX, int pathStartY, int pathEndX, int pathEndY, int mouseX, int mouseY) {
    	  float pw = (float)mouseX - (float)pathStartX;
    	  float px = (float)mouseY - (float)pathStartY;
    	  float py = (float)pathEndX - (float)pathStartX;
    	  float pz = (float)pathEndY - (float)pathStartY;

    	  float dot = pw * py + px * pz;
    	  float len_sq = py * py + pz * pz;
    	  float param = -1;
    	  if (len_sq != 0) //in case of 0 length line
    	      param = dot / len_sq;

    	  float xx, yy;

    	  if (param < 0) {
    	    xx = (float)pathStartX;
    	    yy = (float)pathStartY;
    	  }
    	  else if (param > 1) {
    	    xx = (float)pathEndX;
    	    yy = (float)pathEndY;
    	  }
    	  else {
    	    xx = (float)pathStartX + param * py;
    	    yy = (float)pathStartY + param * pz;
    	  }

    	  float dx = (float)mouseX - xx;
    	  float dy = (float)mouseY - yy;
    	  double dist = Math.sqrt(dx * dx + dy * dy);
    	  return dist;
    }

    private void deleteElements(KeyEvent e) {
    	if (e.getKeyCode()==127) {
    		while (toModifyElements.size()>0) {	
    			IPetriNamedElements choosenElem = (IPetriNamedElements) toModifyElements.get(0);
                for (int l = 0; l< petrinetz.getListSize(); l++) {
                    if (petrinetz.petriElements.get(l) instanceof IPetriNamedElements) {
                        IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(l);
                        if (currElem.GetID().equals(choosenElem.GetID())) {
                        	for (int m = 0; m< petrinetz.getListSize(); m++) {
                        		if (petrinetz.petriElements.get(m) instanceof IArc) {
                        			IArc currArc = (IArc) petrinetz.petriElements.get(m);
                        			if (currArc.GetSource().equals(currElem.GetID())||currArc.GetTarget().equals(currElem.GetID())) {
                        				petrinetz.petriElements.remove(m);
                        			}
                        		}
                        	}
                        	petrinetz.petriElements.remove(l);
                        	toModifyElements.remove(0);
                            refresh();
                        }
                    }
                }
            }
    		for (int p = 0; p<markedArcs.size();p++) {
    			IArc choosenElem = markedArcs.get(p);
    			for (int l = 0; l< petrinetz.getListSize(); l++) {
    				if (petrinetz.petriElements.get(l) instanceof IArc) {
    					IArc currElem = (IArc) petrinetz.petriElements.get(l);
    					if (currElem.GetID().equals(choosenElem.GetID())) {
    						petrinetz.petriElements.remove(l);
    						refresh();
    					}
    				}
    			}
    		}
    	}
    		
    }
    
    protected void drawPlace(Graphics g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			if (toModifyElements.size()!=0) {
    				for (int k = 0; k<toModifyElements.size();k++) {
    					if (stelle.equals(toModifyElements.get(k))) {
    						g.setColor(Color.RED);
    						g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    						g.setColor(Color.BLACK);
    						g.drawOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    	    				if (stelle.getName() != null) {
    	        				g.drawString(stelle.getName(), stelle.getPositionx(), stelle.getPositiony()+elemsizefactor+15);
    	    				}
    						break;
    					}
    					else if ((stelle.GetID().equals(startPlace))||(stelle.GetID().equals(endPlace))) {
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
    						break;
    					}
    					else {
    						g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    	    				if (stelle.getName() != null) {
    	        				g.drawString(stelle.getName(), stelle.getPositionx(), stelle.getPositiony()+elemsizefactor+15);
    	    				}
    					}
    				}
    			}
    			else if ((stelle.GetID().equals(startPlace))||(stelle.GetID().equals(endPlace))) {
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
					//break;
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

    public void startnew() {
    	petrinetz.petriElements.clear();
    	removeAll();
    	revalidate();
    	refresh();
    }
    
    public void refresh() {
    	if (petrinetz.getListSize()>0) {
    		setNetValid(Validator.isValidNet(petrinetz, this));
    	}
    	repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawArc(g);
        drawPlace(g);
        drawTransition(g);
        g.drawRect(xend, yend, chooseFrameWidth, chooseFrameHeight);
        if (invalidReasonMessage != null) {
        	g.drawString(invalidReasonMessage, 10, 10);
        }
    }
	    
}
