package com.example.examenrecu2;

import java.io.Serializable;

public class Persona implements Serializable {
    private int id;
    private String nombre, apellido, calle,pathMediano, pathPequeno;

    public Persona(String nombre, String apellido, String calle, String pathMediano, String pathPequeno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.pathMediano = pathMediano;
        this.pathPequeno = pathPequeno;
    }

    public Persona(int id,String nombre, String apellido, String calle, String pathMediano, String pathPequeno) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.pathMediano = pathMediano;
        this.pathPequeno = pathPequeno;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPathMediano() {
        return pathMediano;
    }

    public void setPathMediano(String pathMediano) {
        this.pathMediano = pathMediano;
    }

    public String getPathPequeno() {
        return pathPequeno;
    }

    public void setPathPequeno(String pathPequeno) {
        this.pathPequeno = pathPequeno;
    }
}
