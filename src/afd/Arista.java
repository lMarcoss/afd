package afd;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lmarcoss
 */
public class Arista {

    private int numero;
    private int vertice1;
    private int vertice2;
    private int posX1;
    private int posY1;
    private int posX2;
    private int posY2;

    public Arista() {
    }

    public Arista(int numero, int vertice1, int vertice2, int posX1, int posY1, int posX2, int posY2) {
        this.numero = numero;
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.posX1 = posX1;
        this.posY1 = posY1;
        this.posX2 = posX2;
        this.posY2 = posY2;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setVertice1(int vertice1) {
        this.vertice1 = vertice1;
    }

    public void setVertice2(int vertice2) {
        this.vertice2 = vertice2;
    }

    public void setPosX1(int posX1) {
        this.posX1 = posX1;
    }

    public void setPosY1(int posY1) {
        this.posY1 = posY1;
    }

    public void setPosX2(int posX2) {
        this.posX2 = posX2;
    }

    public void setPosY2(int posY2) {
        this.posY2 = posY2;
    }

    public int getNumero() {
        return numero;
    }

    public int getVertice1() {
        return vertice1;
    }

    public int getVertice2() {
        return vertice2;
    }

    public int getPosX1() {
        return posX1;
    }

    public int getPosY1() {
        return posY1;
    }

    public int getPosX2() {
        return posX2;
    }

    public int getPosY2() {
        return posY2;
    }
    
    public void dibujarArista(Graphics g){
        g.setColor(Color.black);
        g.drawLine(posX1, posY1, posX2, posY2);
    }
    
    public void etiquetarArista(Graphics g){
        g.setColor(Color.black);
        g.drawString(String.valueOf(getNumero()), (getPosX1()+getPosX2()/2), (getPosY1()+getPosY2()/2));
    }
}
