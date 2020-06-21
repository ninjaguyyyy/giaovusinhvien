package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giaovusinhvien.dao.GiaoVuDAO;
import giaovusinhvien.entity.GiaoVu;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class GiaoVuMenu extends JFrame {
	
	private static final String gvUsername = Login.getGvUsername();
	private JPanel contentPane;
	private JTextField textFieldChangePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoVuMenu frame = new GiaoVuMenu();
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
	public GiaoVuMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnManageClass = new JButton("Qua\u0309n ly\u0301 l\u01A1\u0301p");
		btnManageClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyLop().setVisible(true);
		    	setVisible(false);
			}
		});
		
		JButton btnManageSub = new JButton("Qua\u0309n ly\u0301 m\u00F4n ho\u0323c");
		btnManageSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyMonHoc().setVisible(true);
		    	setVisible(false);
			}
		});
		
		JButton btnManagePoint = new JButton("Qua\u0309n ly\u0301 \u0111i\u00EA\u0309m");
		btnManagePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyDiem().setVisible(true);
		    	setVisible(false);
			}
		});
		
		JButton btnLogout = new JButton("\u0110\u0103ng xu\u00E2\u0301t");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		textFieldChangePass = new JTextField();
		textFieldChangePass.setColumns(10);
		JLabel lblMenu = new JLabel("Menu Gia\u0301o vu\u0323");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton btnChangePass = new JButton("\u0110\u00F4\u0309i m\u00E2\u0323t kh\u00E2\u0309u");
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass = textFieldChangePass.getText();
				GiaoVu gv = GiaoVuDAO.getByUsername(gvUsername);
				gv.setPass(newPass);
				GiaoVuDAO.update(gv);
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLogout)
							.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMenu)
									.addGap(148)
									.addComponent(textFieldChangePass, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnChangePass)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(162)
							.addComponent(btnManageClass)
							.addGap(37)
							.addComponent(btnManageSub)
							.addGap(37)
							.addComponent(btnManagePoint)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(btnLogout))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMenu)
								.addComponent(textFieldChangePass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChangePass)))
					.addGap(100)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnManageClass)
						.addComponent(btnManageSub)
						.addComponent(btnManagePoint))
					.addContainerGap(191, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
