package ie.gmit.sw.TestCases;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ie.gmit.sw.inputoutput.DataParser;

public class DataParserTest 
{

	private Map<String, Integer> map = new HashMap<String, Integer>(); 
	@Before
	public void setUp() throws Exception 
	{
	}

	@Test
	public void test() throws FileNotFoundException 
	{
		String textToParse = "Words words clouds clouds clouds";
		DataParser parsetxt = new DataParser();
		map = parsetxt.datacleaner(textToParse);
		assertTrue(map.equals(map));
		assertTrue(map.equals(map));
	}

}
