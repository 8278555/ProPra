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
    private int elemsizefactor;
    public ArrayList<IPetriElements> toModifyElements;
    
	public WFEPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
		elemsizefactor = 20;
		this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                xstart = e.getX();
                ystart = e.getY();
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
                if(e.getButton() == MouseEvent.BUTTON1) {
                    JOptionPane.showMessageDialog(null, 
                            "Linksklick",
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
            }
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
        int xpressed = e.getX();
        int ypressed = e.getY();
        String choosenElem;
        choosenElem = "";
        for (int k = 0; k< petrinetz.getListSize(); k++) {
            if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
                IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
                if (xpressed >= currElem.getPositionx() && xpressed <= (currElem.getPositionx()+elemsizefactor)&& ypressed >= currElem.getPositiony() && ypressed <= (currElem.getPositiony()+elemsizefactor)) {
                    choosenElem = currElem.GetID();
                }
                else {
                    //TODO: Rahmen zeichnen und alle Elemente im Rahmen auswählen
                }
            }
        }
        for (int l = 0; l< petrinetz.getListSize(); l++) {
            if (petrinetz.petriElements.get(l) instanceof IPetriNamedElements) {
                IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(l);
                if (currElem.GetID().equals(choosenElem)) {
                    currElem.setPosition((e.getX()-elemsizefactor/2), (e.getY()-elemsizefactor/2));
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
