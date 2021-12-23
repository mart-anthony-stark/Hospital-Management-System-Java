import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.records.Doctor;
import com.records.User;

public class Signup {

	private static int viewCount=0;
	private static FileHandler usersFile;
	private static ArrayList<String> fileContents = new ArrayList<>();
	private static ArrayList<User>usersList = new ArrayList<>();
	private static int size;
	
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup window = new Signup();
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
	public Signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(35,119,211));
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
//		Initialize file
		usersFile = new FileHandler("Users.txt");
		if(viewCount==0)
			readUsersFile();
		viewCount++;
		
		JLabel hospitalImg = new JLabel("");
		hospitalImg.setBounds(0, 0, 259, 437);
		hospitalImg.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("hospital.jpg")).getImage().getScaledInstance(694, 437, Image.SCALE_SMOOTH)));
		frame.getContentPane().add(hospitalImg);
		
		JLabel exit = new JLabel("");
		exit.setToolTipText("Exit");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exit.setBounds(676, 0, 34, 32);
		exit.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("xBtn.png")).getImage().getScaledInstance(34, 32, Image.SCALE_SMOOTH)));
		
		frame.getContentPane().add(exit);
		
		JLabel lblNewLabel = new JLabel("SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(337, 74, 288, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HOSPITAL MANAGEMENT");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(337, 41, 288, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblUsername.setBounds(324, 180, 77, 32);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPassword.setBounds(324, 270, 77, 32);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(411, 186, 199, 24);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(411, 276, 199, 24);
		frame.getContentPane().add(password);
		
		JButton signupBtn = new JButton("S I G N U P");
		signupBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Crypt c = new Crypt();
				
				int id = size+1;
				String name = username.getText();
				String mail = email.getText();
				String pass = c.hash(password.getText());
				
				if(name.equals("") || mail.equals("") || pass.equals("")) {
					JOptionPane.showMessageDialog(null, "All fields are required. Please fill out necessary fields");
				}else {
					boolean exist = false;
					for(User x:usersList) {
						if(mail.equals(x.email)) {
							exist=true;
							break;
						}
						
					}
					if(exist)JOptionPane.showMessageDialog(null, "Email already is registered. Please enter different email");
					else {
						usersList.add(new User(id, name, mail, pass));
						writeUsersFile("add");
						
						Global.globalEmail = mail;
						JOptionPane.showMessageDialog(null, "User was created successfully");
						
						new Dashboard();
						frame.dispose();
					}
				}
			}
		});
		signupBtn.setForeground(Color.WHITE);
		signupBtn.setBackground(Color.BLACK);
		signupBtn.setBounds(324, 340, 288, 32);
		frame.getContentPane().add(signupBtn);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblEmail.setBounds(324, 223, 77, 32);
		frame.getContentPane().add(lblEmail);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(411, 231, 199, 24);
		frame.getContentPane().add(email);
		
		JLabel lblNewLabel_2 = new JLabel("Already have an account?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(334, 389, 189, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("LOGIN");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login();
				frame.dispose();
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(519, 383, 87, 28);
		frame.getContentPane().add(lblNewLabel_3);
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
}
