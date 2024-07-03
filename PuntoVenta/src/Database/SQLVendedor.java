package Database;

import Entidades.Vendedor;
import Entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SQLVendedor {
    private final static String TABLA = "VENDEDOR";
    
    public static ArrayList<Vendedor> getAll(String filterDescripcion){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA;
        
        if(!filterDescripcion.equals("")){
            sql += " WHERE DESCRIPCION LIKE '%" + filterDescripcion + "%' ";
        }
        
        var vendedores = new ArrayList<Vendedor>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                Vendedor vendedor = new Vendedor(   rs.getInt("ID"),
                                                    rs.getString("NOMBRE"), 
                                                    rs.getDouble("SUELDO"));
                
                vendedores.add(vendedor);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return vendedores;
    }
    
    public static Vendedor getById(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA + " WHERE ID = " + id;
        
        Vendedor vendedor = null;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            if (rs.next()) {
                vendedor = new Vendedor(rs.getInt("ID"),
                                        rs.getString("NOMBRE"), 
                                        rs.getDouble("SUELDO"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return vendedor;
    }
    
    // <editor-fold defaultstate="collapsed" desc="ABM">
    public static void insert(Vendedor vendedor){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "INSERT INTO " + TABLA
                + "( NOMBRE, SUELDO)" 
                + "VALUES (?, ?)";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, vendedor.getNombre());
            ps.setDouble(2, vendedor.getSueldo());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modificar(Vendedor vendedor){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "UPDATE " + TABLA
                + " SET NOMBRE = ? , SUELDO = ?" 
                + " WHERE ID = ?";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, vendedor.getNombre());
            ps.setDouble(2, vendedor.getSueldo());
                        
            ps.setInt(3, vendedor.getId());
             
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void eliminar(int id) throws Exception {
        if(verificarVentasAsociadas(id) == 0){
            Conectar cc = new Conectar();
            Connection cn = cc.conexion();

            String sql = "DELETE FROM " + TABLA + " WHERE ID = ?";

            try {
                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setInt(1, id);

                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(SQLProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            throw new Exception("No se puede eliminar el Vendedor porque tiene Ventas asociadas");
        }
    }
    
    private static int verificarVentasAsociadas(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT COUNT(1) AS CANTIDAD FROM "+ SQLVenta.getNombreTabla() + " WHERE VENDEDORID = " + id;
        
        int cantidad = 0;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            if (rs.next()) {
                cantidad = rs.getInt("CANTIDAD");
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return cantidad;
    }
    // </editor-fold>
    
    public static HashMap<Integer, String> getAllComboBox(){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT ID, NOMBRE FROM " + TABLA;
        
        var vendedors = new HashMap<Integer, String>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                int id = rs.getInt("ID");
                String descripcion = rs.getString("NOMBRE");
                vendedors.put(id, descripcion);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return vendedors;
    }
}