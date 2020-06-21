package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import giaovusinhvien.dao.BangDiemDAO;
import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.MonHocDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.BangDiem;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.Mon;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.ImporterData;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.Font;

public class QuanLyDiem extends JFrame {

	private String classChosen;
	private Mon subChosen;
	private JPanel contentPane;
	private final JFileChooser openFileChooser;
	private File fileChoosen;
	private JTable table;
	private JTextField textFieldMssv;
	private JTextField textFieldName;
	private JTextField textFieldGk;
	private JTextField textFieldCk;
	private JTextField textFieldKhac;
	private JTextField textFieldTong;
	private int idDiemChosen;
	
	private List<BangDiem> listDiem;
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
	String[][] data = new String[0][9];
	public QuanLyDiem() {
		classChosen = "17CTT1";
		subChosen = MonHocDAO.getByClass(classChosen).get(0);
		listDiem = new ArrayList<BangDiem>();
		List<BangDiem> getListDiem = BangDiemDAO.getBySub(subChosen);
		for(BangDiem diem: getListDiem) {
			listDiem.add(diem);
		}
		data = new String[listDiem.size()][9];
        for (int i = 0; i < listDiem.size(); i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = String.valueOf(listDiem.get(i).getSv().getMssv());
            data[i][2] = listDiem.get(i).getSv().getHoTen();
            data[i][3] = String.valueOf(listDiem.get(i).getGiuaKi());
            data[i][4] = String.valueOf(listDiem.get(i).getCuoiKi());
            data[i][5] = String.valueOf(listDiem.get(i).getDiemkhac());
            data[i][6] = String.valueOf(listDiem.get(i).getDiemtong());
            data[i][7] = String.valueOf(listDiem.get(i).getDiemtong() >= 5 ? "Đậu": "Rớt");
            data[i][8] = String.valueOf(listDiem.get(i).getIdDiem());
        }
        
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("c:\\"));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("csv", "csv"));
		
		final DefaultComboBoxModel<String> classesName = new DefaultComboBoxModel<String>();
		List<Lop> dsLop = LopDAO.getAll();
		for(Lop lop: dsLop) {
			classesName.addElement(lop.getTenLop());
		}
		
		final DefaultComboBoxModel<Mon> subsName = new DefaultComboBoxModel<Mon>();
		List<Mon> dsMon = MonHocDAO.getByClass(classChosen);
		for(Mon mon: dsMon) {
			subsName.addElement(mon);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		

		final JComboBox<String> comboBox = new JComboBox<String>(classesName);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        classChosen = (String) comboBox.getSelectedItem();
		        subsName.removeAllElements();
		        List<Mon> dsMon = MonHocDAO.getByClass(classChosen);
		        for(Mon mon: dsMon) {
					subsName.addElement(mon);
				}
		    }
		});
		
		final JComboBox<Mon> comboBoxSub = new JComboBox<Mon>(subsName);
		comboBoxSub.setSelectedIndex(0);
		comboBoxSub.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        subChosen = (Mon) comboBoxSub.getSelectedItem();
		        if(subChosen != null) {
		        	listDiem.removeAll(listDiem);
			        List<BangDiem> getListDiem = BangDiemDAO.getBySub(subChosen);
					for(BangDiem diem: getListDiem) {
						listDiem.add(diem);
					}
			        data = new String[listDiem.size()][9];
			        for (int i = 0; i < listDiem.size(); i++){
			            data[i][0] = String.valueOf(i+1);
			            data[i][1] = String.valueOf(listDiem.get(i).getSv().getMssv());
			            data[i][2] = listDiem.get(i).getSv().getHoTen();
			            data[i][3] = String.valueOf(listDiem.get(i).getGiuaKi());
			            data[i][4] = String.valueOf(listDiem.get(i).getCuoiKi());
			            data[i][5] = String.valueOf(listDiem.get(i).getDiemkhac());
			            data[i][6] = String.valueOf(listDiem.get(i).getDiemtong());
			            data[i][7] = String.valueOf(listDiem.get(i).getDiemtong() >= 5 ? "Đậu": "Rớt");
			            data[i][8] = String.valueOf(listDiem.get(i).getIdDiem());
			        }
			        table.setModel(new DefaultTableModel(data, new String [] {
			                "STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả", "Id (no care)"
		            }));
		        }
		        
		    }
		});
		
		JButton btnOpenFile = new JButton("Import csv data");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileChoosen = openFileChooser.getSelectedFile();
					try {
						boolean isSuccess = ImporterData.importDiem(fileChoosen, classChosen, subChosen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn file");
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Quản lý Điểm sinh viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Chọn lớp");
		
		JButton btnThongKe = new JButton("Thống kê");
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblMssv = new JLabel("Mssv");
		
		textFieldMssv = new JTextField();
		textFieldMssv.setEnabled(false);
		textFieldMssv.setColumns(10);
		
		JLabel lblName = new JLabel("Họ tên");
		
		textFieldName = new JTextField();
		textFieldName.setEnabled(false);
		textFieldName.setColumns(10);
		
		JLabel lblPoint = new JLabel("Điểm");
		
		textFieldGk = new JTextField();
		textFieldGk.setColumns(10);
		
		
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int total = listDiem.size();
				int passCount = 0;
				for(BangDiem diem: listDiem) {
					if(diem.getDiemtong() >= 5) {
						passCount++;
					}
				}
				double ratePass = (passCount*1.0/total)*100;
				DecimalFormat f = new DecimalFormat("##.00");
			     String ratePassString = f.format(ratePass);
			     String rateFailString = f.format(100 - ratePass);
				JOptionPane.showMessageDialog(
						null, 
						"Tổng: " + total + "\nĐậu: " + passCount + " (" + ratePassString + "%)"
						+ "\nRớt: " + (total - passCount) + " (" + rateFailString + "%)"
				);
			}
		});
		
		textFieldCk = new JTextField();
		textFieldCk.setColumns(10);
		
		textFieldKhac = new JTextField();
		textFieldKhac.setColumns(10);
		
		textFieldTong = new JTextField();
		textFieldTong.setColumns(10);
		
		JLabel lblGk = new JLabel("GK");
		
		JLabel lblCk = new JLabel("CK");
		
		JLabel lblKhac = new JLabel("Khác");
		
		JLabel lblTong = new JLabel("Tổng");
		
		JLabel lblSub = new JLabel("Chọn môn");
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BangDiem bangdiemCur = BangDiemDAO.getById(idDiemChosen);
				bangdiemCur.setGiuaKi(Double.parseDouble(textFieldGk.getText()));
				bangdiemCur.setCuoiKi(Double.parseDouble(textFieldCk.getText()));
				bangdiemCur.setDiemkhac(Double.parseDouble(textFieldKhac.getText()));
				bangdiemCur.setDiemtong(Double.parseDouble(textFieldTong.getText()));
				BangDiemDAO.update(bangdiemCur);
				
				
				int res = JOptionPane.showOptionDialog(null, "Đã cập nhật", "Thông báo", JOptionPane.DEFAULT_OPTION,
				        JOptionPane.INFORMATION_MESSAGE, null, null, null);

				SwingUtilities.updateComponentTreeUI(QuanLyDiem.this);
				System.out.println(res);
			}
		});
		
		JButton btnRefresh = new JButton("Làm mới");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				(new QuanLyDiem()).setVisible(true);
			}
		});
		
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
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnMenu)
							.addGap(355)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSub)
									.addGap(18)
									.addComponent(comboBoxSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(120)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMssv)
								.addComponent(lblName))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblPoint))
								.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblGk, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblCk))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldGk, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldCk, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldKhac, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblKhac))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTong)
										.addComponent(textFieldTong, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(btnOpenFile))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRefresh)
								.addComponent(btnUpdate)
								.addComponent(btnThongKe))))
					.addGap(51))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMenu)
						.addComponent(lblNewLabel))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMssv)
								.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGk)
								.addComponent(lblCk)
								.addComponent(lblKhac)
								.addComponent(lblTong))
							.addGap(5)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblSub)
						.addComponent(comboBoxSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPoint)
						.addComponent(textFieldGk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldKhac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(btnOpenFile)
							.addGap(18)
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRefresh)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnThongKe))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, new String [] {
                "STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả", "Id (no care)"
            });
		table = new JTable();
		table.setModel(defaultTableModel);
		
		scrollPane.setViewportView(table);
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				
				textFieldMssv.setText(String.valueOf(table.getValueAt(row, 1)));
				textFieldName.setText(String.valueOf(table.getValueAt(row, 2)));
				textFieldGk.setText(String.valueOf(table.getValueAt(row, 3)));
				textFieldCk.setText(String.valueOf(table.getValueAt(row, 4)));
				textFieldKhac.setText(String.valueOf(table.getValueAt(row, 5)));
				textFieldTong.setText(String.valueOf(table.getValueAt(row, 6)));
				
				idDiemChosen =  Integer.parseInt(String.valueOf(table.getValueAt(row, 8)));
			}
		});
		
		contentPane.setLayout(gl_contentPane);
	}
	
	public void reloadTable() {
		
	}
}
