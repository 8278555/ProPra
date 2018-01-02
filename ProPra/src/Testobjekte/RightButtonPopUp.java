package Testobjekte;

import java.awt.event.*;
import javax.swing.*;
import Release.*;

public class RightButtonPopUp extends JPopupMenu {

	JMenuItem anItem;
    private JMenuItem jMenuItemNewPlace;
    private JMenuItem jMenuItemNewTransition;
    private JMenuItem jMenuItemNewArc;
    

    
	public RightButtonPopUp() {
//		this(new WFEModelNet() 
			
	};

	public RightButtonPopUp(MouseEvent f, WFEModelNet petrinetz, WFEPanel panel) {
		jMenuItemNewPlace = new JMenuItem("Neue Stelle");
		jMenuItemNewPlace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Stelle angeben", "z.B. S1");
				int newx = f.getX();
				int newy = f.getY();
				petrinetz.addPlace(newid);
				petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
				panel.refresh();
			}
		});
		jMenuItemNewTransition = new JMenuItem("Neue Transition");
		jMenuItemNewTransition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Transition angeben", "z.B. T1");
				int newx = f.getX();
				int newy = f.getY();
				petrinetz.addTransition(newid);
				petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
				panel.refresh();
			}
		});
	    add(jMenuItemNewPlace);
	    add(jMenuItemNewTransition);
		
	}

	public RightButtonPopUp(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
