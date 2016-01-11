package ie.gmit.sw.inputoutput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



/**
 * @author Vytas
 *
 */
public class Htmlreader implements InputOutput 
{
	private static Htmlreader hr = new Htmlreader();
	private String webPageName;
	
	/**
	 * @return
	 */
	public static Htmlreader getInstance() 
	{
		return hr;
	}
	
	/**
	 * 
	 */
	public Htmlreader() 
	{
		this.webPageName = webPageName;
	}

	/* (non-Javadoc)
	 * @see ie.gmit.sw.inputoutput.InputOutput#Connect(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see ie.gmit.sw.inputoutput.InputOutput#Connect(java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see ie.gmit.sw.inputoutput.InputOutput#getWebPageName()
	 */
	/* (non-Javadoc)
	 * @see ie.gmit.sw.inputoutput.InputOutput#getWebPageName()
	 */
	@Override
	public String getWebPageName() 
	{
		return webPageName;
	}


	/* (non-Javadoc)
	 * @see ie.gmit.sw.inputoutput.InputOutput#setWebPageName(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see ie.gmit.sw.inputoutput.InputOutput#setWebPageName(java.lang.String)
	 */
	@Override
	public void setWebPageName(String webPageName) 
	{
		this.webPageName = webPageName;
	}

	

	
	
	

}
