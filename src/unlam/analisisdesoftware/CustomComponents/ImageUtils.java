package unlam.analisisdesoftware.CustomComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageUtils {
	
	// Dibujo una imajen en un rectangulo especifico.
	static public Image stretchImage(Image image, int width, int height, Color overColor)
	{
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = resizedImage.createGraphics();
		
		//Modos de renderizado. 
		HashMap<Key, Object> hints = new HashMap<Key, Object>();
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphic.setRenderingHints(hints);
		
		graphic.clearRect(0, 0, width, height);
		graphic.drawImage(image, 0, 0, width, height, null);
		
		if (overColor != null) {
			graphic.setColor(overColor);
			graphic.fillRect(0, 0, width, height);			
		}

		return resizedImage;
	}
}
