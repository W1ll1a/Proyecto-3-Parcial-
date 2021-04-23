package guis.planillas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.ws.resources.ModelerMessages;
import guis.paginamenus.frmPaginaOpciones;
import models.loggin.Login;
import models.planillas.Planillas;
import models.restApiError.RestApiError;

import javax.jws.WebParam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmPlanillas {
    public JPanel JpaPlanillaPrincipal;
    private JPanel JpaIzquierdo;
    private JPanel JpaDerecho;
    private JPanel JpaSuperior;
    private JButton btnGetById;
    private JButton btnGetAll;
    private JButton btnPost;
    private JButton btnPut;
    private JButton btnGetByName;
    private JButton btnDelete;
    private JLabel lblNombre;
    private JLabel lblTotalPorHora;
    private JLabel lblHorasTrabajadas;
    private JLabel lblHorasExtras;
    private JLabel lblTotalPagar;
    private JTextField txtNombre;
    private JTextField txtTotalPorHora;
    private JTextField txtHorasTrabajadas;
    private JTextField txtHorasExtras;
    private JTextField txtTotalPagar;
    private JTextField txtID;
    private JLabel lblId;
    private JTable tblPlanillas;
    private JPanel JpaInferior;
    private JScrollPane scrlPlanilla;
    private JButton btnVolver;
    DefaultTableModel ModeloPlanilla = new DefaultTableModel();
    public frmPlanillas() {
        Iniciar();

        btnGetAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    //Invocation.Builder solicitud =target.queryParam("id",1).request();
                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    List<Planillas> data = new Gson().fromJson(responseJson,new TypeToken<List<Planillas>>(){}.getType());


                    switch (get.getStatus()) {
                        case 200:
                            ModeloPlanilla.setRowCount(0);
                            for (Planillas planillas : data){
                                Object[] registroPlanillas = {planillas.getId(),planillas.getName(),planillas.getTotalporhora(),planillas.getHorastrabajadas(),planillas.getHorasextra(),planillas.getTotalpagar()};
                                ModeloPlanilla.addRow(registroPlanillas);
                            }
                            tblPlanillas.setModel(ModeloPlanilla);
                            res = "Se encontraron las siguientes planillas en el servicio.";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e.toString());
                }

            }


        });
        tblPlanillas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int infoSeleccionado = tblPlanillas.getSelectedRow();
                txtID.setText(ModeloPlanilla.getValueAt(infoSeleccionado,0).toString());
                txtNombre.setText(ModeloPlanilla.getValueAt(infoSeleccionado, 1).toString());
                txtTotalPagar.setText(ModeloPlanilla.getValueAt(infoSeleccionado,5).toString());
                txtHorasExtras.setText(ModeloPlanilla.getValueAt(infoSeleccionado, 4).toString());
                txtHorasTrabajadas.setText(ModeloPlanilla.getValueAt(infoSeleccionado,3) .toString());
                txtTotalPorHora.setText(ModeloPlanilla.getValueAt(infoSeleccionado , 2).toString());

            }
        });
        btnGetById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/"+txtID.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Planillas data = new Gson().fromJson(responseJson, Planillas.class);

                    res = data.getName();

                    leerDatoId();

                    switch (get.getStatus()) {
                        case 200:
                            leerDatoId();
                            res = "Se encontró planilla exitosamente";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e.toString());
                }
            }
        });
        btnPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addPlanilla");

                    Invocation.Builder solicitud =target.request();

                    Planillas planilla = new Planillas();
                    planilla.setName(txtNombre.getText());
                    planilla.setHorasextra(Integer.parseInt(txtHorasExtras.getText()));
                    planilla.setHorastrabajadas(Integer.parseInt(txtHorasTrabajadas.getText()));
                    planilla.setTotalporhora(Double.parseDouble(txtTotalPorHora.getText()));
                    planilla.setTotalpagar(Double.parseDouble(txtTotalPagar.getText()));;

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(planilla);

                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()){
                        case 201:
                            Planillas data = new Gson().fromJson(responseJson, Planillas.class);
                            res = "exito";
                            break;
                            case 500:
                            RestApiError apiError = new Gson().fromJson(responseJson,RestApiError.class);
                            res = apiError.getErrorDetails();
                            break;
                        default:
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

                    Planillas planilla = new Planillas();
                    planilla.setId(Long.parseLong(txtID.getText()));
                    planilla.setName(txtNombre.getText());
                    planilla.setHorasextra(Integer.parseInt(txtHorasExtras.getText()));
                    planilla.setHorastrabajadas(Integer.parseInt(txtHorasTrabajadas.getText()));
                    planilla.setTotalporhora(Double.parseDouble(txtTotalPorHora.getText()));
                    planilla.setTotalpagar(Double.parseDouble(txtTotalPagar.getText()));


                    Gson gson = new Gson();
                    String jsonString = gson.toJson(planilla);
                    Response put = solicitud.put(Entity.json(jsonString));
                    String responseJson = put.readEntity(String.class);
                    Planillas data = new Gson().fromJson(responseJson, Planillas.class);

                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e.toString());
                }

            }
        });
        btnGetByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/nombre/"+txtNombre.getText());

                    //Invocation.Builder solicitud =target.queryParam("id",1).request();
                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();


                    String responseJson = get.readEntity(String.class);

                    Planillas data = new Gson().fromJson(responseJson, Planillas.class);

                    switch (get.getStatus()) {
                        case 200:
                            leerDatoNombre();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "No se encontro ");
                            break;
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e.toString());
                }

            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/delete/"+txtID.getText());
                    Invocation.Builder solicitud =target.request();
                    Response delete = solicitud.delete();
                    String responseJson = delete.readEntity(String.class);

                    switch (delete.getStatus()) {
                        case 200:
                            JOptionPane.showMessageDialog(null, "Deleted");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"No se encontro");
                            break;
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e.toString());
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
    }

    private void leerDatos() {
        try {
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();


            String responseJson = get.readEntity(String.class);

            List<Planillas> data = new Gson().fromJson(responseJson,new TypeToken<List<Login>>(){}.getType());


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    private void leerDatoId() {
        try {
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URL + "/id/"+txtID.getText());

            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();

            String responseJson = get.readEntity(String.class);
            Planillas data = new Gson().fromJson(responseJson, Planillas.class);
            ModeloPlanilla.setRowCount(0);
            Object[] registroPlanilla = {data.getId(),data.getName(),data.getTotalporhora(),data.getHorastrabajadas(),data.getHorasextra(),data.getTotalpagar()};
            ModeloPlanilla.addRow(registroPlanilla);
            tblPlanillas.setModel(ModeloPlanilla);
        }catch (Exception e){

        }
    }
    private void leerDatoNombre() {
        try {
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URL + "/nombre/"+txtNombre.getText());

            //Invocation.Builder solicitud =target.queryParam("id",1).request();
            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();

            String responseJson = get.readEntity(String.class);
            Planillas data = new Gson().fromJson(responseJson, Planillas.class);
            ModeloPlanilla.setRowCount(0);
            Object[] registroPlanilla = {data.getId(),data.getName(),data.getTotalporhora(),data.getHorastrabajadas(),data.getHorasextra(),data.getTotalpagar()};
            ModeloPlanilla.addRow(registroPlanilla);
            tblPlanillas.setModel(ModeloPlanilla);
        }catch (Exception e){

        }
    }


    private void Iniciar() {
        ModeloPlanilla = (DefaultTableModel)tblPlanillas.getModel();
        ModeloPlanilla.addColumn("Id");
        ModeloPlanilla.addColumn("Nombre");
        ModeloPlanilla.addColumn("Total pago por hora");
        ModeloPlanilla.addColumn("Horas trabajadas");
        ModeloPlanilla.addColumn("Horas extras trabajadas");
        ModeloPlanilla.addColumn("Total a pagar");

    }
    String res = "";
    String URL = "http://192.168.56.1:8080/api/v1/planillas";
}
