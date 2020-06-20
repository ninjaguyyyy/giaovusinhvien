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
	String[][] data = new String[0][8];
	public QuanLyDiem() {
		classChosen = "17CTT1";
		subChosen = MonHocDAO.getByClass(classChosen).get(0);
		listDiem = new ArrayList<BangDiem>();
		List<BangDiem> getListDiem = BangDiemDAO.getBySub(subChosen);
		for(BangDiem diem: getListDiem) {
			listDiem.add(diem);
		}
		data = new String[listDiem.size()][8];
        for (int i = 0; i < listDiem.size(); i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = String.valueOf(listDiem.get(i).getSv().getMssv());
            data[i][2] = listDiem.get(i).getSv().getHoTen();
            data[i][3] = String.valueOf(listDiem.get(i).getGiuaKi());
            data[i][4] = String.valueOf(listDiem.get(i).getCuoiKi());
            data[i][5] = String.valueOf(listDiem.get(i).getDiemkhac());
            data[i][6] = String.valueOf(listDiem.get(i).getDiemtong());
            data[i][7] = String.valueOf(listDiem.get(i).getDiemtong() >= 5 ? "Đậu": "Rớt");
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
		setBounds(100, 100, 846, 445);
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
		        listDiem.removeAll(listDiem);
		        List<BangDiem> getListDiem = BangDiemDAO.getBySub(subChosen);
				for(BangDiem diem: getListDiem) {
					listDiem.add(diem);
				}
		        data = new String[listDiem.size()][8];
		        for (int i = 0; i < listDiem.size(); i++){
		            data[i][0] = String.valueOf(i+1);
		            data[i][1] = String.valueOf(listDiem.get(i).getSv().getMssv());
		            data[i][2] = listDiem.get(i).getSv().getHoTen();
		            data[i][3] = String.valueOf(listDiem.get(i).getGiuaKi());
		            data[i][4] = String.valueOf(listDiem.get(i).getCuoiKi());
		            data[i][5] = String.valueOf(listDiem.get(i).getDiemkhac());
		            data[i][6] = String.valueOf(listDiem.get(i).getDiemtong());
		            data[i][7] = String.valueOf(listDiem.get(i).getDiemtong() >= 5 ? "Đậu": "Rớt");
		        }
		        table.setModel(new DefaultTableModel(data, new String [] {
		                "STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả"
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
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(281)
					.addComponent(lblNewLabel)
					.addContainerGap(429, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
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
											.addComponent(lblGk, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(31)
											.addComponent(lblPoint)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldGk, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
									.addGap(14)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldCk, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCk))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldKhac, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldTong, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblKhac)
											.addGap(18)
											.addComponent(lblTong))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(50)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE)))
							.addGap(109))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnOpenFile)
								.addComponent(btnThongKe))
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
								.addComponent(lblGk)
								.addComponent(lblCk)
								.addComponent(lblKhac)
								.addComponent(lblTong))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoint)
								.addComponent(textFieldCk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldKhac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldTong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldGk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnOpenFile)
							.addGap(19)
							.addComponent(btnThongKe)))
					.addGap(53))
		);
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, new String [] {
                "STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả"
            });
		table = new JTable();
		table.setModel(defaultTableModel);
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
