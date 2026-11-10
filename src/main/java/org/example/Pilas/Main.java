package org.example.Pilas;

public class Main {

    static void main() {
        Stack<Integer> pila1 = new Stack<>();
        pila1.push(6);
        pila1.push(7);
        pila1.push(8);
        pila1.push(9);
        pila1.push(10);


        Stack<Integer> pila2= new Stack<>();
        pila2.push(1);
        pila2.push(2);
        pila2.push(3);
        pila2.push(4);
        pila2.push(5);


        pila1.addAll(pila2);
        pila1.print();
        System.out.println(pila2.getSize()); // esperado: 5
        pila2.addAll(pila2);
        System.out.println(pila2.getSize()); // esperado: 10

    }


}