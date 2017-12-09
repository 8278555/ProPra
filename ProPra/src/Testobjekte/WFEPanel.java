package Testobjekte;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import Release.*;

public class WFEPanel extends JPanel {

    private IPetriNamedElements sourcepoint;
    private IPetriNamedElements destpoint;
    private WFEModelNet petrinetz;
    private int startx;
    private int starty;
    private String elemToMove;
    private int elemsizefactor;
    
	public WFEPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
		elemsizefactor = 20;
        this.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		panelClicked(evt);
        	}
        	public void mousePressed(MouseEvent evt2) {
            	panelPressed(evt2);
        	}
        	public void mouseReleased(MouseEvent evt3) {
        		panelReleased(evt3);
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
        Graphics2D g2 = (Graphics2D) g;
        drawPlace(g2);
        drawTransition(g2);
        drawArc(g2);
    }
    
    protected void drawPlace(Graphics2D g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			g.fillOval(stelle.getPositionx(), stelle.getPositiony(), elemsizefactor, elemsizefactor);
    		}
    	}
    }
    protected void drawTransition(Graphics2D g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
    			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
    			g.fillRect(transition.getPositionx(), transition.getPositiony(), elemsizefactor, elemsizefactor);
    		}
    	}
    }
    
    protected void drawArc(Graphics2D g) {
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
            }
        }
    }
    
    private String panelClicked(MouseEvent evt) {  	
    	int xpressed = evt.getX();
    	int ypressed = evt.getY();
    	String choosenElem;
    	choosenElem = "";
    	for (int k = 0; k< petrinetz.getListSize(); k++) {
    		if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
    			IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
    			if (xpressed >= currElem.getPositionx() && xpressed <= (currElem.getPositionx()+elemsizefactor)&& ypressed >= currElem.getPositiony() && ypressed <= (currElem.getPositiony()+elemsizefactor)) {
    				choosenElem = currElem.GetID();
    			}
    		}
    	}
    	return choosenElem;
    }
    
    private void panelPressed(MouseEvent evt2) {
    	startx = evt2.getX();
		starty = evt2.getY();
		elemToMove = panelClicked(evt2);
    }

    private void panelReleased(MouseEvent evt3) {
    	for (int l = 0; l< petrinetz.getListSize(); l++) {
    		if (petrinetz.petriElements.get(l) instanceof IPetriNamedElements) {
    			IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(l);
    			if (currElem.GetID().equals(elemToMove)) {
    				currElem.setPosition((evt3.getX()-elemsizefactor/2), (evt3.getY()-elemsizefactor/2));
    				refresh();
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
    	repaint();
    }
}
