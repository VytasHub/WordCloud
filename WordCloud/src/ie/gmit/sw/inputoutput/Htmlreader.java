package ie.gmit.sw.inputoutput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Htmlreader 
{
	//private static Map<String, Integer> map = new HashMap<String, Integer>(); 
	//private String[] stopwords = new String[300];
	private String webPageName;
	
	
	public Htmlreader() 
	{
		this.webPageName = webPageName;
	}

	public String Connect(String webPageName)
	{
		String htmldata = "";
		try 
		{
			Document doc = Jsoup.connect(webPageName).get();
			htmldata = doc.body().text();
			//System.out.println(newsHeadlines);
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		return htmldata;
	}
	
	public String getWebPageName() 
	{
		return webPageName;
	}


	public void setWebPageName(String webPageName) 
	{
		this.webPageName = webPageName;
	}

	
	
	

}
