package controller;

import dao.EmpleadoDAO;
import model.Empleado;
import utils.JPAutils;

import javax.persistence.EntityManager;
import java.util.List;

public class EmpleadoController {
    private EntityManager em;

    private EmpleadoDAO empleadoDAO;

    public EmpleadoController() {
        this.em = JPAutils.getEntityManager();
        this.empleadoDAO = new EmpleadoDAO(em);
    }

    public void registrarEmpleado(String nombre, String identificacion, int edad, String jornada, int tiempoLaborado) {
        empleadoDAO.registrarEmpleado(nombre, identificacion, edad, jornada, tiempoLaborado);
    }

    public List<Empleado> obtenerEmpleados() {
        return empleadoDAO.listarEmpleados();
    }

    public Empleado obtenerEmpleadoPorDocumento(String numeroDocumento) {
        return empleadoDAO.obtenerEmpleadoPorDocumento(numeroDocumento);
    }

    public void borrarEmpleado(String numeroDocumento) {
        empleadoDAO.borrarEmpleado(numeroDocumento);
    }

    public void actualizarEmpleado(String numeroDocumento, String nombre, int edad, String jornada, int tiempoLaborado) {
        empleadoDAO.actualizarEmpleado(numeroDocumento, nombre, edad, jornada, tiempoLaborado);
    }

    public long contarEmpleadosPorJornada(String jornada) {
        return em.createQuery("SELECT COUNT(e) FROM Empleado e WHERE e.jornada = :jornada", Long.class)
                .setParameter("jornada", jornada)
                .getSingleResult();
    }

    public void close() {
        em.close();
    }
}
