package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.ImporterData;
import javax.swing.LayoutStyle.ComponentPlacement;

public class QuanLyDiem extends JFrame {

	private String classChosen;
	private JPanel contentPane;
	private final JFileChooser openFileChooser;
	private File fileChoosen;
	private JTable table;
	private JTextField textFieldMssv;
	private JTextField textFieldName;
	private JTextField textFieldCmnd;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyDiem frame = new QuanLyDiem();
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
	public QuanLyDiem() {
		classChosen = "17CTT1";
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
		
		JButton btnThongKe = new JButton("Thống kê");
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblMssv = new JLabel("Mssv");
		
		textFieldMssv = new JTextField();
		textFieldMssv.setColumns(10);
		
		JLabel lblName = new JLabel("Họ tên");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblPoint = new JLabel("Điểm");
		
		textFieldCmnd = new JTextField();
		textFieldCmnd.setColumns(10);
		
		
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("GK");
		
		JLabel lblNewLabel_3 = new JLabel("CK");
		
		JLabel lblNewLabel_4 = new JLabel("Khác");
		
		JLabel lblNewLabel_5 = new JLabel("Tổng");
		
		JLabel lblSub = new JLabel("Chọn môn");
		
		JComboBox comboBoxSub = new JComboBox();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(281)
					.addComponent(lblNewLabel)
					.addContainerGap(429, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(31)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblSub))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(88)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMssv)
										.addComponent(lblName))
									.addGap(23)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(31)
											.addComponent(lblPoint)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldCmnd, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
									.addGap(14)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_4)
											.addGap(18)
											.addComponent(lblNewLabel_5)))
									.addGap(41))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(50)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(77))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(544)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnThongKe)
								.addComponent(btnOpenFile))
							.addGap(32)))
					.addGap(135))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblMssv)
								.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSub)
								.addComponent(comboBoxSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoint)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCmnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnOpenFile)
							.addGap(23)
							.addComponent(btnThongKe)))
					.addGap(53))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("MSSV");
		defaultTableModel.addColumn("Họ tên");
		defaultTableModel.addColumn("Giới tính");
		defaultTableModel.addColumn("CMND");
	}
}
