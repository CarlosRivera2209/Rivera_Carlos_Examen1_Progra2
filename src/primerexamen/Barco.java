
package primerexamen;

import java.util.Date;

public abstract class Barco {
    private final String nombre;
    private final Date fechaCirculacion;

    public Barco(String nombre) {
        this.nombre = nombre;
        this.fechaCirculacion = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaCirculacion() {
        return fechaCirculacion;
    }

    public String toString() {
        return nombre;
    }

    public abstract void agregarElemento();
    public abstract double vaciarCobrar();
    public abstract double precioElemento();
}

