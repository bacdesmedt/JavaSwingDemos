package desmedt.bac.swing.colors;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ColourView extends JComponent {
    int size;
    BufferedImage backgroundImage, colourImage;
    
    public ColourView(int size, Color initialColor) {
        this.size = size;
        updateColor(initialColor, false);
        URL resource = this.getClass().getResource("background.jpg");
        try {
            assert resource != null;
            backgroundImage = ImageIO.read(new File(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        setOpaque(true);
        setPreferredSize(new Dimension(size, size));
        setAlignmentY(CENTER_ALIGNMENT);
        setAlignmentX(CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null)
            g.drawImage(backgroundImage, 0,0,size,size,this);
        if(colourImage != null)
            g.drawImage(colourImage, 0,0,size,size,this);
    }
    
    public void updateColor(Color color) {
        updateColor(color, true);
    }
    
    private void updateColor(Color color, boolean repaint) {
        colourImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = colourImage.createGraphics();
        graphics.setPaint (color );
        graphics.fillRect ( 0, 0, colourImage.getWidth(), colourImage.getHeight() );
        if (repaint) repaint();
        graphics.dispose();
    }
    
}