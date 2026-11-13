package org.example.Arboles;

import org.example.Colas.Cola;


public class ArbolBinario<T extends Comparable<T>>{

    private int peso;
    private NodeArbol<T> raiz;

    public  ArbolBinario(){
        this.peso = 0;
        this.raiz = null;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public NodeArbol<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodeArbol<T> raiz) {
        this.raiz = raiz;
    }

    public boolean isEmpty(){
        return raiz == null;
    }

    public void recorrerInorden(){
        recorrerInorden(raiz);
    }
    private void recorrerInorden(NodeArbol<T> raiz){
        if(raiz == null) return;
        recorrerInorden(raiz.getIzquierdo());
        System.out.print(raiz.getElemento());
        recorrerInorden(raiz.getDerecho());
    }

    public void recorrerPreorden(){
        recorrerPreorden(raiz);
    }
    private void recorrerPreorden(NodeArbol<T> raiz){
        if(raiz == null) return;
        System.out.print(raiz.getElemento());
        recorrerPreorden(raiz.getIzquierdo());
        recorrerPreorden(raiz.getDerecho());
    }

    public void recorrerPostorden(){
        recorrerPostorden(raiz);
    }
    private void recorrerPostorden(NodeArbol<T> raiz){
        if(raiz == null) return;

        recorrerPostorden(raiz.getIzquierdo());
        recorrerPostorden(raiz.getDerecho());
        System.out.print(raiz.getElemento());
    }



    public int obtenerPeso() {
        return obtenerPeso(raiz);
    }
    private int obtenerPeso(NodeArbol<T> raiz) {
        //Caso base
        if (raiz == null) return 0;

        return 1 + obtenerPeso(raiz.getIzquierdo())+
                obtenerPeso(raiz.getDerecho());
    }

    public int ObtenerAltura(){
        return obtenerAltura(raiz);
    }
    private int obtenerAltura(NodeArbol<T> raiz) {
        if (raiz == null) {
            return 0;
        }

        int alturaIzquierda = obtenerAltura(raiz.getIzquierdo());
        int alturaDerecha = obtenerAltura(raiz.getDerecho());

        return 1 + (Math.max(alturaIzquierda, alturaDerecha));
    }


    public void recorridoAmplitud() {
        if (raiz == null) return;                      // árbol vacío: nada que recorrer

        Cola<NodeArbol<T>> cola = new Cola<>();        // la cola guarda NODOS, no elementos
        cola.encolar(raiz);

        while (!cola.isEmpty()) {
            NodeArbol<T> actual = cola.desencolar();
            System.out.print(actual.getElemento() + " ");

            if (actual.getIzquierdo() != null) {
                cola.encolar(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                cola.encolar(actual.getDerecho());
            }
        }
    }


    public boolean existe(T elemento) {
        return existe(raiz, elemento);
    }

    private boolean existe(NodeArbol<T> raizAux, T elemento) {
        if (raizAux == null) {
            return false;
        }
        int comp = raizAux.getElemento().compareTo(elemento);

        if (comp == 0) return true;

        if (comp > 0) {
            return existe(raizAux.getIzquierdo(), elemento);
        } else {
            return existe(raizAux.getDerecho(), elemento);
        }
    }


    public void insertar(T elemento) {
        raiz= insertar(raiz, elemento,raiz);
    }

    private NodeArbol<T> insertar(NodeArbol<T> raizAux, T elemento, NodeArbol<T> padre) {
        if (raizAux == null) {
            peso++;
            NodeArbol<T> nuevoNodo = new NodeArbol<>(elemento);
            nuevoNodo.setPadre(padre);
            return nuevoNodo;
        } else {
            int comp = raizAux.getElemento().compareTo(elemento);

            if (comp == 0) throw new RuntimeException("Error: ya existe ese elemento");

            if (comp > 0) {
                raizAux.setIzquierdo(insertar(raizAux.getIzquierdo(), elemento, raizAux));
            } else {
                raizAux.setDerecho(insertar(raizAux.getDerecho(), elemento, raizAux));
            }
            return raizAux;
        }
    }

    //Tarea
    public void eliminar(T elemento) {
        raiz = eliminar(raiz, elemento);
        if (raiz != null) {
            raiz.setPadre(null);                      // la raíz nunca tiene padre
        }
    }

    private NodeArbol<T> eliminar(NodeArbol<T> nodo, T elemento) {
        if (nodo == null) {
            return null;                              // no se encontró: nada que eliminar
        }

        int cmp = elemento.compareTo(nodo.getElemento());

        if (cmp < 0) {
            NodeArbol<T> nuevoIzq = eliminar(nodo.getIzquierdo(), elemento);
            nodo.setIzquierdo(nuevoIzq);
            if (nuevoIzq != null) nuevoIzq.setPadre(nodo);

        } else if (cmp > 0) {
            NodeArbol<T> nuevoDer = eliminar(nodo.getDerecho(), elemento);
            nodo.setDerecho(nuevoDer);
            if (nuevoDer != null) nuevoDer.setPadre(nodo);

        } else {                                      // encontré el nodo a eliminar
            // Caso 1: hoja
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            // Caso 2: un solo hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            // Caso 3: dos hijos
            NodeArbol<T> sucesor = minimo(nodo.getDerecho());
            nodo.setElemento(sucesor.getElemento());              // copio el valor
            NodeArbol<T> nuevoDer = eliminar(nodo.getDerecho(), sucesor.getElemento());
            nodo.setDerecho(nuevoDer);                            // elimino el sucesor
            if (nuevoDer != null) nuevoDer.setPadre(nodo);
        }
        return nodo;
    }

    private NodeArbol<T> minimo(NodeArbol<T> nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }


    public NodeArbol<T> obtenerMenor(){
        return obtenerMenor(raiz);
    }

    private NodeArbol<T> obtenerMenor(NodeArbol<T> actual){
        if (actual == null) {
            return null;
        }

        while(actual.getIzquierdo()!=null){
            actual = actual.getIzquierdo();
        }
        return actual;
    }


    public int contarEquiposEnRango(T min, T max) {
        return contarEquiposEnRango(raiz, min, max);
    }

    private int contarEquiposEnRango(NodeArbol<T> nodo, T min, T max) {
        if (nodo == null || min.compareTo(max) > 0) {
            return 0;
        }
        int compMin = nodo.getElemento().compareTo(min);
        int compMax = nodo.getElemento().compareTo(max);
        if (compMax > 0) {
            return contarEquiposEnRango(nodo.getIzquierdo(), min, max);
        }

        if (compMin < 0) {
            return contarEquiposEnRango(nodo.getDerecho(), min, max);
        }

        return 1 + contarEquiposEnRango(nodo.getIzquierdo(), min, max) + contarEquiposEnRango(nodo.getDerecho(), min, max);
    }
}