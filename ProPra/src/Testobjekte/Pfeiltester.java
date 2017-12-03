package Testobjekte;

import java.awt.Point;

import Release.WFEModelNet;

public class Pfeiltester {

	

	public static void main(String[] args) {
    	Pfeiltesterpanel testpanel = new Pfeiltesterpanel(new Point(150,50), new Point(250,100), 10.0d);
		Pfeiltesterframe testfenster = new Pfeiltesterframe("Fenstertext", testpanel);
		testfenster.setVisible(true);
	}


}
