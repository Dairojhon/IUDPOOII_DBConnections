
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

        Panel panel = new Panel();
        JLabel lblUrl = new JLabel("URL Conexion:");
        private final JTextField txtUrl = new JTextField(25);
        JLabel lblUser = new JLabel("Nombre de usuario");
        private final JTextField txtuser = new JTextField(25);
        JLabel lblPassword = new JLabel("Nombre de usuario");
        private final JTextField txtPassword = new JTextField(15);    
        private final JButton btnConnection = new JButton("Conectar");
        private final JTextArea txtResultsArea=new JTextArea(8, 40);

    public VentanaPrincipal(){
        configurarVentana();
        
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
        // El ActionListener contiene la lógica de prueba de conexión.
        btnConnection.addActionListener(e -> Conectar());
        getRootPane().setDefaultButton(btnConnection); // Enter también prueba la conexión
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


        

        

        panel.add(lblUrl);
        panel.add(txtUrl);

        panel.add(lblUser);
        panel.add(txtuser);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnConnection);

        add(panel);

        setVisible(true);
    }
}