package primerexamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Muelle extends JFrame {

    private final ArrayList<Barco> barcos;

    public Muelle() {

        barcos = new ArrayList<>();
        setTitle("Muelle");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel bienvenidaLabel = new JLabel("Bienvenido al Muelle");
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 36));
        bienvenidaLabel.setForeground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(bienvenidaLabel, gbc);

        JButton agregarBarcoButton = new JButton("Agregar Barco");
        styleButton(agregarBarcoButton);
        agregarBarcoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarBarco();
            }
        });

        JButton agregarElementoButton = new JButton("Agregar Elemento a Barco");
        styleButton(agregarElementoButton);
        agregarElementoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarElemento();
            }
        });

        JButton vaciarBarcoButton = new JButton("Vaciar y Cobrar Barco");
        styleButton(vaciarBarcoButton);
        vaciarBarcoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarBarco();
            }
        });

        JButton barcosDesdeButton = new JButton("Mostrar Barcos desde Año");
        styleButton(barcosDesdeButton);
        barcosDesdeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barcosDesde();
            }
        });

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(agregarBarcoButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(agregarElementoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(vaciarBarcoButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(barcosDesdeButton, gbc);

        getContentPane().setBackground(Color.BLUE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
    }

    private void agregarBarco() {
        String tipo = JOptionPane.showInputDialog(this, "Ingrese tipo de barco (PESQUERO o PASAJERO):");
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del barco:");

        for (Barco barco : barcos) {
            if (barco.getNombre().equals(nombre)) {
                JOptionPane.showMessageDialog(this, "Nombre de barco ya existe.");
                return;
            }
        }

        if ("PESQUERO".equalsIgnoreCase(tipo)) {
            String[] tiposPesqueros = {"PEZ", "CAMARON", "LANGOSTA"};
            String tipoPesquero = (String) JOptionPane.showInputDialog(this, "Seleccione tipo de pesquero:", "Tipo de Pesquero", JOptionPane.QUESTION_MESSAGE, null, tiposPesqueros, tiposPesqueros[0]);
            TipoPesquero tipoEnum = TipoPesquero.valueOf(tipoPesquero);
            barcos.add(new BarcoPesquero(nombre, tipoEnum));
        } else if ("PASAJERO".equalsIgnoreCase(tipo)) {
            try {
                int capacidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese capacidad de pasajeros:"));
                double precioBoleto = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese precio del boleto:"));
                barcos.add(new BarcoPasajero(nombre, capacidad, precioBoleto));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de barco inválido.");
        }
    }

    private void agregarElemento() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del barco:");
        for (Barco barco : barcos) {
            if (barco.getNombre().equals(nombre)) {
                barco.agregarElemento();
                JOptionPane.showMessageDialog(this, "Elemento agregado al barco " + nombre);

                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Barco no encontrado.");
    }

    private double vaciarBarco() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del barco para vaciar:");
        for (Barco barco : barcos) {
            if (barco.getNombre().equals(nombre)) {
                double total = barco.vaciarCobrar();
                JOptionPane.showMessageDialog(this, "Datos del barco:\n" + barco.toString() + "\nTotal generado: " + total);
                if (barco instanceof BarcoPasajero) {
                    ((BarcoPasajero) barco).listarPasajeros();
                }
                return total;
            }
        }
        JOptionPane.showMessageDialog(this, "Barco no encontrado.");
        return 0;
    }

    private void barcosDesde() {
        String input = JOptionPane.showInputDialog(this, "Ingrese año:");
        try {
            int year = Integer.parseInt(input);
            StringBuilder result = new StringBuilder();
            for (Barco barco : barcos) {
                if (barco.getFechaCirculacion().getYear() + 1900 >= year) {
                    result.append(barco.getNombre()).append(" - ").append(barco.getFechaCirculacion()).append("\n");
                }
            }
            if (result.length() > 0) {
                JOptionPane.showMessageDialog(this, result.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron barcos para el año ingresado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un año válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Muelle().setVisible(true);
            }
        });
    }
}
