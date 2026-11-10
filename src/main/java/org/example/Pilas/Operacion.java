package org.example.Pilas;

public class Operacion {
    private String codigo;
    private String estado;

    public Operacion(String codigo, String estado) {
        this.codigo = codigo;
        this.estado = estado;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // 1

    public static Stack<Operacion> obtenerUltimasConfirmadas(Stack<Operacion> historial, int n) {
        Stack<Operacion> pilaResultado = new Stack<>();
        if (n <= 0 || historial.isEmpty()) {
            return pilaResultado;
        }
        Stack<Operacion> aux = new Stack<>();
        Stack<Operacion> confirmadasTemp = new Stack<>();
        while (!historial.isEmpty()) {
            aux.push(historial.pop());
        }

        while (!aux.isEmpty()) {
            Operacion op = aux.pop();
            historial.push(op);

            if ("CONFIRMADA".equalsIgnoreCase(op.getEstado())) {
                confirmadasTemp.push(op);
            }
        }

        Stack<Operacion> invConfirmadas = new Stack<>();
        while (!confirmadasTemp.isEmpty()) {
            invConfirmadas.push(confirmadasTemp.pop());
        }

        Stack<Operacion> ultimasN = new Stack<>();
        int count = 0;
        while (!invConfirmadas.isEmpty() && count < n) {
            ultimasN.push(invConfirmadas.pop());
            count++;
        }

        while (!ultimasN.isEmpty()) {
            pilaResultado.push(ultimasN.pop());
        }

        return pilaResultado;
    }


    public static <T> Stack<T> obtenerSegmento(Stack<T> pilaOriginal, int inicio, int fin) {
        Stack<T> resultado = new Stack<>();
        if (inicio < 1 || inicio > fin || pilaOriginal.isEmpty()) {
            return resultado;
        }

        Stack<T> aux1 = new Stack<>();
        int total = 0;
        while (!pilaOriginal.isEmpty()) {
            aux1.push(pilaOriginal.pop());
            total++;
        }

        Stack<T> aux2 = new Stack<>();
        while (!aux1.isEmpty()) {
            T elem = aux1.pop();
            pilaOriginal.push(elem);
            aux2.push(elem);
        }

        if (fin > total) {
            return new Stack<>(); // fin supera la cantidad de elementos
        }

        Stack<T> segmentoInvertido = new Stack<>();
        int pos = 1;
        while (!aux2.isEmpty()) {
            T elem = aux2.pop();
            if (pos >= inicio && pos <= fin) {
                segmentoInvertido.push(elem);
            }
            pos++;
        }


        while (!segmentoInvertido.isEmpty()) {
            resultado.push(segmentoInvertido.pop());
        }

        return resultado;
    }
}
