package Modelo;
// Generated 30-oct-2017 17:42:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes  implements java.io.Serializable {


     private Integer idCliente;
     private String razonSocial;
     private String nit;
     private String ciudad;
     private String direccion;
     private String telefono;
     private String contrasena;
     private Set<Cotizaciones> cotizacioneses = new HashSet<Cotizaciones>(0);

    public Clientes() {
    }

	
    public Clientes(String razonSocial, String nit, String ciudad, String direccion, String telefono, String contrasena) {
        this.razonSocial = razonSocial;
        this.nit = nit;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }
    public Clientes(String razonSocial, String nit, String ciudad, String direccion, String telefono, String contrasena, Set<Cotizaciones> cotizacioneses) {
       this.razonSocial = razonSocial;
       this.nit = nit;
       this.ciudad = ciudad;
       this.direccion = direccion;
       this.telefono = telefono;
       this.contrasena = contrasena;
       this.cotizacioneses = cotizacioneses;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public String getRazonSocial() {
        return this.razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getNit() {
        return this.nit;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Set<Cotizaciones> getCotizacioneses() {
        return this.cotizacioneses;
    }
    
    public void setCotizacioneses(Set<Cotizaciones> cotizacioneses) {
        this.cotizacioneses = cotizacioneses;
    }




}


