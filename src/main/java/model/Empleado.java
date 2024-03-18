package model;

import javax.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numero_documento", unique = true)
    private String numeroDocumento;

    @Column(name = "edad")
    private int edad;

    @Column(name = "jornada")
    private String jornada;

    @Column(name = "tiempo_laborado")
    private int tiempoLaborado;

    @Column(name = "descuento_tienda", nullable = true)
    private Double descuentoTienda;

    @Column(name = "descuento_centro_recreacional", nullable = true)
    private Double descuentoCentroRecreacional;

    public Empleado() {
    }

    public Empleado(String nombre, String numeroDocumento, int edad, String jornada, int tiempoLaborado) {
        this.nombre = nombre;
        this.numeroDocumento = numeroDocumento;
        this.edad = edad;
        this.jornada = jornada;
        this.tiempoLaborado = tiempoLaborado;
        // Establecer los descuentos basados en el tiempo laborado
        if (tiempoLaborado < 1) {
            this.descuentoTienda = 0.15;
            this.descuentoCentroRecreacional = 0.20;
        } else if (tiempoLaborado >= 1 && tiempoLaborado <= 5) {
            this.descuentoTienda = 0.30;
            this.descuentoCentroRecreacional = 0.30;
        } else if (tiempoLaborado > 5) {
            this.descuentoTienda = 0.50;
            this.descuentoCentroRecreacional = 0.60;
        }
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getTiempoLaborado() {
        return tiempoLaborado;
    }

    public void setTiempoLaborado(int tiempoLaborado) {
        this.tiempoLaborado = tiempoLaborado;
    }

    public double getDescuentoTienda() {
        return descuentoTienda;
    }

    public void setDescuentoTienda(double descuentoTienda) {
        this.descuentoTienda = descuentoTienda;
    }

    public double getDescuentoCentroRecreacional() {
        return descuentoCentroRecreacional;
    }

    public void setDescuentoCentroRecreacional(double descuentoCentroRecreacional) {
        this.descuentoCentroRecreacional = descuentoCentroRecreacional;
    }
}
