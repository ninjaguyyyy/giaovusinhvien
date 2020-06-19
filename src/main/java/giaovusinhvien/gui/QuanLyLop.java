package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;

public class QuanLyLop extends JFrame {

	private String classChosen;
	private JPanel contentPane;
	private final JFileChooser openFileChooser;
	private File fileChoosen;
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
	public QuanLyLop() {
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("c:\\"));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("csv", "csv"));
		
		final DefaultComboBoxModel<String> classesName = new DefaultComboBoxModel<String>();
		List<Lop> dsLop = LopDAO.getAll();
		for(Lop lop: dsLop) {
			classesName.addElement(lop.getTenLop());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 396);
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
		
		JButton btnAdd = new JButton("Thêm sinh viên");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVien newSv = new SinhVien();
				newSv.setMssv(1712299);
				newSv.setHoTen("Nguyễn Hữu Chí");
				newSv.setGioiTinh("Male");
				newSv.setCmnd(212840552);
				newSv.setLop(LopDAO.getByClassName("17CTT2"));
				SinhVienDAO.add(newSv);
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(281)
					.addComponent(lblNewLabel)
					.addContainerGap(275, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOpenFile)
						.addComponent(btnAdd))
					.addGap(19))
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
					.addGap(9)
					.addComponent(btnOpenFile)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
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
