package ie.gmit.sw.testCases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ie.gmit.sw.inputoutput.Htmlreader;

public class HtmlreaderTest 
{
	Htmlreader hr = Htmlreader.getInstance();
	
	
	@Before
	public void setUp() throws Exception 
	{
	}

	@Test
	public void test() 
	{
		String data = hr.Connect("https://en.wikipedia.org/wiki/Main_Page");
		assertTrue(data.length()>1);

	}
	public void test1() 
	{
		hr.setWebPageName("wiki");
		assertTrue(hr.getWebPageName().equals("wiki"));

	}

}
