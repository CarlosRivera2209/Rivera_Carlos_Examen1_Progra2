package primerexamen;

import javax.swing.JOptionPane;

public final class BarcoPasajero extends Barco {

    private final String[] pasajeros;
    private final double precioBoleto;
    private int contadorPasajeros;

    public BarcoPasajero(String nombre, int capacidad, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[capacidad];
        this.precioBoleto = precioBoleto;
        this.contadorPasajeros = 0;
    }

    public void agregarElemento() {
        if (contadorPasajeros < pasajeros.length) {
            String nombrePasajero = JOptionPane.showInputDialog("Ingrese nombre del pasajero:");
            pasajeros[contadorPasajeros++] = nombrePasajero;
        } else {
            JOptionPane.showMessageDialog(null, "No hay espacio para más pasajeros.");
        }
    }

    public double vaciarCobrar() {
        double total = contadorPasajeros * precioBoleto;
        contadorPasajeros = 0;
        return total;
    }

    public void agregarPasajero() {
        contadorPasajeros++;
    }

    public double precioElemento() {
        return precioBoleto;
    }

    public String toString() {
        return super.toString() + " - Cantidad de Pasajeros: " + contadorPasajeros;
    }

    public void listarPasajeros() {
        for (String pasajero : pasajeros) {
            if (pasajero != null) {
                JOptionPane.showMessageDialog(null, pasajero);
            }
        }
    }
}
