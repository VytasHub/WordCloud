package ie.gmit.sw.file;

import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Vytas
 *
 */
public class OpenFile implements Filepicker 
{
	JFileChooser filePicker = new JFileChooser();
	public StringBuilder sb = new StringBuilder();
	
	/* (non-Javadoc)
	 * @see gmit.ie.sw.file.Filepicker#PickFile()
	 */
	/* (non-Javadoc)
	 * @see ie.gmit.sw.file.Filepicker#PickFile()
	 */
	@Override
	public void PickFile() throws Exception
	{
		if(filePicker.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			java.io.File file = filePicker.getSelectedFile();
			
			Scanner input = new Scanner(file);
			
			while(input.hasNext())
			{
				sb.append(input.nextLine());
				sb.append("\n");
			}
			input.close();
			JOptionPane.showMessageDialog(null, "Word Cloud PNG complete");
		}
		else
		{
			sb.append("No File Selected");
		}
	}

	
	
	

}
