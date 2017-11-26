package Testobjekte;
import java.awt.*;
import java.util.concurrent.TimeUnit;
public class TestCanvas {


	public static void main(String[] args) throws InterruptedException {
		Frame f = new Frame();
		Canvastester testobjekt = new Canvastester("Testtext");
		f.setSize(450, 450);
		f.add(testobjekt);
		f.setVisible(true);
		TimeUnit.SECONDS.sleep(5); 
		testobjekt.setText("Testtext2");
		TimeUnit.SECONDS.sleep(5);
		f.dispose();
	}

}
