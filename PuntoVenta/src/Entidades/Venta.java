package Entidades;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    private int Id;
    private Date Fecha;
    private int clienteId;
    private Cliente Cliente;
    private int vendedorId;
    private Vendedor Vendedor;
    private double Descuento;
    private double Total;
    private ArrayList<VentaProducto> VentaProductos;

    public Venta() {
        VentaProductos = new ArrayList<VentaProducto>();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }
        
    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public Vendedor getVendedor() {
        return Vendedor;
    }

    public void setVendedor(Vendedor Vendedor) {
        this.Vendedor = Vendedor;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    public ArrayList<VentaProducto> getVentaProductos() {
        return VentaProductos;
    }

    public void setVentaProductos(ArrayList<VentaProducto> VentaProductos) {
        this.VentaProductos = VentaProductos;
    }
    
    // </editor-fold>
    
    public double CalculaTotal(){
        double total = 0;
        for(VentaProducto ventaProducto : this.VentaProductos){
            total += ventaProducto.getCantidad()*ventaProducto.getPrecioVenta();
        }
        return total - Descuento;
    }
}
