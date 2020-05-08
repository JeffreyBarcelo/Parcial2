package com.example.formativa_2;

public class Usuarios {
    public String identificacion,nombre,estrato,salario,nivel;

    public Usuarios(String identificacion, String nombre, String estrato, String salario, String nivel) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.estrato = estrato;
        this.salario = salario;
        this.nivel = nivel;
    }

    public Usuarios() {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivelEducativo) {
        this.nivel = nivel;
    }

}
