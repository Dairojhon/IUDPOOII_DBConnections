
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    // Paneles
    private final JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
    private final JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
    private final JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Etiquetas
    private final JLabel lblUrl = new JLabel("URL de conexion:");
    private final JLabel lblUser = new JLabel("Nombre de usuario:");
    private final JLabel lblPassword = new JLabel("Contrasena:");

    // Campos de texto
    private final JTextField txtUrl = new JTextField(25);
    private final JTextField txtUser = new JTextField(25);
    private final JPasswordField txtPassword = new JPasswordField(25);

    // Botones y resultados
    private final JButton btnConnection = new JButton("Probar conexion");
    private final JTextArea txtResultsArea = new JTextArea(8, 40);

    public VentanaPrincipal() {
        configurarVentana();
        configurarComponentes();
    }

    private void configurarVentana() {

        setTitle("DBConnections");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void configurarComponentes() {

        // Configurar el formulario
        panelFormulario.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        panelFormulario.add(lblUrl);
        panelFormulario.add(txtUrl);

        panelFormulario.add(lblUser);
        panelFormulario.add(txtUser);

        panelFormulario.add(lblPassword);
        panelFormulario.add(txtPassword);

        // Configurar el area de resultados
        txtResultsArea.setEditable(false);
        txtResultsArea.setLineWrap(true);
        txtResultsArea.setWrapStyleWord(true);

        JScrollPane scrollResultados = new JScrollPane(txtResultsArea);
        scrollResultados.setBorder(
            BorderFactory.createTitledBorder("Resultados de conexion")
        );

        // Configurar boton
        panelInferior.add(btnConnection);

        // Evento del boton de conexion
        btnConnection.addActionListener(e -> probarConexion());

        // Agregar componentes al panel principal
        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scrollResultados, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        setVisible(true);
    }

    // Metodo para probar la conexion
    private void probarConexion() {

        String url = txtUrl.getText().trim();
        String usuario = txtUser.getText().trim();
        String password = new String(txtPassword.getPassword());

        try {

            String resultado = ConexionBD.probarConexion(
                url,
                usuario,
                password
            );

            txtResultsArea.setForeground(new Color(0, 128, 0));
            txtResultsArea.setText(resultado);

        } catch (ClassNotFoundException e) {

            txtResultsArea.setForeground(Color.RED);
            txtResultsArea.setText(
                "ERROR: No se encontro el controlador JDBC.\n"
                + e.getMessage()
            );

        } catch (java.sql.SQLException e) {

            txtResultsArea.setForeground(Color.RED);
            txtResultsArea.setText(
                "FALLO DE CONEXION.\n"
                + "Detalle tecnico: " + e.getMessage()
            );

        }
    }
}
