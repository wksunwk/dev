package com.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.gui.logic.UiJdLogin;
import com.gui.logic.UiJdOrder;

public class MainJFrame extends JFrame {

	private static final long serialVersionUID = 2049013167116348842L;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField validCodeField;
	private CloseableHttpResponse validCodeResponse = null;
	private JTextField item1Field;
	private JTextField count1Field;;

	public MainJFrame() {
		this.init();
		setBounds(100, 100, 750, 430);
		setIconImage(new ImageIcon("ico/SecKill.png").getImage());
	}
	
	private void init() {
		try {
			UiJdLogin login = new UiJdLogin();
			validCodeResponse = login.getValidCode();
			
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblUser = new JLabel("USER\uFF1A");
			lblUser.setBounds(34, 47, 93, 15);
			panel.add(lblUser);
			
			userField = new JTextField();
			userField.setBounds(180, 44, 101, 21);
			panel.add(userField);
			userField.setColumns(10);
			
			JLabel lblPassword = new JLabel("PASSWORD\uFF1A");
			lblPassword.setBounds(34, 83, 93, 15);
			panel.add(lblPassword);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(180, 80, 101, 21);
			panel.add(passwordField);
			
			JLabel lblValidCodeImg = new JLabel("");
			ImageIcon icon = new ImageIcon(ImageIO.read(validCodeResponse.getEntity().getContent()));
			lblValidCodeImg.setIcon(icon);
			lblValidCodeImg.setBounds(34, 158, 223, 45);
			panel.add(lblValidCodeImg);
			
			validCodeField = new JTextField();
			validCodeField.setBounds(180, 118, 101, 21);
			panel.add(validCodeField);
			validCodeField.setColumns(10);
			
			JLabel lblValidCode = new JLabel("VALIDCODE\uFF1A");
			lblValidCode.setBounds(32, 121, 95, 18);
			panel.add(lblValidCode);
			
			JButton btnLogin = new JButton("LOGIN");
			btnLogin.setBounds(152, 230, 93, 23);
			btnLogin.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					String userName = userField.getText();
					String password = new String(passwordField.getPassword());
					String validCode = validCodeField.getText();
					if (userName != null && !"".equals(userName) && password != null && !"".equals(password)
							&& validCode != null && !"".equals(validCode)) {
						try {
							login.doLogin(userName, password, validCode);
							if (login.isLoginOK()) {
								btnLogin.setText("SUCCESS");
								btnLogin.setEnabled(false);
								validCodeResponse.close();
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			panel.add(btnLogin);
			
			JLabel lblItemUrl = new JLabel("ITEM URL");
			lblItemUrl.setBounds(34, 271, 77, 15);
			panel.add(lblItemUrl);
			
			item1Field = new JTextField();
			item1Field.setBounds(31, 291, 672, 21);
			panel.add(item1Field);
			item1Field.setColumns(10);
			
			JLabel lblCount = new JLabel("COUNT");
			lblCount.setBounds(34, 325, 54, 15);
			panel.add(lblCount);
			
			count1Field = new JTextField();
			count1Field.setBounds(98, 322, 66, 21);
			panel.add(count1Field);
			count1Field.setColumns(10);
			
			JButton btnGo = new JButton("GO!!");
			btnGo.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					UiJdOrder order = new UiJdOrder();
					String itemUrl = item1Field.getText();
					String itemCount = count1Field.getText();
					int count = 1;
					if (itemCount != null && !"".equals(itemCount)) {
						count = Integer.parseInt(itemCount);
					}
					order.createOrder(itemUrl, count);
					JOptionPane.showMessageDialog(null, "Order Submit!");
				}
			});
			btnGo.setBounds(180, 322, 93, 23);
			panel.add(btnGo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MainJFrame frame = new MainJFrame();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
