package giaovusinhvien.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import giaovusinhvien.dao.GiaoVuDAO;
import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.Lop;

/**
 *
 * @author nguye
 */
public class Login extends JFrame {
	public static int mssv;
	
    public static int getUser() {
		return mssv;
	}

	public static void setUser(int mssv) {
		Login.mssv = mssv;
	}
	private JComboBox rolejCB;
    private JLabel imgPass;
    private JLabel imgRole;
    private JLabel imgUser;
    private JLabel usernamejLabel;
    private JLabel passwordjLabel;
    private JLabel accessTypejLabel;
    private JPanel containerjPanel;
    private JPasswordField passwordjTextField;
    private JTextField usernamejTextField;
    private JButton submitJBtn;
    
    public Login() {
        initComponents();
    }
    
    public void initComponents() {
        containerjPanel = new JPanel(){
            ImageIcon icon = new ImageIcon("image//signup-image.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(),50, 30, 300, 300, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        imgUser = new JLabel();
        ImageIcon userIcon = new ImageIcon(new ImageIcon("image//user.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        imgUser.setIcon(userIcon);
        usernamejLabel = new JLabel();
        usernamejTextField = new JTextField();
        passwordjTextField = new JPasswordField();
        passwordjLabel = new JLabel();
        imgPass = new JLabel();
        ImageIcon passIcon = new ImageIcon(new ImageIcon("image//pass.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        imgPass.setIcon(passIcon);
        imgRole = new JLabel();
        ImageIcon roleIcon = new ImageIcon(new ImageIcon("image//role.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        imgRole.setIcon(roleIcon);
        accessTypejLabel = new JLabel();
        rolejCB = new JComboBox();
        submitJBtn = new JButton();

        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        usernamejLabel.setFont(new Font("Tahoma", 0, 12));
        usernamejLabel.setText("Tài khoản:");


        passwordjTextField.setFont(new Font("Tahoma", 1, 11));

        passwordjLabel.setFont(new Font("Tahoma", 0, 12));
        passwordjLabel.setText("Mật khẩu:");

        imgPass.setToolTipText("");
        accessTypejLabel.setFont(new Font("Tahoma", 0, 12));
        accessTypejLabel.setText("Quyền truy cập:");

        rolejCB.setModel(new DefaultComboBoxModel(new String[] { "Giáo vụ", "Sinh viên" }));
        submitJBtn.setText("Xác nhận");
        submitJBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	handleSubmit();
                
            }
        });
        
        GroupLayout containerjPanelLayout = new GroupLayout(containerjPanel);
        containerjPanel.setLayout(containerjPanelLayout);
        containerjPanelLayout.setHorizontalGroup(
            containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(containerjPanelLayout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(imgUser, GroupLayout.Alignment.TRAILING)
                    .addComponent(imgPass, GroupLayout.Alignment.TRAILING)
                    .addComponent(imgRole, GroupLayout.Alignment.TRAILING))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(usernamejLabel)
                    .addComponent(accessTypejLabel)
                    .addComponent(passwordjLabel)
                    .addComponent(submitJBtn))
                .addGap(18, 18, 18)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(rolejCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernamejTextField)
                    .addComponent(passwordjTextField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                .addContainerGap())
        );
        containerjPanelLayout.setVerticalGroup(
            containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(containerjPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernamejTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernamejLabel)
                    .addComponent(imgUser))
                .addGap(18, 18, 18)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordjTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordjLabel)
                    .addComponent(imgPass))
                .addGap(18, 18, 18)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(accessTypejLabel)
                    .addComponent(rolejCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRole))
                .addGap(18, 18, 18)
                .addGroup(containerjPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(submitJBtn))
                .addGap(0, 200, Short.MAX_VALUE))
                
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(containerjPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(containerjPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }
    private void handleSubmit() {
    	String username = usernamejTextField.getText();
		String password = passwordjTextField.getText();
		if(username.trim().equals("") || password.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không được để trống.");
			return;
		}
		int roleIndex = rolejCB.getSelectedIndex();
		if(roleIndex == 0) {
			boolean isTrue = GiaoVuDAO.checkLoginTrue(username, password);
			if(isTrue) {
				new GiaoVuMenu().setVisible(true);
		    	setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng.");
				return;
			}
		} else {
			boolean isSuccess = SinhVienDAO.checkLoginTrue(username, password);
			if(isSuccess) {
				setUser(Integer.parseInt(username));
				new SinhVienMenu().setVisible(true);
		    	setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng.");
				return;
			}
		}
    	
    }
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
       
    }
}
