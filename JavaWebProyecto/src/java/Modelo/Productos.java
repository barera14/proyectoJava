package Modelo;
// Generated 30-oct-2017 17:42:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Productos generated by hbm2java
 */
public class Productos  implements java.io.Serializable {


     private Integer idProducto;
     private String nombre;
     private String descripcion;
     private int valor;
     private Set<ProductosCotizaciones> productosCotizacioneses = new HashSet<ProductosCotizaciones>(0);

    public Productos() {
    }

	
    public Productos(String nombre, String descripcion, int valor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
    }
    public Productos(String nombre, String descripcion, int valor, Set<ProductosCotizaciones> productosCotizacioneses) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.valor = valor;
       this.productosCotizacioneses = productosCotizacioneses;
    }
   
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getValor() {
        return this.valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    public Set<ProductosCotizaciones> getProductosCotizacioneses() {
        return this.productosCotizacioneses;
    }
    
    public void setProductosCotizacioneses(Set<ProductosCotizaciones> productosCotizacioneses) {
        this.productosCotizacioneses = productosCotizacioneses;
    }




}


