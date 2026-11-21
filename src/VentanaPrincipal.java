
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
J
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