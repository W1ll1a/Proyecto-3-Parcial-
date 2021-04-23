package guis.trabajadores;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import guis.paginamenus.frmPaginaOpciones;
import models.restApiError.RestApiError;
import models.sedes.Sedes;
import models.ticket.Ticket;
import models.trabajadores.Trabajadores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmTrabajadores {
    public JPanel JpaTrabajadoresPrincipal;
    private JPanel JpaIzquierdo;
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblFechaNacimiento;
    private JLabel lblFechaInicio;
    private JLabel lblTelefono;
    private JLabel lblPuesto;
    private JPanel JpaSuperioro;
    private JPanel JpaDerecho;
    private JButton btnPost;
    private JButton btnGetAll;
    private JButton btnGetId;
    private JButton btnGetTelefono;
    private JButton btnPut;
    private JButton btnDelete;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtFechaNacimiento;
    private JTextField txtfechaInicio;
    private JTextField txtNumeroTelefono;
    private JTextField txtPuesto;
    private JTable tblTrabajadores;
    private JPanel JpaInferior;
    private JScrollPane scrlTrabajadores;
    private JButton btnVolver;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmTrabajadores() {
        Inicio();
        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addTrabajador");

                    Invocation.Builder solicitud =target.request();

                    Trabajadores trabajador = new Trabajadores();
                    trabajador.setNombre(txtNombre.getText());
                    trabajador.setNumerotelefono(Integer.parseInt(txtNumeroTelefono.getText()));
                    trabajador.setFechanacimiento(txtFechaNacimiento.getText());
                    trabajador.setFechainicio(txtfechaInicio.getText());
                    trabajador.setPuesto(txtPuesto.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(trabajador);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Trabajadores data = new Gson().fromJson(responseJson, Trabajadores.class);
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

                    List<Trabajadores> data = new Gson().fromJson(responseJson,new TypeToken<List<Trabajadores>>(){}.getType());

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            for(Trabajadores trabajador: data){
                                Object[] registro = {trabajador.getId(),trabajador.getNombre(),trabajador.getNumerotelefono(),trabajador.getFechanacimiento(),trabajador.getPuesto(),trabajador.getFechainicio()};
                                modelo.addRow(registro);
                            }
                            tblTrabajadores.setModel(modelo);
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

                    Trabajadores data = new Gson().fromJson(responseJson, Trabajadores.class);

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registro = {data.getId(),data.getNombre(),data.getNumerotelefono(),data.getFechanacimiento(),data.getPuesto(),data.getFechainicio()};
                            modelo.addRow(registro);
                            tblTrabajadores.setModel(modelo);
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
        btnGetTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/telefono/"+txtNumeroTelefono.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Trabajadores data = new Gson().fromJson(responseJson, Trabajadores.class);

                    switch (get.getStatus()) {
                        case 200:
                            modelo.setRowCount(0);
                            Object[] registro = {data.getId(),data.getNombre(),data.getNumerotelefono(),data.getFechanacimiento(),data.getPuesto(),data.getFechainicio()};
                            modelo.addRow(registro);
                            tblTrabajadores.setModel(modelo);
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

                    Trabajadores trabajador = new Trabajadores();
                    trabajador.setId(Long.parseLong(txtId.getText()));
                    trabajador.setPuesto(txtPuesto.getText());
                    trabajador.setFechainicio(txtfechaInicio.getText());
                    trabajador.setFechanacimiento(txtFechaNacimiento.getText());
                    trabajador.setNombre(txtNombre.getText());
                    trabajador.setNumerotelefono(Integer.parseInt(txtNumeroTelefono.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(trabajador);
                    Response put = solicitud.put(Entity.json(jsonString));
                    String responseJson = put.readEntity(String.class);
                    Trabajadores data = new Gson().fromJson(responseJson, Trabajadores.class);

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
        tblTrabajadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblTrabajadores.getSelectedRow();
                txtId.setText(modelo.getValueAt(infoSeleccionado,0).toString());
                txtNombre.setText(modelo.getValueAt(infoSeleccionado,1).toString());
                txtNumeroTelefono.setText(modelo.getValueAt(infoSeleccionado,2).toString());
                txtFechaNacimiento.setText(modelo.getValueAt(infoSeleccionado,3).toString());
                txtPuesto.setText(modelo.getValueAt(infoSeleccionado,4).toString());
                txtfechaInicio.setText(modelo.getValueAt(infoSeleccionado,5).toString());
            }
        });
    }

    private void Inicio() {
        modelo= (DefaultTableModel) tblTrabajadores.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Trabajador");
        modelo.addColumn("Numero de telefono");
        modelo.addColumn("Fechan de nacimiento");
        modelo.addColumn("Puesto");
        modelo.addColumn("Fecha de inicio");
    }
    String res = "";
    String URL = "http://localhost:8080/api/v1/trabajadores";
}
