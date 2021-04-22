package guis.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import guis.paginamenus.frmPaginaOpciones;

import guis.planillas.frmPlanillas;
import models.loggin.Login;
import models.planillas.Planillas;
import guis.paginaprincipal.frmPaginaPrincipal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class frmLogin {
    public JPanel Jpalogin;
    private JButton btnLogin;
    private JTextField txtUsuario;
    private JPanel JpaDerecho;
    private JPanel JpaIzquierdo;
    private JPanel JpaSuperior;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JPasswordField Password;
    private JButton btnGetUsers;
    private JButton btnGetCuentaByUsuario;
    private JTable tblLogin;

    public frmLogin() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String claveIntroducida =new String(Password.getPassword());
                    Client client = ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/user/" + txtUsuario.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Login data = new Gson().fromJson(responseJson, Login.class);
                    if(claveIntroducida.equals(data.getClave())){
                        JOptionPane.showMessageDialog(null,"Bienvenivenido");
                        JFrame frame1 = new JFrame("Menus");
                        frame1.setContentPane(new frmPaginaOpciones().JpaPaginaMenus);
                        frame1.setResizable(false);
                        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }


                }catch (Exception ex){

                }

            }
        });
    }

    String URL ="http://localhost:8080/api/v1/logins";
    String res = "";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Login");
        frame1.setContentPane(new frmLogin().Jpalogin);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}
