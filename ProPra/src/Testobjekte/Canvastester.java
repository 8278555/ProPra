package Testobjekte;
import java.awt.*;

public class Canvastester extends Canvas{
	
	private String word;
	
	public Canvastester(String s) {
		word = s;
	}

	public void paint(Graphics g) {
		g.drawString(word, 3, 200);
	}
	
	public void setText(String s) {
		word = s;
		repaint();
	}
}
