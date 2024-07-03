package Database;

import Entidades.VentaProducto;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SQLVentaProducto {
    private final static String TABLA = "VENTAPRODUCTO";
    
    public static ArrayList<VentaProducto> getAll(String filterVentaId){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA + " WHERE VENTAID = '" + filterVentaId + "' ";
        
        var ventaProductos = new ArrayList<VentaProducto>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                VentaProducto ventaProducto = new VentaProducto();
                
                ventaProducto.setId(rs.getInt("ID"));
                ventaProducto.setProductoId(rs.getInt("PRODUCTOID"));
                
                ventaProducto.setProducto(SQLProducto.getById(ventaProducto.getId()));
                
                ventaProducto.setVentaId(rs.getInt("VENTAID"));
                ventaProducto.setCantidad(rs.getInt("CANTIDAD"));
                ventaProducto.setPrecioVenta(rs.getInt("PRECIOVENTA"));
                
                ventaProductos.add(ventaProducto); // Añado objeto al array
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(VentaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return ventaProductos;
    }
    
    public static void insert(VentaProducto ventaProducto, Connection cn){
        String sql = "INSERT INTO " + TABLA
                + " ( VENTAID, PRODUCTOID, CANTIDAD, PRECIOVENTA)" 
                + " VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setInt(1, ventaProducto.getVentaId());
            ps.setInt(2, ventaProducto.getProductoId());
            ps.setInt(3, ventaProducto.getCantidad());
            ps.setDouble(4, ventaProducto.getPrecioVenta());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VentaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarAllByVentaId(int ventaId, Connection cn){
        String sql = "DELETE FROM " + TABLA + " WHERE VENTAID = ?";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setInt(1, ventaId);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getNombreTabla(){
        return TABLA;
    }
}