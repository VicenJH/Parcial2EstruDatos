package org.example.Arboles;

public class NodeArbol<T> {

    private T elemento;
    private NodeArbol<T> izquierdo;
    private NodeArbol<T> derecho;
    private NodeArbol<T> padre;

    public NodeArbol(T elemento, NodeArbol<T> padre) {
        this.padre = padre;
        this.elemento = elemento;
    }

    public NodeArbol(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NodeArbol<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodeArbol<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodeArbol<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodeArbol<T> derecho) {
        this.derecho = derecho;
    }

    public NodeArbol<T> getPadre() {
        return padre;
    }

    public void setPadre(NodeArbol<T> padre) {
        this.padre = padre;
    }

}
