package controller;

import dao.ProductoDAO;
import model.Producto;
import utils.JPAutils;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductoController {

    private EntityManager em;

    private ProductoDAO productoDAO;

    public ProductoController() {
        this.em = JPAutils.getEntityManager();
        this.productoDAO = new ProductoDAO(em);
    }

    public void registrarProducto(String nombre, String tipo, int unidades, double precio) {
        productoDAO.registrarProducto(nombre, tipo, unidades, precio);
    }

    public List<Producto> obtenerProductos() {
       return productoDAO.listarProductos();
    }

    public void borrarProducto(Integer id) {
        productoDAO.borrarProducto(id);
    }

    public void actualizarProducto(Integer id, String nombre, String tipo, int unidades, double precio) {
        productoDAO.actualizarProducto(id, nombre, tipo, unidades, precio);
    }
}
