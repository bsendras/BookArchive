package unlam.analisisdesoftware.BookArchiveMain;

import java.awt.EventQueue;

import unlam.analisisdesoftware.BookArchiveUI.MainWindow;

public class BookArchiveMain {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mainWindow = new MainWindow();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
