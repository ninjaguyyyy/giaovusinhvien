package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.DATA_CONVERSION;

import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.ImporterData;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

public class QuanLyLop extends JFrame {

	private String classChosen;
	private JPanel contentPane;
	private final JFileChooser openFileChooser;
	private File fileChoosen;
	private JTextField textFieldMssv;
	private JTextField textFieldName;
	private JTextField textFieldCmnd;
	private List<SinhVien> listSv;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyLop frame = new QuanLyLop();
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
	
	String[][] data = new String[0][5];
	public QuanLyLop() {
		classChosen = "17CTT1";
		listSv = new ArrayList<SinhVien>();
		List<SinhVien> getListSv = SinhVienDAO.getByClass(classChosen);
		for(SinhVien sv: getListSv) {
			listSv.add(sv);
		}
		
		data = new String[listSv.size()][5];
        for (int i = 0; i < listSv.size(); i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = String.valueOf(listSv.get(i).getMssv());
            data[i][2] = listSv.get(i).getHoTen();
            data[i][3] = listSv.get(i).getGioiTinh();
            data[i][4] = String.valueOf(listSv.get(i).getMssv());
        }
		
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("c:\\"));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("csv", "csv"));
		
		final DefaultComboBoxModel<String> classesName = new DefaultComboBoxModel<String>();
		List<Lop> dsLop = LopDAO.getAll();
		for(Lop lop: dsLop) {
			classesName.addElement(lop.getTenLop());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		

		final JComboBox<String> comboBox = new JComboBox<String>(classesName);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        classChosen = (String) comboBox.getSelectedItem();
		        listSv.removeAll(listSv);
		        List<SinhVien> getListSv = SinhVienDAO.getByClass(classChosen);
				for(SinhVien sv: getListSv) {
					listSv.add(sv);
				}
		        data = new String[listSv.size()][5];
		        for (int i = 0; i < listSv.size(); i++){
		            data[i][0] = String.valueOf(i+1);
		            data[i][1] = String.valueOf(listSv.get(i).getMssv());
		            data[i][2] = listSv.get(i).getHoTen();
		            data[i][3] = listSv.get(i).getGioiTinh();
		            data[i][4] = String.valueOf(listSv.get(i).getMssv());
		        }
		        table.setModel(new DefaultTableModel(data, new String [] {
		                "STT", "MSSV", "Họ Tên", "Giới Tính", "CMND"
	            }));
		    }
		});
		JButton btnOpenFile = new JButton("Import csv data");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileChoosen = openFileChooser.getSelectedFile();
					try {
						boolean isSuccess = ImporterData.importLop(fileChoosen, classChosen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn file");
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Quản lý Lớp - Sinh viên");
		
		JLabel lblNewLabel_1 = new JLabel("Chọn lớp");
		
		JButton btnAdd = new JButton("Thêm sinh viên");
		
		JLabel lblMssv = new JLabel("Mssv");
		
		textFieldMssv = new JTextField();
		textFieldMssv.setColumns(10);
		
		JLabel lblGender = new JLabel("Giới tính");
		
		JLabel lblName = new JLabel("Họ tên");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblCmnd = new JLabel("Cmnd");
		
		textFieldCmnd = new JTextField();
		textFieldCmnd.setColumns(10);
		
		final JComboBox comboBoxGender = new JComboBox();
		comboBoxGender.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVien newSv = new SinhVien();
				newSv.setMssv(Integer.parseInt(textFieldMssv.getText()));
				newSv.setHoTen(textFieldName.getText());
				newSv.setGioiTinh(comboBoxGender.getSelectedItem().toString());
				newSv.setCmnd(Integer.parseInt(textFieldCmnd.getText()));
				newSv.setLop(LopDAO.getByClassName(classChosen));
				newSv.setPass(textFieldMssv.getText());
				SinhVienDAO.add(newSv);
			}
		});
		
        
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, new String [] {
                "STT", "MSSV", "Họ Tên", "Giới Tính", "CMND"
            });
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		table.setModel(defaultTableModel);
		
		JButton btnMenu = new JButton("Về menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GiaoVuMenu().setVisible(true);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(31)
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(54)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMssv)
										.addComponent(lblName))
									.addGap(23)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(32)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCmnd)
										.addComponent(lblGender))
									.addGap(29)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldCmnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)))
							.addGap(103))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(281)
							.addComponent(lblNewLabel)
							.addGap(211)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnMenu)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd)
								.addComponent(btnOpenFile))
							.addGap(109))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnMenu))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMssv)
						.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGender)
						.addComponent(comboBoxGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCmnd)
						.addComponent(textFieldCmnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(81)
							.addComponent(btnOpenFile)
							.addGap(34)
							.addComponent(btnAdd))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
					.addGap(54))
		);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
	}
}
