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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.records.Patients;
import com.records.Staff;

public class StaffsPanel {
	private static ArrayList<String> fileContents = new ArrayList<>();
	private static ArrayList<Staff> staffList = new ArrayList<>();
	private static FileHandler staffsFile;
	private static int size;
	private static int viewCount=0;

	private JFrame frame;
	private final JPanel sidebar = new JPanel();
	private JLabel exit;
	private JButton btnNewButton;
	private JButton btnDoctors;
	private JButton btnPatient;
	private JButton btnAdmin;
	private JPanel panel;
	private JButton btnDoctors_1;
	private static JTable table;
	private JLabel lblNewLabel_3;
	private JTextField searchID;
	private JButton deleteBtn;
	private JButton addBtn;
	private JTextField addContact;
	private JLabel lblNewLabel_4_1_1_2;
	private JLabel lblNewLabel_4_1_2;
	private JTextField addPosition;
	private JTextField addName;
	private JLabel lblNewLabel_4_2;
	private JLabel lblNewLabel_5;
	private JTextField addAddress;
	private JTextField editAddress;
	private JTextField editContact;
	private JTextField editPosition;
	private JTextField editName;
	private JTextField addSalary;
	private JTextField editSalary;
	private JLabel lblNewLabel_4_1_1_2_3;
	private static JLabel usernameLabel = new JLabel("Username");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffsPanel window = new StaffsPanel();
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
	public StaffsPanel() {
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
		staffsFile = new FileHandler("Staffs.txt");
		if(viewCount==0)
			readStaffsFile();
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
		btnDoctors.setForeground(Color.WHITE);
		btnDoctors.setBackground(Color.GRAY);
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
		
		JLabel lblNewLabel_2 = new JLabel("STAFFS");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(25, 12, 137, 32);
		panel.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 65, 768, 210);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		populateTable();
		scrollPane.setViewportView(table);
		
		lblNewLabel_3 = new JLabel("Edit Information");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBounds(790, 287, 133, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		searchID = new JTextField();
		searchID.setBounds(977, 287, 32, 26);
		frame.getContentPane().add(searchID);
		searchID.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID No.");
		lblNewLabel_4.setBounds(941, 295, 32, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = searchID.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input Staff ID to search");
				}else {
					boolean exist=false;
					for(Staff x:staffList) {
						try {
							String staffID = String.valueOf(x.id);
							if(id.equals(staffID)) {
								exist=true;
								editName.setText(x.name);
								editPosition.setText(x.position);
								editSalary.setText(String.valueOf(x.salary));
								editContact.setText(x.contact);
								editAddress.setText(x.address);
							}
						}catch(Exception exc) {
							System.out.println(exc.getLocalizedMessage());
						}
					}
					if(!exist) {
						JOptionPane.showMessageDialog(null, "Staff ID not found");
					}
				}
			}
		});
		searchBtn.setForeground(Color.WHITE);
		searchBtn.setBackground(new Color(35, 119, 211));
		searchBtn.setBounds(1015, 287, 75, 26);
		frame.getContentPane().add(searchBtn);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = searchID.getText();
				String name = editName.getText();
				String position = editPosition.getText();
				float salary = 0;
				try {
					salary = Float.parseFloat(editSalary.getText());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter number to salary field");
				}
				String contact = editContact.getText();
				String address = editAddress.getText();
				
				if(id.equals("") || name.equals("") || position.equals("") || salary == 0 || contact.equals("") || address.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input necessary fields");
				}else {
					boolean exist=false;
					for(Staff x:staffList) {
						try {
							String staffID = String.valueOf(x.id);
							if(id.equals(staffID)) {
								exist=true;
								
								x.name = name;
								x.position = position;
								x.salary = salary;
								x.contact = contact;
								x.address = address;
								
								writePatientsFile("edit");
								searchID.setText("");
								editName.setText("");
								editPosition.setText("");
								editSalary.setText("");
								editContact.setText("");
								editAddress.setText("");
								JOptionPane.showMessageDialog(null, "Updated the record successfully");
								populateTable();
							}
						}catch(Exception exc) {
							System.out.println(exc.getLocalizedMessage());
						}
					}
					if(!exist) {
						JOptionPane.showMessageDialog(null, "Staff ID not found");
					}
				}
			}
		});
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setBackground(new Color(35, 119, 211));
		saveBtn.setBounds(884, 511, 75, 26);
		frame.getContentPane().add(saveBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = searchID.getText();
				String name = editName.getText();
				String position = editPosition.getText();
				float salary = 0;
				try {
					salary = Float.parseFloat(editSalary.getText());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter number to salary field");
				}
				String contact = editContact.getText();
				String address = editAddress.getText();
				
				if(id.equals("") || name.equals("") || position.equals("") || salary == 0 || contact.equals("") || address.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input necessary fields");
				}else {
					boolean exist=false;
					for(int x=0; x<staffList.size(); x++) {
						try {
							String staffID = String.valueOf(staffList.get(x).id);
							if(id.equals(staffID)) {
								exist=true;
								
								staffList.remove(x);
								
								writePatientsFile("delete");
								searchID.setText("");
								editName.setText("");
								editPosition.setText("");
								editSalary.setText("");
								editContact.setText("");
								editAddress.setText("");
								JOptionPane.showMessageDialog(null, "Deleted the record successfully");
								populateTable();
							}
						}catch(Exception exc) {
							System.out.println(exc.getLocalizedMessage());
						}
					}
					if(!exist) {
						JOptionPane.showMessageDialog(null, "Staff ID not found");
					}
				}
			}
		});
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(Color.RED);
		deleteBtn.setBounds(999, 511, 75, 26);
		frame.getContentPane().add(deleteBtn);
		
		addBtn = new JButton("Add");
		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = addName.getText().replaceAll("``", "_");
				String position = addPosition.getText().replaceAll("``", "_");
				float salary = 0;
				try {
					salary = Float.parseFloat(addSalary.getText());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter number to salary field");
				}
				String contact = addContact.getText().replaceAll("``", "_");
				String address = addAddress.getText().replaceAll("``", "_");
				
				if(name.equals("") || position.equals("") || salary==0 || contact.equals("") || address.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input necessary fields");
				}else {
					int id = size+1;
					staffList.add(new Staff(id,name,position,salary,contact,address));
					
					writePatientsFile("add");
					JOptionPane.showMessageDialog(null, "Successfully added a record");
					addName.setText("");
					addPosition.setText("");
					addSalary.setText("");
					addContact.setText("");
					addAddress.setText("");
					populateTable();
				}
			}
		});
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(new Color(35, 119, 211));
		addBtn.setBounds(523, 527, 75, 26);
		frame.getContentPane().add(addBtn);
		
		addContact = new JTextField();
		addContact.setColumns(10);
		addContact.setBounds(449, 452, 149, 26);
		frame.getContentPane().add(addContact);
		
		lblNewLabel_4_1_1_2 = new JLabel("Contact Number");
		lblNewLabel_4_1_1_2.setBounds(322, 457, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2);
		
		lblNewLabel_4_1_2 = new JLabel("Position");
		lblNewLabel_4_1_2.setBounds(322, 385, 89, 16);
		frame.getContentPane().add(lblNewLabel_4_1_2);
		
		addPosition = new JTextField();
		addPosition.setColumns(10);
		addPosition.setBounds(449, 378, 149, 26);
		frame.getContentPane().add(addPosition);
		
		addName = new JTextField();
		addName.setColumns(10);
		addName.setBounds(449, 340, 149, 26);
		frame.getContentPane().add(addName);
		
		lblNewLabel_4_2 = new JLabel("Name");
		lblNewLabel_4_2.setBounds(322, 350, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		lblNewLabel_5 = new JLabel("Add Record");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5.setBounds(322, 305, 133, 23);
		frame.getContentPane().add(lblNewLabel_5);
		
		addAddress = new JTextField();
		addAddress.setColumns(10);
		addAddress.setBounds(449, 489, 149, 26);
		frame.getContentPane().add(addAddress);
		
		JLabel lblNewLabel_4_1_1_2_1 = new JLabel("Address");
		lblNewLabel_4_1_1_2_1.setBounds(322, 494, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_1);
		
		editAddress = new JTextField();
		editAddress.setColumns(10);
		editAddress.setBounds(941, 473, 149, 26);
		frame.getContentPane().add(editAddress);
		
		JLabel lblNewLabel_4_1_1_2_1_1 = new JLabel("Address");
		lblNewLabel_4_1_1_2_1_1.setBounds(814, 478, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_1_1);
		
		JLabel lblNewLabel_4_1_1_2_2 = new JLabel("Contact Number");
		lblNewLabel_4_1_1_2_2.setBounds(814, 441, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_2);
		
		editContact = new JTextField();
		editContact.setColumns(10);
		editContact.setBounds(941, 436, 149, 26);
		frame.getContentPane().add(editContact);
		
		JLabel lblNewLabel_4_1_2_1 = new JLabel("Position");
		lblNewLabel_4_1_2_1.setBounds(814, 370, 89, 16);
		frame.getContentPane().add(lblNewLabel_4_1_2_1);
		
		editPosition = new JTextField();
		editPosition.setColumns(10);
		editPosition.setBounds(941, 363, 149, 26);
		frame.getContentPane().add(editPosition);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Name");
		lblNewLabel_4_2_1.setBounds(814, 335, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2_1);
		
		editName = new JTextField();
		editName.setColumns(10);
		editName.setBounds(941, 325, 149, 26);
		frame.getContentPane().add(editName);
		
		addSalary = new JTextField();
		addSalary.setColumns(10);
		addSalary.setBounds(449, 414, 149, 26);
		frame.getContentPane().add(addSalary);
		
		JLabel salary = new JLabel("Salary");
		salary.setBounds(322, 419, 120, 16);
		frame.getContentPane().add(salary);
		
		editSalary = new JTextField();
		editSalary.setColumns(10);
		editSalary.setBounds(941, 398, 149, 26);
		frame.getContentPane().add(editSalary);
		
		lblNewLabel_4_1_1_2_3 = new JLabel("Salary");
		lblNewLabel_4_1_1_2_3.setBounds(814, 403, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_3);
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
	static void readStaffsFile() {
		fileContents = staffsFile.read();
		
		size = staffsFile.size;
		
		for(String x:fileContents) {
			String[] arr = x.split("``");
			staffList.add(new Staff(Integer.parseInt(arr[0]), arr[1], arr[2], Float.parseFloat(arr[3]), arr[4], arr[5]));
		}
		System.out.println("Size: "+size);
	}
	static void writePatientsFile(String type) {
		ArrayList<String> content = new ArrayList<>();
		
		if(type.equalsIgnoreCase("add"))
			size = size+1;
		
		content.add(String.valueOf(size));
		
		for(Staff x:staffList) {
			String id = String.valueOf(x.id);
			String name = x.name;
			String position = x.position;
			String salary = String.valueOf(x.salary);
			String contact = x.contact;
			String address = x.address;
			
			content.add(id+ "``" + name + "``" + position + "``" + salary + "``" + contact + "``" + address);
		}
		staffsFile.write(content);
	}
	static void populateTable() {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[6];
		columnsName[0] = "ID";
		columnsName[1] = "Name";
		columnsName[2] = "Position";
		columnsName[3] = "Salary";
		columnsName[4] = "Contact Number";
		columnsName[5] = "Address";

		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[6];
		for(int i=0; i<staffList.size(); i++) {
			rowData[0] = staffList.get(i).id;
			rowData[1] = staffList.get(i).name;
			rowData[2] = staffList.get(i).position;
			rowData[3] = staffList.get(i).salary;
			rowData[4] = staffList.get(i).contact;
			rowData[5] = staffList.get(i).address;
			
			model.addRow(rowData);
		}
		
		table.setEnabled(false);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
	}
	public void setUsername() {
		usernameLabel.setText(Global.globalEmail);
	}
}
