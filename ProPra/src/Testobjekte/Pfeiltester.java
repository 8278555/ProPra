package Testobjekte;

import java.awt.Point;

import Release.WFEModelNet;

public class Pfeiltester {

	

	public static void main(String[] args) {
    	Pfeiltesterpanel testpanel = new Pfeiltesterpanel();
		Pfeiltesterframe testfenster = new Pfeiltesterframe("Fenstertext", testpanel);
		testfenster.setVisible(true);
	}


}
