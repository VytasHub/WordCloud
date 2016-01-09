package ie.gmit.sw.runner;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ie.gmit.drawing.WordPainter;
import ie.gmit.sw.inputoutput.*;




public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		String html;
		String url = "https://en.wikipedia.org/wiki/Main_Page";
		
		
		
		Htmlreader reader = new Htmlreader();
		html = reader.Connect(url);
		//"https://en.wikipedia.org/wiki/Main_Page"
		//http://www.tutorialspoint.com/java/java_methods.htm
		DataParser parse = new DataParser();
		map = parse.datacleaner(html);
		
		Sorter sort = new Sorter();
		map = sort.sortByFrequency(map,false);
		
		WordPainter paint = new WordPainter();
		paint.Painter(map);
		
	}
	
	

}
