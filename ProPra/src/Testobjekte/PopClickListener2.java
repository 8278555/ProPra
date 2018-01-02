package Testobjekte;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PopClickListener2 extends MouseAdapter {

	public PopClickListener2() {
		// TODO Auto-generated constructor stub
	}
	
	public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger()) {
        	RightButtonPopUp menu = new RightButtonPopUp();
        	menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger()) {
        	RightButtonPopUp menu = new RightButtonPopUp();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
public static void main(String[] args) {
	JFrame test = new JFrame();
	JPanel testpanel = new JPanel();
	testpanel.addMouseListener(new PopClickListener2());
	test.add(testpanel);
	test.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	test.setVisible(true);
}
}
