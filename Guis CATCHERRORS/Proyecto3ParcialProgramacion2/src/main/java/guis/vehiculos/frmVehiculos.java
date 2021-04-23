package guis.vehiculos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import guis.paginamenus.frmPaginaOpciones;
import models.loggin.Login;
import models.restApiError.RestApiError;
import models.trabajadores.Trabajadores;
import models.vehiculos.Vehiculos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmVehiculos {
    public JPanel JpaPrincipalVehiculos;
    private JPanel JpaSuperior;
    private JButton btnPost;
    private JButton btnGetAll;
    private JButton btnGetById;
    private JButton btnGetByVehiculo;
    private JButton btnPut;
    private JButton btnDelete;
    private JPanel JpaIzquierdo;
    private JPanel JpaDerecho;
    private JLabel lblId;
    private JLabel lblTipoVehiculo;
    private JLabel lblMarca;
    private JLabel lblPlaca;
    private JLabel lblColor;
    private JLabel lblEstado;
    private JTextField txtId;
    private JTextField txtTipoVehiculo;
    private JTextField txtMarca;
    private JTextField txtPlaca;
    private JTextField txtColor;
    private JTextField txtEstado;
    private JPanel JpaInferior;
    private JScrollPane scrlVehiculos;
    private JTable tblVehiculos;
    private JButton btnVolver;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmVehiculos() {
        Inicio();
        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addVehiculo");

                    Invocation.Builder solicitud =target.request();

                    Vehiculos vehiculo = new Vehiculos();
                    vehiculo.setTipodevehiculo(txtTipoVehiculo.getText());
                    vehiculo.setMarcavehiculo(txtTipoVehiculo.getText());
                    vehiculo.setColor(txtColor.getText());
                    vehiculo.setPlaca(txtPlaca.getText());
                    vehiculo.setEstado(txtEstado.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(vehiculo);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Vehiculos data = new Gson().fromJson(responseJson, Vehiculos.class);
                            res = "Exito";
                            break;
                        case 500:
                            RestApiError apiError = new Gson().fromJson(responseJson,RestApiError.class);
                            res = apiError.getErrorDetails();
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
        btnGetAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    List<Vehiculos> data = new Gson().fromJson(responseJson,new TypeToken<List<Vehiculos>>(){}.getType());

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            for(Vehiculos vehiculos: data){
                                Object[] registroVehiculos = {vehiculos.getId(),vehiculos.getTipodevehiculo(),vehiculos.getMarcavehiculo(),vehiculos.getPlaca(),vehiculos.getColor(),vehiculos.getEstado()};
                                modelo.addRow(registroVehiculos);
                            }
                            tblVehiculos.setModel(modelo);
                            res = "Exito";
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
        btnGetById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Vehiculos data = new Gson().fromJson(responseJson, Vehiculos.class);

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registro = {data.getId(),data.getTipodevehiculo(),data.getMarcavehiculo(),data.getPlaca(),data.getColor(),data.getEstado()};
                            modelo.addRow(registro);
                            tblVehiculos.setModel(modelo);
                            res = "Exito";
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
        btnGetByVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/placa/"+txtPlaca.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Vehiculos data = new Gson().fromJson(responseJson, Vehiculos.class);

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registro = {data.getId(),data.getTipodevehiculo(),data.getMarcavehiculo(),data.getPlaca(),data.getColor(),data.getEstado()};
                            modelo.addRow(registro);
                            tblVehiculos.setModel(modelo);
                            res = "Exito";
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
        btnPut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Vehiculos vehiculos = new Vehiculos();
                    vehiculos.setId(Long.parseLong(txtId.getText()));
                    vehiculos.setTipodevehiculo(txtTipoVehiculo.getText());
                    vehiculos.setMarcavehiculo(txtMarca.getText());
                    vehiculos.setPlaca(txtPlaca.getText());
                    vehiculos.setColor(txtColor.getText());
                    vehiculos.setEstado(txtEstado.getText());



                    Gson gson = new Gson();
                    String jsonString = gson.toJson(vehiculos);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Vehiculos data = new Gson().fromJson(responseJson, Vehiculos.class);

                    switch (put.getStatus()) {
                        case 200:
                            res = "Exito";
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

                    switch (delete.getStatus()) {
                        case 200:
                            res = "Delete";
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
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame framOpciones = new JFrame("Opciones ");
                framOpciones.setContentPane(new frmPaginaOpciones().JpaPaginaMenus);
                framOpciones.setResizable(false);
                framOpciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                framOpciones.pack();
                framOpciones.setLocationRelativeTo(null);
                framOpciones.setVisible(true);
            }
        });
        tblVehiculos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblVehiculos.getSelectedRow();
                txtId.setText(modelo.getValueAt(infoSeleccionado,0).toString());
                txtTipoVehiculo.setText(modelo.getValueAt(infoSeleccionado,1).toString());
                txtMarca.setText(modelo.getValueAt(infoSeleccionado,2).toString());
                txtPlaca.setText(modelo.getValueAt(infoSeleccionado,3).toString());
                txtColor.setText(modelo.getValueAt(infoSeleccionado,4).toString());
                txtEstado.setText(modelo.getValueAt(infoSeleccionado,5).toString());
            }
        });
    }

    private void Inicio() {
        modelo = (DefaultTableModel) tblVehiculos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Tipo de vehiculo");
        modelo.addColumn("Marca");
        modelo.addColumn("Placa");
        modelo.addColumn("Color");
        modelo.addColumn("Estado");
    }
    String res = "";
    String URL = "http://localhost:8080/api/v1/vehiculos";
}
