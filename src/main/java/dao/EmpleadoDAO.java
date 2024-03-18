package dao;

import model.Empleado;
import utils.JPAutils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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
    }

    public List<Empleado> listarEmpleados() {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    public Empleado obtenerEmpleadoPorDocumento(String numeroDocumento) {
        try {
            return em.createQuery("SELECT e FROM Empleado e WHERE e.numeroDocumento = :numeroDocumento", Empleado.class)
                    .setParameter("numeroDocumento", numeroDocumento)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void borrarEmpleado(String numeroDocumento) {
        em.getTransaction().begin();
        TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e WHERE e.numeroDocumento = :numeroDocumento", Empleado.class);
        query.setParameter("numeroDocumento", numeroDocumento);
        Empleado empleado = query.getSingleResult();
        if (empleado != null) {
            em.remove(empleado);
        }
        em.getTransaction().commit();
    }

    public void actualizarEmpleado(String numeroDocumento, String nombre, int edad, String jornada, int tiempoLaborado) {
        em.getTransaction().begin();
        TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e WHERE e.numeroDocumento = :numeroDocumento", Empleado.class);
        query.setParameter("numeroDocumento", numeroDocumento);
        Empleado empleado = query.getSingleResult();
        if (empleado != null) {
            empleado.setNombre(nombre);
            empleado.setEdad(edad);
            empleado.setJornada(jornada);
            empleado.setTiempoLaborado(tiempoLaborado);
            // Establecer los descuentos basados en el tiempo laborado
            if (tiempoLaborado < 1) {
                empleado.setDescuentoTienda(0.15);
                empleado.setDescuentoCentroRecreacional( 0.20);
            } else if (tiempoLaborado >= 1 && tiempoLaborado <= 5) {
                empleado.setDescuentoTienda(0.30);
                empleado.setDescuentoCentroRecreacional( 0.30);
            } else if (tiempoLaborado > 5) {
                empleado.setDescuentoTienda(0.50);
                empleado.setDescuentoCentroRecreacional( 0.60);
            }
        }
        em.getTransaction().commit();
    }
}

