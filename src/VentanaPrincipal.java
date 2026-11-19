
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("DBConnections");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Panel panel = new Panel();

        JLabel lblUrl = new JLabel("URL Conexion:");
        JTextField txtUrl = new JTextField(15);
        JLabel lblUser = new JLabel("Nombre de usuario");
        JTextField txtuser = new JTextField(15);
        JLabel lblPassword = new JLabel("Nombre de usuario");
        JTextField txtPassword = new JTextField(15);

        JButton btnConnection = new JButton("Conectar");

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