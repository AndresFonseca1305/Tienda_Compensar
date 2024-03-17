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

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "edad")
    private int edad;

    @Column(name = "jornada")
    private String jornada;

    @Column(name = "tiempo_laborado")
    private int tiempoLaborado;

    public Empleado() {
    }

    public Empleado(String nombre, String numeroDocumento, int edad, String jornada, int tiempoLaborado) {
        this.nombre = nombre;
        this.numeroDocumento = numeroDocumento;
        this.edad = edad;
        this.jornada = jornada;
        this.tiempoLaborado = tiempoLaborado;
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
}
