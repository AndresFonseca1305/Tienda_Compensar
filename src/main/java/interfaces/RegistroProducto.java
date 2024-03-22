package interfaces;

import controller.EmpleadoController;
import controller.ProductoController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistroProducto extends JFrame {
    //////////////////////////////////////////////////////////////////////////////////
    private JTextField nombreField;
    private JComboBox<String> tipoProductoBox;
    private JTextField numeroUnidadesField;
    private JTextField valorUnitarioField;

    private VistaPrincipal vistaPrincipal;

    public RegistroProducto(VistaPrincipal vistaPrincipal) {
        setTitle("Registro de Productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Tipo de producto:"));
        tipoProductoBox = new JComboBox<>(new String[]{"Aseo", "Papelería", "Víveres", "Producto para mascotas", "Otros"});
        panel.add(tipoProductoBox);

        panel.add(new JLabel("Número de unidades:"));
        numeroUnidadesField = new JTextField();
        panel.add(numeroUnidadesField);

        panel.add(new JLabel("Valor unitario:"));
        valorUnitarioField = new JTextField();
        panel.add(valorUnitarioField);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(e -> {
            ProductoController controller = new ProductoController();
            controller.registrarProducto(
                    nombreField.getText(),
                    tipoProductoBox.getSelectedItem().toString(),
                    Integer.parseInt(numeroUnidadesField.getText()),
                    Double.parseDouble(valorUnitarioField.getText())
            );
            vistaPrincipal.actualizarTablaProductos();
            dispose();
        });
        panel.add(registrarButton);

        add(panel);
    }
    //////////////////////////////////////////////////////////////////////////////////
}