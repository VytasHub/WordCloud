package ie.gmit.sw.inputoutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataParser
{
	
	private static String file = "stopwords.txt";
	
	private static Map<String, Integer> map = new HashMap<String, Integer>(); 
	
	public static String getFile() 
	{
		return file;
	}



	public static void setFile(String file) 
	{
		DataParser.file = file;
	}




	public static Map<String, Integer> datacleaner(String data) throws FileNotFoundException
	{
		
		
		String[] words = data.split("[^a-zA-Z']+");
		int j;
		int count =0;
		boolean goodword = true;
		
		BufferedReader br = new BufferedReader(new FileReader(file));  
		String line = null;
		
		String[] stopwords = new String[300];

		try 
		{
			int i = 0;
			while ((line = br.readLine()) != null)  
			{  
				stopwords[i] = line;
				
				i++;
			}
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		for (String t : words)
		{
			
			
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
		System.out.println("Data Parser"+map);
		return map;
		
	}
	

}
