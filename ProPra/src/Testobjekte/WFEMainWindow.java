package Testobjekte;
import Release.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WFEMainWindow extends JFrame {

    private JMenuBar jMenuBar1;
    private JMenu jMenuFile;
    private JMenuItem jMenuItemFileNew;
    private JMenuItem jMenuItemFileOpen;
    private JMenuItem jMenuItemFileSave;
    private JMenuItem jMenuItemFileSaveAs;
    private JMenuItem jMenuItemFileClose;
    private JMenu jMenuNet;
    private JMenu jMenuNetNew;
    private JMenuItem jMenuItemNewPlace;
    private JMenuItem jMenuItemNewTransition;
    private JMenuItem jMenuItemNewArc;
    private JMenuItem jMenuItemNetTestRun;
    private JMenuItem jMenuItemNetSettings;
    private JScrollBar jScrollBar1;
    private JScrollBar jScrollBar2;
    private WFEPanel panel;
    private BorderLayout layout;
	
	private static final long serialVersionUID = 1L;
	
	public WFEMainWindow(String title, WFEPanel testpanel, WFEModelNet netz) {
		setPreferredSize(new Dimension(500, 500));
		this.setTitle(title);
		this.panel = testpanel;
		initComponents();        
    }
    
    //public void paintComponent(Graphics2D g) {
    //	super.paintComponents(g);
    //	g.drawRect(150, 150, 50, 50);
    //}

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    	jScrollBar1 = new JScrollBar();
    	jScrollBar1.setOrientation(JScrollBar.VERTICAL);
    	jScrollBar2 = new JScrollBar();
        jScrollBar2.setOrientation(JScrollBar.HORIZONTAL);

        jMenuBar1 = new JMenuBar();

        jMenuFile = new JMenu();
        jMenuFile.setText("Datei");

        jMenuItemFileNew = new JMenuItem();
        jMenuItemFileNew.setText("Neu");
        jMenuItemFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jMenuItemFileNewActionPerformed(evt);
            }
        });

        jMenuItemFileOpen = new JMenuItem();
        jMenuItemFileOpen.setText("Öffnen");
        jMenuItemFileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jMenuItemFileOpenActionPerformed(evt);
            }
        });

        jMenuItemFileSave = new JMenuItem();
        jMenuItemFileSave.setText("Speichern");
        jMenuItemFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jMenuItemFileSaveActionPerformed(evt);
            }
        });

        jMenuItemFileSaveAs = new JMenuItem();
        jMenuItemFileSaveAs.setText("Speichern unter");
        jMenuItemFileSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jMenuItemFileSaveAsActionPerformed(evt);
            }
        });

        jMenuItemFileClose = new JMenuItem();
        jMenuItemFileClose.setText("Beenden");
        jMenuItemFileClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		jMenuItemFileCloseActionPerformed(evt);
        	}
        });

        jMenuNet = new JMenu();
        jMenuNet.setText("Netz");

        jMenuNetNew = new JMenu();
        jMenuNetNew.setText("Neu");

        jMenuItemNewPlace = new JMenuItem();
        jMenuItemNewPlace.setText("Stelle");

        jMenuItemNewTransition = new JMenuItem();
        jMenuItemNewTransition.setText("Transition");

        jMenuItemNewArc = new JMenuItem();
        jMenuItemNewArc.setText("Kante");

        jMenuItemNetTestRun = new JMenuItem();
        jMenuItemNetTestRun.setText("Testlauf");
        
        jMenuItemNetSettings = new JMenuItem();
        jMenuItemNetSettings.setText("Einstellungen");
        jMenuItemNetSettings.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		panel.setelemsizefactor(50);
        	}
        });
        
        jMenuFile.add(jMenuItemFileNew);
        jMenuFile.add(jMenuItemFileOpen);
        jMenuFile.add(jMenuItemFileSave);
        jMenuFile.add(jMenuItemFileSaveAs);
        jMenuFile.add(jMenuItemFileClose);

        jMenuNetNew.add(jMenuItemNewArc);
        jMenuNetNew.add(jMenuItemNewTransition);
        jMenuNetNew.add(jMenuItemNewPlace);

        jMenuNet.add(jMenuNetNew);
        jMenuNet.add(jMenuItemNetTestRun);
        jMenuNet.add(jMenuItemNetSettings);
        
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(jMenuNet);

        layout = new BorderLayout();
        
        this.setTitle("Fenstertext");
        this.setLayout(layout);
        this.add(jMenuBar1, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        //getContentPane().setLayout(layout);
        //getContentPane().add(jMenuBar1, BorderLayout.NORTH);
        //getContentPane().add(panel, BorderLayout.CENTER);        
        pack();
    }                        


    private void jMenuItemFileNewActionPerformed(ActionEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem3MouseClicked() Eintrag NEU");        // TODO add your handling code here:
    }                                       

    public void jMenuItemFileOpenActionPerformed(ActionEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem2MouseClicked() Eintrag Open");
        JOptionPane.showMessageDialog(null, "Eggs aren't supposed to be green."); // TODO add your handling code here:
    }                                       

    private void jMenuItemFileSaveActionPerformed(ActionEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem4MouseClicked() Eintrag Save");        // TODO add your handling code here:
    }                                       

    private void jMenuItemFileSaveAsActionPerformed(ActionEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem6MouseClicked() Eintrag SaveAs");        // TODO add your handling code here:
    }                                       
    private void jMenuItemFileCloseActionPerformed (ActionEvent evt) {
    	this.dispose();
    }


}