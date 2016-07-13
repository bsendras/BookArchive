package unlam.analisisdesoftware.BookArchiveUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicScrollBarUI;

import unlam.analisisdesoftware.BookArchiveClasses.Book;
import unlam.analisisdesoftware.CustomComponents.CustomBasicScrollBarUI;
import unlam.analisisdesoftware.CustomComponents.CustomTextField;
import unlam.analisisdesoftware.CustomComponents.ImageUtils;

public class BookArchiveABMDialogWindow extends BaseDialogWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2622976702651993360L;
	
	public enum AbmDialogMode {ADM_ADD, ADM_EDIT, ADM_DELETE, ADM_VIEW};
	
	public Vector<Book> books;
	public Book book;
	public String coverImage = "";
	public AbmDialogMode dialogMode;
	
	private ModalResult modalResult = ModalResult.MR_CANCELL;

	public BookArchiveABMDialogWindow(Component parent, AbmDialogMode aDialogMode, Book abook, Vector<Book> abooks) {
		super(parent);
		
		book = abook;
		books = abooks;
		dialogMode = aDialogMode;
		
		getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 15));
		getContentPane().setBounds(0, 0, 655, 400);
		
		JButton btnCancel = new JButton("CANCELAR");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnCancel.setFocusPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(520, 340, 141, 25);
		this.getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("ACEPTAR");
		btnOk.setForeground(Color.BLACK);
		btnOk.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnOk.setFocusPainted(false);
		btnOk.setContentAreaFilled(false);
		btnOk.setBorderPainted(false);
		btnOk.setBackground(Color.WHITE);
		btnOk.setBounds(380, 340, 128, 25);
		getContentPane().add(btnOk);
		
		JLabel labelCover = new JLabel("");
		labelCover.setBounds(40, 30, 195, 300);
		getContentPane().add(labelCover);

		ImageIcon icon = new ImageIcon("gui\\nobookcover.jpg");
		icon.setImage(ImageUtils.stretchImage(icon.getImage(), 195, 300, null));
		labelCover.setIcon(icon);
		
		CustomTextField txtTitle = new CustomTextField("<INSERTE UN TITULO AQUI>");
		txtTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
		txtTitle.setBounds(265, 24, 377, 36);
		txtTitle.setUpperCase(true);
		getContentPane().add(txtTitle);
		
		CustomTextField txtAuthor = new CustomTextField("<INSERTE EL AUTOR>");
		txtAuthor.setBackground(Color.WHITE);
		txtAuthor.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAuthor.setBounds(299, 60, 343, 25);
		txtTitle.setPlaceHolderBold(true);
		getContentPane().add(txtAuthor);
		
		CustomTextField txtYear = new CustomTextField("<ANIO>");
		txtYear.setBackground(Color.WHITE);
		txtYear.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtYear.setOnlyDigits(true);
		txtYear.setBounds(292, 86, 66, 25);
		getContentPane().add(txtYear);
		
		JLabel lblPor = new JLabel("por");
		lblPor.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPor.setBounds(266, 60, 32, 25);
		getContentPane().add(lblPor);
		
		JLabel lblEn = new JLabel("en");
		lblEn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEn.setBounds(265, 86, 31, 25);
		getContentPane().add(lblEn);
		
		JLabel lblInformacionAdicional = new JLabel("INFORMACION ADICIONAL:");
		lblInformacionAdicional.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblInformacionAdicional.setBounds(265, 124, 356, 25);
		getContentPane().add(lblInformacionAdicional);
		
		JLabel lblEditorial = new JLabel("Editorial: ");
		lblEditorial.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEditorial.setBounds(433, 162, 93, 25);
		getContentPane().add(lblEditorial);
		
		CustomTextField txtEditorial = new CustomTextField("<EDITORIAL>");
		txtEditorial.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtEditorial.setBackground(Color.WHITE);
		txtEditorial.setBounds(506, 162, 136, 25);
		getContentPane().add(txtEditorial);
		
		JLabel lblEdicion = new JLabel("Edicion:");
		lblEdicion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEdicion.setBounds(265, 162, 75, 25);
		getContentPane().add(lblEdicion);
		
		CustomTextField txtEdition = new CustomTextField("<EDICION>");
		txtEdition.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtEdition.setBackground(Color.WHITE);
		txtEdition.setBounds(330, 162, 100, 25);
		txtEdition.setOnlyDigits(true);
		getContentPane().add(txtEdition);		
		
		JLabel lblResmen = new JLabel("ISBN:");
		lblResmen.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblResmen.setBounds(265, 194, 75, 25);
		getContentPane().add(lblResmen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(265, 232, 377, 95);
		scrollPane.setDoubleBuffered(true);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
		getContentPane().add(scrollPane);
		
		BasicScrollBarUI scrollBarUI = new CustomBasicScrollBarUI();
		scrollPane.getVerticalScrollBar().setUI(scrollBarUI);
		
		JTextArea txtSummary = new JTextArea();
		txtSummary.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtSummary.setText("Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et doloremagna aliqua. Ut enim ad minim veniam quis nostrud exercitation");
		scrollPane.setViewportView(txtSummary);
		txtSummary.setLineWrap(true);
		txtSummary.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		CustomTextField txtISBN = new CustomTextField("<ISBN>");
		txtISBN.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtISBN.setBackground(Color.WHITE);
		txtISBN.setBounds(314, 194, 328, 25);
		getContentPane().add(txtISBN);
		
		JPanel deleteMessagePanel = new JPanel();
		deleteMessagePanel.setBackground(Color.WHITE);
		deleteMessagePanel.setBounds(247, 60, 414, 267);
		getContentPane().add(deleteMessagePanel);
		deleteMessagePanel.setLayout(null);
		
		JLabel lblVasAEliminar = new JLabel("Vas a eliminar este titulo de tu biblioteca");
		lblVasAEliminar.setForeground(Color.LIGHT_GRAY);
		lblVasAEliminar.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblVasAEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblVasAEliminar.setBounds(12, 117, 390, 24);
		deleteMessagePanel.add(lblVasAEliminar);
		
		JLabel lblEstasSeguro = new JLabel("Estas seguro?");
		lblEstasSeguro.setForeground(Color.LIGHT_GRAY);
		lblEstasSeguro.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstasSeguro.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblEstasSeguro.setBounds(12, 154, 390, 24);
		deleteMessagePanel.add(lblEstasSeguro);
		
		JLabel lblAtencion = new JLabel("ATENCION!");
		lblAtencion.setForeground(Color.LIGHT_GRAY);
		lblAtencion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtencion.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblAtencion.setBounds(12, 80, 390, 24);
		deleteMessagePanel.add(lblAtencion);
		deleteMessagePanel.setVisible(false);
		
		// creo un mouseAdapter con el comportamiento comun a todos los botones
		MouseAdapter ButtonMouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				event.getComponent().setForeground(new Color(0,170,255));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}	
			
			@Override
			public void mouseExited(MouseEvent event) {
				event.getComponent().setForeground(Color.BLACK);
			}	
		};
		btnCancel.addMouseListener(ButtonMouseAdapter);
		btnOk.addMouseListener(ButtonMouseAdapter);
		
		FocusAdapter focusAdapter = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				((CustomTextField)arg0.getComponent()).setCaretPosition(0);
				arg0.getComponent().repaint();
			}
		};
		
		txtTitle.addFocusListener(focusAdapter);
		txtAuthor.addFocusListener(focusAdapter);
		txtEdition.addFocusListener(focusAdapter);
		txtEditorial.addFocusListener(focusAdapter);
		txtYear.addFocusListener(focusAdapter);
		txtISBN.addFocusListener(focusAdapter);
		
		if (book != null) {
			txtTitle.setText(book.getTitulo());
			txtAuthor.setText(book.getAutor());
			
			int year = book.getAnno_de_publicacion();
			if (year > 0) {
				txtYear.setText(String.valueOf(year));
			}
			
			int edition = book.getEdicion();
			if (edition > 0) {
				txtEdition.setText(String.valueOf(book.getEdicion()));
			}
			
			txtEditorial.setText(book.getEditorial());
			txtISBN.setText(book.getISBN());
			
			coverImage = book.getCoverImage();
			String ImagePath = coverImage;
			File file = new File(ImagePath);
			if (!ImagePath.equals("") && file.exists() && !file.isDirectory()) {
				icon = new ImageIcon(ImagePath);
				icon.setImage(ImageUtils.stretchImage(icon.getImage(), 195, 300, null));
				labelCover.setIcon(icon);
			}
			
			// MANEJO EL MODO DE LA VENTANA
			switch (dialogMode) {
			case ADM_ADD:
				//
				break;
			case ADM_EDIT:
				//
				break;
			case ADM_DELETE:
				txtTitle.setEditable(false);
				txtAuthor.setVisible(false);
				txtEdition.setVisible(false);
				txtEditorial.setVisible(false);
				txtYear.setVisible(false);
				txtISBN.setVisible(false);
				txtSummary.setVisible(false);

				getContentPane().setComponentZOrder(deleteMessagePanel, 0);
				deleteMessagePanel.setVisible(true);
				deleteMessagePanel.repaint();
				break;
			case ADM_VIEW:
				txtTitle.setEditable(false);
				txtAuthor.setEditable(false);
				txtEdition.setEditable(false);
				txtEditorial.setEditable(false);
				txtYear.setEditable(false);
				txtISBN.setEditable(false);
				txtSummary.setEditable(false);
				break;
			}
		}
		
		// EVENTOS
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean canClose = true;
				switch (dialogMode) {
				case ADM_ADD:
					if (txtTitle.getText().trim().equals("") ||
							txtAuthor.getText().trim().equals("") ||
							txtEdition.getText().trim().equals("") ||
							txtEditorial.getText().trim().equals("") ||
							txtYear.getText().trim().equals("") ||
							txtISBN.getText().trim().equals("")) {
							
							javax.swing.JOptionPane.showMessageDialog(getContentPane(), 
									"Todos los campos del libro son necesarios. Por favor,\ntomate un minuto para completarlos.",
									"Atencion!", JOptionPane.WARNING_MESSAGE);
							
							canClose = false;
					}
					
					if (checkISBNExists(txtISBN.getText().trim())) {
						javax.swing.JOptionPane.showMessageDialog(getContentPane(), 
								"El ISBN que ingesaste ya existe.",
								"Atencion!", JOptionPane.WARNING_MESSAGE);
						
						canClose = false;
					}
					break;
				case ADM_EDIT:
					if (txtTitle.getText().trim().equals("") ||
							txtAuthor.getText().trim().equals("") ||
							txtEdition.getText().trim().equals("") ||
							txtEditorial.getText().trim().equals("") ||
							txtYear.getText().trim().equals("") ||
							txtISBN.getText().trim().equals("")) {
							
							javax.swing.JOptionPane.showMessageDialog(getContentPane(), 
									"Todos los campos del libro son necesarios. Por favor,\ntomate un minuto para completarlos.",
									"Atencion!", JOptionPane.WARNING_MESSAGE);
							canClose = false;
					}
					
					if (checkISBNExists(txtISBN.getText().trim())) {
						javax.swing.JOptionPane.showMessageDialog(getContentPane(), 
								"El ISBN que ingesaste ya existe.",
								"Atencion!", JOptionPane.WARNING_MESSAGE);
						
						canClose = false;
					}
					break;
				case ADM_DELETE:
					//
					break;
				case ADM_VIEW:
					//
					break;
				}

				if (canClose) {
					// guardo los datos de la ventana en el libro
					book.setTitulo(txtTitle.getText().trim()); 
					book.setAutor(txtAuthor.getText().trim()); 
					book.setAnno_de_publicacion(Integer.parseInt(txtYear.getText().trim())); 
					book.setEdicion(Integer.parseInt(txtEdition.getText().trim())); 
					book.setEditorial(txtEditorial.getText().trim()); 
					book.setISBN(txtISBN.getText().trim());
					book.setCoverImage(coverImage);
					
					modalResult = ModalResult.MR_OK;
				}
			}
		});
		
		labelCover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if ((dialogMode == AbmDialogMode.ADM_DELETE) ||
					(dialogMode == AbmDialogMode.ADM_VIEW)) {
					return;
				}
				
				if (event.getClickCount() == 2) {
					File file = null;
					JFileChooser fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Filter", ImageIO.getReaderFileSuffixes());;
					fileChooser.addChoosableFileFilter(filter);
					fileChooser.showOpenDialog(null);
					file = fileChooser.getSelectedFile();
					
					if (file != null && file.exists() && fileChooser.getSelectedFile().isFile()) {
						String ImagePath = fileChooser.getSelectedFile().getPath(); 
						
						// guardo en el libro.
						coverImage = ImagePath;
						
						// actualizo la imagen de la interfaz.
						ImageIcon icon = new ImageIcon(ImagePath);
						icon.setImage(ImageUtils.stretchImage(icon.getImage(), 195, 300, null));
						labelCover.setIcon(icon);
					}
				}
			}
		});
	}
	
	private boolean checkISBNExists(String ISBN) {
		if (dialogMode == AbmDialogMode.ADM_ADD) {
			for (Book abook : books) {
				if (abook.getISBN().equals(ISBN)) {
					return true;
				}
			}
		} else if (dialogMode == AbmDialogMode.ADM_EDIT) {
			for (Book abook : books) {
				if ((abook != book) && (abook.getISBN().equals(ISBN))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public ModalResult getModalResult() {
		return modalResult;
	}
}