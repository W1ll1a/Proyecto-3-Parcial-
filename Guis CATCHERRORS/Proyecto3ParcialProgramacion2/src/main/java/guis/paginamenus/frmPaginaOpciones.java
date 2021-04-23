package guis.paginamenus;

import guis.login.frmLogin;
import guis.planillas.frmPlanillas;
import guis.registros.frmRegistros;
import guis.sedes.frmSedes;
import guis.ticket.frmTicket;
import guis.trabajadores.frmTrabajadores;
import guis.usuarios.frmUsuarios;
import guis.vehiculos.frmVehiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPaginaOpciones {
    public JPanel JpaPaginaMenus;
    private JButton btnPlanilla;
    private JPanel JpaSuperior;
    private JButton btnRegistro;
    private JButton btnSede;
    private JButton btnTrabajador;
    private JButton btnVehiculo;
    private JButton btnTickets;
    private JButton btnUsers;

    public frmPaginaOpciones() {
        btnPlanilla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame framePlanilla = new JFrame("Planilla");
                framePlanilla.setContentPane(new frmPlanillas().JpaPlanillaPrincipal);
                framePlanilla.setResizable(false);
                framePlanilla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                framePlanilla.pack();
                framePlanilla.setLocationRelativeTo(null);
                framePlanilla.setVisible(true);
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameRegistros = new JFrame("Registros");
                frameRegistros.setContentPane(new frmRegistros().JpaRegistrosPrincipal);
                frameRegistros.setResizable(false);
                frameRegistros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameRegistros.pack();
                frameRegistros.setLocationRelativeTo(null);
                frameRegistros.setVisible(true);
            }
        });
        btnSede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSedes = new JFrame("Sedes");
                frameSedes.setContentPane(new frmSedes().JpaSedesPrincipal);
                frameSedes.setResizable(false);
                frameSedes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameSedes.pack();
                frameSedes.setLocationRelativeTo(null);
                frameSedes.setVisible(true);
            }
        });
        btnTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameTrabajadores = new JFrame("Trabajadores");
                frameTrabajadores.setContentPane(new frmTrabajadores().JpaTrabajadoresPrincipal);
                frameTrabajadores.setResizable(false);
                frameTrabajadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameTrabajadores.pack();
                frameTrabajadores.setLocationRelativeTo(null);
                frameTrabajadores.setVisible(true);
            }
        });
        btnVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameVehiculos = new JFrame("Vehiculos");
                frameVehiculos.setContentPane(new frmVehiculos().JpaPrincipalVehiculos);
                frameVehiculos.setResizable(false);
                frameVehiculos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameVehiculos.pack();
                frameVehiculos.setLocationRelativeTo(null);
                frameVehiculos.setVisible(true);
            }
        });
        btnTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameOpciones = new JFrame("Tickets");
                frameOpciones.setContentPane(new frmTicket().JpaTicketPrincipal);
                frameOpciones.setResizable(false);
                frameOpciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameOpciones.pack();
                frameOpciones.setLocationRelativeTo(null);
                frameOpciones.setVisible(true);
            }
        });
        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameOpciones = new JFrame("Users");
                frameOpciones.setContentPane(new frmUsuarios().JpaUsersPrincipal);
                frameOpciones.setResizable(true);
                frameOpciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameOpciones.pack();
                frameOpciones.setLocationRelativeTo(null);
                frameOpciones.setVisible(true);
            }
        });
    }
}
