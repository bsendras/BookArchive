package unlam.analisisdesoftware.BookArchiveUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import unlam.analisisdesoftware.BookArchiveClasses.User;
import unlam.analisisdesoftware.CustomComponents.CustomPasswordField;
import unlam.analisisdesoftware.CustomComponents.CustomTextField;
import unlam.analisisdesoftware.CustomComponents.ImageUtils;

public class LoginDialogWindow extends BaseDialogWindow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932933169433310969L;

	private ModalResult modalResult = ModalResult.MR_CANCELL;
	
	CustomTextField txtUser;
	CustomPasswordField txtPassword;
	
	private User glbUser;

	public LoginDialogWindow(Component parent, User user) {
		super(parent);
		setBounds(new Rectangle(383, 236, 300, 360));
		setLocationRelativeTo(parent);
		
		glbUser = user;
		
		ImageIcon icon = new ImageIcon("gui" + File.separator + "banner.png");
		icon.setImage(ImageUtils.stretchImage(icon.getImage(), 95, 174, null));
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(241, 244, 245));
		panelUser.setBounds(35, 97, 230, 42);
		panelUser.setLayout(null);
		getContentPane().add(panelUser);
		
		txtUser = new CustomTextField("<USUARIO>");
		txtUser.setBounds(5, 0, 220, 42);
		txtUser.setBackground(new Color(241, 244, 245));
		txtUser.setPlaceHolder("usuario");
		txtUser.setFont(new Font("SansSerif", Font.PLAIN, 20));
		panelUser.add(txtUser);
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBackground(new Color(241, 244, 245));
		panelPassword.setBounds(35, 150, 230, 42);
		panelPassword.setLayout(null);
		getContentPane().add(panelPassword);
		
		txtPassword = new CustomPasswordField("<PASSWORD>");
		txtPassword.setBounds(5, 0, 220, 42);
		txtPassword.setBackground(new Color(241, 244, 245));
		txtPassword.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		txtPassword.setPlaceHolder("password");
		txtPassword.setText("");
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		panelPassword.add(txtPassword);
		
		JButton btnSignIn = new JButton("REGISTRAR");
		btnSignIn.setForeground(Color.BLACK);
		btnSignIn.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnSignIn.setFocusPainted(false);
		btnSignIn.setContentAreaFilled(false);
		btnSignIn.setBorderPainted(false);
		btnSignIn.setBackground(Color.WHITE);
		btnSignIn.setBounds(275, 162, 172, 25);
		btnSignIn.setVisible(false);
		getContentPane().add(btnSignIn);
		
		JLabel lblPleaseLogin = new JLabel("Login");
		lblPleaseLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseLogin.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblPleaseLogin.setBounds(12, 33, 276, 44);
		getContentPane().add(lblPleaseLogin);
		
		final JPanel panel = new JPanel();
		panel.setBounds(35, 204, 230, 42);
		panel.setBackground(new Color(53, 152, 220));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLogin.setFocusPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(0, 0, 230, 42);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(53, 152, 220));
		panel_1.setBounds(35, 258, 230, 42);
		getContentPane().add(panel_1);
		
		JButton btnCancel = new JButton("salir");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnCancel.setFocusPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(0, 0, 230, 42);
		panel_1.add(btnCancel);
		
		// creo un mouseAdapter con el comportamiento comun a todos los botones
		MouseAdapter ButtonMouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				event.getComponent().setForeground(new Color(236, 241, 244));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getComponent() == btnLogin) {
					glbUser.setUserName(txtUser.getText());
					glbUser.setPassword(new String(txtPassword.getPassword()));
					
					modalResult = ModalResult.MR_OK;
				}
				dispose();
			}	
			
			@Override
			public void mouseExited(MouseEvent event) {
				event.getComponent().setForeground(Color.WHITE);
			}	
		};
		btnSignIn.addMouseListener(ButtonMouseAdapter);
		btnLogin.addMouseListener(ButtonMouseAdapter);
		btnCancel.addMouseListener(ButtonMouseAdapter);
		
		FocusAdapter focusAdapter = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (arg0.getComponent() instanceof CustomTextField) {
					((CustomTextField)arg0.getComponent()).setCaretPosition(0);
				} else if (arg0.getComponent() instanceof CustomPasswordField) {
					((CustomPasswordField)arg0.getComponent()).setCaretPosition(0);
				}
				arg0.getComponent().repaint();
			}
		};
		txtUser.addFocusListener(focusAdapter);
		txtPassword.addFocusListener(focusAdapter);
	}

	public ModalResult getModalResult() {
		return modalResult;
	}
}
