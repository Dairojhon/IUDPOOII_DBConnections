
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Metodo para probar la conexion con MySQL
    public static String probarConexion(
            String url,
            String usuario,
            String password
    ) throws ClassNotFoundException, SQLException {

        // Cargar el controlador JDBC de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establecer conexion utilizando try-with-resources
        try (Connection conexion =
                DriverManager.getConnection(url, usuario, password)) {

            return "CONEXION EXITOSA A MYSQL\n"
                    + "Base de datos: ea3_conexiones\n"
                    + "Servidor: " + conexion.getMetaData().getURL();
        }
    }
}
