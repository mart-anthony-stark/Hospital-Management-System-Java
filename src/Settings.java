import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import com.records.User;

public class Settings {
	
	private static int viewCount=0;
	private static FileHandler usersFile;
	private static ArrayList<String> fileContents = new ArrayList<>();
	private static ArrayList<User>usersList = new ArrayList<>();
	private static int size;

	private JFrame frame;
	private final JPanel sidebar = new JPanel();
	private JLabel exit;
	private JButton btnNewButton;
	private JButton btnDoctors;
	private JButton btnPatient;
	private JButton btnAdmin;
	private JPanel panel;
	private JButton btnDoctors_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private static JTextField nameField = new JTextField();
	private static JTextField emailField = new JTextField();
	private JLabel lblNewLabel_5;
	private JTextField passField;
	private JLabel lblNewLabel_6;
	private JTextField rePassField;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JButton btnNewButton_2;
	private static JLabel usernameLabel = new JLabel("Username");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
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
	public Settings() {
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
		
//		Initialize file
		usersFile = new FileHandler("Users.txt");
		if(viewCount==0)
			readUsersFile();
		viewCount++;
		
		setUsername();
		setValues();
		
		
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setBounds(94, 39, 204, 30);
		sidebar.add(usernameLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(37, 38, 45, 37);
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("user.png")).getImage().getScaledInstance(45, 37, Image.SCALE_SMOOTH)));
		sidebar.add(lblNewLabel_1);
		
		btnNewButton = new JButton("DASHBOARD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Dashboard();
				frame.dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(0, 132, 310, 46);
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
		btnAdmin.setForeground(Color.WHITE);
		btnAdmin.setBackground(Color.GRAY);
		btnAdmin.setBounds(37, 363, 247, 46);
		sidebar.add(btnAdmin);
		
		btnDoctors_1 = new JButton("DOCTORS");
		btnDoctors_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DoctorsPanel();
				frame.dispose();
			}
		});
		btnDoctors_1.setForeground(Color.BLACK);
		btnDoctors_1.setBackground(Color.WHITE);
		btnDoctors_1.setBounds(0, 176, 310, 46);
		sidebar.add(btnDoctors_1);
		
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
		
		lblNewLabel_2 = new JLabel("SETTINGS");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setBounds(12, 12, 137, 32);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Admin Information");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_3.setBounds(633, 87, 152, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(604, 163, 55, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		nameField.setBounds(694, 163, 189, 28);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		
		emailField.setEditable(false);
		emailField.setColumns(10);
		emailField.setBounds(694, 220, 189, 28);
		frame.getContentPane().add(emailField);
		
		lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(604, 220, 55, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setBounds(694, 280, 189, 28);
		frame.getContentPane().add(passField);
		
		lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setBounds(604, 280, 103, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		rePassField = new JPasswordField();
		rePassField.setColumns(10);
		rePassField.setBounds(694, 333, 189, 28);
		frame.getContentPane().add(rePassField);
		
		lblNewLabel_7 = new JLabel("Re-enter");
		lblNewLabel_7.setBounds(604, 333, 55, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setBounds(604, 359, 103, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("Save Changes");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText();
				String password = passField.getText();
				String reenter = rePassField.getText();
				
				if(password.equals("")) {
					for(User x:usersList) {
						if(x.email.equals(Global.globalEmail)) {
								x.name = name;
								
								writeUsersFile("edit");
								
								JOptionPane.showMessageDialog(null, "Saved Successfully");
							}else {
								JOptionPane optionPane = new JOptionPane("ErrorMsg", JOptionPane.ERROR_MESSAGE);    
								optionPane.showMessageDialog(null, "Passwords do not match");
							}
							
								break;
						}
					
				}else if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter values to fields");
					
				}else {
					for(User x:usersList) {
						if(x.email.equals(Global.globalEmail)) {
							if(password.equals(reenter)) {
								Crypt c = new Crypt();
								x.name = name;
								x.password = c.hash(password);
								
								writeUsersFile("edit");
								
								JOptionPane.showMessageDialog(null, "Saved Successfully");
							}else {
								JOptionPane optionPane = new JOptionPane("ErrorMsg", JOptionPane.ERROR_MESSAGE);    
								optionPane.showMessageDialog(null, "Passwords do not match");
							}
							
								break;
						}
					}
				}
			}
		});
		btnNewButton_1.setBackground(new Color(35,119,211));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(755, 405, 128, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete User");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for(int i=0; i< usersList.size(); i++) {
					if(Global.globalEmail.equals(usersList.get(i).email)) {
						usersList.remove(i);
					}
					writeUsersFile("delete");
					
					JOptionPane.showMessageDialog(null, "User Account Deleted Successfully");
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setBounds(755, 463, 128, 35);
		frame.getContentPane().add(btnNewButton_2);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	static void readUsersFile() {
		fileContents = usersFile.read();
//		Read file and store to doctors list
		size = fileContents.size();
		for(String x:fileContents) {
				String[] arr = x.split("``");
				usersList.add(new User(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3]));
		}
		System.out.println("Size: "+usersList.size());
	}
	static void writeUsersFile(String type) {
		ArrayList<String> content = new ArrayList<String>();

		if(type.equalsIgnoreCase("add"))
			size = size+1;
		
		
		content.add(String.valueOf(size));
		
		for(User x:usersList) {
			String id = String.valueOf(x.id);
			String name = x.name;
			String email = x.email;
			String password = x.password;
			
			content.add(id + "``" + name + "``" + email + "``" + password);
		}
		usersFile.write(content);
		System.out.println(content);
	}
	public void setUsername() {
		usernameLabel.setText(Global.globalEmail);
	}
	public static void setValues() {
		String email = Global.globalEmail;
		
		for(User x:usersList) {
			if(x.email.equals(email)) {
				emailField.setText(x.email);
				nameField.setText(x.name);
			}
			
		}
	}
}
