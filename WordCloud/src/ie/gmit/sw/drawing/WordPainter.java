package ie.gmit.sw.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * @author Vytas
 *
 */
public class WordPainter implements Paintable 
{
	
	
	
	/* (non-Javadoc)
	 * @see ie.gmit.sw.drawing.Paintable#Painter(java.util.Map)
	 */
	@Override
	public void Painter(Map<String, Integer> sortedMap) throws IOException
	{
		 BufferedImage image = new BufferedImage(900, 900, BufferedImage.TYPE_4BYTE_ABGR);
			
		 Graphics2D  graphics = (Graphics2D) image.getGraphics();
		 
		
		 int x = 500;
		 int y = 30;
		 int fontsize;
		 int anngle = 0;
		 int yCordinates = 0;
		 int xCordiantes = 500;
		 int fontSize = 30;
		 int r = 0;
		 int g = 0;
		 int b = 0;
		 
		 
		//GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsEnvironment fontstyle = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String[] fontNames = fontstyle.getAvailableFontFamilyNames();
		//String fontName = fontNames[random];
		
		
		 
		 for (Entry<String, Integer> entry : sortedMap.entrySet()) 
		 {
			 	
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			   
			    if(value > 3)
			    {
			    	if(fontSize == 1)
			    	{
			    		fontSize = 2;
			    	}
			    	
			    	fontsize = 20;
			    	
			    	
			    	int random = (int)(Math.random() * (20 - 1));
			    	Font font = new Font(fontNames[random], Font.BOLD, fontSize--);
			    	//System.out.println(entry.getValue());
			    	//entry.getValue()+8
			    	
			    	FontMetrics metrics = graphics.getFontMetrics(font);
			    
			    	r = (int)(Math.random() * (200 - 1));
			    	g = (int)(Math.random() * (200 - 1));
			    	b = (int)(Math.random() * (200 - 1));
			    	graphics.setColor(Color.getHSBColor(r, b, b));
			    	graphics.setFont(font);
			    	
			    	
			    	drawRotate(graphics, x , y + metrics.getHeight(), anngle, key);
			    	anngle = anngle + 90;
			    	
			    
			    	if(anngle > 91)
			    	{
			    		y = y + metrics.stringWidth(key)+metrics.getHeight();
			    		anngle = 0;
			    		
			    	}
			    	else
			    	{
			    		x = x + metrics.stringWidth(key);
			    		
			    	}
		
			    	if(x > 800)
			    	{
			    		yCordinates = yCordinates+30;
			    		y = yCordinates;
			    		xCordiantes = xCordiantes - 100;
			    		x = xCordiantes;
			    		
			    	}
			    	else if(y > 800)
			    	{
			    		yCordinates = yCordinates+30 ;
			    		y = yCordinates;
			    		xCordiantes = xCordiantes - 100;
			    		x = xCordiantes;
			    	}
			    	
			    }
		}
		 
		 
		 graphics.dispose();
		 
		 ImageIO.write(image, "png", new File("WordCloud.png"));
		 
		 
		 
		 //System.out.println("Painter"+sortedMap);
		 System.out.println("END");
		 
		
	}
	
	/**
	 * @param g2d
	 * @param x
	 * @param y
	 * @param angle
	 * @param text
	 */
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) 
	{    
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	} 
	
	

}
