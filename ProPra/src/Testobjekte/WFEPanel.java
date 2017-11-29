package Testobjekte;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import Release.*;


public class WFEPanel extends JPanel {

    private IPetriNamedElements sourcepoint;
    private IPetriNamedElements destpoint;

    
	public WFEPanel() {
		// TODO Auto-generated constructor stub
	}

	public WFEPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WFEPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WFEPanel(WFEModelNet petrinetz) {
		this.petrinetz = petrinetz;
	}

	
	public WFEPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	private WFEModelNet petrinetz;
	
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
    			g.drawOval(stelle.getPositionx(), stelle.getPositiony(), 20, 20);
    		}
    	}
    }
    protected void drawTransition(Graphics2D g) {
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
    			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
    			g.drawRect(transition.getPositionx(), transition.getPositiony(), 20, 20);
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
                g.drawLine((sourcepoint.getPositionx()+10), (sourcepoint.getPositiony()+10), (destpoint.getPositionx()+10), (destpoint.getPositiony()+10));
            }
        }
    }
    public void refresh() {
        repaint();
    }
}
