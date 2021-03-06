package model;
// Generated 09-ene-2020 17:36:09 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Expedientes generated by hbm2java
 */
public class Expedientes  implements java.io.Serializable {

     private Integer id;
     private Usuarios usuarios;
     private String nombre;
     private String apellidos;
     private String dni;
     private String cp;
     private Date fechaalta;
     private String telefono;
     private int nmascotas;

    public Expedientes() {
    }

    public Expedientes(Usuarios usuarios, String nombre, String apellidos, String dni, String cp, Date fechaalta, String telefono, int nmascotas) {
       this.usuarios = usuarios;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.dni = dni;
       this.cp = cp;
       this.fechaalta = fechaalta;
       this.telefono = telefono;
       this.nmascotas = nmascotas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getCp() {
        return this.cp;
    }
    
    public void setCp(String cp) {
        this.cp = cp;
    }
    public Date getFechaalta() {
        return this.fechaalta;
    }
    
    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getNmascotas() {
        return this.nmascotas;
    }
    
    public void setNmascotas(int nmascotas) {
        this.nmascotas = nmascotas;
    }

    @Override
    public String toString() {
        return "Name: "+this.nombre+", surname: "+this.apellidos+", DNI: "+this.dni+", CP: "+this.cp+", phone: "+telefono+ ", pet's number: "+this.nmascotas;
    }

    


}


