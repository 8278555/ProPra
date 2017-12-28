package Testobjekte;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.*;
import Release.*;

public class WFEPanel extends JPanel {

    private IPetriNamedElements sourcepoint;
    private IPetriNamedElements destpoint;
    private WFEModelNet petrinetz;
    private int xstart;
    private int ystart;
    private int elemsizefactor;
    public ArrayList<IPetriElements> toModifyElements = new ArrayList<IPetriElements>();

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
            		if (verschiebemodus == false) {
        				for (int k = 0; k< petrinetz.getListSize(); k++) {
                            if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                            	IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                                if (xstart <= currElem.getPositionx() && e.getX() >= (currElem.getPositionx()+elemsizefactor)&& ystart <= currElem.getPositiony() && e.getY() >= (currElem.getPositiony()+elemsizefactor)) {
                                    System.out.println("Nr " + k + ": X: " + currElem.getPositionx() + " Y: " + currElem.getPositiony());
                                	toModifyElements.add(currElem);
                                	verschiebemodus = true;
                                }
                            }
                        }
        			}
        			else {
        				verschiebemodus = false;
        				toModifyElements.clear();
        			}
            		xstart = 0;
        			ystart = 0;
        			chooseFrameWidth = 0;
        			chooseFrameHeight = 0;
        			refresh();
                  }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                xstart = e.getX();
                ystart = e.getY();
                for (int k = 0; k< petrinetz.getListSize(); k++) {
                    if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                        IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                        if (xstart >= currElem.getPositionx() && xstart <= (currElem.getPositionx()+elemsizefactor)&& ystart >= currElem.getPositiony() && ystart <= (currElem.getPositiony()+elemsizefactor)) {
                            if (toModifyElements.isEmpty()) {
                            	toModifyElements.add(currElem);	
                            }                       	
                            verschiebemodus = true;
                            break;
                        }
                    }
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            /*/    if(e.getButton() == MouseEvent.BUTTON1) {
                    JOptionPane.showMessageDialog(null, 
                            "Pressed Linksklick auf X: " + xstart + " Y: " + ystart,
                            "MouseButton", 
                            JOptionPane.PLAIN_MESSAGE);
                  }
                  if(e.getButton() == MouseEvent.BUTTON2) {
                      JOptionPane.showMessageDialog(null, 
                              "Mittelklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);
                    }
                  if(e.getButton() == MouseEvent.BUTTON3) {
                      JOptionPane.showMessageDialog(null, 
                              "Rechtsklick",
                              "MouseButton", 
                              JOptionPane.PLAIN_MESSAGE);
                     }
            /*/}
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                panelDragged(e);
            }
        });
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
        g.drawRect(xstart, ystart, chooseFrameWidth, chooseFrameHeight);
    }
    
    
    protected void drawPlace(Graphics g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    		}
    	}
    }
    protected void drawTransition(Graphics g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
    			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
    			g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
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
    
    private void panelDragged(MouseEvent e) {
        IPetriNamedElements choosenElem;
        if (toModifyElements.size()==0) {
        	chooseFrameWidth = e.getX()-xstart;
        	chooseFrameHeight = e.getY()-ystart;
        	refresh();
        }
        else {
        	for (int p = 0; p<toModifyElements.size();p++) {
            	choosenElem = (IPetriNamedElements) toModifyElements.get(p);
                for (int l = 0; l< petrinetz.getListSize(); l++) {
                    if (petrinetz.petriElements.get(l) instanceof IPetriNamedElements) {
                        IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(l);
                        if (currElem.GetID().equals(choosenElem.GetID())) {
                        	int newx = currElem.getPositionx() + (e.getX() - xstart);
                        	int newy = currElem.getPositiony() + (e.getY() - ystart);
                        	currElem.setPosition((newx), (newy));
                            refresh();
                        }
                    }
                }
            }
        	xstart = e.getX();
        	ystart = e.getY();
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
