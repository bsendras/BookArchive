package unlam.analisisdesoftware.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import javax.swing.FocusManager;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class CustomPasswordField extends JPasswordField{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6764378467990873323L;

	private String placeHolder;
	private boolean placeHolderBold;

	public CustomPasswordField(String placeHolder) {
		super();
		this.placeHolder = placeHolder;
		this.placeHolderBold = false;
	}


	@Override public void setBorder(Border border) {
	    // No!
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) {
	    super.paintComponent(g);
	
	    
	    if((getPassword().length == 0) && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
	    	
	    	// Get the FontMetrics
	        FontMetrics metrics = g.getFontMetrics(this.getFont());
	        // Determine the X coordinate for the text
	        int x = 5; // (getBounds().width - metrics.stringWidth(placeHolder)) / 2;
	        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	        int y = ((getBounds().height - metrics.getHeight()) / 2) + metrics.getAscent();
	        // Set the font
	        
	    	Graphics2D g2 = (Graphics2D)g.create();
	        g2.setColor(new Color(215, 215, 215));
	        if (placeHolderBold) {
	        	g2.setFont(new Font(getFont().getFamily(), Font.BOLD, getFont().getSize()));
	        } else {
	        	g2.setFont(new Font(getFont().getFamily(), Font.PLAIN, getFont().getSize()));
	        }
	        g2.drawString(placeHolder, x, y);
	        g2.dispose();
	   }
	    
	    if(!isEditable()) {
	    	setBackground(new Color(255,255,255));
	    }
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public boolean isPlaceHolderBold() {
		return placeHolderBold;
	}

	public void setPlaceHolderBold(boolean placeHolderBold) {
		this.placeHolderBold = placeHolderBold;
	}
}
