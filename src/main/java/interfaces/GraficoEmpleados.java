package interfaces;

import controller.EmpleadoController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class GraficoEmpleados extends JFrame {
    private EmpleadoController empleadoController;
//////////////////////////////////////////////////////////////////////////////////
    public GraficoEmpleados() {
        empleadoController = new EmpleadoController();

        setTitle("Gráfico de Empleados por Jornada");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear el dataset para el gráfico de empleados
        DefaultPieDataset empleadosDataset = new DefaultPieDataset();
        empleadosDataset.setValue("Diurna", empleadoController.contarEmpleadosPorJornada("Diurno"));
        empleadosDataset.setValue("Nocturna", empleadoController.contarEmpleadosPorJornada("Nocturno"));

        // Crear el gráfico de empleados
        JFreeChart empleadosChart = ChartFactory.createPieChart(
                "Empleados por Jornada", // título del gráfico
                empleadosDataset, // dataset
                true, // incluir leyenda
                true, // tooltips
                false // urls
        );

        // Crear el panel del gráfico de empleados
        ChartPanel empleadosPanel = new ChartPanel(empleadosChart);

        // Añadir el panel del gráfico al JFrame
        add(empleadosPanel, BorderLayout.CENTER);
        // Crear los JLabel para mostrar los valores de cada jornada
        JLabel labelDiurnos = new JLabel("Empleados Diurnos: " + empleadoController.contarEmpleadosPorJornada("Diurno"));
        JLabel labelNocturnos = new JLabel("Empleados Nocturnos: " + empleadoController.contarEmpleadosPorJornada("Nocturno"));

        // Crear un JPanel para los JLabel
        JPanel panelLabels = new JPanel();
        panelLabels.add(labelDiurnos);
        panelLabels.add(labelNocturnos);

        // Añadir el JPanel al JFrame
        add(panelLabels, BorderLayout.SOUTH);
        System.out.println("Empleados no diurnos: " + empleadoController.contarEmpleadosPorJornada("Diurno"));
        System.out.println("Empleados nocturnos: " + empleadoController.contarEmpleadosPorJornada("Nocturno"));
    }
    //////////////////////////////////////////////////////////////////////////////////
}
