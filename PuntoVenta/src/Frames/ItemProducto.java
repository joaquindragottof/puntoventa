package Frames;

public class ItemProducto extends Item {
    private String descripcion;
    private double precio;

    public ItemProducto(int id, String descripcion, double precio) {
        super(id, descripcion);
        this.precio = precio;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    // </editor-fold>
}