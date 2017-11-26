package Testobjekte;
import Release.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.*;

public class WFEMainWindow extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WFEMainWindow(String title, WFEPanel testpanel, WFEModelNet netz) {
		setPreferredSize(new Dimension(500, 500));
		this.setTitle(title);
		this.panel = testpanel;
		initComponents();        
    }
    
    public void paintComponent(Graphics2D g) {
    	super.paintComponents(g);
    	g.drawRect(150, 150, 50, 50);
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    	jScrollBar1 = new JScrollBar();
        jScrollBar2 = new JScrollBar();
        jMenuBar1 = new JMenuBar();
        jMenuFile = new JMenu();
        jMenuItemFileNew = new JMenuItem();
        jMenuItemFileOpen = new JMenuItem();
        jMenuItemFileSave = new JMenuItem();
        jMenuItemFileSaveAs = new JMenuItem();
        jMenuItemFileClose = new JMenuItem();

        jMenuNet = new JMenu();

        jMenuNetNew = new JMenu();
        jMenuItemNewPlace = new JMenuItem();
        jMenuItemNewTransition = new JMenuItem();
        jMenuItemNewArc = new JMenuItem();
        jMenuItemNetTestRun = new JMenuItem();

        jScrollBar1.setOrientation(JScrollBar.VERTICAL);
        jScrollBar2.setOrientation(JScrollBar.HORIZONTAL);
        
        jMenuFile.setText("Datei");

        jMenuItemFileNew.setText("Neu");
        jMenuItemFileOpen.setText("Öffnen");
        jMenuItemFileSave.setText("Speichern");
        jMenuItemFileSaveAs.setText("Speichern unter");
        jMenuItemFileClose.setText("Beenden");

        
        jMenuItemFileNew.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jMenuItemFileNewMouseClicked(evt);
            }
        });
        jMenuItemFileOpen.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jMenuItemFileOpenMouseClicked(evt);
            }
        });
        jMenuItemFileSave.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jMenuItemFileSaveMouseClicked(evt);
            }
        });
        jMenuItemFileSaveAs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jMenuItemFileSaveAsMouseClicked(evt);
            }
        });
        jMenuItemFileClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jMenuItemFileCloseMouseClicked(evt);
            }
        });
        jMenuFile.add(jMenuItemFileNew);
        jMenuFile.add(jMenuItemFileOpen);
        jMenuFile.add(jMenuItemFileSave);
        jMenuFile.add(jMenuItemFileSaveAs);
        jMenuFile.add(jMenuItemFileClose);


        jMenuNet.setText("Netz");

        jMenuNetNew.setText("Neu");

        jMenuItemNewPlace.setText("Stelle");
        jMenuItemNewTransition.setText("Transition");
        jMenuItemNewArc.setText("Kante");
        jMenuNetNew.add(jMenuItemNewArc);
        jMenuNetNew.add(jMenuItemNewTransition);
        jMenuNetNew.add(jMenuItemNewPlace);

        jMenuItemNetTestRun.setText("Testlauf");

        jMenuNet.add(jMenuNetNew);
        jMenuNet.add(jMenuItemNetTestRun);
        
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(jMenuNet);

        setJMenuBar(jMenuBar1);

        
        layout = new BorderLayout();
        myCanvas = new Canvas();

        
        this.setTitle("Fenstertext");
        this.setLayout(layout);
        this.add(jMenuBar1, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);        
        // this.add(myCanvas, BorderLayout.CENTER);
        //panel.add(g);
        pack();
    }                        


    private void jMenuItemFileNewMouseClicked(java.awt.event.MouseEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem3MouseClicked() Eintrag NEU");        // TODO add your handling code here:
    }                                       

    private void jMenuItemFileOpenMouseClicked(java.awt.event.MouseEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem2MouseClicked() Eintrag Open");        // TODO add your handling code here:
    }                                       

    private void jMenuItemFileSaveMouseClicked(java.awt.event.MouseEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem4MouseClicked() Eintrag Save");        // TODO add your handling code here:
    }                                       

    private void jMenuItemFileSaveAsMouseClicked(java.awt.event.MouseEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem6MouseClicked() Eintrag SaveAs");        // TODO add your handling code here:
    }                                       

    private void jMenuItemFileCloseMouseClicked(java.awt.event.MouseEvent evt) {                                        
        this.dispose();        // TODO add your handling code here:
    }                                       


    private JMenu jMenuFile;
    private JMenu jMenuNet;
    private JMenu jMenuNetNew;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItemNetTestRun;
    private JMenuItem jMenuItemFileOpen;
    private JMenuItem jMenuItemFileNew;
    private JMenuItem jMenuItemFileSave;
    private JMenuItem jMenuItemNewPlace;
    private JMenuItem jMenuItemFileSaveAs;
    private JMenuItem jMenuItemNewTransition;
    private JMenuItem jMenuItemNewArc;
    private JMenuItem jMenuItemFileClose;
    private JScrollBar jScrollBar1;
    private JScrollBar jScrollBar2;
    private WFEPanel panel;
    private BorderLayout layout;
    private Canvas myCanvas;
    private WFEModelNet netz;
}