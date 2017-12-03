package Testobjekte;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Pfeiltesterframe extends JFrame {

    private Pfeiltesterpanel panel;
    private BorderLayout layout;

	
	public Pfeiltesterframe(String title, Pfeiltesterpanel testpanel) {
		
		this.panel = testpanel;
		this.layout = new BorderLayout();
		this.add(panel, BorderLayout.CENTER);
		this.setSize(new Dimension(500, 500));
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	public Pfeiltesterframe() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public Pfeiltesterframe(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Pfeiltesterframe(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Pfeiltesterframe(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
