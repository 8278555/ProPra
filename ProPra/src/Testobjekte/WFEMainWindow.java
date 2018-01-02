package Testobjekte;
import Release.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private WFEPanel panel;
    private BorderLayout layout;
    private File selectedPath;
    private File selectedFile;
    private WFEModelNet petrinetz;
    private JScrollPane scroller;
	private JPanel scrollpanel;
	
	/**
	 * @wbp.parser.constructor
	 */
	
	
	public WFEMainWindow(String title) {
		setPreferredSize(new Dimension(700, 500));
		this.setTitle(title);		
		initComponents();
        petrinetz = new WFEModelNet();
    	if (panel != null) {
            this.remove(panel);
            this.validate();
    	};
    	panel = new WFEPanel(petrinetz);
    	panel.startnew();    	
		panel.setPreferredSize(new Dimension (600, 500));
        scroller.add(panel);
		scroller.setViewportView(panel);
    	this.panel.refresh();
    	panel.startnew();
    	this.update(getGraphics());
	}
	
	public WFEMainWindow(String title, WFEPanel editpanel, WFEModelNet netz) {
		setPreferredSize(new Dimension(700, 500));
		this.setTitle(title);
		this.panel = editpanel;
		initComponents();
		panel.setPreferredSize(new Dimension (500, 500));
		scroller = new JScrollPane(editpanel);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setBounds(50, 30, 300, 50);
        
		this.getContentPane().add(scroller, BorderLayout.CENTER);
        scroller.add(panel);
		scroller.setViewportView(panel);
        
        scrollpanel = new JPanel();
        scrollpanel.setLayout(new BorderLayout());
        scrollpanel.add(editpanel, BorderLayout.CENTER);
        scrollpanel.setOpaque(true);
        scroller.setViewportView(scrollpanel);
    }
    

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        	@Override
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
        jMenuItemNewPlace.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Stellle angeben", "z.B. S1");
        		int newx = Integer.parseInt(JOptionPane.showInputDialog("Bitte die X-Koordinate der neuen Stelle angeben", "z.B. 75"));
        		int newy = Integer.parseInt(JOptionPane.showInputDialog("Bitte die Y-Koordinate der neuen Stelle angeben", "z.B. 75"));
        		petrinetz.addPlace(newid);
        		petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
        		panel.refresh();
        	}
       });

        jMenuItemNewTransition = new JMenuItem();
        jMenuItemNewTransition.setText("Transition");
        jMenuItemNewTransition.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		String newid = JOptionPane.showInputDialog("Bitte die ID der neuen Transition angeben", "z.B. T1");
        		int newx = Integer.parseInt(JOptionPane.showInputDialog("Bitte die X-Koordinate der neuen Transition angeben", "z.B. 75"));
        		int newy = Integer.parseInt(JOptionPane.showInputDialog("Bitte die Y-Koordinate der neuen Transition angeben", "z.B. 75"));
        		petrinetz.addTransition(newid);
        		petrinetz.setPosition(newid, Integer.toString(newx), Integer.toString(newy));
        		panel.refresh();
        	}
       });


        jMenuItemNewArc = new JMenuItem();
        jMenuItemNewArc.setText("Kante");

        jMenuItemNetTestRun = new JMenuItem();
        jMenuItemNetTestRun.setText("Testlauf");
        
        jMenuItemNetSettings = new JMenuItem();
        jMenuItemNetSettings.setText("Einstellungen");
        jMenuItemNetSettings.addActionListener(new ActionListener() {
        	@Override
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
        
        
        scroller = new JScrollPane();
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setBounds(50, 30, 300, 50); 
        scroller.setOpaque(true);
        
        layout = new BorderLayout();
        
        getContentPane().setLayout(layout);
        getContentPane().add(jMenuBar1, BorderLayout.NORTH);
        getContentPane().add(scroller, BorderLayout.CENTER);    	    	
        pack();
    }                        


    private void jMenuItemFileNewActionPerformed(ActionEvent evt) {                                        
        petrinetz = new WFEModelNet();
    	if (panel != null) {
            this.remove(panel);
            this.validate();
    	};
    	panel = new WFEPanel(petrinetz);
    	panel.startnew();    	
		panel.setPreferredSize(new Dimension (600, 500));
        scroller.add(panel);
		scroller.setViewportView(panel);
    	this.panel.refresh();
    	panel.startnew();
    	this.update(getGraphics());
    	pack();
    }                                       

    public void jMenuItemFileOpenActionPerformed(ActionEvent evt) {                                                
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.pnml", "pnml"));
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
            if (panel != null) {
            	panel.startnew();
                remove(panel);
                revalidate();
            };
            PNMLParser pnmlParser = new MyPNMLParser(selectedFile, petrinetz);
            pnmlParser.initParser();
            pnmlParser.parse();
            panel = new WFEPanel(petrinetz);
        	int maxX = 0;
        	int maxY = 0;
        	for (int k = 0; k< petrinetz.getListSize(); k++) {
        		if (petrinetz.petriElements.get(k) instanceof IPetriNamedElements) {
        			IPetriNamedElements currElem = (IPetriNamedElements) petrinetz.petriElements.get(k);
        	        if (currElem.getPositionx() > maxX) {maxX = currElem.getPositionx();}
        	        if (currElem.getPositiony() > maxY) {maxY = currElem.getPositiony();}
        	    }
        	}
        	panel.setPreferredSize(new Dimension ((maxX+40), (maxY+40)));
            scroller.add(panel);
            scroller.setViewportView(panel);
        	this.panel.refresh();
        	this.update(getGraphics());
        	pack();
        }
    }                                       

    private void jMenuItemFileSaveActionPerformed(ActionEvent evt) {                                        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.pnml", "pnml"));
        if (selectedFile == null) {
            if (selectedPath == null) {
            	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            	}
            else {
            	fileChooser.setCurrentDirectory(selectedPath);
            }
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                selectedPath = fileChooser.getCurrentDirectory();
                if (!selectedFile.toString().matches(".*.pnml.*")) {
                    File file2 = new File(selectedFile.toString()+".pnml");
                    selectedFile.renameTo(file2);
                }
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.pnml", "pnml"));
        if (selectedPath == null) {
        	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        	}
        else {
        	fileChooser.setCurrentDirectory(selectedPath);
        }
        int result = fileChooser.showSaveDialog(this);
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