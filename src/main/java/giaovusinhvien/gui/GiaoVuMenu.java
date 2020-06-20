package giaovusinhvien.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class GiaoVuMenu extends JFrame {

	private JPanel contentPane;

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
		
		JLabel lblMenu = new JLabel("Menu");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addComponent(btnManageClass)
					.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
					.addComponent(btnManageSub)
					.addGap(134)
					.addComponent(btnManagePoint)
					.addGap(64))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogout)
					.addContainerGap(464, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(334)
					.addComponent(lblMenu)
					.addContainerGap(341, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogout)
					.addGap(70)
					.addComponent(lblMenu)
					.addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnManageClass)
						.addComponent(btnManagePoint)
						.addComponent(btnManageSub))
					.addGap(92))
		);
		panel.setLayout(gl_panel);
	}

}
