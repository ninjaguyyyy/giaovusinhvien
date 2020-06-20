package giaovusinhvien.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import giaovusinhvien.dao.BangDiemDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.BangDiem;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class DanhSachDiem extends JFrame {

	private static final int mssv = Login.getUser(); 
	private JPanel contentPane;
	private JTable table;
	private List<BangDiem> listDiem;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textFieldMssv;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DanhSachDiem frame = new DanhSachDiem();
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
	public DanhSachDiem() {
		
		listDiem = BangDiemDAO.getByMssvSv(mssv);
		System.out.println(listDiem.get(0).getMon().getTenMon());
		
		String[][] data = new String[listDiem.size()][7];
        for (int i = 0; i < listDiem.size(); i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = listDiem.get(i).getMon().getTenMon();
            data[i][2] = String.valueOf(listDiem.get(i).getGiuaKi());
            data[i][3] = String.valueOf(listDiem.get(i).getCuoiKi());
            data[i][4] = String.valueOf(listDiem.get(i).getDiemkhac());
            data[i][5] = String.valueOf(listDiem.get(i).getDiemtong());
            data[i][6] = String.valueOf(listDiem.get(i).getDiemtong() >= 5 ? "Đậu": "Rớt");
        }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblNewLabel = new JLabel("Thông tin sinh viên");
		
		lblNewLabel_1 = new JLabel("Mssv");
		
		lblNewLabel_2 = new JLabel("Họ tên");
		
		textFieldMssv = new JTextField();
		textFieldMssv.setEnabled(false);
		textFieldMssv.setColumns(10);
		textFieldMssv.setText(String.valueOf(SinhVienDAO.getByMssv(mssv).getMssv()));
		
		textFieldName = new JTextField();
		textFieldName.setEnabled(false);
		textFieldName.setColumns(10);
		textFieldName.setText(SinhVienDAO.getByMssv(mssv).getHoTen());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldMssv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, new String [] {
                "STT","Môn học", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả"
            });
		table.setModel(defaultTableModel);
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
