package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Searching extends JFrame {

  private JPanel contentPane;
  private JTextField txtSearch;
  private JTable tbGuests;
  private JTable tbBookings;
  private DefaultTableModel model;
  private DefaultTableModel modelGuests;
  private JLabel labelBack;
  private JLabel labelExit;
  int xMouse, yMouse;
  String booking;
  String guests;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Searching frame = new Searching();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Searching() {
    this.BookingController = new BookingsController();
    this.guestsController = new GuestsController();
    setIconImage(Toolkit.getDefaultToolkit().getImage(Searching.class.getResource("/imagenes/lupa2.png")));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 910, 571);
    contentPane = new JPanel();
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    setLocationRelativeTo(null);
    setUndecorated(true);
    contentPane.setLayout(null);
    JScrollPane scrollPane = new JScrollPane(tbBookings);

    txtSearch = new JTextField();
    txtSearch.setBounds(536, 127, 193, 31);
    txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    contentPane.add(txtSearch);
    txtSearch.setColumns(10);

    JLabel lblTitle = new JLabel("SEARCHING SYSTEM");
    lblTitle.setBounds(MAXIMIZED_BOTH, yMouse, WIDTH, HEIGHT);
    lblTitle.setForeground(new Color(12, 138, 199));
    lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
    contentPane.add(lblTitle);

    JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
    panel.setBounds(20, 169, 865, 328);
    panel.setBackground(new Color(12, 138, 199));
    panel.setFont(new Font("Roboto", Font.PLAIN, 16));
    contentPane.add(panel);

    tbGuests = new JTable();
    tbGuests.setFont(new Font("Roboto", Font.PLAIN, 16));
    panel.addTab("Huéspedes", new ImageIcon(Searching.class.getResource("/imagenes/pessoas.png")), tbGuests, null);
    modelGuests = (DefaultTableModel) tbGuests.getModel();
    modelGuests.addColumn("Guest number");
    modelGuests.addColumn("Name");
    modelGuests.addColumn("Last Name");
    modelGuests.addColumn("Birth Date");
    modelGuests.addColumn("Nacionality");
    modelGuests.addColumn("Phone Number");
    modelGuests.addColumn("Booking Number");
    LlenarTablaHuespedes();

    tbBookings = new JTable();
    tbBookings.setFont(new Font("Roboto", Font.PLAIN, 16));
    panel.addTab("Reservas", new ImageIcon(Searching.class.getResource("/imagenes/reservado.png")), tbBookings, null);
    model = (DefaultTableModel) tbBookings.getModel();
    model.addColumn("Booking number");
    model.addColumn("Check In Date");
    model.addColumn("Check Out Date");
    model.addColumn("Value");
    model.addColumn("Payment Method");
    tbBookings.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    LlenarTablaReservas();

    JLabel logo = new JLabel("");
    logo.setBounds(56, 51, 104, 107);
    logo.setIcon(new ImageIcon(Searching.class.getResource("/imagenes/Ha-100px.png")));
    contentPane.add(logo);

    JPanel header = new JPanel();
    header.setBounds(0, 0, 910, 36);
    header.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        headerMouseDragged(e);

      }
    });
    header.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        headerMousePressed(e);
      }
    });
    header.setLayout(null);
    header.setBackground(Color.WHITE);
    contentPane.add(header);

    JPanel btnBack = new JPanel();
    btnAtras.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        UserMenu usuario = new UserMenu();
        user.setVisible(true);
        dispose();
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnBack.setBackground(new Color(12, 138, 199));
        labelBack.setForeground(Color.white);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        btnBack.setBackground(Color.white);
        labelBack.setForeground(Color.black);
      }
    });
    btnBack.setLayout(null);
    btnBack.setBackground(Color.WHITE);
    btnBack.setBounds(0, 0, 53, 36);
    header.add(btnBack);

    labelBack = new JLabel("<");
    labelBack.setHorizontalAlignment(SwingConstants.CENTER);
    labelBack.setFont(new Font("Roboto", Font.PLAIN, 23));
    labelBack.setBounds(0, 0, 53, 36);
    btnBack.add(labelBack);

    JPanel btnexit = new JPanel();
    btnexit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        UserMenu usuario = new UserMenu();
        user.setVisible(true);
        dispose();
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnexit.setBackground(Color.red);
        labelExit.setForeground(Color.white);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        btnexit.setBackground(Color.white);
        labelExit.setForeground(Color.black);
      }
    });
    btnexit.setLayout(null);
    btnexit.setBackground(Color.WHITE);
    btnexit.setBounds(857, 0, 53, 36);
    header.add(btnexit);

    labelExit = new JLabel("X");
    labelExit.setHorizontalAlignment(SwingConstants.CENTER);
    labelExit.setForeground(Color.BLACK);
    labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
    labelExit.setBounds(0, 0, 53, 36);
    btnexit.add(labelExit);

    JSeparator separator_1_2 = new JSeparator();
    separator_1_2.setBounds(539, 159, 193, 2);
    separator_1_2.setForeground(new Color(12, 138, 199));
    separator_1_2.setBackground(new Color(12, 138, 199));
    contentPane.add(separator_1_2);

    JPanel btnSearch = new JPanel();
    btnSearch.setBounds(748, 125, 122, 35);
    btnSearch.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        limpiarTabla();
        if (txtSearch.getText().equals("")) {
          LlenarTablaHuespedes();
          LlenarTablaReservas();
        } else {
          LlenarTablaReservasId();
          LlenarTablaHuespedesId();
        }
      }
    });
    btnSearch.setLayout(null);
    btnSearch.setBackground(new Color(12, 138, 199));
    btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    contentPane.add(btnSearch);

    JLabel lblSearch = new JLabel("BUSCAR");
    lblSearch.setBounds(0, 0, 122, 35);
    btnSearch.add(lblSearch);
    lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
    lblSearch.setForeground(Color.WHITE);
    lblSearch.setFont(new Font("Roboto", Font.PLAIN, 18));

    JPanel btnEditar = new JPanel();
    btnEditar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int rowBookings = tbBookings.getSelectedRow();
        int rowGuests = tbGuests.getSelectedRow();

        if (rowBookings >= 0) {
          ActualizarReservas();
          limpiarTabla();
          LlenarTablaReservas();
          LlenarTablaHuespedes();
        } else if (rowGuests >= 0) {
          ActualizarHuesped();
          limpiarTabla();
          LlenarTablaHuespedes();
          LlenarTablaReservas();
        }
      }
    });
    btnEditar.setBounds(635, 508, 122, 35);
    btnEditar.setLayout(null);
    btnEditar.setBackground(new Color(12, 138, 199));
    btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    contentPane.add(btnEditar);

    JLabel lblEditar = new JLabel("EDITAR");
    lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
    lblEditar.setForeground(Color.WHITE);
    lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
    lblEditar.setBounds(0, 0, 122, 35);
    btnEditar.add(lblEditar);

    JPanel btnEliminar = new JPanel();
    btnEliminar.setBounds(767, 508, 122, 35);
    btnEliminar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int rowBookings = tbBookings.getSelectedRow();
        int rowGuests = tbGuests.getSelectedRow();

        if (rowBookings >= 0) {

          booking = tbBookings.getValueAt(rowBookings, 0).toString();
          int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?");

          if (confirmar == JOptionPane.YES_OPTION) {

            String valor = tbBookings.getValueAt(rowBookings, 0).toString();
            BookingController.Eliminar(Integer.valueOf(valor));
            JOptionPane.showMessageDialog(contentPane, "Registro Eliminado");
            limpiarTabla();
            LlenarTablaReservas();
            LlenarTablaHuespedes();
          }
        }

        else if (rowGuests >= 0) {

          guests = tbGuests.getValueAt(filaHuespedes, 0).toString();
          int confirmarh = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?");

          if (confirmarh == JOptionPane.YES_OPTION) {

            String valor = tbGuests.getValueAt(filaHuespedes, 0).toString();
            guestsController.Eliminar(Integer.valueOf(valor));
            JOptionPane.showMessageDialog(contentPane, "Registro Eliminado");
            limpiarTabla();
            LlenarTablaHuespedes();
            LlenarTablaReservas();
          }
        } else {
          JOptionPane.showMessageDialog(null,
              "Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar");
        }
      }
    });
    btnEliminar.setLayout(null);
    btnEliminar.setBackground(new Color(12, 138, 199));
    btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    contentPane.add(btnEliminar);

    JLabel lblEliminar = new JLabel("ELIMINAR");
    lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
    lblEliminar.setForeground(Color.WHITE);
    lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
    lblEliminar.setBounds(0, 0, 122, 35);
    btnEliminar.add(lblEliminar);
    setResizable(false);
  }

  private List<Booking> BuscarReservas() {
    return this.bookingController.buscar();
  }

  private List<Booking> BuscarReservasId() {
    return this.bookingController.buscarId(txtBuscar.getText());
  }

  private List<Guests> BuscarHuespedes() {
    return this.huespedesController.listarHuespedes();
  }

  private List<Guests> BuscarHuespedesId() {
    return this.huespedesController.listarHuespedesId(txtBuscar.getText());
  }

  private void limpiarTabla() {
    ((DefaultTableModel) tbGuests.getModel()).setRowCount(0);
    ((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
  }

  private void LlenarTablaReservas() {

    // Llenar tabla
    List<Booking> reserva = BuscarReservas();
    try {
      for (Booking reservas : reserva) {
        modelo.addRow(new Object[] { reservas.getId(), reservas.getfechaE(), reservas.getfechaS(), reservas.getvalor(),
            reservas.getformaPago() });
      }
    } catch (Exception e) {
      throw e;
    }
  }

  private void LlenarTablaReservasId() {

    // Llenar tabla
    List<Booking> reserva = BuscarReservasId();
    try {
      for (Booking reservas : reserva) {
        modelo.addRow(new Object[] { reservas.getId(), reservas.getfechaE(), reservas.getfechaS(), reservas.getvalor(),
            reservas.getformaPago() });
      }
    } catch (Exception e) {
      throw e;
    }
  }

  private void LlenarTablaHuespedes() {
    // Llenar Tabla
    List<Guests> huesped = BuscarHuespedes();
    try {
      for (Guests huespedes : huesped) {
        modelGuests.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
            huespedes.getFechaNacimiento(), huespedes.getNacionalidad(), huespedes.getTelefono(),
            huespedes.getIdReserva() });
      }
    } catch (Exception e) {
      throw e;
    }
  }

  private void LlenarTablaHuespedesId() {
    // Llenar Tabla
    List<Guests> huesped = BuscarHuespedesId();
    try {
      for (Guests huespedes : huesped) {
        modelGuests.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
            huespedes.getFechaNacimiento(), huespedes.getNacionalidad(), huespedes.getTelefono(),
            huespedes.getIdReserva() });
      }
    } catch (Exception e) {
      throw e;
    }
  }

  private void ActualizarReservas() {
    Optional.ofNullable(model.getValueAt(tbBookings.getSelectedRow(), tbBookings.getSelectedColumn()))
        .map(Object::toString)
        .ifPresentOrElse(row -> {
          Date fechaE = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
          Date fechaS = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
          String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
          String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
          Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
          this.reservaController.actualizar(fechaE, fechaS, valor, formaPago, id);
          JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
  }

  private void ActualizarHuesped() {
    Optional.ofNullable(modelGuests.getValueAt(tbGuests.getSelectedRow(), tbGuests.getSelectedColumn()))
        .ifPresentOrElse(rowGuests -> {

          String nombre = (String) modelGuests.getValueAt(tbGuests.getSelectedRow(), 1);
          String apellido = (String) modelGuests.getValueAt(tbGuests.getSelectedRow(), 2);
          Date fechaN = Date.valueOf(modelGuests.getValueAt(tbGuests.getSelectedRow(), 3).toString());
          String nacionalidad = (String) modelGuests.getValueAt(tbGuests.getSelectedRow(), 4);
          String telefono = (String) modelGuests.getValueAt(tbGuests.getSelectedRow(), 5);
          Integer idReserva = Integer.valueOf(modelGuests.getValueAt(tbGuests.getSelectedRow(), 6).toString());
          Integer id = Integer.valueOf(modelGuests.getValueAt(tbGuests.getSelectedRow(), 0).toString());
          this.huespedesController.actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
          JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));

  }

  private void headerMousePressed(java.awt.event.MouseEvent evt) {
    xMouse = evt.getX();
    yMouse = evt.getY();
  }

  private void headerMouseDragged(java.awt.event.MouseEvent evt) {
    int x = evt.getXOnScreen();
    int y = evt.getYOnScreen();
    this.setLocation(x - xMouse, y - yMouse);
  }
}