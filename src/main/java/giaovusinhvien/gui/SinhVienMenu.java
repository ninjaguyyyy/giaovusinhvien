package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giaovusinhvien.dao.GiaoVuDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.GiaoVu;
import giaovusinhvien.entity.SinhVien;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class SinhVienMenu extends JFrame {

	private static final int mssv = Login.getUser(); 
	private JPanel contentPane;
	private JTextField textFieldChangePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinhVienMenu frame = new SinhVienMenu();
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
	public SinhVienMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnLogout = new JButton("\u0110\u0103ng xu\u00E2\u0301t");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnManagePoint = new JButton("Xem \u0111i\u00EA\u0309m");
		btnManagePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DanhSachDiem().setVisible(true);
				setVisible(false);
			}
		});
		
		textFieldChangePass = new JTextField();
		textFieldChangePass.setColumns(10);
		JLabel lblMenu = new JLabel("Menu Sinh vi\u00EAn");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnChangePass = new JButton("\u0110\u00F4\u0309i m\u00E2\u0323t kh\u00E2\u0309u");
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass = textFieldChangePass.getText();
				SinhVien sv = SinhVienDAO.getByMssv(mssv);
				sv.setPass(newPass);
				SinhVienDAO.update(sv);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogout)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(123)
							.addComponent(lblMenu)
							.addContainerGap(208, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textFieldChangePass)
								.addComponent(btnChangePass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(22))))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(237)
					.addComponent(btnManagePoint)
					.addContainerGap(266, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldChangePass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogout))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChangePass))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(lblMenu)))
					.addGap(56)
					.addComponent(btnManagePoint)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
