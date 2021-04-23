package guis.paginaprincipal;

import com.google.gson.Gson;
import guis.login.frmLogin;
import models.loggin.Login;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPaginaPrincipal {
    private JPanel JpaPaginaPrincipal;
    private JPanel JpaSuperior;
    private JButton btnLogin;
    private JButton btnCreat;
    private JLabel lblUsuario;
    private JLabel lblEmail;
    private JLabel lblTelefono;
    private JLabel lblPassword;
    private JLabel lblfechaNacimiento;
    private JPanel JpaIzquierdo;
    private JPanel JpaDerecho;
    private JTextField txtUsuario;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtPassword;
    private JTextField txtFechaNacimiento;
    private JButton btnActualizar;
    private JLabel lblId;
    private JButton btnDelete;
    private JTextField txtId;

    public frmPaginaPrincipal() {
        btnCreat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addLogin");

                    Invocation.Builder solicitud =target.request();

                    Login Cuenta = new Login();
                    Cuenta.setUsuario(txtUsuario.getText());
                    Cuenta.setClave(txtPassword.getText());
                    Cuenta.setEmail(txtEmail.getText());
                    Cuenta.setNumerotelefono(Integer.parseInt(txtTelefono.getText()));
                    Cuenta.setFechanacimiento(txtFechaNacimiento.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(Cuenta);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);
                    Login data = new Gson().fromJson(responseJson, Login.class);

                    res = data.getUsuario();

                    JOptionPane.showMessageDialog(null,post.getStatus());

                    switch (post.getStatus()) {
                        case 201:
                            res = data.getUsuario();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameLogin = new JFrame("Pagina login");
                frameLogin.setContentPane(new frmLogin().Jpalogin);
                frameLogin.setResizable(false);
                frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameLogin.pack();
                frameLogin.setLocationRelativeTo(null);
                frameLogin.setVisible(true);
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Login acount = new Login();
                    acount.setId(Long.parseLong(txtId.getText()));
                    acount.setUsuario(txtUsuario.getText());
                    acount.setEmail(txtEmail.getText());
                    acount.setNumerotelefono(Integer.parseInt(txtTelefono.getText()));
                    acount.setFechanacimiento(txtFechaNacimiento.getText());
                    acount.setClave(txtPassword.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(acount);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Login data = new Gson().fromJson(responseJson, Login.class);

                    res = data.getUsuario();

                    JOptionPane.showMessageDialog(null,put.getStatus());

                    switch (put.getStatus()) {
                        case 200:
                            res = "La actualizacion de su cuenta se ha logrado correctamente";
                            break;
                        default:
                            res = "Error, no se pudo actualizar.";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/delete/"+txtId.getText());

                    //Invocation.Builder solicitud =target.queryParam("id",4).request();
                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);

                    JOptionPane.showMessageDialog(null,delete.getStatus());

                    switch (delete.getStatus()) {
                        case 200:
                            res = "Delete";
                            break;
                        case 404:
                            res = "No se encontro ";
                            break;
                        case 500:
                            res ="Error con el servidor";
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
    }
    String res = "";
    String URL = "http://localhost:8080/api/v1/logins";

}
