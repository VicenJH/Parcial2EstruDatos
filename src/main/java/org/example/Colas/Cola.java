package org.example.Colas;

public class Cola<T> {

    private int size;
    private NodeSimple <T> firstNode;
    private NodeSimple <T> lastNode;

    public Cola() {
        this.size = 0;
        firstNode = null;
        lastNode = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public void encolar(T data) {
        encolarFinal(data);
    }

    public void encolarFinal (T data){
        NodeSimple<T> newNodeSimple = new NodeSimple(data);
        if(isEmpty()){
            firstNode = newNodeSimple;
            lastNode = newNodeSimple;
        }else{
            lastNode.setNext(newNodeSimple);
            lastNode = newNodeSimple;
        }
        size++;
    }

    public void encolarInicio(T data) {
        NodeSimple<T> nuevo = new NodeSimple<>(data);
        if (isEmpty()) {
            firstNode = nuevo;
            lastNode = nuevo;
        } else {
            nuevo.setNext(firstNode);
            firstNode = nuevo;
        }
        size++;
    }


    public T desencolar() {
        if(isEmpty()){
            throw new RuntimeException("La cola esta vacia");
        }else{
            T data = firstNode.getData();
            firstNode = firstNode.getNext();

            if (firstNode == null) {     // la cola quedó vacía
                lastNode = null;
            }

            size--;
            return data;
        }
    }

    public Cola<T> clonarCola() {
        Cola<T> copia = new Cola<>();
        NodeSimple<T> actual = firstNode;

        while (actual != null) {
            copia.encolar(actual.getData());
            actual = actual.getNext();
        }
        return copia;
    }

    public void invertirCola() {
        if (isEmpty()) {
            return;
        }
        T elemento = desencolar();
        invertirCola();
        encolar(elemento);
    }

    public boolean sonIdenticas(Cola<T> otra) {
        if (otra == null) {
            return false;
        }
        if (this.size != otra.size) {
            return false;
        }

        NodeSimple<T> a = this.firstNode;
        NodeSimple<T> b = otra.firstNode;

        while (a != null && b != null) {
            if (!a.getData().equals(b.getData())) {
                return false;
            }
            a = a.getNext();
            b = b.getNext();
        }
        return true;
    }

    public static Cola<Solicitud> seleccionarSolicitudes(Cola<Solicitud> pendientes, int minutosDisponibles) {
        Cola<Solicitud> seleccionadas = new Cola<>();
        if (minutosDisponibles <= 0 || pendientes.isEmpty()) {
            return seleccionadas;
        }

        Cola<Solicitud> aux = new Cola<>();
        int total = pendientes.getSize();


        for (int i = 0; i < total; i++) {
            Solicitud s = pendientes.desencolar();
            if (s.getPrioridad() == Prioridad.ALTA) {
                if (s.getDuracion() <= minutosDisponibles) {
                    minutosDisponibles -= s.getDuracion();
                    seleccionadas.encolar(s);
                } else {
                    aux.encolar(s);
                }
            } else {
                aux.encolar(s);
            }
        }

        int pendientesRestantes = aux.getSize();
        for (int i = 0; i < pendientesRestantes; i++) {
            Solicitud s = aux.desencolar();
            if (s.getPrioridad() == Prioridad.NORMAL) {
                if (s.getDuracion() <= minutosDisponibles) {
                    minutosDisponibles -= s.getDuracion();
                    seleccionadas.encolar(s);
                } else {
                    pendientes.encolar(s);
                }
            } else {
                pendientes.encolar(s);
            }
        }

        return seleccionadas;
    }


}