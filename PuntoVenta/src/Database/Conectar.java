package Database;

import java.sql.*;

public class Conectar {
    private Connection conexion = null;
    
    private final String MYSQL_DRIVER  = "com.mysql.jdbc.Driver";
    private final String nombreBD = "puntoventa";
    private final String port = "3306";
    private final String user = "root";
    private final String pw = "";
    
    public Connection conexion(){
        try {
            Class.forName(MYSQL_DRIVER);
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + nombreBD, user, pw);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conexion;
    }
}