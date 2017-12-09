package Testobjekte;
import Release.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

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
    private File selectedPath;
    private File selectedFile;
    private WFEModelNet petrinetz;
    
	private static final long serialVersionUID = 1L;
	
	public WFEMainWindow(String title) {
		setPreferredSize(new Dimension(500, 500));
		this.setTitle(title);
		initComponents();
	}
	
	public WFEMainWindow(String title, WFEPanel testpanel, WFEModelNet netz) {
		setPreferredSize(new Dimension(800, 800));
		this.setTitle(title);
		this.panel = testpanel;
		initComponents();
        this.add(panel, BorderLayout.CENTER);
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
        /*        jMenuItemNewPlace.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Stellle angeben", "z.B. S1");
        		int newx = Integer.parseInt(JOptionPane.showInputDialog("Bitte die X-Koordinate der neuen Stellle angeben", "z.B. 75"));
        		int newy = Integer.parseInt(JOptionPane.showInputDialog("Bitte die Y-Koordinate der neuen Stellle angeben", "z.B. 75"));
        		petrinetz.addPlace(newid);
        		petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
        		panel.refresh();
        	}
       });
*/
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
        		int sizefactor = Integer.parseInt(JOptionPane.showInputDialog("Bitte gewünschten Größenfaktor angeben", "z.B. 25"));
        		panel.setelemsizefactor(sizefactor);
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
        this.setLayout(layout);
        this.add(jMenuBar1, BorderLayout.NORTH);
        //getContentPane().setLayout(layout);
        //getContentPane().add(jMenuBar1, BorderLayout.NORTH);
        //getContentPane().add(panel, BorderLayout.CENTER);        
        pack();
    }                        


    private void jMenuItemFileNewActionPerformed(ActionEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem3MouseClicked() Eintrag NEU");        // TODO add your handling code here:
        WFEModelNet petrinetz = new WFEModelNet();
    	WFEPanel editpanel = new WFEPanel(petrinetz);
    	this.remove(editpanel);
    	this.validate();
    	panel = editpanel;
    	panel.startnew();
    	this.add(editpanel, BorderLayout.CENTER);
    	this.panel.refresh();
    	panel.startnew();
    	this.update(getGraphics());
    	pack();
    }                                       

    public void jMenuItemFileOpenActionPerformed(ActionEvent evt) {                                                
        JFileChooser fileChooser = new JFileChooser();
        if (selectedPath == null) {
        	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        	}
        else {
        	fileChooser.setCurrentDirectory(selectedPath);
        }
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            selectedPath = fileChooser.getCurrentDirectory();
            petrinetz = new WFEModelNet();
            PNMLParser pnmlParser = new MyPNMLParser(selectedFile, petrinetz);
            pnmlParser.initParser();
            pnmlParser.parse();
        	WFEPanel editpanel = new WFEPanel(petrinetz);
        	panel = editpanel;
        	this.add(editpanel, BorderLayout.CENTER);
        	this.panel.refresh();
        	pack();
        }
    }                                       

    private void jMenuItemFileSaveActionPerformed(ActionEvent evt) {                                        
        System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem4MouseClicked() Eintrag Save");        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (selectedFile == null) {
            if (selectedPath == null) {
            	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            	}
            else {
            	fileChooser.setCurrentDirectory(selectedPath);
            }
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                selectedPath = fileChooser.getCurrentDirectory();
            }
        }
        PNMLWriter pnmlWriter = new PNMLWriter(selectedFile);
        pnmlWriter.startXMLDocument();
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
    			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
    			pnmlWriter.addTransition(transition.GetID(), transition.getName(), Integer.toString(transition.getPositionx()), Integer.toString(transition.getPositiony()));
    		}
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			pnmlWriter.addPlace(stelle.GetID(), stelle.getName(), Integer.toString(stelle.getPositionx()), Integer.toString(stelle.getPositiony()), stelle.GetToken());
    		}
    		if (petrinetz.petriElements.get(i) instanceof WFEModelArc) {
    			WFEModelArc kante = (WFEModelArc) petrinetz.petriElements.get(i);
    			pnmlWriter.addArc(kante.GetID(), kante.GetSource(), kante.GetTarget());
    		}
    	}
    	pnmlWriter.finishXMLDocument();
    }                                       

    private void jMenuItemFileSaveAsActionPerformed(ActionEvent evt) {                                        
    	System.out.println("my.numberaddition.NumberAdditionUI.jMenuItem6MouseClicked() Eintrag SaveAs");        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (selectedPath == null) {
        	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        	}
        else {
        	fileChooser.setCurrentDirectory(selectedPath);
        }
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            selectedPath = fileChooser.getCurrentDirectory();
        }
        PNMLWriter pnmlWriter = new PNMLWriter(selectedFile);
        pnmlWriter.startXMLDocument();
    	for (int i = 0; i< petrinetz.getListSize(); i++) {
    		if (petrinetz.petriElements.get(i) instanceof WFEModelTransition) {
    			WFEModelTransition transition = (WFEModelTransition) petrinetz.petriElements.get(i);
    			pnmlWriter.addTransition(transition.GetID(), transition.getName(), Integer.toString(transition.getPositionx()), Integer.toString(transition.getPositiony()));
    		}
    		if (petrinetz.petriElements.get(i) instanceof WFEModelPlace) {
    			WFEModelPlace stelle = (WFEModelPlace) petrinetz.petriElements.get(i);
    			pnmlWriter.addPlace(stelle.GetID(), stelle.getName(), Integer.toString(stelle.getPositionx()), Integer.toString(stelle.getPositiony()), stelle.GetToken());
    		}
    		if (petrinetz.petriElements.get(i) instanceof WFEModelArc) {
    			WFEModelArc kante = (WFEModelArc) petrinetz.petriElements.get(i);
    			pnmlWriter.addArc(kante.GetID(), kante.GetSource(), kante.GetTarget());
    		}
    	}
    	pnmlWriter.finishXMLDocument();
    }                                       

                                           
    private void jMenuItemFileCloseActionPerformed (ActionEvent evt) {
    	this.dispose();
    }


}