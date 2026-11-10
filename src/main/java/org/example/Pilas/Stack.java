package org.example.Pilas;

import org.example.Colas.Cola;
import java.util.Objects;

public class Stack<T> {

    private int size;
    private Node<T> top;

    public Stack() {
        this.size = 0;
        this.top = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }


    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            top = newNode;
        }else{
            newNode.setNext(top);   // el nuevo apunta al antiguo tope
            top = newNode;          // el nuevo pasa a ser el tope
        }
        size++;
    }


    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("La pila esta vacia");
        }else{
            T data = top.getData();
            top = top.getNext();
            size--;
            return data;
        }

    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("La pila esta vacia");
        }
        return top.getData();
    }


    //clonar pila
    public Stack<T> clonStack(){
        Stack<T> aux= new Stack<>();
        Stack<T> copy= new Stack<>();

        Node<T> actual = top;

        while(actual != null){
            aux.push(actual.getData());
            actual = actual.getNext();
        }

        while (!aux.isEmpty()) {
            copy.push(aux.pop());
        }
        return copy;
    }

    public void print(){
        Node<T> aux = top;
        while(aux != null){
            System.out.println("Elemento :" + aux.getData()+"\t");
            aux = aux.getNext();
        }
    }

    public void addAll(Stack<T> stack) {
        if (stack == null) return;

        Stack<T> aux = new Stack<>();
        int n = stack.size;
        Node<T> actual = stack.top;

        for (int i = 0; i < n; i++) {
            aux.push(actual.getData());
            actual = actual.getNext();
        }

        while (!aux.isEmpty()) {
            this.push(aux.pop());
        }
    }




    public static Cola<Integer> pasarPares(Stack<Integer> pila) {
        Cola<Integer> cola = new Cola<>();
        Stack<Integer> aux = new Stack<>();

        while (!pila.isEmpty()) {
            int n = pila.pop();
            if (n % 2 == 0) {
                cola.encolar(n);
            }
            aux.push(n);
        }

        while (!aux.isEmpty()) {
            pila.push(aux.pop());
        }
        return cola;
    }





    public static <T> boolean eliminar(Stack<T> pila, T valor) {
        Stack<T> aux = new Stack<>();
        boolean eliminado = false;

        while (!pila.isEmpty() && !eliminado) {
            T dato = pila.pop();
            if (Objects.equals(dato, valor)) {
                eliminado = true;
            } else {
                aux.push(dato);
            }
        }

        while (!aux.isEmpty()) {
            pila.push(aux.pop());
        }
        return eliminado;
    }

    public boolean eliminar(T valor) {
        if (isEmpty()) return false;

        if (Objects.equals(top.getData(), valor)) {   // caso especial: es el tope
            top = top.getNext();
            size--;
            return true;
        }

        Node<T> anterior = top;
        while (anterior.getNext() != null
                && !Objects.equals(anterior.getNext().getData(), valor)) {
            anterior = anterior.getNext();
        }

        if (anterior.getNext() == null) return false; // no estaba

        anterior.setNext(anterior.getNext().getNext()); // salto el nodo
        size--;
        return true;
    }
}