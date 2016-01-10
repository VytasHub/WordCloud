package ie.gmit.sw.TestCases;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;



import ie.gmit.sw.inputoutput.Sorter;

public class SorterTest 
{
	private Map<String, Integer> map = new HashMap<String, Integer>(); 
	
	public void setUp() throws Exception 
	{
		
	}

	public void test() 
	{
		Sorter sorturl = new Sorter();
		//System.out.println("URL before sort" + map);
		map = sorturl.sortByFrequency(map,false);
	}

}
