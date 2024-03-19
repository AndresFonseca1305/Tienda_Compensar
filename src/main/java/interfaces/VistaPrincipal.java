package interfaces;

import controller.EmpleadoController;
import controller.ProductoController;
import model.Empleado;
import model.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        empleadoController = new EmpleadoController();
        productoController = new ProductoController();



        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));

        // Crear los modelos de tabla
        DefaultTableModel modeloEmpleados = new DefaultTableModel(new Object[]{"Nombre", "Identificacion", "Edad", "Jornada", "Tiempo Laborado", "Descuento en Tiendas", "Descuento en Centros Recreacionales"}, 0);
        DefaultTableModel modeloProductos = new DefaultTableModel(new Object[]{"Id", "Nombre", "Tipo", "Cantidad", "Valor Unitario", "Valor IVA", "Valor Total"}, 0);

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
        actualizarTablaProductos();

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

        JButton borrarButton = new JButton("Borrar Empleado");
        borrarButton.addActionListener(e -> {
            int filaSeleccionada = tablaEmpleados.getSelectedRow();
            if (filaSeleccionada != -1) {
                String numeroDocumento = (String) tablaEmpleados.getValueAt(filaSeleccionada, 1); // Asume que el número de documento es la segunda columna
                empleadoController.borrarEmpleado(numeroDocumento);
                actualizarTablaEmpleados();
            }
        });

        JButton editarButton = new JButton("Editar Empleado");
        editarButton.addActionListener(e -> {
            int filaSeleccionada = tablaEmpleados.getSelectedRow();
            if (filaSeleccionada != -1) {
                String numeroDocumento = (String) tablaEmpleados.getValueAt(filaSeleccionada, 1); // Asume que el número de documento es la segunda columna
                String nombre = (String) tablaEmpleados.getValueAt(filaSeleccionada, 0);
                int edad = (int) tablaEmpleados.getValueAt(filaSeleccionada, 2);
                String jornada = (String) tablaEmpleados.getValueAt(filaSeleccionada, 3);
                int tiempoLaborado = (int) tablaEmpleados.getValueAt(filaSeleccionada, 4);

                JTextField campoNombre = new JTextField(nombre);
                JTextField campoEdad = new JTextField(String.valueOf(edad));
                JComboBox<String> campoJornada = new JComboBox<>(new String[]{"Diurno", "Nocturno"});
                campoJornada.setSelectedItem(jornada);
                JTextField campoTiempoLaborado = new JTextField(String.valueOf(tiempoLaborado));

                Object[] campos = {
                        "Nombre:", campoNombre,
                        "Edad:", campoEdad,
                        "Jornada:", campoJornada,
                        "Tiempo laborado:", campoTiempoLaborado
                };

                int opcion = JOptionPane.showConfirmDialog(null, campos, "Editar Empleado", JOptionPane.OK_CANCEL_OPTION);

                if (opcion == JOptionPane.OK_OPTION) {
                    nombre = campoNombre.getText();
                    edad = Integer.parseInt(campoEdad.getText());
                    jornada = (String) campoJornada.getSelectedItem();
                    tiempoLaborado = Integer.parseInt(campoTiempoLaborado.getText());

                    empleadoController.actualizarEmpleado(numeroDocumento, nombre, edad, jornada, tiempoLaborado);
                    actualizarTablaEmpleados();
                }
            }
        });

        // Crear el botón "Refrescar"
        JButton botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(e -> actualizarTablaEmpleados());
        botonRefrescar.addActionListener(e -> actualizarTablaProductos());

        JButton borrarProductoButton = new JButton("Borrar Producto");
        borrarProductoButton.addActionListener(e -> {
            int filaSeleccionada = tablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Integer id = (Integer) tablaProductos.getValueAt(filaSeleccionada, 0); // Asume que el número de documento es la segunda columna
                productoController.borrarProducto(id);
                actualizarTablaProductos();
            }
        });

        JButton editarProductoButton = new JButton("Editar Producto");
        editarProductoButton.addActionListener(e -> {
            int filaSeleccionada = tablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Integer id = (Integer) tablaProductos.getValueAt(filaSeleccionada, 0);
                String nombre = (String) tablaProductos.getValueAt(filaSeleccionada, 1);
                String tipo = (String) tablaProductos.getValueAt(filaSeleccionada, 2);
                int unidades = (int) tablaProductos.getValueAt(filaSeleccionada, 3);
                double precio = (double) tablaProductos.getValueAt(filaSeleccionada, 4);

                JTextField campoNombre = new JTextField(nombre);
                JComboBox<String> campoTipo = new JComboBox<>(new String[]{"Aseo", "Papeleria", "Viveres", "Mascotas", "Otros"});
                campoTipo.setSelectedItem(tipo);
                JTextField campoUnidades = new JTextField(String.valueOf(unidades));
                JTextField campoPrecio = new JTextField(String.valueOf(precio));

                Object[] campos = {
                        "Nombre:", campoNombre,
                        "Tipo:", campoTipo,
                        "Unidades:", campoUnidades,
                        "Precio:", campoPrecio
                };

                int opcion = JOptionPane.showConfirmDialog(null, campos, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);

                if (opcion == JOptionPane.OK_OPTION) {
                    nombre = campoNombre.getText();
                    tipo = (String) campoTipo.getSelectedItem();
                    unidades = Integer.parseInt(campoUnidades.getText());
                    precio = Double.parseDouble(campoPrecio.getText());

                    productoController.actualizarProducto(id, nombre, tipo, unidades, precio);
                    actualizarTablaProductos();
                }
            }
        });

        // Crear el botón "Gráfico Empleados"
        JButton botonGraficoEmpleados = new JButton("Gráfico Empleados");
        botonGraficoEmpleados.addActionListener(e -> {
            GraficoEmpleados graficoEmpleados = new GraficoEmpleados();
            graficoEmpleados.setVisible(true);
        });

        // Crear el botón "Gráfico Ventas"
        JButton botonGraficoVentas = new JButton("Gráfico Ventas");
        botonGraficoVentas.addActionListener(e -> {
            GraficoVentas graficoVentas = new GraficoVentas();
            graficoVentas.setVisible(true);
        });

        // Añadir los botones al panel
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRegistroEmpleado);
        panelBotones.add(botonRegistroProducto);
        panelBotones.add(borrarButton);
        panelBotones.add(editarButton);
        panelBotones.add(botonRefrescar);
        panelBotones.add(borrarProductoButton);
        panelBotones.add(editarProductoButton);
        panelBotones.add(botonGraficoEmpleados);
        panelBotones.add(botonGraficoVentas);

        panel.add(panelBotones);

        add(panel);

    }

    public void actualizarTablaEmpleados() {
        DefaultTableModel modelo = (DefaultTableModel) tablaEmpleados.getModel();
        modelo.setRowCount(0); // Borra los registros existentes

        // Asegúrate de que estás obteniendo los datos más recientes de la base de datos
        List<Empleado> empleados = empleadoController.obtenerEmpleados();
        for (Empleado empleado : empleados) {
            Object[] fila = new Object[]{
                    empleado.getNombre(),
                    empleado.getNumeroDocumento(),
                    empleado.getEdad(),
                    empleado.getJornada(),
                    empleado.getTiempoLaborado(),
                    empleado.getDescuentoTienda()*100 + "%",
                    empleado.getDescuentoCentroRecreacional()*100 + "%"
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
                    producto.getValorUnitario(),
                    producto.getIva(),
                    producto.getValorTotal()
            };
            modelo.addRow(fila);
        }
    }

    public static void main(String[] args) {
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.setVisible(true);
    }
}
