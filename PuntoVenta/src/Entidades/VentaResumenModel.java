package Entidades;

import java.util.Date;

public class VentaResumenModel {
    private Date Fecha;
    private String Nombre;
    private double Total;

    public VentaResumenModel(Date Fecha, String Nombre, double Total) {
        this.Fecha = Fecha;
        this.Nombre = Nombre;
        this.Total = Total;
    }

    public Date getFecha() {
        return Fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public double getTotal() {
        return Total;
    }
    
    
}
