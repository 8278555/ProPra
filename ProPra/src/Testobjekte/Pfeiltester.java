package Testobjekte;

import javax.swing.JScrollPane;

public class Pfeiltester {

	

	public static void main(String[] args) {
    	Pfeiltesterpanel testpanel = new Pfeiltesterpanel();
    	JScrollPane scroller = new JScrollPane(testpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setBounds(50, 30, 300, 50);

		Pfeiltesterframe testfenster = new Pfeiltesterframe("Fenstertext", scroller, testpanel);
		testfenster.setVisible(true);
	}


}
