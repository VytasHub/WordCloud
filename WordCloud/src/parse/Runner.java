package parse;

import org.jsoup.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;


public class Runner 
{
	private static Map<String, Integer> map = new HashMap<String, Integer>(); 
	private String[] stopwords = new String[300];
	
	
	public String Parser()
	{
		String newsHeadlines = "";
		try 
		{
			Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
			newsHeadlines = doc.body().text();
			//System.out.println(newsHeadlines);
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		return newsHeadlines;
	}
	
	
	
	private void maping() throws Exception
	{
		String file = "stopwords.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));  
		String line = null;  

		try 
		{
			int i = 0;
			while ((line = br.readLine()) != null)  
			{  
				stopwords[i] = line;
				//System.out.println(stopwords[i]);
				i++;
			}
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	private void cleaning(String text) throws FileNotFoundException
	{
		
		String[] words = text.split("[^a-zA-Z']+");
		int j;
		int count =0;
		boolean goodword = true;
		
		for (String t : words)
		{
			
			//System.out.println(t);	
			for(j = 0; j < 300; j++)
			{
				if(t.equalsIgnoreCase(stopwords[j]))
				{
					goodword = false;
				}
				
			}
			
			if(goodword)
			{
				if(t.length() > 1)
					{
						int frequency = 0;
						
						if(map.containsKey(t))
						{
							frequency = map.get(t);
						}
			
						frequency++;
						map.put(t, frequency);
					}
			}
			//System.out.println(t);
			goodword = true;
			  
		}
		//return map;
		//System.out.println(map);
	}
	
	
	
	
	
	
	

	public static void main(String[] args) throws IOException 
 	{
		
	//Map<String, Integer> parsedWord = new HashMap<String, Integer>(); 
	
	
		String parsedString;
		Runner wrdle = new Runner();
		parsedString = wrdle.Parser();
		//System.out.println(parsedString);
		System.out.println("=================");
		////////////////////////////////////////////////
		
		try 
		{
			wrdle.maping();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		/////////////////////////////////////////////////
		wrdle.cleaning(parsedString);
		//clean.cleaning();
		/////////////////////////////////////////////////
		 
		 BufferedImage image = new BufferedImage(1200, 500, BufferedImage.TYPE_4BYTE_ABGR);
		
		 Graphics2D  graphics = (Graphics2D) image.getGraphics();
		 
		
		 int x = 300;
		 int y = 0;
		 int fontsize;
		 int hgtY = 0; 
		 int widtX = 0;
		 int anngle = 0;
		 
		 for (Entry<String, Integer> entry : map.entrySet()) 
		 {
			 
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    //System.out.println(key + " " + value );
			    if(value > 1)
			    {
			    	
			    	
			    	fontsize = 20;
			    	fontsize = fontsize + entry.getValue();
			    	Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontsize);
			    	
			    	
			    	
			    	
			    	
			    	FontMetrics metrics = graphics.getFontMetrics(font);
			    	System.out.println(metrics.getHeight() + " " + metrics.stringWidth(key));
			    	metrics.getLeading();
			    	//metrics.getHeight();
			    	//metrics.stringWidth(str)
			    	
			    	
			    	graphics.setColor(Color.orange);
			    	graphics.setFont(font);
			    	
			    	//graphics.drawString(key, x, y);
			    	drawRotate(graphics, x, y, anngle, key);
			    	anngle = anngle + 90;
			    	x = x + metrics.stringWidth(key+1);
			    	
			    	
			    	if(anngle == 360)
			    	{
			    		anngle = 0;
			    	}
			    	
			    	//System.out.println(x+" "+y);
			    	//System.out.println(key + " " + key.length() );
			    	
			    	
			    	if(x > 500)
			    	{
			    		y = y + 20;
			    		x = 20;
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


