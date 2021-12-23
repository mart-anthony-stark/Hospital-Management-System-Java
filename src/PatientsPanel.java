import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.records.Doctor;
import com.records.Patients;

public class PatientsPanel {
	private static int size;
	private static FileHandler patientsFile;
	private static ArrayList<String> fileContents = new ArrayList<>();
	private static ArrayList<Patients> patientsList = new ArrayList<>();
	private static int viewCount=0;
	
	private static JComboBox addSex;
	private static JComboBox editSex;
	private JFrame frame;
	private final JPanel sidebar = new JPanel();
	private JLabel exit;
	private JButton btnNewButton;
	private JButton btnDoctors;
	private JButton btnPatient;
	private JButton btnAdmin;
	private JPanel panel;
	private JButton btnDoctors_1;
	private JLabel lblNewLabel_3;
	private JTextField searchID;
	private JButton deleteBtn;
	private JButton addBtn;
	private JTextField addNumber;
	private JLabel lblNewLabel_4_1_1_2;
	private JLabel lblNewLabel_4_1_2;
	private JTextField addName;
	private JLabel lblNewLabel_4_2;
	private JLabel lblNewLabel_5;
	private JTextField addMedicalHistory;
	private static JTable table;
	private JTextField addRoomNo;
	private JTextField editName;
	private JTextField editNumber;
	private JTextField editHistory;
	private JTextField editRoomNo;
	private static JLabel usernameLabel = new JLabel("Username");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientsPanel window = new PatientsPanel();
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
	public PatientsPanel() {
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
		
//		File initialization
		patientsFile = new FileHandler("Patients.txt");
		if(viewCount==0)
			readPatientsFile();
		viewCount++;
		
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
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Dashboard();
				frame.dispose();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(0, 131, 310, 46);
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
		btnPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPatient.setForeground(Color.WHITE);
		btnPatient.setBackground(Color.GRAY);
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
		
		JLabel lblNewLabel_2 = new JLabel("PATIENTS");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(25, 12, 137, 32);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Edit Information");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBounds(649, 289, 133, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		searchID = new JTextField();
		searchID.setBounds(849, 305, 32, 26);
		frame.getContentPane().add(searchID);
		searchID.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Patient ID");
		lblNewLabel_4.setBounds(784, 313, 61, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = searchID.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input patient ID to search");
				}else {
					boolean exist = false;
					for(Patients x:patientsList) {
						try {
							String patientID = String.valueOf(x.id);
							if(id.equals(patientID)) {
								exist = true;
								editHistory.setText(x.history);
								editName.setText(x.name);
								editNumber.setText(x.contact);
								editRoomNo.setText(x.roomNo);
								editSex.setSelectedItem(x.sex);
							}
							
						}catch(Exception ex){
							System.out.println(ex.getLocalizedMessage());
						}
					}
					if(!exist) {
						JOptionPane.showMessageDialog(null, "Patient ID not found");
						editHistory.setText("");
						editName.setText("");
						editNumber.setText("");
						editRoomNo.setText("");
						editSex.setSelectedItem("");
					}
				}
			}
		});
		searchBtn.setForeground(Color.WHITE);
		searchBtn.setBackground(new Color(35, 119, 211));
		searchBtn.setBounds(887, 305, 75, 26);
		frame.getContentPane().add(searchBtn);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = searchID.getText().replaceAll("``", "_");
				String name = editName.getText().replaceAll("``", "_");
				String roomNo = editRoomNo.getText().replaceAll("``", "_");
				String contact = editNumber.getText().replaceAll("``", "_");
				String sex = (String) editSex.getSelectedItem();
				String history = editHistory.getText().replaceAll("``", "_");
				
				if(id.equals("") || name.equals("") || roomNo.equals("") || contact.equals("") || sex.equals("") || history.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input necessary fields");
				}else{
					boolean exist = false;
					for(Patients x:patientsList) {
						try {
							String patientID = String.valueOf(x.id);
							if(id.equals(patientID)) {
								exist = true;
								
								x.name = name;
								x.roomNo = roomNo;
								x.contact = contact;
								x.sex = sex;
								x.history = history;
								
								writePatientsFile("edit");
								searchID.setText("");
								editName.setText("");
								editRoomNo.setText("");
								editNumber.setText("");
								editSex.setSelectedItem("");
								editHistory.setText("");
								JOptionPane.showMessageDialog(null, "Record updated successfully");
								populateTable();
							}
							
						}catch(Exception ex){
							System.out.println(ex.getLocalizedMessage());
						}
					}
					if(!exist) {
						JOptionPane.showMessageDialog(null, "Patient ID not found");
						editHistory.setText("");
						editName.setText("");
						editNumber.setText("");
						editRoomNo.setText("");
						editSex.setSelectedItem("");
					}
				}
			}
		});
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setBackground(new Color(35, 119, 211));
		saveBtn.setBounds(784, 527, 75, 26);
		frame.getContentPane().add(saveBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = searchID.getText();
				String name = editName.getText();
				String roomNo = editRoomNo.getText();
				String contact = editNumber.getText();
				String sex = (String) editSex.getSelectedItem();
				String history = editHistory.getText();
				
				if(id.equals("") || name.equals("") || roomNo.equals("") || contact.equals("") || sex.equals("") || history.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input necessary fields");
				}else{
					boolean exist = false;
					for(int x=0; x<patientsList.size(); x++) {
						try {
							String patientID = String.valueOf(patientsList.get(x).id);
							if(id.equals(patientID)) {
								exist = true;
								
								patientsList.remove(x);
								
								writePatientsFile("delete");
								searchID.setText("");
								editName.setText("");
								editRoomNo.setText("");
								editNumber.setText("");
								editSex.setSelectedItem("");
								editHistory.setText("");
								JOptionPane.showMessageDialog(null, "Record deleted successfully");
								populateTable();
								
							}
							
						}catch(Exception ex){
							System.out.println(ex.getLocalizedMessage());
						}
					}
					if(!exist) {
						JOptionPane.showMessageDialog(null, "Patient ID not found");
						editHistory.setText("");
						editName.setText("");
						editNumber.setText("");
						editRoomNo.setText("");
						editSex.setSelectedItem("");
					}
				}
			}
		});
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(Color.RED);
		deleteBtn.setBounds(887, 527, 75, 26);
		frame.getContentPane().add(deleteBtn);
		
		addBtn = new JButton("Add");
		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = addName.getText().replaceAll("``", "_");
				String roomNo = addRoomNo.getText().replaceAll("``", "_");
				String contact = addNumber.getText().replaceAll("``", "_");
				String sex = (String) addSex.getSelectedItem();
				String history = addMedicalHistory.getText().replaceAll("``", "_");
				
				if(name.equals("") || roomNo.equals("") || contact.equals("") || sex.equals("") || history.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input necessary fields");
				}else {
					int id = size+1;
					patientsList.add(new Patients(id, roomNo, name, contact, sex, history));
					
					writePatientsFile("add");
					JOptionPane.showMessageDialog(null, "Successfully added a record");
					addName.setText("");
					addRoomNo.setText("");
					addNumber.setText("");
					addSex.setSelectedItem("");
					addMedicalHistory.setText("");
					populateTable();
				}
			}
		});
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(new Color(35, 119, 211));
		addBtn.setBounds(523, 527, 75, 26);
		frame.getContentPane().add(addBtn);
		
		addNumber = new JTextField();
		addNumber.setColumns(10);
		addNumber.setBounds(449, 452, 149, 26);
		frame.getContentPane().add(addNumber);
		
		lblNewLabel_4_1_1_2 = new JLabel("Contact Number");
		lblNewLabel_4_1_1_2.setBounds(322, 457, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2);
		
		lblNewLabel_4_1_2 = new JLabel("Sex");
		lblNewLabel_4_1_2.setBounds(322, 423, 89, 16);
		frame.getContentPane().add(lblNewLabel_4_1_2);
		
		addName = new JTextField();
		addName.setColumns(10);
		addName.setBounds(449, 378, 149, 26);
		frame.getContentPane().add(addName);
		
		lblNewLabel_4_2 = new JLabel("Name");
		lblNewLabel_4_2.setBounds(322, 388, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		lblNewLabel_5 = new JLabel("Add Record");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5.setBounds(322, 305, 133, 23);
		frame.getContentPane().add(lblNewLabel_5);
		
		addMedicalHistory = new JTextField();
		addMedicalHistory.setColumns(10);
		addMedicalHistory.setBounds(449, 489, 149, 26);
		frame.getContentPane().add(addMedicalHistory);
		
		JLabel lblNewLabel_4_1_1_2_1 = new JLabel("Medical History");
		lblNewLabel_4_1_1_2_1.setBounds(322, 494, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 66, 768, 217);
		frame.getContentPane().add(scrollPane);
		
//		SET PATIENTS TABLE
		table = new JTable();
		populateTable();
		scrollPane.setViewportView(table);
		
		addRoomNo = new JTextField();
		addRoomNo.setColumns(10);
		addRoomNo.setBounds(449, 339, 45, 26);
		frame.getContentPane().add(addRoomNo);
		
		JLabel lblNewLabel_4_2_2 = new JLabel("Room No.");
		lblNewLabel_4_2_2.setBounds(322, 349, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2_2);
		
		editName = new JTextField();
		editName.setColumns(10);
		editName.setBounds(813, 373, 149, 26);
		frame.getContentPane().add(editName);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Name");
		lblNewLabel_4_2_1.setBounds(686, 383, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2_1);
		
		editNumber = new JTextField();
		editNumber.setColumns(10);
		editNumber.setBounds(813, 447, 149, 26);
		frame.getContentPane().add(editNumber);
		
		editHistory = new JTextField();
		editHistory.setColumns(10);
		editHistory.setBounds(813, 484, 149, 26);
		frame.getContentPane().add(editHistory);
		
		JLabel lblNewLabel_4_1_1_2_1_1 = new JLabel("Medical History");
		lblNewLabel_4_1_1_2_1_1.setBounds(686, 489, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_1_1);
		
		JLabel lblNewLabel_4_1_1_2_2 = new JLabel("Contact Number");
		lblNewLabel_4_1_1_2_2.setBounds(686, 452, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_2);
		
		JLabel lblNewLabel_4_1_2_1 = new JLabel("Sex");
		lblNewLabel_4_1_2_1.setBounds(686, 418, 89, 16);
		frame.getContentPane().add(lblNewLabel_4_1_2_1);
		
		
		String[] sex = {"M", "F"};
		addSex = new JComboBox(sex);		
		addSex.setBounds(449, 419, 149, 25);
		frame.getContentPane().add(addSex);
		
		
		editSex = new JComboBox(sex);
		editSex.setBounds(813, 414, 149, 25);
		frame.getContentPane().add(editSex);
		
		editRoomNo = new JTextField();
		editRoomNo.setColumns(10);
		editRoomNo.setBounds(813, 343, 45, 26);
		frame.getContentPane().add(editRoomNo);
		
		JLabel lblNewLabel_4_2_2_1 = new JLabel("Room No.");
		lblNewLabel_4_2_2_1.setBounds(686, 353, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2_2_1);
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
	static void readPatientsFile() {
		fileContents = patientsFile.read();
		
		size = patientsFile.size;
		
		for(String x:fileContents) {
			String[] arr = x.split("``");
			patientsList.add(new Patients(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]));
		}
		System.out.println("Size: "+size);
	}
	static void writePatientsFile(String type) {
		ArrayList<String> content = new ArrayList<>();
		
		if(type.equalsIgnoreCase("add"))
			size = size+1;
		
		content.add(String.valueOf(size));
		
		for(Patients x:patientsList) {
			String id = String.valueOf(x.id);
			String room = x.roomNo;
			String name = x.name;
			String contact = x.contact;
			String sex = x.sex;
			String history = x.history;
			
			content.add(id+ "``" + room + "``" + name + "``" + contact + "``" + sex + "``" + history);
		}
		patientsFile.write(content);
	}
	static void populateTable() {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[6];
		columnsName[0] = "ID";
		columnsName[1] = "Room No.";
		columnsName[2] = "Name";
		columnsName[3] = "Contact Number";
		columnsName[4] = "Sex";
		columnsName[5] = "Medical History";

		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[6];
		for(int i=0; i<patientsList.size(); i++) {
			rowData[0] = patientsList.get(i).id;
			rowData[1] = patientsList.get(i).roomNo;
			rowData[2] = patientsList.get(i).name;
			rowData[3] = patientsList.get(i).contact;
			rowData[4] = patientsList.get(i).sex;
			rowData[5] = patientsList.get(i).history;
			
			model.addRow(rowData);
		}
		
		table.setEnabled(false);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
	}
	public void setUsername() {
		usernameLabel.setText(Global.globalEmail);
	}
}
