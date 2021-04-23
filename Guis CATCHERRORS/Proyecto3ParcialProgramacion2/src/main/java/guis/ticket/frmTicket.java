package guis.ticket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.collections.ImmutableObservableList;
import guis.paginamenus.frmPaginaOpciones;
import models.restApiError.RestApiError;
import models.sedes.Sedes;
import models.ticket.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmTicket {
    public JPanel JpaTicketPrincipal;
    private JPanel JpaIzquierdo;
    private JPanel JpaSuperior;
    private JPanel JpaDerecho;
    private JButton btnPost;
    private JButton btnGetAll;
    private JButton btnGetById;
    private JButton btnGetCliente;
    private JButton btnPut;
    private JButton btnDelete;
    private JButton btnVolver;
    private JLabel lblId;
    private JLabel lblCliente;
    private JLabel lblTelefono;
    private JLabel lblProducto;
    private JLabel lblCantidad;
    private JLabel lblFecha;
    private JTextField txtId;
    private JTextField txtCliente;
    private JTextField txtTelefono;
    private JTextField txtProducto;
    private JTextField txtCantidad;
    private JTextField txtFecha;
    private JTable tblTicket;
    private JPanel JpaInferior;
    private JScrollPane scrlTicket;
    DefaultTableModel model = new DefaultTableModel();

    public frmTicket() {
        iniciar();
        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addTicket");

                    Invocation.Builder solicitud =target.request();

                   Ticket ticket  = new Ticket();
                   ticket.setCliente(txtCliente.getText());
                   ticket.setCantidad(Integer.parseInt(txtCantidad.getText()));
                   ticket.setProducto(txtProducto.getText());
                   ticket.setTelefono(Integer.parseInt(txtTelefono.getText()));
                   ticket.setFechaentrega(txtFecha.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(ticket);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Ticket data = new Gson().fromJson(responseJson, Ticket.class);
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

                    List<Ticket> data = new Gson().fromJson(responseJson,new TypeToken<List<Ticket>>(){}.getType());


                    switch (get.getStatus()) {
                        case 200:
                            model.setRowCount(0);
                            for(Ticket ticket: data){
                                Object[] registro = {ticket.getId(),ticket.getCliente(),ticket.getTelefono(),ticket.getProducto(),ticket.getCantidad(),ticket.getFechaentrega() };
                                model.addRow(registro);
                            }
                            tblTicket.setModel(model);
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

                    Ticket data = new Gson().fromJson(responseJson, Ticket.class);

                    switch (get.getStatus()) {
                        case 200:
                            model.setRowCount(0);
                            Object[] registro = {data.getId(),data.getCliente(),data.getTelefono(),data.getProducto(),data.getCantidad(),data.getFechaentrega()};
                            model.addRow(registro);
                            tblTicket.setModel(model);
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
        btnGetCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/cliente/"+txtCliente.getText());


                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Ticket data = new Gson().fromJson(responseJson, Ticket.class);

                    switch (get.getStatus()) {
                        case 200:
                            model.setRowCount(0);
                            Object [] registroTicket = {data.getId(),data.getCliente(),data.getTelefono(),data.getProducto(),data.getCantidad(),data.getFechaentrega()};
                            model.addRow(registroTicket);
                            tblTicket.setModel(model);
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

                    Ticket ticket  = new Ticket();
                    ticket.setId(Long.parseLong(txtId.getText()));
                    ticket.setCliente(txtCliente.getText());
                    ticket.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    ticket.setProducto(txtProducto.getText());
                    ticket.setTelefono(Integer.parseInt(txtTelefono.getText()));
                    ticket.setFechaentrega(txtFecha.getText());


                    Gson gson = new Gson();
                    String jsonString = gson.toJson(ticket);
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
        tblTicket.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblTicket.getSelectedRow();
                txtId.setText(model.getValueAt(infoSeleccionado,0).toString());
                txtCliente.setText(model.getValueAt(infoSeleccionado,1).toString());
                txtTelefono.setText(model.getValueAt(infoSeleccionado,2).toString());
                txtProducto.setText(model.getValueAt(infoSeleccionado,3).toString());
                txtCantidad.setText(model.getValueAt(infoSeleccionado,4).toString());
                txtFecha.setText(model.getValueAt(infoSeleccionado,5).toString());
            }
        });
    }

    private void iniciar() {
        model = (DefaultTableModel)tblTicket.getModel();
        model.addColumn("Id");
        model.addColumn("Cliente");
        model.addColumn("Telefono");
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("Fecha de entrega");

    }

    String res = "";
    String URL = "http://localhost:8080/api/v1/tickets";
}
