package views;

@SuppressWarnings("serial")
public class Searching extends JFrame {

 private JPanel contentPane;
 private JTextField textSearch;
 private JTable tbGuests;
 private JTable tbBookings;
 private DefaultTableModel model;
 private DefaultTableModel modelGuests;
 private BookingsController bookingsController;
 private GuestsController guestsController;
 private JLabel lableBack;
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
  this.bookingsController = new BookingsController();
  this.guestsController = new GuestsController();
  setIconImage(Toolkit.getDefaultToolkit().getImage(Searching.class.getResource("/images/hotel.png")));
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 800, 600);
  contentPane = new JPanel();
  contentPane.setBackground(new Color(255, 255, 255));
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  setLocationRelativeTo(null);
  setUndecorated(true);
  contentPane.setLayout(null);
  JScrollPane scrollPane = new JScrollPane();

  txtSearch = new JTextField();
  txtSearch.setBounds(10, 11, 780, 20);
  txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
  contentPane.add(txtSearch);
  txtSearch.setColumns(10);

  JLabel lblTitle = new JLabel("SEARCHING SYSTEM");
  lblTitle.setBounds(10, 42, 780, 20);
  lblTitle.setForeground(new Color(0, 0, 0));
  lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
  contentPane.add(lblTitle);

  JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
  panel.setBounds(10, 73, 780, 516);
  panel.setBackground(new Color(255, 255, 255));
  panel.setFont(new Font("Tahoma", Font.BOLD, 12));
  contentPane.add(panel);

  tbGuests = new JTable();
  tbGuests.setFont(new Font("Tahoma", Font.PLAIN, 12));
  panel.addTab("Guests", new ImageIcon(Searching.class.getResource("/images/guests.png")), tbGuests, null);
  modelGuests = (DefaultTableModel) tbGuests.getModel();
  modelGuests.addColumn("Number of Guest");
  modelGuests.addColumn("Name");
  modelGuests.addColumn("Last Name");
  modelGuests.addColumn("Birth Date");
  modelGuests.addColumn("Nationality");
  modelGuests.addColumn("phone");
  modelGuests.addColumn("Number of Booking");
  FillTableGuests();

  tbBookings = new JTable();
  tbBookings.setFont(new Font("Tahoma", Font.PLAIN, 12));
  panel.addTab("Bookings", new ImageIcon(Searching.class.getResource("/images/bookings.png")), tbBookings, null);
  model = (DefaultTableModel) tbBookings.getModel();
  model.addColumn("Number of Booking");
  model.addColumn("Check In Date");
  model.addColumn("Check Out Date");
  model.addColumn("Value");
  model.addColumn("Payment Method");
  tbBookings.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  FillTableBookings();

  JLabel logo = new JLabel("");
  logo.setBounds(0, 0, 800, 600);
  logo.setIcon(new ImageIcon(Searching.class.getResource("/images/background.png")));
  contentPane.add(logo);

  JPanel header = new JPanel();
  header.setBounds(0, 0, 800, 40);
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
  header.setBackground(new Color(0, 0, 0, 0));
  contentPane.add(header);

  JPanel btnBack = new JPanel();
  btnBack.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent e) {
    UserMenu user = new UserMenu();
    user.setVisible(true);
    dispose();
   }
   @Override
   public void mouseEntered(MouseEvent e) {
    btnBack.setBackground(new Color(0, 0, 0, 80));
    lableBack.setForeground(new Color(255, 255, 255));
   }
   @Override
   public void mouseExited(MouseEvent e) {
    btnBack.setBackground(new Color(0, 0, 0, 0));
    lableBack.setForeground(new Color(0, 0, 0));
   }
  });
  btnBack.setLayout(null);
  btnBack.setBackground(new Color(0, 0, 0, 0));
  btnBack.setBounds(0, 0, 80, 40);
  header.add(btnBack);

  lableBack = new JLabel("<");
  lableBack.setHorizontalAlignment(SwingConstants.CENTER);
  lableBack.setFont(new Font("Tahoma", Font.BOLD, 18));
  lableBack.setBounds(0, 0, 80, 40);
  btnBack.add(lableBack);

  JPanel btnExit = new JPanel();
  btnExit.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent e) {
    UserMenu user = new UserMenu();
    user.setVisible(true);
    dispose();
   }
   @Override
   public void mouseEntered(MouseEvent e) {
    btnExit.setBackground(new Color(0, 0, 0, 80));
    labelExit.setForeground(new Color(255, 255, 255));
   }
   @Override
   public void mouseExited(MouseEvent e) {
    btnExit.setBackground(new Color(0, 0, 0, 0));
    labelExit.setForeground(new Color(0, 0, 0));
   }
  });
  btnExit.setLayout(null);
  btnExit.setBackground(new Color(0, 0, 0, 0));
  btnExit.setBounds(720, 0, 80, 40);
  header.add(btnExit);

  labelExit = new JLabel("X");
  labelExit.setHorizontalAlignment(SwingConstants.CENTER);
  labelExit.setFont(new Font("Tahoma", Font.BOLD, 18));
  labelExit.setBounds(720, 0, 80, 40);
  labelExit.setForeground(new Color(0, 0, 0));
  btnExit.add(labelExit);

  JSeparator separator_1_2 = new JSeparator();
  separator_1_2.setBounds(0, 40, 800, 2);
  separator_1_2.setForeground(new Color(0, 0, 0));
  separator_1_2.setBackground(new Color(0, 0, 0));
  contentPane.add(separator_1_2);

  JPanel btnSearch = new JPanel();
  btnSearch.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent e) {
    CleanTable();
    if (txtSearch.getText().equals("")) {
     FillTableGuests();
     FillTableBookings();
    } else {
     FillTableGuestsId();
     FillTableBookingsId();
    }
   }
  });
  
