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

import com.records.User;

public class Login {

	private static int viewCount=0;
	private static FileHandler usersFile;
	private static ArrayList<String> fileContents = new ArrayList<>();
	private static ArrayList<User>usersList = new ArrayList<>();
	private static int size;
	
	private JFrame frame;
	private JTextField email;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblUsername.setBounds(324, 180, 77, 32);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPassword.setBounds(324, 242, 77, 32);
		frame.getContentPane().add(lblPassword);
		
		email = new JTextField();
		email.setBounds(411, 186, 199, 24);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(411, 248, 199, 24);
		frame.getContentPane().add(passwordField);
		
		JButton loginBtn = new JButton("L O G I N");
		loginBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Crypt c = new Crypt();
				
				int id = size+1;
				String mail = email.getText();
				String pass = c.hash(passwordField.getText());
				User user = null;
				if(mail.equals("") || pass.equals("")) {
					JOptionPane.showMessageDialog(null, "All fields are required. Please fill out necessary fields");
				}else {
					boolean exist = false;
					for(User x:usersList) {
						if(mail.equals(x.email)) {
							exist=true;
							user = x;
							break;
						}
						
					}
					if(!exist)JOptionPane.showMessageDialog(null, "Account not found! Please register first");
					else {
						if(pass.equals(user.password)) {
							Global.globalEmail = user.email;
							JOptionPane.showMessageDialog(null, "Logged in successfully");
							new Dashboard();
							
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "The password you entered is incorrect");
						}
						
					}
				}
			}
		});
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(Color.BLACK);
		loginBtn.setBounds(324, 301, 288, 32);
		frame.getContentPane().add(loginBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Doesn't have an account?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(334, 362, 199, 19);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Signup");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Signup();
				frame.dispose();
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(531, 358, 79, 24);
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
}
