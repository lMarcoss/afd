package afd;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lmarcoss
 */
public class Vertice {
    public static Color c;
    //Coordenadas
    private int numero, posX, posY;
    private int alto = 25;
    private int ancho = 25;

    public Vertice() {
    }

    public Vertice(int numero, int posX, int posY) {
        this.numero = numero;
        this.posX = posX;
        this.posY = posY;
    }

    public static void setC(Color c) {
        Vertice.c = c;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public static Color getC() {
        return c;
    }

    public int getNumero() {
        return numero;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }
    public void dibujarVertice(Graphics g){
        g.setColor(Color.red);
        g.fillOval(getPosX(),getPosY(),getAlto(),getAncho());
    }
    
    public void DibujarNumero(Graphics g){
        g.setColor(Color.blue);
        g.drawString(String.valueOf(numero),getPosX(),getPosY());
    }
    
}
