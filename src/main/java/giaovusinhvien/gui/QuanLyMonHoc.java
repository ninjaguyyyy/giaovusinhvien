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

public class QuanLyMonHoc extends JFrame {

	private String classChosen;
	private final JFileChooser openFileChooser;
	private File fileChoosen;
	private JTable table;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyMonHoc frame = new QuanLyMonHoc();
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
	public QuanLyMonHoc() {
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
						boolean isSuccess = ImporterData.importMon(fileChoosen, classChosen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn file");
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Quản lý Môn học - Sinh viên");
		
		JLabel lblNewLabel_1 = new JLabel("Chọn lớp");
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(281)
					.addComponent(lblNewLabel)
					.addContainerGap(406, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addComponent(btnOpenFile)
					.addGap(109))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(55, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(93)
							.addComponent(btnOpenFile)
							.addGap(177))))
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
