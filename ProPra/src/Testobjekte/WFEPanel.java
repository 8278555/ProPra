package Testobjekte;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import Release.*;

public class WFEPanel extends JPanel {

    private IPetriNamedElements sourcepoint;
    private IPetriNamedElements destpoint;
    private WFEModelNet petrinetz;
    private int xstart;
    private int ystart;
    private int xend;
    private int yend;
    private int elemsizefactor;
    public ArrayList<IPetriElements> toModifyElements = new ArrayList<IPetriElements>();
    private boolean singleelemclicked;
    private boolean verschiebemodus;
    int chooseFrameWidth;
    int chooseFrameHeight;
    
	public WFEPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
		elemsizefactor = 20;
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
                    //System.out.println(menu.getNewPlace());
                	/*JOptionPane.showMessageDialog(null, 
                              "Rechtsklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);*/
                }
            }

			/**
			 * @param e
			 * @param petrinetz
			 */
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    /*/JOptionPane.showMessageDialog(null, 
                    "Pressed Linksklick auf X: " + e.getX() + " Y: " + e.getY(),
                    "MouseButton", 
                    JOptionPane.PLAIN_MESSAGE);/*/
                }
                /*/      if(e.getButton() == MouseEvent.BUTTON2) {
                      JOptionPane.showMessageDialog(null, 
                              "Mittelklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);
                    }/*/
                
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
 
		});
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            @Override
            public void mouseDragged(MouseEvent e) {
            	//if(e.getButton() == MouseEvent.BUTTON1) {
            	panelDragged(e);
            	//}
            }
        });
	}

	public void panelRightButtonMenu(MouseEvent e, WFEModelNet petrinetz) {
    	RightButtonPopUp menu = new RightButtonPopUp(e, petrinetz, this);
        menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public void setelemsizefactor(int newsize) {
		elemsizefactor = newsize;
		refresh();
	}
	
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlace(g);
        drawTransition(g);
        drawArc(g);
        g.drawRect(xend, yend, chooseFrameWidth, chooseFrameHeight);
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
    						break;
    					}
    					else {
    						g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    						//break;
    					}
    				}
    			}
    			else {
    				g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
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
    						break;
    					}
    					else {
    						g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    						//break;
    					}
    				}
    			}
    			else {
    				g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
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

	private void panelPressed(MouseEvent e, WFEModelNet petrinetz) {
		xstart = e.getX();
        ystart = e.getY();
		xend = e.getX();
        yend = e.getY();
        singleelemclicked = false;
        for (int k = 0; k< petrinetz.getListSize(); k++) {
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

    private void panelDragged(MouseEvent e) {
        IPetriNamedElements choosenElem;
        if (toModifyElements.size()==0) {
        	chooseFrameWidth = e.getX()-xend;
        	chooseFrameHeight = e.getY()-yend;
        	refresh();
        }
        else {
        	for (int p = 0; p<toModifyElements.size();p++) {
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
        	xend = e.getX();
        	yend = e.getY();
        }    	
    }
    
    public void startnew() {
    	petrinetz.petriElements.clear();
    	removeAll();
    	revalidate();
    	refresh();
    }
    
    public void refresh() {
        repaint();
    }
}
