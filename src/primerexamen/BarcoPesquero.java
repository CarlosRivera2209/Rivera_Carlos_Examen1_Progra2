package primerexamen;

public final class BarcoPesquero extends Barco {

    private int pecesCapturados;
    private final TipoPesquero tipoPesquero;

    public BarcoPesquero(String nombre, TipoPesquero tipoPesquero) {
        super(nombre);
        this.pecesCapturados = 0;
        this.tipoPesquero = tipoPesquero;
    }

    public void agregarElemento() {
        pecesCapturados++;
    }

    public double vaciarCobrar() {
        double total = pecesCapturados * tipoPesquero.price;
        pecesCapturados = 0;
        return total;

    }

    public void agregarPeces(int cantidad) {
        pecesCapturados += cantidad;
    }

    public double precioElemento() {
        return tipoPesquero.price;
    }

    @Override
    public String toString() {
        return super.toString() + " - Tipo: " + tipoPesquero + ", Peces Capturados: " + pecesCapturados;
    }
}
