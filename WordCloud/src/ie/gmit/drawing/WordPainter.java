package ie.gmit.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class WordPainter 
{
	
	
	
	public static void Painter(Map<String, Integer> sortedMap) throws IOException
	{
		 BufferedImage image = new BufferedImage(700, 700, BufferedImage.TYPE_4BYTE_ABGR);
			
		 Graphics2D  graphics = (Graphics2D) image.getGraphics();
		 
		
		 int x = 0;
		 int y = 50;
		 int fontsize;
		 int hgtY = 0; 
		 int widtX = 0;
		 int anngle = 0;
		 

		 
		 for (Entry<String, Integer> entry : sortedMap.entrySet()) 
		 {
			 	
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			   
			    if(value > 1)
			    {
			    	
			    	fontsize = 20;
			    	Font font = new Font(Font.SANS_SERIF, Font.BOLD, entry.getValue()+8);
			    	System.out.println(entry.getValue());
			    	
			    	FontMetrics metrics = graphics.getFontMetrics(font);
			    
			    	
			    	graphics.setColor(Color.blue);
			    	graphics.setFont(font);
			    	
			    	
			    	drawRotate(graphics, x, y + metrics.getHeight(), anngle, key);
			    	anngle = anngle + 90;
			    	
			    
			    	if(anngle > 91)
			    	{
			    		y = y + metrics.stringWidth(key);
			    		anngle = 0;
			    		
			    	}
			    	else
			    	{
			    		x = x + metrics.stringWidth(key);
			    	}
		
			    	if(x > 600)
			    	{
			    		y = 100;
			    		x = 0;
			    		
			    	}
			    	
			    }
		}
		 
		 
		 graphics.dispose();
		 ImageIO.write(image, "png", new File("image.png"));
		 
		 
		 System.out.println("Painter"+sortedMap);
		 System.out.println("END");
		
	}
	
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) 
	{    
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	} 

}
