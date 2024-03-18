package interfaces;

import javax.swing.*;
import java.awt.*;

public class Inicio extends JFrame {
    public Inicio() {
        setTitle("Inicio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JDesktopPane y establecer su layout
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setLayout(new BoxLayout(desktopPane, BoxLayout.Y_AXIS));

        // Cargar la imagen del logo de la universidad
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Images/Ucompensar Logo.jpeg"));

        // Reducir el tamaño de la imagen
        Image image = logoIcon.getImage();
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // 200x200 es el nuevo tamaño
        logoIcon = new ImageIcon(newimg);

        // Crear un JLabel para el logo
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el logo horizontalmente

        // Crear el botón para acceder a la funcionalidad de productos y empleados
        JButton botonAcceder = new JButton("Acceder");
        botonAcceder.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el botón horizontalmente
        botonAcceder.addActionListener(e -> {
            VistaPrincipal vistaPrincipal = new VistaPrincipal();
            vistaPrincipal.setVisible(true);
            this.dispose(); // Cierra la ventana de Inicio
        });

        // Crear un JLabel para el texto de información
        JLabel infoLabel = new JLabel("<html><center><p>La Fundación Universitaria Compensar es una institución educativa privada colombiana con sedes en Bogotá y Villavicencio. Está vinculada con la institución financiera Compensar Caja de Compensación Familiar y EPS. Ofrece programas de formación técnica, tecnológica y profesional en modalidad presencial y virtual. Los estudiantes pueden obtener títulos como Técnico, Tecnólogo y Profesional.</p><p>A través de su modelo Universidad-Empresa, los estudiantes adquieren herramientas para competir en el mercado laboral y fortalecer la relación entre la universidad y las empresas. Además, la Fundación Universitaria Compensar se encuentra entre las 20 instituciones de educación superior con mejores resultados en las pruebas Saber Pro.</p></center></html>");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el texto horizontalmente
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Configura la fuente del texto

        // Añadir un espacio flexible antes del logo
        desktopPane.add(Box.createVerticalGlue());

        // Añadir el logo al JDesktopPane
        desktopPane.add(logoLabel);

        // Añadir un espacio fijo entre el logo y el texto de información
        desktopPane.add(Box.createVerticalStrut(20));

        // Añadir el texto de información al JDesktopPane
        desktopPane.add(infoLabel);

        // Añadir un espacio fijo entre el texto de información y el botón
        desktopPane.add(Box.createVerticalStrut(20));

        // Añadir el botón al JDesktopPane
        desktopPane.add(botonAcceder);

        // Añadir un espacio flexible después del botón
        desktopPane.add(Box.createVerticalGlue());

        // Crear el JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú "Sobre nosotros"
        JMenu sobreNosotrosMenu = new JMenu("Sobre nosotros");
        JMenuItem informacionItem = new JMenuItem("Información");
        informacionItem.addActionListener(e -> {
            // Crear un JDialog para mostrar la información de los desarrolladores
            JDialog dialogo = new JDialog(this, "Información sobre los desarrolladores", true);
            dialogo.setSize(400, 200);
            dialogo.setLocationRelativeTo(this);

            // Crear un JPanel con un BoxLayout para mostrar la información de manera vertical
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Crear los JLabel para la información de cada desarrollador
            JLabel tituloDesarrollador2 = new JLabel("Sebastian Gomez");
            JLabel infoDesarrollador2 = new JLabel("Estudiante de Ingeniería de Sistemas en Segundo Semestre...");
            JLabel espacio = new JLabel(" ");
            JLabel tituloDesarrollador1 = new JLabel("Andres Fonseca");
            JLabel infoDesarrollador1 = new JLabel("Estudiante de Ingeniería de Sistemas en Segundo Semestre...");

            // Añadir los JLabel al JPanel
            panel.add(tituloDesarrollador2);
            panel.add(infoDesarrollador2);
            panel.add(espacio);
            panel.add(tituloDesarrollador1);
            panel.add(infoDesarrollador1);

            // Añadir el JPanel al JDialog
            dialogo.add(panel);

            // Hacer visible el JDialog
            dialogo.setVisible(true);
        });

        sobreNosotrosMenu.add(informacionItem);
        menuBar.add(sobreNosotrosMenu);

        // Añadir el menú "Sobre nosotros" al JMenuBar
        menuBar.add(sobreNosotrosMenu);

        // Añadir el JMenuBar al JFrame
        setJMenuBar(menuBar);

        // Añadir el JDesktopPane al JFrame
        add(desktopPane);

        // Hacer visible el JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        new Inicio();
    }
}