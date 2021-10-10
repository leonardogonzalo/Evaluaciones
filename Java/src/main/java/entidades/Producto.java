package entidades;

public class Producto {

    public Producto(String descripcion, Double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto(){}
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String descripcion;
    public Double precio;
}
