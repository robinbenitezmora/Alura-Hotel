package views;

public class Searching extends JFrame {
 private JPanel contentPane;
 private JTextField textSearch;
 private JTable tbGuest;

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
  setIconImage(Toolkit.getDefaultToolkit().getImage(Searching.class.getResource("/images/icon.png")));
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 450, 300);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  contentPane.setLayout(null);
  setLocationRelativeTo(null);

  txtSearch = new JTextField();
  txtSearch.setBounds(10, 11, 414, 20);
  contentPane.add(txtSearch);
  txtSearch.setColumns(10);

  JButton btnSearch = new JButton("Search");
  btnSearch.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  btnSearch.setBackground(Color.WHITE);
  btnSearch.setIcon(new ImageIcon(Searching.class.getResource("/images/search.png")));
  btnSearch.setBounds(10, 42, 89, 23);
  contentPane.add(btnSearch);

  JButton btnEdit = new JButton("Edit");
  btnEdit.setIcon(new ImageIcon(Searching.class.getResource("/images/edit.png")));
  btnEdit.setBackground(SystemColor.menu);
  btnEdit.setBounds(109, 42, 89, 23);
  contentPane.add(btnEdit);

  JLabel lblNewLabel_4 = new JLabel("Guest List");
  lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
  lblNewLabel_4.setBounds(10, 76, 89, 14);
  lblNewLabel_4.setForeground(new Color(12, 115, 101));
  contentPane.add(lblNewLabel_4);

  JButton btnExit = new JButton("Exit");
  btnExit.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    UserMenu user = new UserMenu();
    user.setVisible(true);
    dispose();
   }
  });
  btnExit.setIcon(new ImageIcon(Searching.class.getResource("/images/exit.png")));
  btnExit.setBackground(SystemColor.menu);
  btnExit.setBounds(335, 42, 89, 23);
  contentPane.add(btnExit);

  JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
  panel.setBounds(10, 101, 414, 149);
  contentPane.add(panel);

  tbGuest = new JTable();
  tbGuest.setFont(new Font("Tahoma", Font.PLAIN, 12));
  panel.addTab("Guest List", new ImageIcon(Searching.class.getResource("/images/list.png")), tbGuest, null);

  JTable tdBookings = new JTable();
  tdBookings.setFont(new Font("Tahoma", Font.PLAIN, 12));
  panel.addTab("Bookings", new ImageIcon(Searching.class.getResource("/images/booking.png")), tdBookings, null);

  JButton btnDelete = new JButton("Delete");
  btnDelete.setIcon(new ImageIcon(Searching.class.getResource("/images/delete.png")));
  btnDelete.setBackground(SystemColor.menu);
  btnDelete.setBounds(208, 42, 89, 23);
  contentPane.add(btnDelete);

  JButton btnDelete_1 = new JButton("Delete");
  btnDelete_1.setIcon(new ImageIcon(Searching.class.getResource("/images/delete.png")));
  btnDelete_1.setBackground(SystemColor.menu);
  btnDelete_1.setBounds(208, 42, 89, 23);
  contentPane.add(btnDelete_1);

  JButton btnDelete_2 = new JButton("Delete");
  btnDelete_2.setIcon(new ImageIcon(Searching.class.getResource("/images/delete.png")));
  btnDelete_2.setBackground(SystemColor.menu);
  btnDelete_2.setBounds(208, 42, 89, 23);
  contentPane.add(btnDelete_2);

  JButton btnDelete_3 = new JButton("Delete");
  btnDelete_3.setIcon(new ImageIcon(Searching.class.getResource("/images/delete.png")));
  btnDelete_3.setBackground(SystemColor.menu);
  btnDelete_3.setBounds(208, 42, 89, 23);
  contentPane.add(btnDelete_3);

  JButton btnDelete_4 = new JButton("Delete");
  btnDelete_4.setIcon(new ImageIcon(Searching.class.getResource("/images/delete.png")));
  btnDelete_4.setBackground(SystemColor.menu);
  btnDelete_4.setBounds(208, 42, 89, 23);
  contentPane.add(btnDelete_4);
  setResizable(false);
 }
}
