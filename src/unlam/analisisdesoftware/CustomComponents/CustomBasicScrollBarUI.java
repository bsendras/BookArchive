package unlam.analisisdesoftware.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomBasicScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // your code
    	g.setColor(new Color(229, 229, 229));
    	g.drawRoundRect(trackBounds.x, trackBounds.y, trackBounds.width/2, trackBounds.height, 20, 15);
    	g.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width/2, trackBounds.height, 20, 15);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        // your code
    	g.setColor(new Color(67, 67, 67));
    	g.drawRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width/2, thumbBounds.height, 20, 15);
    	g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width/2, thumbBounds.height, 20, 15);
        g.dispose();
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override    
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }
}
