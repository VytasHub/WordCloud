package ie.gmit.drawing;

import org.jsoup.*;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

		LinkedList<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	
	
	
	
	
	
	

	public static void main(String[] args) throws IOException 
 	{
		
	Map<String, Integer> sortedMap = new HashMap<String, Integer>(); 
	
	
		String parsedString;
		Runner wordle = new Runner();
		parsedString = wordle.Parser();
		//System.out.println(parsedString);
		System.out.println("=================");
		////////////////////////////////////////////////
		
		try 
		{
			wordle.maping();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		/////////////////////////////////////////////////
		wordle.cleaning(parsedString);
		//clean.cleaning();
		/////////////////////////////////////////////////
		sortedMap = sortByComparator(map,false);
		System.out.println(sortedMap);
		 
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


