package Database;

import Entidades.Cliente;
import Entidades.Producto;
import Entidades.Vendedor;
import Entidades.Venta;
import Entidades.VentaProducto;
import Entidades.VentaResumenModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SQLVenta {
    private final static String TABLA = "VENTA";
    
    public static ArrayList<Venta> getAll(String filterId){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA;
        
        if(!filterId.equals(""))
        {
            sql += " WHERE ID ='" + filterId + "' ";
        }
        
        var ventas = new ArrayList<Venta>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                Venta venta = new Venta();
                
                venta.setId(rs.getInt("ID"));
                venta.setFecha(rs.getDate("FECHA"));
                
                Cliente cliente = SQLCliente.getById(rs.getInt("CLIENTEID"));
                venta.setCliente(cliente);
                
                Vendedor vendedor = SQLVendedor.getById(rs.getInt("VENDEDORID"));
                venta.setVendedor(vendedor);
                
                venta.setDescuento(rs.getDouble("DESCUENTO"));
                
                venta.setTotal(rs.getDouble("TOTAL"));
                
                ventas.add(venta);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return ventas;
    }
    
    // <editor-fold defaultstate="collapsed" desc="ABM">
    public static void insert(Venta venta){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "INSERT INTO " + TABLA
                + "( FECHA, VENDEDORID, CLIENTEID, DESCUENTO, TOTAL) " 
                + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            cn.setAutoCommit(false);
            PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            var fecha = venta.getFecha();
            
            ps.setDate(1, new java.sql.Date(venta.getFecha().getTime()) );
            
            if(venta.getVendedorId() != 0){
                ps.setInt(2, venta.getVendedorId());
            }
            
            if(venta.getClienteId() != 0){
                ps.setInt(3, venta.getClienteId());
            }
            
            ps.setDouble(4, venta.getDescuento());
            ps.setDouble(5, venta.CalculaTotal());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int ventaId = rs.getInt(1);
                if(venta.getVentaProductos() != null){
                    for(VentaProducto ventaProducto : venta.getVentaProductos()){
                        ventaProducto.setVentaId(ventaId);
                        SQLVentaProducto.insert(ventaProducto, cn);
                    }
                }
            }
            
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modificar(Venta venta){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "UPDATE " + TABLA
                + " SET FECHA = ? , VENDEDORID = ? , CLIENTEID = ?, DESCUENTO = ?, TOTAL = ? " 
                + " WHERE ID = ?";
        
        try {
            cn.setAutoCommit(false);
            
            // Eliminar VentaProductos anteriores relacionados
            SQLVentaProducto.eliminarAllByVentaId(venta.getId(), cn);
            
            // Modificar Venta
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(venta.getFecha().getTime()) );
            ps.setInt(2, venta.getVendedorId());
            ps.setInt(3, venta.getClienteId());
            ps.setDouble(4, venta.getDescuento());
            ps.setDouble(5, venta.CalculaTotal());
            
            ps.setInt(6, venta.getId());
            
            ps.executeUpdate();
            
            // Agregar VentaProductos nuevos
            if(venta.getVentaProductos() != null){
                for(VentaProducto ventaProducto : venta.getVentaProductos()){
                    ventaProducto.setVentaId(venta.getId());
                    SQLVentaProducto.insert(ventaProducto, cn);
                }
            }
            
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void eliminar(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "DELETE FROM " + TABLA + " WHERE ID = ?";
        
        try {
            cn.setAutoCommit(false);
            // Eliminar VentaProductos relacionados
            SQLVentaProducto.eliminarAllByVentaId(id, cn);
            
            // Eliminar Venta
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SQLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>
    
    public static Venta getById(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA + " WHERE ID = " + id;
        
        Venta venta = new Venta();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            if (rs.next()) {
                venta = new Venta();
                venta.setId(rs.getInt("ID"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return venta;
    }
    
    public static ArrayList<VentaResumenModel> getResumenVentas(String vendedor){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT "
                + "VENTA.FECHA AS FECHA, "
                + "VENDEDOR.NOMBRE AS NOMBRE, "
                + "SUM(TOTAL) AS TOTAL "
                + "FROM VENTA ";
        sql += " LEFT JOIN " + "VENDEDOR ON VENTA.VENDEDORID = VENDEDOR.ID ";
        
        if(vendedor != null && !vendedor.equals("") && !vendedor.equals("Todos")){
            sql += " WHERE VENDEDOR.NOMBRE = '" + vendedor + "' ";
        }
                
        sql += " GROUP BY VENTA.FECHA, VENDEDOR.NOMBRE";
        
        var ventasResumen = new ArrayList<VentaResumenModel>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                VentaResumenModel venta = new VentaResumenModel(rs.getDate("FECHA"), rs.getString("NOMBRE") ,rs.getDouble("TOTAL"));
                
                ventasResumen.add(venta);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(SQLVenta.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return ventasResumen;
    }
    
    public static String getNombreTabla(){
        return TABLA;
    }
}