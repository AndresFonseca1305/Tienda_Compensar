package model;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "numero_unidades")
    private int numeroUnidades;

    @Column(name = "valor_unitario")
    private double valorUnitario;

    @Column(name = "iva")
    private double iva;

    @Column(name = "valor_total")
    private double valorTotal;

    public Producto() {
    }

    public Producto(String nombre, String tipo, int numeroUnidades, double valorUnitario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.numeroUnidades = numeroUnidades;
        this.valorUnitario = valorUnitario;
        // Establecer el IVA basado en el tipo de producto
        switch (tipo.toLowerCase()) {
            case "aseo":
                this.iva = valorUnitario * 0.19;
                break;
            case "papeleria":
                this.iva = valorUnitario * 0.09;
                break;
            case "viveres":
                this.iva = valorUnitario * 0.15;
                break;
            case "mascotas":
                this.iva = valorUnitario * 0.16;
                break;
            default:
                this.iva = valorUnitario * 0.10;
                break;
        }
        this.valorTotal = valorUnitario + iva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
