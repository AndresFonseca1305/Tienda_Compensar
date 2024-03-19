package interfaces;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class GraficoVentas extends JFrame {

    public GraficoVentas() {
        setTitle("Gráfico de Ventas de Productos");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear el dataset para el gráfico de ventas
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(30, "Productos", "Aseo");
        dataset.addValue(23, "Productos", "Papelería");
        dataset.addValue(54, "Productos", "Víveres");
        dataset.addValue(24, "Productos", "Mascotas");
        dataset.addValue(50, "Productos", "Otros");

        // Crear el gráfico de ventas
        JFreeChart chart = ChartFactory.createBarChart(
                "Ventas por Tipo de Producto", // título del gráfico
                "Tipo de Producto", // etiqueta del eje x
                "Ventas", // etiqueta del eje y
                dataset // dataset
        );

        // Crear el panel del gráfico de ventas
        ChartPanel chartPanel = new ChartPanel(chart);

        // Añadir el panel del gráfico al JFrame
        add(chartPanel, BorderLayout.CENTER);
    }
}
