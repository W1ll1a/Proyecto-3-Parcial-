package guis.sedes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import guis.paginamenus.frmPaginaOpciones;
import models.sedes.Sedes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmSedes {
    public JPanel JpaSedesPrincipal;
    private JButton btnPost;
    private JButton btnGetAll;
    private JButton btnGetId;
    private JButton btnGetSede;
    private JButton btnPut;
    private JButton btnDelete;
    private JPanel JpaSuperior;
    private JPanel JpaIzquierdo;
    private JPanel JpaDerecho;
    private JLabel lblId;
    private JLabel lblCiudad;
    private JLabel lblCantidad;
    private JLabel lblNombreDirector;
    private JLabel lblNombreSede;
    private JLabel lblDireccion;
    private JTextField txtId;
    private JTextField txtCiudad;
    private JTextField txtCantidadVehiculos;
    private JTextField txtDirector;
    private JTextField txtNombreSede;
    private JTextField txtDireccion;
    private JPanel JpaInferior;
    private JScrollPane scrlSedes;
    private JTable tblSedes;
    private JButton btnVolver;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmSedes() {
        Inicio();
        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addSede");

                    Invocation.Builder solicitud =target.request();

                    Sedes sede = new Sedes();
                    sede.setNombredesede(txtNombreSede.getText());
                    sede.setCiudad(txtCiudad.getText());
                    sede.setCantidaddevehiculosensede(Integer.parseInt(txtCantidadVehiculos.getText()));
                    sede.setNombredirector(txtDirector.getText());
                    sede.setDireccion(txtDireccion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(sede);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);
                    Sedes data = new Gson().fromJson(responseJson, Sedes.class);

                    switch (post.getStatus()) {
                        case 201:
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
        btnGetAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");


                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    List<Sedes> data = new Gson().fromJson(responseJson,new TypeToken<List<Sedes>>(){}.getType());


                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            for(Sedes sede: data){
                                Object[] registro = {sede.getId(),sede.getNombredesede(),sede.getCiudad(),sede.getCantidaddevehiculosensede(),sede.getNombredirector(),sede.getDireccion() };
                                modelo.addRow(registro);
                            }
                            tblSedes.setModel(modelo);
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
        btnGetId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    Sedes data = new Gson().fromJson(responseJson, Sedes.class);

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registro = {data.getId(),data.getNombredesede(),data.getCiudad(),data.getCantidaddevehiculosensede(),data.getNombredirector(),data.getDireccion()};
                            modelo.addRow(registro);
                            tblSedes.setModel(modelo);
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
        btnGetSede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/nombreDeSede/"+txtNombreSede.getText());


                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Sedes data = new Gson().fromJson(responseJson, Sedes.class);

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            Object [] registrosSedes = {data.getId(),data.getNombredesede(),data.getCiudad(),data.getCantidaddevehiculosensede(),data.getNombredirector(),data.getDireccion()};
                            modelo.addRow(registrosSedes);
                            tblSedes.setModel(modelo);
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

                    Sedes sede = new Sedes();
                    sede.setId(Long.parseLong(txtId.getText()));
                    sede.setCiudad(txtCiudad.getText());
                    sede.setDireccion(txtDireccion.getText());
                    sede.setNombredirector(txtDirector.getText());
                    sede.setNombredesede(txtNombreSede.getText());
                    sede.setCantidaddevehiculosensede(Integer.parseInt(txtCantidadVehiculos.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(sede);
                    Response put = solicitud.put(Entity.json(jsonString));
                    String responseJson = put.readEntity(String.class);
                    Sedes data = new Gson().fromJson(responseJson, Sedes.class);

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
        tblSedes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblSedes.getSelectedRow();
                txtId.setText(modelo.getValueAt(infoSeleccionado,0).toString());
                txtNombreSede.setText(modelo.getValueAt(infoSeleccionado,1).toString());
                txtCiudad.setText(modelo.getValueAt(infoSeleccionado,2).toString());
                txtCantidadVehiculos.setText(modelo.getValueAt(infoSeleccionado,3).toString());
                txtDirector.setText(modelo.getValueAt(infoSeleccionado,4).toString());
                txtDireccion.setText(modelo.getValueAt(infoSeleccionado,5).toString());
            }
        });
    }

    private void Inicio() {
        modelo = (DefaultTableModel) tblSedes.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre de Sede");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Cantidad de vehiculos en sede");
        modelo.addColumn("Directo");
        modelo.addColumn("Direccion");
    }
    String res = "";
    String URL = "http://localhost:8080/api/v1/sedes";
}
