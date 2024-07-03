package Entidades;

public class Producto {
    private int Id;
    private String Alias;
    private String Descripcion;
    private double Precio;
    
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }
    
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }
    // </editor-fold>
}
