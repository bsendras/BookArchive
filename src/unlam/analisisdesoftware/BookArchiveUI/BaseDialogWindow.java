package unlam.analisisdesoftware.BookArchiveUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BaseDialogWindow extends JDialog {
	public JButton btnClose = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5878869311548093267L;
	
	public enum ModalResult {MR_OK, MR_CANCELL};

	private JPanel contentPane;
	
	public static final int ABM_DIALOG_WIDTH = 675;
	public static final int ABM_DIALOG_HEIGHT = 400;

	/**
	 * Create the frame.
	 */
	public BaseDialogWindow(Component parent) {
		setBounds(0, 0, ABM_DIALOG_WIDTH, 380);
		setLocationRelativeTo(parent);
		
		setUndecorated(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);		
		//contentPane.setBorder(new LineBorder(new Color(195,195,195)));
		contentPane.setBorder(new LineBorder(new Color(0,0,0)));
		contentPane.setLayout(null);
		setContentPane(contentPane);	
	}
}
