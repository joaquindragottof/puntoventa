package Entidades;

public class Vendedor extends Persona {
    private double Sueldo;

    public Vendedor(int Id, String Nombre) {
        super(Id, Nombre);
    }

    public Vendedor() {
        
    }
    
    public Vendedor(int Id, String Nombre, double Sueldo) {
        super(Id, Nombre);
        this.Sueldo = Sueldo;
    }

    public double getSueldo() {
        return Sueldo;
    }

    public void setSueldo(double Sueldo) {
        this.Sueldo = Sueldo;
    }
    
    
}
