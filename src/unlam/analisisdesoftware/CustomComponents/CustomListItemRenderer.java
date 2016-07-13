package unlam.analisisdesoftware.CustomComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import unlam.analisisdesoftware.BookArchiveClasses.Book;

public class CustomListItemRenderer extends JLabel implements ListCellRenderer<Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5401335611814238704L;
	/**
	 * 
	 */
	
	private String defaultIconPath;

	public CustomListItemRenderer(String defaultIconPth) {
		super();
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        this.defaultIconPath = defaultIconPth;
    }
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		
		// Ahora tengo toda la informacion del
		// libro disponible en el renderer.
		ImageIcon icon;
		Book book = (Book) value;
		String ImagePath = book.getCoverImage();
		
		File file = new File(ImagePath);
		if (!ImagePath.equals("") && file.exists() && !file.isDirectory()) {
			icon= new ImageIcon(ImagePath);
		} else {
			icon = new ImageIcon(defaultIconPath);			
		}
		
		setIcon(icon);
		
		setPreferredSize(new Dimension(150, 230));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		if (isSelected) {
			icon.setImage(ImageUtils.stretchImage(icon.getImage(), 130, 195, new Color(255, 255, 255, 100)));
			setForeground(new Color(0, 170, 255));
		} else {
			icon.setImage(ImageUtils.stretchImage(icon.getImage(), 130, 195, null));
			setForeground(new Color(120, 120, 120));
		}	
		
		setText(book.getTitulo());
		return this;
	}
}

