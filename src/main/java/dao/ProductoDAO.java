package dao;

import model.Producto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductoDAO {

    private EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    public void registrarProducto(String nombre, String tipo, int unidades, double precio) {
        Producto producto = new Producto(nombre, tipo,unidades, precio);
        em.getTransaction().begin();
        this.em.persist(producto);
        em.getTransaction().commit();
        em.close();
    }

    public List<Producto> listarProductos() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }
}
