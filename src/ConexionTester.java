
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class ConexionTester extends JFrame {

    private static final Color COLOR_EXITO = new Color(0, 128, 0);
    private static final Color COLOR_ERROR = new Color(200, 0, 0);
    private static final int TIEMPO_ESPERA_SEGUNDOS = 5;

    private final JTextField txtUrl = new JTextField(25);
    private final JTextField txtuser = new JTextField(25);
    private final JPasswordField txtPassword = new JPasswordField(25);    
    private final JButton btnConnection = new JButton("Conectar");
    private final JTextArea txtResultsArea=new JTextArea(8, 40);

    public ConexionTester(){
        configurarVentana();
        add(crearPanelPrincipal());
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);        
    }

    private void configurarVentana(){
        setTitle("DBConnections");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());        
        setSize(600, 400);        
        setLocationRelativeTo(null);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JPanel superior = new JPanel(new BorderLayout(0, 10));
        superior.add(crearPanelFormulario(), BorderLayout.CENTER);
        superior.add(crearPanelBoton(), BorderLayout.SOUTH);
        panel.add(superior, BorderLayout.NORTH);
        panel.add(crearPanelResultado(), BorderLayout.CENTER);
        return panel;
    }


    private JPanel crearPanelFormulario() {
        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8));
        formulario.add(crearEtiqueta("URL de conexión:", txtUrl));
        formulario.add(txtUrl);
        formulario.add(crearEtiqueta("Nombre de usuario:", txtuser));
        formulario.add(txtuser);
        formulario.add(crearEtiqueta("Contraseña:", txtPassword));
        formulario.add(txtPassword);

        txtUrl.setToolTipText("Ejemplo: jdbc:sqlite:inventario.db");
        txtuser.setToolTipText("Dejar vacío si es SQLite");
        txtPassword.setToolTipText("Dejar vacío si es SQLite");
        return formulario;
    }

    private JLabel crearEtiqueta(String texto, JComponent campo) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setLabelFor(campo);
        return etiqueta;
    }

    private JPanel crearPanelBoton() {
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnConnection.addActionListener(e -> probarConexion());
        getRootPane().setDefaultButton(btnConnection); 
        panelBoton.add(btnConnection);
        return panelBoton;
    }
    private JScrollPane crearPanelResultado() {
        txtResultsArea.setEditable(false);
        txtResultsArea.setLineWrap(true);
        txtResultsArea.setWrapStyleWord(true);
        txtResultsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(txtResultsArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado de la prueba"));
        return scroll;
    }

    private void probarConexion() {
        String url = txtUrl.getText().trim();
        String usuario = txtuser.getText().trim();
        char[] password = txtPassword.getPassword();

        if (url.isEmpty()) {
            Arrays.fill(password, '\0');
            mostrarResultado("Fallo de conexión: debe escribir la URL de conexión.", COLOR_ERROR);
            return;
        }

        Properties credenciales = new Properties();
        if (!usuario.isEmpty()) {
            credenciales.setProperty("user", usuario);
        }
        if (password.length > 0) {
            credenciales.setProperty("password", new String(password));
        }
        Arrays.fill(password, '\0'); 

        try {
            cargarDriver(url);                               
            DriverManager.setLoginTimeout(TIEMPO_ESPERA_SEGUNDOS);

            
            try (Connection conexion = DriverManager.getConnection(url, credenciales)) {
                DatabaseMetaData info = conexion.getMetaData();
                mostrarResultado("Conexión exitosa.\n"
                        + "Base de datos: " + info.getDatabaseProductName()
                        , COLOR_EXITO);
            }
        } catch (ClassNotFoundException e) {
            mostrarResultado("Fallo del driver: no se encontró la clase " + e.getMessage()
                    + ".\nAgregue el .jar del driver JDBC a las librerías del proyecto.", COLOR_ERROR);
        } catch (SQLException e) {
            mostrarResultado("Fallo de conexión: " + e.getMessage(), COLOR_ERROR);
        }
    }

    
    private void cargarDriver(String url) throws ClassNotFoundException {
        String driver = obtenerClaseDriver(url);
        if (driver != null) {
            Class.forName(driver);
        }
    }

    private String obtenerClaseDriver(String url) {
        String u = url.toLowerCase();
        if (u.startsWith("jdbc:sqlite:"))      return "org.sqlite.JDBC";
        if (u.startsWith("jdbc:mysql:"))       return "com.mysql.cj.jdbc.Driver";
        if (u.startsWith("jdbc:mariadb:"))     return "org.mariadb.jdbc.Driver";       
        if (u.startsWith("jdbc:sqlserver:"))   return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        return null;
    }

    /** Reemplaza el contenido del área de resultado y le asigna el color del mensaje. */
    private void mostrarResultado(String mensaje, Color color) {
        txtResultsArea.setForeground(color);
        txtResultsArea.setText(mensaje);
        txtResultsArea.setCaretPosition(0);
    }
}
