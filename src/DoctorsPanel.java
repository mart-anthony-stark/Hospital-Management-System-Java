import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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

import com.records.Doctor;

public class DoctorsPanel {
	private static FileHandler doctorsFile;
	private static ArrayList<String> fileContents = new ArrayList<String>();
	private static ArrayList<Doctor> doctorsList = new ArrayList<Doctor>();
	private static int size;
	private static int viewCount=0;
	private static JLabel usernameLabel = new JLabel("Username");
	
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
	private JTextField idSearch;
	private JTextField editName;
	private JTextField editAppointment;
	private JTextField editNumber;
	private JButton deleteBtn;
	private JButton addDoctorBtn;
	private JTextField addNumber;
	private JLabel lblNewLabel_4_1_1_2;
	private JLabel lblNewLabel_4_1_2;
	private JTextField addAppointment;
	private JTextField addName;
	private JLabel lblNewLabel_4_2;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorsPanel window = new DoctorsPanel();
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
	public DoctorsPanel() {
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
		doctorsFile = new FileHandler("Doctors.txt");
		if(viewCount==0)
			readDoctorsFile();
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
				frame.setVisible(false);
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
				frame.setVisible(false);
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
		btnDoctors_1.setForeground(Color.WHITE);
		btnDoctors_1.setBackground(Color.GRAY);
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
		
		JLabel lblNewLabel_2 = new JLabel("DOCTORS");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(25, 12, 137, 32);
		panel.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 65, 768, 210);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
//		if(viewCount==1)
			populateTable();
		
		scrollPane.setViewportView(table);
		
		lblNewLabel_3 = new JLabel("Edit Information");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBounds(814, 305, 133, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		idSearch = new JTextField();
		idSearch.setBounds(977, 332, 32, 26);
		frame.getContentPane().add(idSearch);
		idSearch.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID No.");
		lblNewLabel_4.setBounds(941, 340, 32, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
//		Search Doctors ID before editing
		JButton searchIDBtn = new JButton("Search");
		searchIDBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = idSearch.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input ID to search");
				}else {
					boolean exist = false;
					for(Doctor x:doctorsList) {
						int doctorID = x.id;
						try {
							int search =Integer.valueOf(id);
						
							if(search == doctorID) {
								exist = true;
								editName.setText(x.name);
								editAppointment.setText(x.appointment);
								editNumber.setText(x.contact);
							}
						}catch(Exception ex) {
							System.out.println(ex.getLocalizedMessage());
						}
					}
					if(!exist) {
						editName.setText("");
						editAppointment.setText("");
						editNumber.setText("");
						JOptionPane.showMessageDialog(null, "Doctor ID does not exist!");
					}
				}
			}
		});
		searchIDBtn.setForeground(Color.WHITE);
		searchIDBtn.setBackground(new Color(35, 119, 211));
		searchIDBtn.setBounds(1015, 332, 75, 26);
		frame.getContentPane().add(searchIDBtn);
		
		JLabel lblNewLabel_4_1 = new JLabel("Name");
		lblNewLabel_4_1.setBounds(814, 397, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Appointments");
		lblNewLabel_4_1_1.setBounds(814, 432, 89, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Emergency Number");
		lblNewLabel_4_1_1_1.setBounds(814, 466, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_1);
		
		editName = new JTextField();
		editName.setColumns(10);
		editName.setBounds(941, 387, 149, 26);
		frame.getContentPane().add(editName);
		
		editAppointment = new JTextField();
		editAppointment.setColumns(10);
		editAppointment.setBounds(941, 425, 149, 26);
		frame.getContentPane().add(editAppointment);
		
		editNumber = new JTextField();
		editNumber.setColumns(10);
		editNumber.setBounds(941, 461, 149, 26);
		frame.getContentPane().add(editNumber);
		
//		Save Edit Doctor
		JButton saveEditBtn = new JButton("Save");
		saveEditBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = idSearch.getText();
				String name = editName.getText().replaceAll("``", "_");
				String appointment = editName.getText().replaceAll("``", "_");
				String number = editNumber.getText().replaceAll("``", "_");
				
				if(id.equals("") || name.equals("") || appointment.equals("") || number.equals("")) {
					JOptionPane.showMessageDialog(null, "Please complete the input fields for edit");
				}else {
					boolean exist = false;
					for(Doctor x:doctorsList) {
						int doctorID = x.id;
						try {
							int search =Integer.valueOf(id);
						
							if(search == doctorID) {
								exist = true;
								
								x.name = editName.getText();
								x.appointment = editAppointment.getText();
								x.contact = editNumber.getText();
								
								writeDoctorsFile("edit");
								idSearch.setText("");
								editName.setText("");
								editAppointment.setText("");
								editNumber.setText("");
								JOptionPane.showMessageDialog(null, "The record was saved successfully!");
								populateTable();
								break;
							}
						}catch(Exception ex) {
							System.out.println(ex.getLocalizedMessage());
						}
					}
					if(!exist) {
						editName.setText("");
						editAppointment.setText("");
						editNumber.setText("");
						JOptionPane.showMessageDialog(null, "Doctor ID does not exist!");
					}
				}
			}
		});
		saveEditBtn.setForeground(Color.WHITE);
		saveEditBtn.setBackground(new Color(35, 119, 211));
		saveEditBtn.setBounds(1015, 489, 75, 26);
		frame.getContentPane().add(saveEditBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = editName.getText();
				String appointment = editAppointment.getText();
				String number = editNumber.getText();
				String id = idSearch.getText();
				
				if(name.equals("") || appointment.equals("") || number.equals("") || id.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out necessary fields");
				}else {
					boolean exist = true;
					for(int i=0; i < doctorsList.size(); i++) {
						int doctorID = Integer.valueOf(doctorsList.get(i).id);
						int searchID = Integer.parseInt(id);
						if(searchID == doctorID) {
							exist = true;
							doctorsList.remove(i);
							
							writeDoctorsFile("delete");
							idSearch.setText("");
							editName.setText("");
							editAppointment.setText("");
							editNumber.setText("");
							JOptionPane.showMessageDialog(null, "The record was saved successfully!");
							populateTable();
							break;
						}
					}
					if(!exist) JOptionPane.showMessageDialog(null, "Doctor ID not found");
				}
			}
		});
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(Color.RED);
		deleteBtn.setBounds(1015, 527, 75, 26);
		frame.getContentPane().add(deleteBtn);
		
//		ADD A RECORD TO DOCTORS FILE
		addDoctorBtn = new JButton("Add");
		addDoctorBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = addName.getText().replaceAll("``", "_");
				String appointment = addAppointment.getText().replaceAll("``", "_");
				String number = addNumber.getText().replaceAll("``", "_");
				
				if(name.equals("") || appointment.equals("") || number.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out necessary fields");
				}else {
					int id = size + 1;
					doctorsList.add(new Doctor(id, name, appointment, number));
					
					writeDoctorsFile("add");
					JOptionPane.showMessageDialog(null, "Successfully added a record");
					addName.setText("");
					addAppointment.setText("");
					addNumber.setText("");
					populateTable();
				}
			}
		});
		addDoctorBtn.setForeground(Color.WHITE);
		addDoctorBtn.setBackground(new Color(35, 119, 211));
		addDoctorBtn.setBounds(523, 489, 75, 26);
		frame.getContentPane().add(addDoctorBtn);
		
		addNumber = new JTextField();
		addNumber.setColumns(10);
		addNumber.setBounds(449, 461, 149, 26);
		frame.getContentPane().add(addNumber);
		
		lblNewLabel_4_1_1_2 = new JLabel("Emergency Number");
		lblNewLabel_4_1_1_2.setBounds(322, 466, 120, 16);
		frame.getContentPane().add(lblNewLabel_4_1_1_2);
		
		lblNewLabel_4_1_2 = new JLabel("Appointments");
		lblNewLabel_4_1_2.setBounds(322, 432, 89, 16);
		frame.getContentPane().add(lblNewLabel_4_1_2);
		
		addAppointment = new JTextField();
		addAppointment.setColumns(10);
		addAppointment.setBounds(449, 425, 149, 26);
		frame.getContentPane().add(addAppointment);
		
		addName = new JTextField();
		addName.setColumns(10);
		addName.setBounds(449, 387, 149, 26);
		frame.getContentPane().add(addName);
		
		lblNewLabel_4_2 = new JLabel("Name");
		lblNewLabel_4_2.setBounds(322, 397, 61, 16);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		lblNewLabel_5 = new JLabel("Add Record");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5.setBounds(322, 305, 133, 23);
		frame.getContentPane().add(lblNewLabel_5);
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
	static void readDoctorsFile() {
		fileContents = doctorsFile.read();
//		Read file and store to doctors list
		size = fileContents.size();
		for(String x:fileContents) {
				String[] arr = x.split("``");
				doctorsList.add(new Doctor(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3]));
		}
		System.out.println("Size: "+doctorsList.size());
	}
	static void writeDoctorsFile(String type) {
		ArrayList<String> content = new ArrayList<String>();

		if(type.equalsIgnoreCase("add"))
			size = size+1;
		
		
		content.add(String.valueOf(size));
		
		for(Doctor x:doctorsList) {
			String id = String.valueOf(x.id);
			String name = x.name;
			String appointment = x.appointment;
			String number = x.contact;
			
			content.add(id + "``" + name + "``" + appointment + "``" + number);
		}
		doctorsFile.write(content);
		System.out.println(content);
	}
	static void populateTable() {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[4];
		columnsName[0] = "ID";
		columnsName[1] = "Name";
		columnsName[2] = "Appointments";
		columnsName[3] = "Emergency Number";
		model.setColumnIdentifiers(columnsName);
		Object[] rowdata = new Object[4];
		for(int i=0; i<doctorsList.size(); i++) {
			rowdata[0] = doctorsList.get(i).id;
			rowdata[1] = doctorsList.get(i).name;
			rowdata[2] = doctorsList.get(i).appointment;
			rowdata[3] = doctorsList.get(i).contact;
			
			model.addRow(rowdata);
		}
		table.setEnabled(false);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
	}
	static void deleteTableRows() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged(); 
	}
	public void setUsername() {
		usernameLabel.setText(Global.globalEmail);
	}
}
