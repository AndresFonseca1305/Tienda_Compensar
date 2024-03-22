package interfaces;

import controller.EmpleadoController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistroEmpleado extends JFrame {
    //////////////////////////////////////////////////////////////////////////////////
    private JTextField nombreField;
    private JTextField identificacionField;
    private JTextField edadField;
    private JComboBox<String> jornadaBox;
    private JTextField tiempoTrabajandoField;

    private VistaPrincipal vistaPrincipal;

    public RegistroEmpleado(VistaPrincipal vistaPrincipal) {
        setTitle("Registro de Empleados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.vistaPrincipal = vistaPrincipal;

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Identificación:"));
        identificacionField = new JTextField();
        panel.add(identificacionField);

        panel.add(new JLabel("Edad:"));
        edadField = new JTextField();
        panel.add(edadField);

        panel.add(new JLabel("Jornada:"));
        jornadaBox = new JComboBox<>(new String[]{"Diurno", "Nocturno"});
        panel.add(jornadaBox);

        panel.add(new JLabel("Tiempo trabajando (años):"));
        tiempoTrabajandoField = new JTextField();
        panel.add(tiempoTrabajandoField);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(e -> {
            EmpleadoController controller = new EmpleadoController();
            controller.registrarEmpleado(
                    nombreField.getText(),
                    identificacionField.getText(),
                    Integer.parseInt(edadField.getText()),
                    jornadaBox.getSelectedItem().toString(),
                    Integer.parseInt(tiempoTrabajandoField.getText())
            );
            vistaPrincipal.actualizarTablaEmpleados();
            dispose();
        });
        panel.add(registrarButton);

        add(panel);
    }
    //////////////////////////////////////////////////////////////////////////////////
}