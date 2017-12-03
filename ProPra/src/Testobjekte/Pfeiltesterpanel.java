package Testobjekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Pfeiltesterpanel extends JPanel {

    private Math m;
    private Point von, nach; // die zwei Punkte, die die Pfeil-Linie darstellen
    private double phi;      // Rotationswinkel
    private Polygon pfeil;
    private double s;      // Seitenlaenge des Pfeils
    private double xAlt, yAlt;          // x-,y-Werte vor der Rotation,relativ zur Pfeilspitze
    private double xNeu, yNeu;          // x-,y-Werte nach der Rotation,absolut
    private double xSpitze, ySpitze;    // x-,y-Werte der Pfeilspitze
    public Pfeiltesterpanel(Point k1, Point k2, double length) {
        von = k1;
        nach = k2;
        s = length;
        phi = m.atan( ((double)nach.y  -  (double)von.y ) / ((double)nach.x  -  (double)von.x ) );
        if(nach.x < von.x )
            phi = phi + m.PI;
        // den Pfeil zeichnen
        pfeil = new Polygon();
        pfeil.addPoint(nach.x , nach.y );  // Pfeilspitze
        // Die beiden anderen Punkte des Pfeils muessen um den Winkel phi relative zur
        // Pfeilspitze rotiert werden.
        // x_rot = x*cos(phi) - y*sin(phi)
        // y_rot = x*sin(phi) + y*cos(phi)
        xSpitze = (double)nach.x;
        ySpitze = (double)nach.y;
        // erster Punkt
        xAlt = (double)nach.x - s - xSpitze;
        yAlt = (double)nach.y + s/2.5 - ySpitze;
        xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze;
        yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze;
        pfeil.addPoint((int)xNeu, (int)yNeu);
        // zweiter Punkt
        yAlt = (double)nach.y - s/2.5 - ySpitze;
        xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze;
        yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze;
        pfeil.addPoint((int)xNeu, (int)yNeu);
       
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
       
        g2d.setColor(Color.blue);
  }
    
        public void zeichnen(Graphics2D g) {
        g.drawLine(von.x, von.y, nach.x , nach.y );
        g.fillPolygon(pfeil);
    }



}