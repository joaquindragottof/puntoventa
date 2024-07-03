package Entidades;

public abstract class Persona {
    private int Id;
    private String Nombre;

    public Persona() {
        
    }
    
    public Persona(int Id, String Nombre) {
        this.Id = Id;
        this.Nombre = Nombre;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    // </editor-fold>
    
    public String toString(){
        return this.Nombre;
    }
}
