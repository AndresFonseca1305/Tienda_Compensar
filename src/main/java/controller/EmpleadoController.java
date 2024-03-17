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
}
