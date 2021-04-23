package guis.registros;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import guis.paginamenus.frmPaginaOpciones;
import models.planillas.Planillas;
import models.registros.Registros;
import models.restApiError.RestApiError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmRegistros {
    public JPanel JpaRegistrosPrincipal;
    private JPanel JpaIzquierdo;
    private JPanel JpaDerecho;
    private JPanel JpaSuperior;
    private JLabel lblId;
    private JLabel lblVehiculo;
    private JLabel lblFechaEntrada;
    private JLabel lblFechaSalida;
    private JLabel lblFechaMaxima;
    private JLabel lblCantidad;
    private JButton btnPost;
    private JButton btnGetAll;
    private JButton btnGetId;
    private JButton btnGetVehiculo;
    private JButton btnPut;
    private JButton btnDelete;
    private JTextField txtId;
    private JTextField txtVehiculo;
    private JTextField txtEntrada;
    private JTextField txtSalida;
    private JTextField txtFechaMaxima;
    private JTextField txtCantidad;
    private JPanel JpaInferior;
    private JScrollPane scrlRegistros;
    private JTable tblRegistros;
    private JButton btnVolver;
    DefaultTableModel modeloRegistro  = new DefaultTableModel();

    public frmRegistros() {
        Iniciar();


        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addRegistro");

                    Invocation.Builder solicitud =target.request();

                    Registros registros = new Registros();
                    registros.setVehiculo(txtVehiculo.getText());
                    registros.setFechaentrada(txtEntrada.getText());
                    registros.setFechasalida(txtSalida.getText());
                    registros.setFechamaximaentrega(txtFechaMaxima.getText());
                    registros.setCantidadproducto(Integer.parseInt(txtCantidad.getText()));


                    Gson gson = new Gson();
                    String jsonString = gson.toJson(registros);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()){
                        case 201:
                            Registros data = new Gson().fromJson(responseJson, Registros.class);
                            res = "exito";
                            break;
                        case 500:
                            RestApiError apiError = new Gson().fromJson(responseJson,RestApiError.class);
                            res = apiError.getErrorDetails();
                            break;
                        default:
                            res = "Error ";
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

                    Registros registros = new Registros();
                    registros.setId(Long.parseLong(txtId.getText()));
                    registros.setVehiculo(txtVehiculo.getText());
                    registros.setFechaentrada(txtEntrada.getText());
                    registros.setFechasalida(txtSalida.getText());
                    registros.setFechamaximaentrega(txtFechaMaxima.getText());
                    registros.setCantidadproducto(Integer.parseInt(txtCantidad.getText()));


                    Gson gson = new Gson();
                    String jsonString = gson.toJson(registros);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Registros data = new Gson().fromJson(responseJson, Registros.class);



                    switch (put.getStatus()) {
                        case 200:
                            res = "Modificado";
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
                            res = "deleted";
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

                    List<Registros> data = new Gson().fromJson(responseJson,new TypeToken<List<Registros>>(){}.getType());

                    switch (get.getStatus()) {
                        case 200:
                            modeloRegistro.setRowCount(0);
                            for(Registros registro: data){
                                Object[] registroRegistros = {registro.getId(),registro.getVehiculo(),registro.getFechaentrada(),registro.getFechasalida(),registro.getFechamaximaentrega(),registro.getCantidadproducto()};
                                modeloRegistro.addRow(registroRegistros);
                            }
                            tblRegistros.setModel(modeloRegistro);
                            res = "Se han encontrado estos registros";
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
                    Registros data = new Gson().fromJson(responseJson, Registros.class);


                    switch (get.getStatus()) {
                        case 200:
                            modeloRegistro.setRowCount(0);
                            Object [] registrosRegistros = {data.getId(),data.getVehiculo(),data.getFechaentrada(),data.getFechasalida(),data.getFechamaximaentrega(),data.getCantidadproducto()};
                            modeloRegistro.addRow(registrosRegistros);
                            tblRegistros.setModel(modeloRegistro);
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
        btnGetVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/vehiculo/"+txtVehiculo.getText());
                    Invocation.Builder solicitud =target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Registros data = new Gson().fromJson(responseJson, Registros.class);

                    switch (get.getStatus()) {
                        case 200:
                            modeloRegistro.setRowCount(0);
                            Object [] registrosRegistros = {data.getId(),data.getVehiculo(),data.getFechaentrada(),data.getFechasalida(),data.getFechamaximaentrega(),data.getCantidadproducto()};
                            modeloRegistro.addRow(registrosRegistros);
                            tblRegistros.setModel(modeloRegistro);
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
        tblRegistros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblRegistros.getSelectedRow();
                txtId.setText(modeloRegistro.getValueAt(infoSeleccionado,0).toString());
                txtVehiculo.setText(modeloRegistro.getValueAt(infoSeleccionado, 1).toString());
                txtEntrada.setText(modeloRegistro.getValueAt(infoSeleccionado,5).toString());
                txtSalida.setText(modeloRegistro.getValueAt(infoSeleccionado, 4).toString());
                txtFechaMaxima.setText(modeloRegistro.getValueAt(infoSeleccionado,3) .toString());
                txtCantidad.setText(modeloRegistro.getValueAt(infoSeleccionado , 2).toString());

            }
        });
    }

    private void Iniciar() {
        modeloRegistro = (DefaultTableModel) tblRegistros.getModel();
        modeloRegistro.addColumn("Id");
        modeloRegistro.addColumn("Vehiculo");
        modeloRegistro.addColumn("Fecha entrada");
        modeloRegistro.addColumn("Fecha salida");
        modeloRegistro.addColumn("Fecha maxima entrega");
        modeloRegistro.addColumn("Cantidad Producto que lleva");

    }

    String res = "";
    String URL = "http://localhost:8080/api/v1/registros";
}
