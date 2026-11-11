package org.example.Arboles;

public class RunArboles {
    public static void main(String[] args) {
        ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();

        // Recorrido Preorden obtenido en el Punto 5.b
        int[] datosPreorden = {45, 33, 29, 28, 22, 32, 34, 39, 36, 43, 60, 50, 46, 48, 55, 65, 70, 69, 86, 79, 88};

        System.out.println("--- CONSTRUCCIÓN DEL ÁRBOL AVL (Punto 5.c) ---");
        for (int x : datosPreorden) {
            arbolAVL.insertar(x);
        }

        System.out.print("Preorden AVL final: ");
        arbolAVL.preOrden();

        System.out.print("Amplitud AVL final: ");
        arbolAVL.recorridoAmplitud();

        System.out.println("\n--- ELIMINACIÓN DEL NODO 45 (Punto 5.d) ---");
        arbolAVL.eliminar(45);

        System.out.print("Preorden tras eliminar 45: ");
        arbolAVL.preOrden();
    }
}