package unlam.analisisdesoftware.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import javax.swing.FocusManager;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CustomTextField extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010216738422175397L;
	
	private String placeHolder;
	private boolean placeHolderBold;
	private boolean onlyDigits;
	private boolean upperCase;
	private boolean password;

	public CustomTextField(String placeHolder) {
		super();
		this.placeHolder = placeHolder;
		this.placeHolderBold = false;
		this.onlyDigits = false;
		this.upperCase = false;
		this.password = false;
	}


	@Override public void setBorder(Border border) {
	    // No!
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) {
	    super.paintComponent(g);
	
	    if(getText().trim().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
	    	
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
	
	public boolean isOnlyDigits() {
		return onlyDigits;
	}

	public void setOnlyDigits(boolean onlyDigits) {
		this.onlyDigits = onlyDigits;
		((AbstractDocument)getDocument()).setDocumentFilter(
                new onlyDigitsDocumentFilter());
	}
	
	public boolean isUpperCase() {
		return upperCase;
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
		((AbstractDocument)getDocument()).setDocumentFilter(
                new upperCaseDocumentFilter());
	}
	
	public boolean getPassword() {
		return password;
	}


	public void setPassword(boolean password) {
		this.password = password;
		((AbstractDocument)getDocument()).setDocumentFilter(
                new passwordDocumentFilter());
	}
}

class onlyDigitsDocumentFilter extends DocumentFilter {

	@Override
	public void insertString(FilterBypass fb, int off
	                    , String str, AttributeSet attr) 
	                            throws BadLocationException 
	{
	    // remove non-digits
	    fb.insertString(off, str.replaceAll("\\D++", ""), attr);
	} 
	@Override
	public void replace(FilterBypass fb, int off
	        , int len, String str, AttributeSet attr) 
	                        throws BadLocationException 
	{
	    // remove non-digits
	    fb.replace(off, len, str.replaceAll("\\D++", ""), attr);
	}
}

class upperCaseDocumentFilter extends DocumentFilter {

	@Override
	public void insertString(FilterBypass fb, int off
	                    , String str, AttributeSet attr) 
	                            throws BadLocationException 
	{
	    // replace to uppercase
	    fb.insertString(off, str.toUpperCase(), attr);
	} 
	@Override
	public void replace(FilterBypass fb, int off
	        , int len, String str, AttributeSet attr) 
	                        throws BadLocationException 
	{
		// replace to uppercase
	    fb.replace(off, len, str.toUpperCase(), attr);
	}
}

class passwordDocumentFilter extends DocumentFilter {

	@Override
	public void insertString(FilterBypass fb, int off
	                    , String str, AttributeSet attr) 
	                            throws BadLocationException 
	{
	    // replace to uppercase
	    //fb.insertString(off, str.replaceAll(".", "*"), attr);
	} 
	@Override
	public void replace(FilterBypass fb, int off
	        , int len, String str, AttributeSet attr) 
	                        throws BadLocationException 
	{
		// replace to uppercase
	    fb.replace(off, len, str.replaceAll(".", "*"), attr);
	}
}