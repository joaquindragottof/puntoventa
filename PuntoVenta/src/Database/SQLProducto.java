package Database;

import Entidades.Producto;
import Frames.ItemProducto;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SQLProducto {
    private final static String TABLA = "PRODUCTO";
    
    public static ArrayList<Producto> getAll(String filterId, String filterDescripcion){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA;
        
        if(!filterId.equals(""))
        {
            sql += " WHERE ID='" + filterId + "'";
            
            if(!filterDescripcion.equals("")){
                sql += " AND DESCRIPCION LIKE '%" + filterDescripcion + "%' ";
            }
        }
        else if(!filterDescripcion.equals("")){
            sql += " WHERE DESCRIPCION LIKE '%" + filterDescripcion + "%' ";
        }
        
        var productos = new ArrayList<Producto>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                Producto producto = new Producto();
                
                producto.setId(rs.getInt(1));
                producto.setAlias(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setPrecio(rs.getDouble(4));
                
                productos.add(producto);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return productos;
    }
    
    public static Producto getById(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA + " WHERE ID = " + id;
        
        Producto producto = new Producto();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("ID"));
                producto.setAlias(rs.getString("ALIAS"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setPrecio(rs.getDouble("PRECIO"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return producto;
    }
    
    // <editor-fold defaultstate="collapsed" desc="ABM">
    public static void insert(Producto producto){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "INSERT INTO " + TABLA
                + "( alias, descripcion, precio )" 
                + "VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, producto.getAlias());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modificar(Producto producto){ // Version que no permite nulos
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "UPDATE " + TABLA
                + " SET alias = ? , descripcion = ? , precio = ?" 
                + " WHERE ID = ?";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, producto.getAlias());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public static void modificar(Producto producto) { // Opcion que permite campos nulos
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();

        // Almacenar los valores de los campos en variables locales
        int id = producto.getId();
        String alias = producto.getAlias();
        String descripcion = producto.getDescripcion();
        Double precio = producto.getPrecio();

        ArrayList<Object> valores = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE " + TABLA + " SET ");

        boolean first = true;

        if (alias != null) {
            sql.append("alias = ?");
            valores.add(alias);
            first = false;
        }

        if (descripcion != null) {
            if (!first) sql.append(", ");
            sql.append("descripcion = ?");
            valores.add(descripcion);
            first = false;
        }

        if (precio != null) {
            if (!first) sql.append(", ");
            sql.append("precio = ?");
            valores.add(precio);
        }

        if (valores.isEmpty()) {
            System.out.println("No se han proporcionado valores para actualizar.");
            return; // Salir del método si no hay valores para actualizar
        }

        sql.append(" WHERE ID = ?");
        valores.add(id);
        
        try (PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            for (int i = 0; i < valores.size(); i++) {
                ps.setObject(i + 1, valores.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
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
            throw new Exception("No se puede eliminar el Producto porque tiene Ventas asociadas");
        }
    }
    
    private static int verificarVentasAsociadas(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT COUNT(1) AS CANTIDAD FROM "+ SQLVentaProducto.getNombreTabla() + " WHERE PRODUCTOID = " + id;
        
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
    
    public static ArrayList<ItemProducto> getAllComboBox(){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT ID, DESCRIPCION, PRECIO FROM " + TABLA;
        
        var productos = new ArrayList<ItemProducto>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                int id = rs.getInt("ID");
                String descripcion = rs.getString("DESCRIPCION");
                double precio = rs.getDouble("PRECIO");
                productos.add(new ItemProducto(id, descripcion, precio));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return productos;
    }
}