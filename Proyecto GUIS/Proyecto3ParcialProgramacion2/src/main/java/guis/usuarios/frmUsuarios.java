package guis.usuarios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.loggin.Login;
import models.planillas.Planillas;
import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmUsuarios {
    public JPanel JpaUsersPrincipal;
    private JPanel JpaIzquierdo;
    private JPanel JpaDerecho;
    private JPanel JpaSuperior;
    private JLabel lblId;
    private JLabel lblUsuario;
    private JLabel lblClave;
    private JLabel lblEmail;
    private JLabel lblFechaNacimiento;
    private JLabel lblnumeroTelefono;
    private JTextField txtId;
    private JTextField txtUsuario;
    private JTextField txtClave;
    private JTextField txtEmail;
    private JTextField txtFechaNacimiento;
    private JTextField txtNumero;
    private JButton btnPost;
    private JButton btnGetAll;
    private JButton btnGetId;
    private JButton btnGetUser;
    private JButton btnPut;
    private JButton btnDelete;
    private JPanel JpaInferior;
    private JTable tblUsuarios;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmUsuarios() {
        iniciar();

        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addLogin");

                    Invocation.Builder solicitud =target.request();

                    Login Cuenta = new Login();
                    Cuenta.setUsuario(txtUsuario.getText());
                    Cuenta.setClave(txtClave.getText());
                    Cuenta.setEmail(txtEmail.getText());
                    Cuenta.setNumerotelefono(Integer.parseInt(txtNumero.getText()));
                    Cuenta.setFechanacimiento(txtFechaNacimiento.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(Cuenta);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);
                    Login data = new Gson().fromJson(responseJson, Login.class);


                    switch (post.getStatus()) {
                        case 201:
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                }

            }
        });
        btnGetAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    List<Login> data = new Gson().fromJson(responseJson,new TypeToken<List<Login>>(){}.getType());

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            for (Login users : data){
                                Object[] registroUsuarios = {users.getId(),users.getUsuario(),users.getClave(),users.getEmail(),users.getNumerotelefono(),users.getFechanacimiento()};
                                modelo.addRow(registroUsuarios);
                            }
                            tblUsuarios.setModel(modelo);
                            res = "Se encontraron las siguientes planillas en el servicio.";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
            }
        });
        btnGetId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Login data = new Gson().fromJson(responseJson, Login.class);
                    switch (get.getStatus()){
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registroUsers = {data.getId(),data.getUsuario(),data.getClave(),data.getEmail(),data.getNumerotelefono(),data.getFechanacimiento()};
                            modelo.addRow(registroUsers);
                            tblUsuarios.setModel(modelo);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"No se encontro");
                            break;
                    }
                }catch(Exception e1){

                }
            }
        });
        btnGetUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/user/"+txtUsuario.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Login data = new Gson().fromJson(responseJson, Login.class);
                    switch (get.getStatus()){
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registroUsers = {data.getId(),data.getUsuario(),data.getClave(),data.getEmail(),data.getNumerotelefono(),data.getFechanacimiento()};
                            modelo.addRow(registroUsers);
                            tblUsuarios.setModel(modelo);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"No se encontro");
                            break;
                    }
                }catch(Exception e1){

                }
            }
        });
        btnPut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Login cuenta = new Login();
                    cuenta.setId(Long.parseLong(txtId.getText()));
                    cuenta.setUsuario(txtUsuario.getText());
                    cuenta.setClave(txtClave.getText());
                    cuenta.setEmail(txtEmail.getText());
                    cuenta.setNumerotelefono(Integer.parseInt(txtNumero.getText()));
                    cuenta.setFechanacimiento(txtFechaNacimiento.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(cuenta);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);

                    Login data = new Gson().fromJson(responseJson, Login.class);

                    switch (put.getStatus()) {
                        case 200:
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){

                }

            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/delete/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);

                 if (delete.getStatus() ==200 ){
                     JOptionPane.showMessageDialog(null,"Se ha eliminado con exito");
                 }else {
                     if (delete.getStatus() ==404) {
                         JOptionPane.showMessageDialog(null, "No se encontro");
                     }else {
                         JOptionPane.showMessageDialog(null, "Se encontro un error");
                     }
                 }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e.toString());
                }
            }
        });
        tblUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblUsuarios.getSelectedRow();
                txtId.setText(modelo.getValueAt(infoSeleccionado,0).toString());
                txtUsuario.setText(modelo.getValueAt(infoSeleccionado,1).toString());
                txtClave.setText(modelo.getValueAt(infoSeleccionado,2).toString());
                txtEmail.setText(modelo.getValueAt(infoSeleccionado,3).toString());
                txtNumero.setText(modelo.getValueAt(infoSeleccionado,4).toString());
                txtFechaNacimiento.setText(modelo.getValueAt(infoSeleccionado,5).toString());
            }
        });
    }

    private void iniciar() {
        modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Usuario");
        modelo.addColumn("Password");
        modelo.addColumn("Email");
        modelo.addColumn("Numero de telefono");
        modelo.addColumn("Fecha de nacimiento");
    }

    String res = "";
    String URL = "http://localhost:8080/api/v1/logins";
}
