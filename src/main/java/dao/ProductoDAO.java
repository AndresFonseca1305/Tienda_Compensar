package dao;


import model.Empleado;
import model.Producto;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public void borrarProducto(Integer id) {
        em.getTransaction().begin();
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.id = :id", Producto.class);
        query.setParameter("id", id);
        Producto producto = query.getSingleResult();
        if (producto != null) {
            em.remove(producto);
        }
        em.getTransaction().commit();
    }

    public void actualizarProducto(Integer id, String nombre, String tipo, int unidades, double precio) {
        em.getTransaction().begin();
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.id = :id", Producto.class);
        query.setParameter("id", id);
        Producto producto = query.getSingleResult();
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setNumeroUnidades(unidades);
            producto.setValorUnitario(precio);
            switch (tipo.toLowerCase()) {
                case "aseo":
                    producto.setIva(precio * 0.19);
                    break;
                case "papeleria":
                    producto.setIva(precio * 0.09);
                    break;
                case "viveres":
                    producto.setIva(precio * 0.15);
                    break;
                case "mascotas":
                    producto.setIva(precio * 0.16);
                    break;
                default:
                    producto.setIva(precio * 0.10);
                    break;
            }
            producto.setValorTotal(precio + producto.getIva());
        }
        em.getTransaction().commit();
    }
}
