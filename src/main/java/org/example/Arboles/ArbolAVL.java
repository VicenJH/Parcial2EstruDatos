package org.example.Arboles;
import org.example.Colas.Cola;

public class ArbolAVL<T extends Comparable<T>> {
    private NodoAVL<T> raiz;

    public NodoAVL<T> getRaiz() { return raiz; }

    private int obtenerAltura(NodoAVL<T> nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private void actualizarFE(NodoAVL<T> nodo) {
        if (nodo != null) {
            int altIzq = obtenerAltura(nodo.getIzquierdo());
            int altDer = obtenerAltura(nodo.getDerecho());
            nodo.setAltura(1 + Math.max(altIzq, altDer));
            nodo.setFe(altDer - altIzq); // FE = Altura Derecha - Altura Izquierda
        }
    }

    // --- ROTACIONES ---

    // Rotación Simple a la Derecha (RSD)
    private NodoAVL<T> rotacionDerecha(NodoAVL<T> y) {
        NodoAVL<T> x = y.getIzquierdo();
        NodoAVL<T> T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        actualizarFE(y);
        actualizarFE(x);

        return x;
    }

    // Rotación Simple a la Izquierda (RSI)
    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> x) {
        NodoAVL<T> y = x.getDerecho();
        NodoAVL<T> T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        actualizarFE(x);
        actualizarFE(y);

        return y;
    }

    // --- INSERCIÓN ---
    public void insertar(T elemento) {
        raiz = insertar(raiz, elemento);
    }

    private NodoAVL<T> insertar(NodoAVL<T> nodo, T elemento) {
        if (nodo == null) {
            return new NodoAVL<>(elemento);
        }

        int comp = elemento.compareTo(nodo.getElemento());
        if (comp < 0) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), elemento));
        } else if (comp > 0) {
            nodo.setDerecho(insertar(nodo.getDerecho(), elemento));
        } else {
            return nodo; // Claves duplicadas no permitidas
        }

        actualizarFE(nodo);

        // Reequilibrio según el FE
        if (nodo.getFe() == -2) {
            if (nodo.getIzquierdo() != null && nodo.getIzquierdo().getFe() <= 0) {
                return rotacionDerecha(nodo); // Izquierda-Izquierda (RSD)
            } else {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                return rotacionDerecha(nodo); // Izquierda-Derecha (RDID)
            }
        }

        if (nodo.getFe() == 2) {
            if (nodo.getDerecho() != null && nodo.getDerecho().getFe() >= 0) {
                return rotacionIzquierda(nodo); // Derecha-Derecha (RSI)
            } else {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                return rotacionIzquierda(nodo); // Derecha-Izquierda (RDDI)
            }
        }

        return nodo;
    }

    // --- ELIMINACIÓN (Regla Subárbol Derecho) ---
    public void eliminar(T elemento) {
        raiz = eliminar(raiz, elemento);
    }

    private NodoAVL<T> eliminar(NodoAVL<T> nodo, T elemento) {
        if (nodo == null) return null;

        int comp = elemento.compareTo(nodo.getElemento());
        if (comp < 0) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), elemento));
        } else if (comp > 0) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), elemento));
        } else {
            // Nodo con uno o cero hijos
            if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                NodoAVL<T> temp = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                // Nodo con dos hijos: Regla subárbol derecho (menor del subárbol derecho)
                NodoAVL<T> temp = obtenerMinimo(nodo.getDerecho());
                nodo.setElemento(temp.getElemento());
                nodo.setDerecho(eliminar(nodo.getDerecho(), temp.getElemento()));
            }
        }

        if (nodo == null) return null;

        actualizarFE(nodo);

        // Reequilibrio tras eliminación
        if (nodo.getFe() == -2) {
            if (nodo.getIzquierdo().getFe() <= 0) {
                return rotacionDerecha(nodo);
            } else {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                return rotacionDerecha(nodo);
            }
        }

        if (nodo.getFe() == 2) {
            if (nodo.getDerecho().getFe() >= 0) {
                return rotacionIzquierda(nodo);
            } else {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                return rotacionIzquierda(nodo);
            }
        }

        return nodo;
    }

    private NodoAVL<T> obtenerMinimo(NodoAVL<T> nodo) {
        NodoAVL<T> actual = nodo;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }

    // --- RECORRIDOS (Punto 5.b) ---
    public void preOrden() {
        preOrden(raiz);
        System.out.println();
    }

    private void preOrden(NodoAVL<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getElemento() + "(FE:" + nodo.getFe() + ") ");
            preOrden(nodo.getIzquierdo());
            preOrden(nodo.getDerecho());
        }
    }

    public void recorridoAmplitud() {
        if (raiz == null) return;
        Cola<NodoAVL<T>> cola = new Cola<>();
        cola.encolar(raiz);

        while (!cola.isEmpty()) {
            NodoAVL<T> actual = cola.desencolar();
            System.out.print(actual.getElemento() + "(FE:" + actual.getFe() + ") ");

            if (actual.getIzquierdo() != null) cola.encolar(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.encolar(actual.getDerecho());
        }
        System.out.println();
    }
}