package unlam.analisisdesoftware.BookArchiveUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import unlam.analisisdesoftware.BookArchiveClasses.Book;
import unlam.analisisdesoftware.BookArchiveClasses.Funcion;
import unlam.analisisdesoftware.BookArchiveClasses.User;
import unlam.analisisdesoftware.BookArchiveUI.BaseDialogWindow.ModalResult;
import unlam.analisisdesoftware.BookArchiveUI.BookArchiveABMDialogWindow.AbmDialogMode;
import unlam.analisisdesoftware.CustomComponents.CustomBasicScrollBarUI;
import unlam.analisisdesoftware.CustomComponents.CustomFileUtils;
import unlam.analisisdesoftware.CustomComponents.CustomListItemRenderer;
import unlam.analisisdesoftware.CustomComponents.CustomTextField;

public class MainWindow {
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 768;
	public static final String BOOK_ARCHIVE_ICON_IMAGE_PATH = "gui\\bookarchive.ico";
	private static final int ABOUT_FIXED_X = 924;
	private static final int HELP_FIXED_X = 882;
	private static final int SCROLL_PANE_FIXED_WITH = 920;
	private static final int SCROLL_PANE_FIXED_HEIGHT = 570;
	private static final String SYS_USER = "Admin";
	private static final String SYS_PASS = "123456";
	
	public JFrame window = null;
	private JScrollPane scrollPane = null;
	private JTextField txtSearch = null;
	private BookArchiveABMDialogWindow dialog = null;
	private LoginDialogWindow dialogLogin = null;
	private JButton btnAbout = null;
	private JButton btnHelp = null;
	private JList<Book> bookList = null;
	private JPanel placeHolderPanel = null;
	private Integer OLD_WINDOW_WIDTH = WINDOW_WIDTH;
	private Integer OLD_WINDOW_HEIGHT = WINDOW_HEIGHT;
	public boolean showSplash = true;
	public boolean showLogin = true;
	
	Timer timer = null;
	
	public static String bookFile = "libros.tsv";
	public static String userFile = "usuarios.tsv";
	
	public User currentUser;
	
    Funcion<Book> imprimirEnArchivo = new Funcion<Book>() {
        @Override
        public void funcion(Book book, Object parameters) {
        	PrintStream outFile= (PrintStream) parameters;
        	outFile.print(book.getISBN() + "\t");
        	outFile.print(book.getTitulo() + "\t");
        	outFile.print(book.getAutor() + "\t");
        	outFile.print(book.getEditorial() + "\t");
        	outFile.print(book.getEdicion() + "\t");
        	outFile.print(book.getAnno_de_publicacion() + "\t");
        	outFile.print(book.getCoverImage()+ "\n");
        }
    };
 
    Funcion<User> imprimirUserEnArchivo = new Funcion<User>() {
        @Override
        public void funcion(User user, Object parameters) {
        	PrintStream outFile= (PrintStream) parameters;
        	outFile.print(user.getUserName() + "\t");
        	outFile.print(user.getPassword() + "\n");
        }
    };   
    
    Vector<Book> books = new Vector<Book>();

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	/**
	 * Show the window.
	 */
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		this.window.setVisible(visible);
	}	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		currentUser = new User("", "");
		
		//COMPONENTES
		window = new JFrame();		

		// Dimensiones
		window.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
			
		// Centro la ventana.
		window.setLocationRelativeTo(null);
		
		// Estetica default.
		window.getContentPane().setBackground(new Color(255, 255, 255));
		
		// Trabajamos en modo de posicionamiento absoluto.
		window.getContentPane().setLayout(null);
		
		// Que debo hacer al cerrar esta ventana?
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// IMAGENES
		
		ImageIcon icon = new ImageIcon("gui" + File.separator + "bookarchive.png");
		ImageIcon appLogoIcon = new ImageIcon("gui" + File.separator + "logo1.png"); 
		ImageIcon aboutIcon = new ImageIcon("gui" + File.separator + "i_about.png"); 
		ImageIcon aboutIconOver = new ImageIcon("gui" + File.separator + "i_about_over.png"); 
		ImageIcon helpIcon = new ImageIcon("gui" + File.separator + "i_help.png");
		ImageIcon helpIconOver = new ImageIcon("gui" + File.separator + "i_help_over.png");
		
		// COMPONENTES
		window.setIconImage(icon.getImage());
		
		JLabel lblBookArchive = new JLabel("BOOK ARCHIVE");
		lblBookArchive.setFont(new Font("SansSerif", Font.BOLD, 34));
		lblBookArchive.setBounds(54, 13, 344, 42);
		lblBookArchive.setIcon(appLogoIcon);
		window.getContentPane().add(lblBookArchive);
		
		btnAbout = new JButton("");
		btnAbout.setBorderPainted(false);
		btnAbout.setForeground(Color.BLACK);
		btnAbout.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnAbout.setFocusPainted(false);
		btnAbout.setContentAreaFilled(false);
		btnAbout.setBackground(Color.WHITE);
		btnAbout.setBounds(ABOUT_FIXED_X, 13, 30, 29);
		btnAbout.setIcon(aboutIcon);
		btnAbout.setRolloverIcon(aboutIconOver);
		window.getContentPane().add(btnAbout);
		
		btnHelp = new JButton("");
		btnHelp.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				try {
					Process process = new ProcessBuilder("SumatraPDF.exe","manual.pdf", "-restrict").start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnHelp.setIconTextGap(0);
		btnHelp.setForeground(Color.BLACK);
		btnHelp.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnHelp.setFocusPainted(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		btnHelp.setBackground(Color.WHITE);
		btnHelp.setBounds(HELP_FIXED_X, 13, 30, 29);
		btnHelp.setIcon(helpIcon);
		btnHelp.setRolloverIcon(helpIconOver);
		window.getContentPane().add(btnHelp);		
		
		scrollPane = new JScrollPane();
		scrollPane.setDoubleBuffered(true);
		scrollPane.setBounds(44, 130, SCROLL_PANE_FIXED_WITH, SCROLL_PANE_FIXED_HEIGHT);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
		window.getContentPane().add(scrollPane);
		
		BasicScrollBarUI scrollBarUI = new CustomBasicScrollBarUI();
		scrollPane.getVerticalScrollBar().setUI(scrollBarUI);
		
		txtSearch = new CustomTextField("BUSCAR: Titulo, Autor, Año, Editorial, ISBN...");
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					doSerchBooks(txtSearch.getText().trim());
				}
			}
		});
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtSearch.setBounds(565, 82, 374, 36);
		//txtSearch.setColumns(10);
		window.getContentPane().add(txtSearch);		
		
		bookList = new JList<Book>();
		bookList.setFont(new Font("SansSerif", Font.PLAIN, 13));
		bookList.setDoubleBuffered(true);
		bookList.setVisibleRowCount(-1);
		bookList.setValueIsAdjusting(true);
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		bookList.setBackground(Color.WHITE);
		scrollPane.setViewportView(bookList);
		
		final JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnNuevo.setBorderPainted(false);
		btnNuevo.setContentAreaFilled(false);
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBackground(Color.WHITE);
		btnNuevo.setForeground(Color.BLACK);
		btnNuevo.setBounds(38, 88, 97, 25);
		window.getContentPane().add(btnNuevo);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnEditar.setFocusPainted(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(147, 88, 107, 25);
		window.getContentPane().add(btnEditar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(266, 88, 133, 25);
		window.getContentPane().add(btnEliminar);
		
		placeHolderPanel = new JPanel();
		placeHolderPanel.setBackground(new Color(255, 255, 255, 0));
		placeHolderPanel.setBounds(118, 171, 770, 380);		
		placeHolderPanel.setLayout(null);
		placeHolderPanel.setDoubleBuffered(true);
		placeHolderPanel.setVisible(false);
		window.getContentPane().add(placeHolderPanel);
		
		JLabel lblOops = new JLabel("Oops! Parece que aun no hay libros en tu biblioteca.");
		lblOops.setHorizontalAlignment(SwingConstants.CENTER);
		lblOops.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblOops.setBounds(12, 168, 746, 44);
		lblOops.setForeground(new Color(215, 215, 215));
		lblOops.setDoubleBuffered(true);
		placeHolderPanel.add(lblOops);
		
		JLabel lblComienzaHaciendoClick = new JLabel("Comienza haciendo click en \"NUEVO\"");
		lblComienzaHaciendoClick.setHorizontalAlignment(SwingConstants.CENTER);
		lblComienzaHaciendoClick.setForeground(new Color(215, 215, 215));
		lblComienzaHaciendoClick.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblComienzaHaciendoClick.setBounds(12, 205, 746, 44);
		placeHolderPanel.add(lblComienzaHaciendoClick);
		
		// EVENTOS
		
		// Al abrir la ventana.
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				// Levanto los libros del archivo.
				loadBooksFromFile(bookFile);
				
				CustomListItemRenderer itemRenderer =  new CustomListItemRenderer("gui" + File.separator + "nobookcover.jpg");
			
				// Una lista que almacena libros WOOOW!!.
				DefaultListModel<Book> listModel = new DefaultListModel<Book>();
				bookList.setModel(listModel);
				bookList.setCellRenderer(itemRenderer);
				int i;
				
				for (i = 0; i < books.toArray().length; i++ ) {
					addBookToList((Book)books.toArray()[i]);
				}
				
				/*
				DefaultListModel<String> listModel = new DefaultListModel<String>();
				
				bookList.setModel(listModel);
				bookList.setCellRenderer(itemRenderer);
				listModel.addElement("stg\\img" + File.separator + "1.jpg");
				listModel.addElement("stg\\img" + File.separator + "2.jpg");
				listModel.addElement("stg\\img" + File.separator + "3.jpg");
				listModel.addElement("stg\\img" + File.separator + "4.jpg");
				listModel.addElement("stg\\img" + File.separator + "5.jpg");
				listModel.addElement("stg\\img" + File.separator + "6.jpg");
				listModel.addElement("stg\\img" + File.separator + "7.jpg");
				listModel.addElement("stg\\img" + File.separator + "8.jpg");
				listModel.addElement("stg\\img" + File.separator + "9.jpg");
				listModel.addElement("stg\\img" + File.separator + "10.jpg");
				listModel.addElement("stg\\img" + File.separator + "11.png");
				listModel.addElement("stg\\img" + File.separator + "12.jpg");
				listModel.addElement("stg\\img" + File.separator + "13.jpg");
				listModel.addElement("stg\\img" + File.separator + "14.png");
				listModel.addElement("stg\\img" + File.separator + "15.jpg");
				listModel.addElement("stg\\img" + File.separator + "16.jpg");
				listModel.addElement("stg\\img" + File.separator + "17.jpg");
				listModel.addElement("stg\\img" + File.separator + "18.jpg");
				listModel.addElement("stg\\img" + File.separator + "19.jpg");
				listModel.addElement("stg\\img" + File.separator + "20.jpg");
				*/		
			}
		});
		
		// Capturamos el resizing
		window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	windowResized();
            }
		});
		
		// Capturamos las acciones de restaurar, maximizar y minimizar
		window.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				windowResized();
			}
		});
		
		// creo un mouseAdapter con el comportamiento comun a todos los botones
		MouseAdapter ButtonMouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				event.getComponent().setForeground(new Color(0,170,255));
			}
			
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getComponent() == btnNuevo) {
					showBookABMWindow(AbmDialogMode.ADM_ADD);
				} else if (event.getComponent() == btnEditar) {
					showBookABMWindow(AbmDialogMode.ADM_EDIT);
				} else if (event.getComponent() == btnEliminar) {
					showBookABMWindow(AbmDialogMode.ADM_DELETE);
				}
			}	
			
			@Override
			public void mouseExited(MouseEvent event) {
				event.getComponent().setForeground(Color.BLACK);
			}	
		};

		btnNuevo.addMouseListener(ButtonMouseAdapter);
		btnEditar.addMouseListener(ButtonMouseAdapter);		
		btnEliminar.addMouseListener(ButtonMouseAdapter);
		
		bookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Component Component = bookList.getComponentAt(event.getX(), event.getY());
					if (Component != null) {
						showBookABMWindow(AbmDialogMode.ADM_VIEW);
					}
				}
			}
		});
		
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtSearch.repaint();
			}
		});	
		
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSplash = true;
			}
		});
		
		// Por ultimo inicializo el timer principal
		timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
            	onMainTimer();
            }
        }, 0, 700);
	}
	
	public void onMainTimer() {
		// aca voy a controlar el mostrar y ocultar el placeholder
		// cuando la lista esta vacia.
		if (bookList.getModel().getSize() == 0) {
			placeHolderPanel.setVisible(true);
			window.getContentPane().setComponentZOrder(placeHolderPanel, 0);
			window.getContentPane().setComponentZOrder(scrollPane, 1);
		} else {
			placeHolderPanel.setVisible(false);
			window.getContentPane().setComponentZOrder(placeHolderPanel, 1);
			window.getContentPane().setComponentZOrder(scrollPane, 0);
			window.getContentPane().repaint();
		}
		
		if (showSplash) {
			showSplash = false;
			SplashWindow splash = new SplashWindow(window, "gui" + File.separator+ "splash.png", 500, 390);
			splash.setVisible(true);
			splash.repaint();
			
			window.setEnabled(false);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				window.setEnabled(true);
				splash.dispose();
			}
		}
		
		if (showLogin) {
			placeHolderPanel.setVisible(false);
			showLogin = false;
			// muestro la ventana de login
			do {
				dialogLogin = new LoginDialogWindow(window, currentUser);
				dialogLogin.setVisible(true);
				
				if (dialogLogin.getModalResult() != ModalResult.MR_OK) {
					// cierro la aplicacion.
					System.exit(0);
				}
			} while (!currentUser.getUserName().equals(SYS_USER) ||
					 !currentUser.getPassword().equals(SYS_PASS));
		}
	}
	
	public void windowResized() {
		// Calculo la diferencia de tamanio
		Integer withGap = window.getWidth() - OLD_WINDOW_WIDTH;
		Integer heightGap = window.getHeight() - OLD_WINDOW_HEIGHT;
		
		// Cambio las posiciones.
		scrollPane.setBounds(scrollPane.getX(), scrollPane.getY(), scrollPane.getWidth() + withGap, scrollPane.getHeight() + heightGap);
		txtSearch.setBounds(txtSearch.getX() + withGap, txtSearch.getY(), txtSearch.getX(), txtSearch.getHeight());
		btnAbout.setBounds(btnAbout.getX() + withGap, btnAbout.getY(), btnAbout.getWidth(), btnAbout.getHeight());
		btnHelp.setBounds(btnHelp.getX() + withGap, btnHelp.getY(), btnHelp.getWidth(), btnHelp.getHeight());
		placeHolderPanel.setBounds(placeHolderPanel.getX() + (withGap/2), placeHolderPanel.getY() + (heightGap/2), placeHolderPanel.getWidth(), placeHolderPanel.getHeight());
		
		// Guardo el tamanio actual para la proxima vez.
		OLD_WINDOW_WIDTH = window.getWidth();
		OLD_WINDOW_HEIGHT = window.getHeight();
	}
	
	private void showBookABMWindow(AbmDialogMode dialogMode) {
		
		switch (dialogMode) {
			case ADM_ADD:
				Book newBook= new Book();
				dialog = new BookArchiveABMDialogWindow(window, dialogMode, newBook, books);
				dialog.setVisible(true);
				
				if (dialog.getModalResult() == ModalResult.MR_OK) {
					
					// guardo la imagen configurada.
					String newStoredImage = CustomFileUtils.copyFileToStorage(newBook.getCoverImage());
					newStoredImage =  CustomFileUtils.renameFileInStorage(newStoredImage, newBook.getISBN());
					newBook.setCoverImage(newStoredImage);
					addBookToList(newBook);
					addBookToVector(newBook);
					saveBooksToFile(bookFile);
				}
				break;
			case ADM_EDIT:
				if (bookList.getSelectedIndex() >= 0) {
					// Puedo proceder
					Book selectedBook = bookList.getSelectedValue();
					String oldCoverImage = selectedBook.getCoverImage();
					dialog = new BookArchiveABMDialogWindow(window, dialogMode, selectedBook, books);
					dialog.setVisible(true);
					
					if (dialog.getModalResult() == ModalResult.MR_OK) {
						// Cambio la imagen?
						if (!oldCoverImage.equals(selectedBook.getCoverImage())) {
							// Borro la vieja
							CustomFileUtils.deleteFileFromStorage(oldCoverImage);
							
							// Agrego la nueva la copio y guardo el valor correcto.
							String newStoredImage = CustomFileUtils.copyFileToStorage(selectedBook.getCoverImage());
							newStoredImage =  CustomFileUtils.renameFileInStorage(newStoredImage, selectedBook.getISBN());
							selectedBook.setCoverImage(newStoredImage);
						}
						
						saveBooksToFile(bookFile);						
					}
				}
				break;
			case ADM_DELETE:
				if (bookList.getSelectedIndex() >= 0) {
					// Puedo proceder
					Book selectedBook = bookList.getSelectedValue();
					dialog = new BookArchiveABMDialogWindow(window, dialogMode, selectedBook, books);
					dialog.setVisible(true);

					if (dialog.getModalResult() == ModalResult.MR_OK) {
						// debo borrar el registro y borrar la imagen.
						CustomFileUtils.deleteFileFromStorage(selectedBook.getCoverImage());
						selectedBook.setCoverImage("");
						removeBookFromList(selectedBook);
						removeBookFromVector(selectedBook);
						saveBooksToFile(bookFile);
					}
				}			
				break;
			case ADM_VIEW:
				if (bookList.getSelectedIndex() >= 0) {
					// Puedo proceder
					Book selectedBook = bookList.getSelectedValue();
					dialog = new BookArchiveABMDialogWindow(window, dialogMode, selectedBook, books);
					dialog.setVisible(true);			
				}			
				break;
		}
	}
	
	private void loadBooksFromFile(String path) {
        Book  book;
        String[] fields;
		
        try {
            Scanner in = new Scanner(new FileReader(path));
            while (in.hasNextLine()) {
            	fields = in.nextLine().split("\t");
                book = new Book();
                book.setISBN(fields[0]);
                book.setTitulo(fields[1]);
                book.setAutor(fields[2]);
                book.setEditorial(fields[3]);
                book.setEdicion(Integer.parseInt(fields[4]));
                book.setAnno_de_publicacion(Integer.parseInt(fields[5]));
                
                // Compatibilidad con listas viejas de libros.
                if (fields.length > 6) {
                	
                	// ver por que pincha el setCoverImage
                	book.setCoverImage(fields[6]);
                }
                
                books.add(book);
            }
            in.close();
        } catch (FileNotFoundException e) {
        	// do nothing.
        }
	}
	
	private void saveBooksToFile(String path) {
        int i, n;
        
		// Actualizo la db
        try {
            PrintStream salida = new PrintStream(path);
            n = books.size();
            for (i=0; i<n; i++)
                imprimirEnArchivo.funcion(books.get(i), salida);
            salida.close();
        } catch (FileNotFoundException e) {
        	// do nothing.
        }		
	}
	
	// Ambas listas mantienen la misma referencia a los libros.
	private void addBookToList(Book book) {
		// lo agrego a la lista visible.
		DefaultListModel<Book> listModel = (DefaultListModel<Book>) bookList.getModel();
		listModel.addElement(book);
	}
	
	private void removeBookFromList(Book book) {
		DefaultListModel<Book> listModel = (DefaultListModel<Book>) bookList.getModel();
		if (listModel.indexOf(book) >= 0) {
			listModel.removeElement(book);
		}
	}
	
	private void addBookToVector(Book book) {
		// luego lo agrego al vector.
		books.add(book);
	}
	
	private void removeBookFromVector(Book book) {
		if (books.indexOf(book) >= 0) {
			books.remove(book);
		}
	}
	
	private void doSerchBooks(String searchText) {
		
		DefaultListModel<Book> listModel = (DefaultListModel<Book>) bookList.getModel();
		listModel.clear();
		
		for (Book book : books) {
			if (book.getISBN().toUpperCase().contains(searchText.toUpperCase()) ||
				book.getTitulo().toUpperCase().contains(searchText.toUpperCase()) || 
				book.getAutor().toUpperCase().contains(searchText.toUpperCase()) ||
				book.getEditorial().toUpperCase().contains(searchText.toUpperCase()) ||
				String.valueOf(book.getAnno_de_publicacion()).toUpperCase() == searchText.toUpperCase()){
				
				listModel.addElement(book);
			}
		}
	}
}