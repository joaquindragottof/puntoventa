package Database;

import Entidades.Cliente;
import Entidades.Producto;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SQLCliente {
    private final static String TABLA = "CLIENTE";
    
    public static Cliente getById(int id){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT * FROM " + TABLA + " WHERE ID = " + id;
        
        Cliente cliente = null;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            if (rs.next()) {
                cliente = new Cliente(rs.getInt("ID"), rs.getString("NOMBRE"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return cliente;
    }
    
    public static HashMap<Integer, String> getAllComboBox(){
        Conectar cc = new Conectar();
        Connection cn = cc.conexion();
        
        String sql = "SELECT ID, NOMBRE FROM " + TABLA;
        
        var clientes = new HashMap<Integer, String>();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                int id = rs.getInt("ID");
                String descripcion = rs.getString("NOMBRE");
                clientes.put(id, descripcion);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return clientes;
    }
}