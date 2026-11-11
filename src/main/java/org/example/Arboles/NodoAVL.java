package org.example.Arboles;

public class NodoAVL<T extends Comparable<T>> {
    private T elemento;
    private int altura;
    private int fe; // Factor de Equilibrio
    private NodoAVL<T> izquierdo;
    private NodoAVL<T> derecho;

    public NodoAVL(T elemento) {
        this.elemento = elemento;
        this.altura = 1;
        this.fe = 0;
    }

    public T getElemento() { return elemento; }
    public void setElemento(T elemento) { this.elemento = elemento; }

    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }

    public int getFe() { return fe; }
    public void setFe(int fe) { this.fe = fe; }

    public NodoAVL<T> getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoAVL<T> izquierdo) { this.izquierdo = izquierdo; }

    public NodoAVL<T> getDerecho() { return derecho; }
    public void setDerecho(NodoAVL<T> derecho) { this.derecho = derecho; }
}