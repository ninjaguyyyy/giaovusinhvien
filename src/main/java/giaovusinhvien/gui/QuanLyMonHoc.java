package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.MonHocDAO;
import giaovusinhvien.dao.SV_Mon_DAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.Mon;
import giaovusinhvien.entity.SV_Mon;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.ImporterData;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class QuanLyMonHoc extends JFrame {

	private String classChosen;
	private Mon subChosen;
	private final JFileChooser openFileChooser;
	private File fileChoosen;
	private JTable table;
	private List<Mon> listMon;
	private List<SinhVien> listSv;
	private List<SV_Mon> listSvm;
	
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
	String[][] data = new String[0][3];
	String[][] dataSv = new String[0][5];
	private JTable tableSv;
	private JTextField txtMssvOut;
	private JTextField textFieldMssvIn;
	public QuanLyMonHoc() {
		classChosen = "17CTT1";
		listSv = SinhVienDAO.getByClass(classChosen);
		subChosen = MonHocDAO.getByClass(classChosen).get(0);
		listSvm = SV_Mon_DAO.getBySub(subChosen);
		for(int i = 0; i < listSv.size(); i++) {
			for(int j = 0; j < listSvm.size(); j++) {
				if(listSv.get(i).getIdSV() == listSvm.get(j).getSv().getIdSV() && listSvm.get(j).getActionStatus().equals("out")) {
					listSv.remove(i);
					i--;
					break;
				}
			}
		}
		for(int k = 0; k < listSvm.size(); k++) {
			if(listSvm.get(k).getActionStatus().equals("in")) {
				listSv.add(listSvm.get(k).getSv());
			}
		}
		
		
		listMon = new ArrayList<Mon>();
		List<Mon> getListMon = MonHocDAO.getByClass(classChosen);
		for(Mon mon: getListMon) {
			listMon.add(mon);
		}
		data = new String[1][3];
        data[0][0] = String.valueOf(subChosen.getMaMon());
        data[0][1] = subChosen.getTenMon();
        data[0][2] = subChosen.getPhong();
        
        dataSv = new String[listSv.size()][5];
        for (int i = 0; i < listSv.size(); i++){
        	dataSv[i][0] = String.valueOf(i+1);
        	dataSv[i][1] = String.valueOf(listSv.get(i).getMssv());
        	dataSv[i][2] = listSv.get(i).getHoTen();
        	dataSv[i][3] = listSv.get(i).getGioiTinh();
        	dataSv[i][4] = String.valueOf(listSv.get(i).getMssv());
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
		setBounds(100, 100, 775, 480);
		contentPane = new JPanel();
		contentPane.setToolTipText("Nhập mssv");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final DefaultComboBoxModel<Mon> subsName = new DefaultComboBoxModel<Mon>();
		List<Mon> dsMon = MonHocDAO.getByClass(classChosen);
		for(Mon mon: dsMon) {
			subsName.addElement(mon);
		}
		

		final JComboBox<String> comboBox = new JComboBox<String>(classesName);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        classChosen = (String) comboBox.getSelectedItem();
		        // update combobox sub
		        subsName.removeAllElements();
		        List<Mon> dsMon = MonHocDAO.getByClass(classChosen);
		        for(Mon mon: dsMon) {
					subsName.addElement(mon);
				}
		        subChosen = dsMon.get(0);
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
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Chọn lớp");
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnMenu = new JButton("Về menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GiaoVuMenu().setVisible(true);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Chọn môn");
		
		final JComboBox<Mon> comboBoxSub = new JComboBox<Mon>(subsName);
		comboBoxSub.setSelectedIndex(0);
		comboBoxSub.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				subChosen = (Mon) comboBoxSub.getSelectedItem();
				// update table mon
		        if(subChosen != null) {
		        	// update table mon
		        	data = new String[1][3];
		            data[0][0] = String.valueOf(subChosen.getMaMon());
		            data[0][1] = subChosen.getTenMon();
		            data[0][2] = subChosen.getPhong();
			        
			        table.setModel(new DefaultTableModel(data, new String [] {
			        		"Mã môn", "Tên môn", "Phòng học"
		            }));
			        
			        // update table sv
			        listSv.removeAll(listSv);
			        List<SinhVien> getListSv = SinhVienDAO.getByClass(classChosen);
					for(SinhVien sv: getListSv) {
						listSv.add(sv);
					}
					
					listSvm = SV_Mon_DAO.getBySub(subChosen);
					for(int i = 0; i < listSv.size(); i++) {
						for(int j = 0; j < listSvm.size(); j++) {
							if(listSv.get(i).getIdSV() == listSvm.get(j).getSv().getIdSV() && listSvm.get(j).getActionStatus().equals("out")) {
								listSv.remove(i);
								i--;
								break;
							}
						}
					}
					for(int k = 0; k < listSvm.size(); k++) {
						if(listSvm.get(k).getActionStatus().equals("in")) {
							listSv.add(listSvm.get(k).getSv());
						}
					}
					
			        dataSv = new String[listSv.size()][5];
			        for (int i = 0; i < listSv.size(); i++){
			        	dataSv[i][0] = String.valueOf(i+1);
			        	dataSv[i][1] = String.valueOf(listSv.get(i).getMssv());
			        	dataSv[i][2] = listSv.get(i).getHoTen();
			        	dataSv[i][3] = listSv.get(i).getGioiTinh();
			        	dataSv[i][4] = String.valueOf(listSv.get(i).getMssv());
			        }
			        tableSv.setModel(new DefaultTableModel(dataSv, new String [] {
			                "STT", "MSSV", "Họ Tên", "Giới Tính", "CMND"
		            }));
		        }
		        
			}
			
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnOut = new JButton("Rút khỏi lớp");
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVien svOut = SinhVienDAO.getByMssv(Integer.parseInt(txtMssvOut.getText()));
				SV_Mon sv_mon = new SV_Mon();
				sv_mon.setMon(subChosen);
				sv_mon.setSv(svOut);
				sv_mon.setActionStatus("out");
				boolean result = SV_Mon_DAO.add(sv_mon);
				String nofitication;
				if(result == false) {
					nofitication = "Sinh viên này đã xin rút hoặc xin học 1 lần rồi. Không thể xin thêm lần nữa.";
				} else {
					nofitication = "Đã xin thành công";
				}
				JOptionPane.showMessageDialog(null, nofitication);
			}
		});
		
		txtMssvOut = new JTextField();
		txtMssvOut.setToolTipText("Nhập mssv");
		txtMssvOut.setColumns(10);
		
		textFieldMssvIn = new JTextField();
		textFieldMssvIn.setToolTipText("Nhập mssv");
		textFieldMssvIn.setColumns(10);
		
		JButton btnIn = new JButton("Xin vào lớp");
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVien svOut = SinhVienDAO.getByMssv(Integer.parseInt(textFieldMssvIn.getText()));
				SV_Mon sv_mon = new SV_Mon();
				sv_mon.setMon(subChosen);
				sv_mon.setSv(svOut);
				sv_mon.setActionStatus("in");
				boolean result = SV_Mon_DAO.add(sv_mon);
				String nofitication;
				if(result == false) {
					nofitication = "Sinh viên này đã xin rút hoặc xin học 1 lần rồi. Không thể xin thêm lần nữa.";
				} else {
					nofitication = "Đã xin thành công";
				}
				JOptionPane.showMessageDialog(null, nofitication);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnMenu)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addGap(188))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(51)
									.addComponent(lblNewLabel_2)
									.addGap(18)
									.addComponent(comboBoxSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnOpenFile)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldMssvIn, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(txtMssvOut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnOut)
										.addComponent(btnIn))))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(327))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnMenu))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboBoxSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOpenFile)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(52)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtMssvOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOut))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldMssvIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIn)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addGap(38))
		);
		
		DefaultTableModel defaultTableModelSv = new DefaultTableModel(dataSv, new String [] {
                "STT", "MSSV", "Họ Tên", "Giới Tính", "CMND"
            });
		
		tableSv = new JTable();
		tableSv.setModel(defaultTableModelSv);
		scrollPane_1.setViewportView(tableSv);
		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, new String [] {
                "Mã môn", "Tên môn", "Phòng học"
            });
		table = new JTable();
		table.setModel(defaultTableModel);
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
