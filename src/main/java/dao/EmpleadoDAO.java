package dao;

import model.Empleado;

import javax.persistence.EntityManager;
import java.util.List;

public class EmpleadoDAO {

    private EntityManager em;

    public EmpleadoDAO(EntityManager em) {
        this.em = em;
    }

    public void registrarEmpleado(String nombre, String identificacion, int edad, String jornada, int tiempoLaborado) {
        Empleado empleado = new Empleado(nombre, identificacion, edad, jornada, tiempoLaborado);
        em.getTransaction().begin();
        this.em.persist(empleado);
        em.getTransaction().commit();
        em.close();
    }

    public List<Empleado> listarEmpleados() {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }
}
