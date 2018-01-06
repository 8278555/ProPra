package Testobjekte;

import java.awt.event.*;
import javax.swing.*;
import Release.*;

public class RightButtonPopUp extends JPopupMenu {

	//JMenuItem anItem;
	private JMenu jMenuPlace;
	private JMenu jMenuTransition;
	private JMenu jMenuArc;
    private JMenuItem jMenuItemNewPlace;
    private JMenuItem jMenuItemNewTransition;
    private JMenuItem jMenuChangePlaceName;
    private JMenuItem jMenuChangePlaceMarking;
    private JMenuItem jMenuChangeTransitionName;
    private JMenuItem jMenuItemNewArc;

	public RightButtonPopUp(MouseEvent f, WFEModelNet petrinetz, WFEPanel panel) {
		jMenuPlace = new JMenu("Stelle");
		jMenuTransition = new JMenu("Transition");
		jMenuArc = new JMenu("Kante");
		jMenuItemNewPlace = new JMenuItem("Neue Stelle");
		jMenuItemNewPlace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Stelle angeben", "z.B. S1");
				if (newid == null) {
					return;
					}
				int newx = f.getX();
				int newy = f.getY();
				petrinetz.addPlace(newid);
				petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
				panel.refresh();
			}
		});
		jMenuChangePlaceName = new JMenuItem("Name ändern");
		jMenuChangePlaceName.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < petrinetz.getListSize();i++) {
					if (petrinetz.petriElements.get(i) instanceof IPlace) {
						IPlace stelle = (IPlace) petrinetz.petriElements.get(i);
						if (f.getX() >= stelle.getPositionx() && f.getY() <= (stelle.getPositionx()+panel.getElemsizefactor())&& f.getY() >= stelle.getPositiony() && f.getY() <= (stelle.getPositiony()+panel.getElemsizefactor())) {
							String newname = JOptionPane.showInputDialog("Bitte den neuen Namen der Stelle angeben", "z.B. Stelle 1"); 
							if (newname!=null) {
								stelle.setName(newname);
								panel.refresh();
							};
							
						}
					}
				}
				
			}
		});
		jMenuChangePlaceMarking = new JMenuItem("Markierung ändern");
		jMenuChangePlaceMarking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < petrinetz.getListSize();i++) {
					if (petrinetz.petriElements.get(i) instanceof IPlace) {
						IPlace stelle = (IPlace) petrinetz.petriElements.get(i);
						if (f.getX() >= stelle.getPositionx() && f.getY() <= (stelle.getPositionx()+panel.getElemsizefactor())&& f.getY() >= stelle.getPositiony() && f.getY() <= (stelle.getPositiony()+panel.getElemsizefactor())) {
							String newMark = JOptionPane.showInputDialog("Bitte die neue Markierung der Stelle angeben", stelle.GetToken()); 
							if (newMark!=null) {
								stelle.SetToken(newMark);
								panel.refresh();
							};
							
						}
					}
				}
			}
		});
		jMenuItemNewTransition = new JMenuItem("Neue Transition");
		jMenuItemNewTransition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Transition angeben", "z.B. T1");
				if (newid == null) {
					return;
					}
				int newx = f.getX();
				int newy = f.getY();
				petrinetz.addTransition(newid);
				petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
				panel.refresh();
			}
		});
		jMenuChangeTransitionName = new JMenuItem("Name ändern");
		jMenuChangeTransitionName.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < petrinetz.getListSize();i++) {
					if (petrinetz.petriElements.get(i) instanceof ITransition) {
						ITransition stelle = (ITransition) petrinetz.petriElements.get(i);
						if (f.getX() >= stelle.getPositionx() && f.getY() <= (stelle.getPositionx()+panel.getElemsizefactor())&& f.getY() >= stelle.getPositiony() && f.getY() <= (stelle.getPositiony()+panel.getElemsizefactor())) {
							String newname = JOptionPane.showInputDialog("Bitte den neuen Namen der Transition angeben", "z.B. Transition 1"); 
							if (newname!=null) {
								stelle.setName(newname);
								panel.refresh();
							};
							
						}
					}
				}
				
			}
		});

		jMenuItemNewArc = new JMenuItem("Neue Kante");
		jMenuItemNewArc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (petrinetz.getListSize()<2) {
					JOptionPane.showMessageDialog(getRootPane(), "Zu wenig Elemente für eine Kante vorhanden!!!", "Warnung", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				panel.setNewArcId(JOptionPane.showInputDialog("Bitte die ID der neuen Kante angeben, danach nacheinander Quelle und Ziel anklicken.", "z.B. K1"));
				panel.setArcAddMode(true);
				}; 
			
		});
		
		jMenuPlace.add(jMenuItemNewPlace);
		for (int i = 0; i < petrinetz.getListSize();i++) {
			if (petrinetz.petriElements.get(i) instanceof IPlace) {
				IPlace stelle = (IPlace) petrinetz.petriElements.get(i);
				if ((f.getX() >= stelle.getPositionx()) && (f.getX() <= (stelle.getPositionx()+panel.getElemsizefactor())) && ( f.getY() >= stelle.getPositiony()) && (f.getY() <= (stelle.getPositiony()+panel.getElemsizefactor()))) {
					jMenuPlace.add(jMenuChangePlaceName);
					jMenuPlace.add(jMenuChangePlaceMarking);
				}
			}
		}
		add(jMenuPlace);
	    jMenuTransition.add(jMenuItemNewTransition);
		for (int i = 0; i < petrinetz.getListSize();i++) {
			if (petrinetz.petriElements.get(i) instanceof ITransition) {
				ITransition stelle = (ITransition) petrinetz.petriElements.get(i);
				if ((f.getX() >= stelle.getPositionx()) && (f.getX() <= (stelle.getPositionx()+panel.getElemsizefactor())) && ( f.getY() >= stelle.getPositiony()) && (f.getY() <= (stelle.getPositiony()+panel.getElemsizefactor()))) {
					jMenuTransition.add(jMenuChangeTransitionName);
				}
			}
		}
	    add(jMenuTransition);
	    jMenuArc.add(jMenuItemNewArc);
	    add(jMenuArc);
		
	}

	public RightButtonPopUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public RightButtonPopUp(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
