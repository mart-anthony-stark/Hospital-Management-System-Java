import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard {

	private JFrame frame;
	private final JPanel sidebar = new JPanel();
	private JLabel exit;
	private JLabel doctor;
	private JLabel patient;
	private JLabel patientlabel;
	private JButton btnNewButton;
	private JButton btnDoctors;
	private JButton btnPatient;
	private JButton btnAdmin;
	private JPanel panel;
	private JLabel staff;
	private JLabel staffLabel;
	private JButton btnDoctors_1;
	private JLabel usernameLabel = new JLabel("Username");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1102, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		sidebar.setBounds(0, 0, 310, 563);
		sidebar.setBackground(new Color(35,119,211));
		frame.getContentPane().add(sidebar);
		sidebar.setLayout(null);
		
		frame.setUndecorated(true);
		
		setUsername();
		
		
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setBounds(94, 39, 204, 30);
		sidebar.add(usernameLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(37, 38, 45, 37);
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("user.png")).getImage().getScaledInstance(45, 37, Image.SCALE_SMOOTH)));
		sidebar.add(lblNewLabel_1);
		
		btnNewButton = new JButton("DASHBOARD");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(0, 130, 310, 46);
		sidebar.add(btnNewButton);
		
		btnDoctors = new JButton("STAFF");
		btnDoctors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new StaffsPanel();
				frame.dispose();
			}
		});
		btnDoctors.setForeground(Color.BLACK);
		btnDoctors.setBackground(Color.WHITE);
		btnDoctors.setBounds(0, 220, 310, 46);
		sidebar.add(btnDoctors);
		
		btnPatient = new JButton("PATIENT");
		btnPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new PatientsPanel();
				frame.dispose();
			}
		});
		btnPatient.setForeground(Color.BLACK);
		btnPatient.setBackground(Color.WHITE);
		btnPatient.setBounds(0, 264, 310, 46);
		sidebar.add(btnPatient);
		
		btnAdmin = new JButton("ADMIN SETTINGS");
		btnAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Settings();
				frame.dispose();
			}
		});
		btnAdmin.setForeground(Color.BLACK);
		btnAdmin.setBackground(Color.WHITE);
		btnAdmin.setBounds(37, 363, 247, 46);
		sidebar.add(btnAdmin);
		
		btnDoctors_1 = new JButton("DOCTORS");
		btnDoctors_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorsPanel dp = new DoctorsPanel();
				frame.dispose();
			}
		});
		btnDoctors_1.setForeground(Color.BLACK);
		btnDoctors_1.setBackground(Color.WHITE);
		btnDoctors_1.setBounds(0, 176, 310, 46);
		sidebar.add(btnDoctors_1);
		
		doctor = new JLabel("");
		doctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DoctorsPanel();
				
				frame.dispose();
			}
		});
		doctor.setBounds(364, 114, 141, 115);
		doctor.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("doctor.png")).getImage().getScaledInstance(141, 115, Image.SCALE_SMOOTH)));
		frame.getContentPane().add(doctor);
		
		JLabel doctorlabel = new JLabel("Doctors");
		doctorlabel.setFont(new Font("Dialog", Font.BOLD, 16));
		doctorlabel.setBounds(405, 241, 74, 39);
		frame.getContentPane().add(doctorlabel);
		
		patient = new JLabel("");
		patient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new PatientsPanel();
				frame.dispose();
			}
		});
		patient.setBounds(842, 114, 141, 115);
		patient.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("patient.png")).getImage().getScaledInstance(141, 115, Image.SCALE_SMOOTH)));
		frame.getContentPane().add(patient);
		
		patientlabel = new JLabel("Patient");
		patientlabel.setFont(new Font("Dialog", Font.BOLD, 16));
		patientlabel.setBounds(883, 241, 74, 39);
		frame.getContentPane().add(patientlabel);
		
		panel = new JPanel();
		panel.setBounds(308, 0, 794, 53);
		panel.setBackground(new Color(35,119,211));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		exit = new JLabel("");
		exit.setBounds(748, 12, 34, 32);
		panel.add(exit);
		exit.setToolTipText("Exit");
		exit.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("xBtn.png")).getImage().getScaledInstance(34, 32, Image.SCALE_SMOOTH)));
		
		staff = new JLabel("");
		staff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new StaffsPanel();
				frame.dispose();
			}
		});
		staff.setBounds(620, 114, 141, 115);
		staff.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("staff.png")).getImage().getScaledInstance(141, 115, Image.SCALE_SMOOTH)));
		frame.getContentPane().add(staff);
		
		staffLabel = new JLabel("Staff");
		staffLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		staffLabel.setBounds(676, 241, 42, 39);
		frame.getContentPane().add(staffLabel);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public void setUsername() {
		usernameLabel.setText(Global.globalEmail);
	}
}
