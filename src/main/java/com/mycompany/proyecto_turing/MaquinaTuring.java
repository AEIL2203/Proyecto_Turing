package com.mycompany.proyecto_turing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaquinaTuring {

    // Definición de atributos de la máquina de Turing
    private String[] cinta;  // Representación de la cinta de la máquina, donde se almacenan los datos
    private int posicionCabezal;  // Posición actual del cabezal de lectura/escritura en la cinta
    private String estadoActual;  // Estado actual de la máquina de Turing
    private String estadoInicial;  // Estado inicial de la máquina de Turing
    private String estadoAceptacion;  // Estado de aceptación (final) de la máquina
    private Map<String, Transicion> tablaTransiciones;  // Tabla de transiciones que define el comportamiento de la máquina

    // Constructor de la máquina de Turing
    public MaquinaTuring(String estadoInicial, String estadoAceptacion) {
        this.cinta = new String[100];  // Inicializa la cinta con un tamaño de 100 celdas
        this.posicionCabezal = 50;  // Coloca el cabezal en el centro de la cinta
        this.estadoInicial = estadoInicial;
        this.estadoActual = estadoInicial;
        this.estadoAceptacion = estadoAceptacion;
        this.tablaTransiciones = new HashMap<>();  // Inicializa la tabla de transiciones
    }

    // Método para agregar una nueva transición a la tabla de transiciones
    public void agregarTransicion(String estado, char simboloLectura, String siguienteEstado, char simboloEscribir, char movimiento) {
        int direccionMovimiento;
        // Define la dirección del movimiento: derecha (R) o izquierda (L)
        if (movimiento == 'R') {
            direccionMovimiento = 1;
        } else if (movimiento == 'L') {
            direccionMovimiento = -1;
        } else {
            throw new IllegalArgumentException("Movimiento inválido. Use 'R' para derecha y 'L' para izquierda.");
        }

        // Crea una clave única para la transición (estado y símbolo de lectura)
        String claveTransicion = estado + "_" + simboloLectura;
        // Agrega la transición a la tabla de transiciones
        tablaTransiciones.put(claveTransicion, new Transicion(siguienteEstado, simboloEscribir, direccionMovimiento));
    }

    // Método para configurar la entrada en la cinta
    public void configurarEntrada(String entrada) {
        this.cinta = new String[100];  // Reinicia la cinta a su tamaño inicial
        this.posicionCabezal = 50;  // Reinicia la posición del cabezal al centro
        for (int i = 0; i < entrada.length(); i++) {
            // Llena la cinta con la entrada, empezando desde la posición del cabezal
            cinta[posicionCabezal + i] = String.valueOf(entrada.charAt(i));
        }
        this.estadoActual = this.estadoInicial;  // Restablece el estado actual al inicial
    }

    // Método para ejecutar la máquina de Turing y registrar cada paso
    public boolean ejecutar(StringBuilder registroPasos, List<String> estadosList) {
        while (!estadoActual.equals(estadoAceptacion)) {
            // Obtiene el símbolo de la cinta en la posición del cabezal o un espacio en blanco si es nulo
            char simboloLectura = cinta[posicionCabezal] != null ? cinta[posicionCabezal].charAt(0) : '_';
            String claveTransicion = estadoActual + "_" + simboloLectura;

            // Registrar el contenido de la cinta y el estado actual
            StringBuilder cintaActual = new StringBuilder();
            cintaActual.append("Cinta: ");
            for (int i = 0; i < cinta.length; i++) {
                if (i == posicionCabezal) {
                    cintaActual.append("[ ").append(cinta[i] != null ? cinta[i] : "_").append(" ] ");
                } else {
                    cintaActual.append(cinta[i] != null ? cinta[i] : "_").append(" ");
                }
            }
            cintaActual.append("\n");

            registroPasos.append("Estado actual: ").append(estadoActual).append("\n");

            // Si no existe una transición para el estado actual y el símbolo de lectura, rechaza la cadena
            if (!tablaTransiciones.containsKey(claveTransicion)) {
                registroPasos.append(cintaActual).append("No hay otra transición: cadena RECHAZADA.\n");
                return false;
            }

            // Obtiene la transición y realiza las operaciones correspondientes
            Transicion transicion = tablaTransiciones.get(claveTransicion);
            cinta[posicionCabezal] = String.valueOf(transicion.simboloEscribir);  // Escribe el nuevo símbolo en la cinta

            // Registrar la transición
            String movimiento = transicion.direccionMovimiento == 1 ? "Derecha →" : "Izquierda ←";
            cintaActual.append("Escribiendo: ").append(transicion.simboloEscribir)
                    .append(", Movimiento: ").append(movimiento).append("\n");

            registroPasos.append(cintaActual);  // Agrega la información de la cinta al registro de pasos

            // Actualiza la posición del cabezal y el estado actual
            posicionCabezal += transicion.direccionMovimiento;
            estadoActual = transicion.siguienteEstado;
        }

        // Registrar el estado final y el contenido final de la cinta
        registroPasos.append("Estado actual: ").append(estadoActual).append("\n");

        StringBuilder cintaFinal = new StringBuilder();
        cintaFinal.append("Cinta: ");
        for (int i = 0; i < cinta.length; i++) {
            if (i == posicionCabezal) {
                cintaFinal.append("[ ").append(cinta[i] != null ? cinta[i] : "_").append(" ] ");
            } else {
                cintaFinal.append(cinta[i] != null ? cinta[i] : "_").append(" ");
            }
        }
        registroPasos.append(cintaFinal).append("\n");

        // Indica si la cadena es aceptada
        if (estadoActual.equals(estadoAceptacion)) {
            registroPasos.append("CADENA ACEPTADA.\n");
        }

        return estadoActual.equals(estadoAceptacion);
    }

    // Método para mostrar el contenido de la cinta en consola
    public void mostrarCinta() {
        for (String celda : cinta) {
            System.out.print(celda != null ? celda : "_");  // Imprime el contenido de cada celda o un espacio en blanco si es nula
        }
        System.out.println();
    }
}

// Clase interna que representa una transición de la máquina de Turing
class Transicion {
    String siguienteEstado;  // Estado al que se transita
    char simboloEscribir;  // Símbolo que se escribe en la cinta
    int direccionMovimiento;  // Dirección del movimiento del cabezal (1 para derecha, -1 para izquierda)

    // Constructor para crear una transición
    Transicion(String siguienteEstado, char simboloEscribir, int direccionMovimiento) {
        this.siguienteEstado = siguienteEstado;
        this.simboloEscribir = simboloEscribir;
        this.direccionMovimiento = direccionMovimiento;
    }
}
