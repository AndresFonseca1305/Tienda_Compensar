package interfaces;

import controller.EmpleadoController;
import controller.ProductoController;
import model.Empleado;
import model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class VistaPrincipal extends JFrame {
    private JTable tablaEmpleados;
    private JTable tablaProductos;

    private EmpleadoController empleadoController;

    private ProductoController productoController;

    public VistaPrincipal() {
        setTitle("Vista Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        empleadoController = new EmpleadoController();
        productoController = new ProductoController();



        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear los modelos de tabla
        DefaultTableModel modeloEmpleados = new DefaultTableModel(new Object[]{"Nombre", "Identificacion", "Edad", "Jornada", "Tiempo Laborado"}, 0);
        DefaultTableModel modeloProductos = new DefaultTableModel(new Object[]{"Id", "Nombre", "Tipo", "Cantidad", "Valor Unitario"}, 0);

        // Crear las tablas con los modelos
        tablaEmpleados = new JTable(modeloEmpleados);
        tablaProductos = new JTable(modeloProductos);

        // Configurar las tablas para mostrar un máximo de 5 filas a la vez
        tablaEmpleados.setPreferredScrollableViewportSize(new Dimension(tablaEmpleados.getPreferredSize().width, tablaEmpleados.getRowHeight() * 5));
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(tablaProductos.getPreferredSize().width, tablaProductos.getRowHeight() * 5));

        // Crear las etiquetas
        JLabel etiquetaEmpleados = new JLabel("Empleados");
        etiquetaEmpleados.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel etiquetaProductos = new JLabel("Productos");
        etiquetaProductos.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir las etiquetas y las tablas al panel
        panel.add(etiquetaEmpleados);
        panel.add(new JScrollPane(tablaEmpleados));
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Añade un espacio entre las tablas
        panel.add(etiquetaProductos);
        panel.add(new JScrollPane(tablaProductos));

        actualizarTablaEmpleados();

        // Crear los botones
        JButton botonRegistroEmpleado = new JButton("Registrar Empleado");
        botonRegistroEmpleado.addActionListener(e -> {
            RegistroEmpleado registroEmpleado = new RegistroEmpleado(this);
            registroEmpleado.setVisible(true);
        });

        JButton botonRegistroProducto = new JButton("Registrar Producto");
        botonRegistroProducto.addActionListener(e -> {
            RegistroProducto registroProducto = new RegistroProducto(this);
            registroProducto.setVisible(true);
        });

        // Añadir los botones al panel
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRegistroEmpleado);
        panelBotones.add(botonRegistroProducto);

        panel.add(panelBotones);

        add(panel);
    }

    public void actualizarTablaEmpleados() {
        DefaultTableModel modelo = (DefaultTableModel) tablaEmpleados.getModel();
        modelo.setRowCount(0); // Borra los registros existentes

        List<Empleado> empleados = empleadoController.obtenerEmpleados();
        for (Empleado empleado : empleados) {
            Object[] fila = new Object[]{
                    empleado.getNombre(),
                    empleado.getNumeroDocumento(),
                    empleado.getEdad(),
                    empleado.getJornada(),
                    empleado.getTiempoLaborado()
            };
            modelo.addRow(fila);
        }
    }
    public void actualizarTablaProductos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
        modelo.setRowCount(0); // Borra los registros existentes

        List<Producto> productos = productoController.obtenerProductos();
        for (Producto producto : productos) {
            Object[] fila = new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getTipo(),
                    producto.getNumeroUnidades(),
                    producto.getValorUnitario()
            };
            modelo.addRow(fila);
        }
    }

    public static void main(String[] args) {
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.setVisible(true);
    }
}
