package SIdP;
import java.util.*;
public class Identificacion{

    Integer ci;
    String nombre;
    String apellido;
    String estado;
    String fecha;
    String estado_c;
    String ciudad_nac;
    String ciudad_act;
    String profesion;

    public Identificacion() {
    }

    public Identificacion(Integer ci, String nombre, String apellido, String estado, String fecha, String estado_c, String ciudad_nac, String ciudad_act, String profesion) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.fecha = fecha;
        this.estado_c = estado_c;
        this.ciudad_nac = ciudad_nac;
        this.ciudad_act = ciudad_act;
        this.profesion = profesion;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado_c() {
        return estado_c;
    }

    public void setEstado_c(String estado_c) {
        this.estado_c = estado_c;
    }

    public String getCiudad_nac() {
        return ciudad_nac;
    }

    public void setCiudad_nac(String ciudad_nac) {
        this.ciudad_nac = ciudad_nac;
    }

    public String getCiudad_act() {
        return ciudad_act;
    }

    public void setCiudad_act(String ciudad_act) {
        this.ciudad_act = ciudad_act;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}

