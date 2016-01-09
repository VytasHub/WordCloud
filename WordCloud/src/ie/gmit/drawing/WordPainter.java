package ie.gmit.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class WordPainter 
{
	
	
	
	public static void Painter(Map<String, Integer> sortedMap) throws IOException
	{
		 BufferedImage image = new BufferedImage(800, 500, BufferedImage.TYPE_4BYTE_ABGR);
			
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
			    //System.out.println(key + " " + value );
			    if(value > 1)
			    {
			    	
			    	
			    	fontsize = 20;
			    	//fontsize = fontsize + entry.getValue();
			    	Font font = new Font(Font.SANS_SERIF, Font.BOLD, entry.getValue()+8);
			    	
			    	
			    	
			    	
			    	
			    	FontMetrics metrics = graphics.getFontMetrics(font);
			    	//System.out.println(metrics.getHeight() + " " + metrics.stringWidth(key));
			    	//metrics.getLeading();
			    	//metrics.getHeight();
			    	//metrics.stringWidth(str)
			    	
			    	
			    	graphics.setColor(Color.blue);
			    	graphics.setFont(font);
			    	
			    	//graphics.drawString(key, x, y);
			    	drawRotate(graphics, x, y + metrics.getHeight(), anngle, key);
			    	anngle = anngle + 90;
			    	x = x + metrics.stringWidth(key);
			    	//y = y + (metrics.getHeight()/4);
			    	
			    	
			    	if(anngle > 91)
			    	{
			    		//x = x + metrics.getHeight();
			    		y = y + (x + metrics.stringWidth(key));
			    		anngle = 0;
			    	}
			    	
			    	
			    	
			    	//System.out.println(x+" "+y);
			    	//System.out.println(key + " " + key.length() );
			    	
//			    	
			    	if(x > 700)
			    	{
			    		y = y+metrics.getHeight();
			    		x = 0;
			    		//System.out.println("Entered");
			    	}
			    	
			    }
				
			    //////////////////////////L////H///
                //////////////////////////X////Y///
			  
		}
		 
		 
//		 font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
//		 graphics.setFont(font);
//		 graphics.setColor(Color.yellow);
//		 graphics.drawString("Software Development", 10, 150);
//
//		 font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
//		 graphics.setFont(font);
//		 graphics.setColor(Color.blue);
//		 graphics.drawString("2012 Assignment", 40, 180);

		 graphics.dispose();
		 ImageIO.write(image, "png", new File("image.png"));
		 
		 
		 
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
