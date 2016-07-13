package unlam.analisisdesoftware.BookArchiveUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import unlam.analisisdesoftware.CustomComponents.ImageUtils;

public class SplashWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1381190149891331602L;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SplashWindow(Component parent, String Image, int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 390);
		setBounds(0,0, width, height);
		setUndecorated(true);
		setLocationRelativeTo(parent);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(getBounds());
		lblNewLabel.setLocation(0, 0);
		contentPane.add(lblNewLabel);
		
		ImageIcon splashIcon = new ImageIcon("gui" + File.separator+ "splash.png");
		splashIcon.setImage(ImageUtils.stretchImage(splashIcon.getImage(), width, height, null));
		lblNewLabel.setIcon(splashIcon);
		
		addWindowListener(new WindowAdapter(){
	        public void windowClosing(WindowEvent e){
	            e.getWindow().dispose();
	        }
	    });
	}
}
