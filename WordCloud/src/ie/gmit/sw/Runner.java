package ie.gmit.sw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import org.apache.commons.validator.routines.*;

import ie.gmit.sw.drawing.Paintable;
import ie.gmit.sw.drawing.WordPainter;
import ie.gmit.sw.file.OpenFile;
import ie.gmit.sw.inputoutput.DataParser;
import ie.gmit.sw.inputoutput.Htmlreader;
import ie.gmit.sw.inputoutput.Sorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Vytas
 *
 */
public class Runner
{

	private JFrame frmWordCloud;
	private JTextField textField;
	private Map<String, Integer> map = new HashMap<String, Integer>(); 
	
	private String html;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Runner window = new Runner();
					window.frmWordCloud.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Runner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmWordCloud = new JFrame();
		frmWordCloud.setTitle("Word Cloud");
		frmWordCloud.setBounds(100, 100, 722, 282);
		frmWordCloud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWordCloud.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Visualize");
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Britannic Bold", Font.ITALIC, 18));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				System.out.println("Before Button" + map);
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Text Field is Empty");
				}
				else
				{
					//textField.getText().equals(urlChecker);
					UrlValidator defaultValidator = new UrlValidator(); // default schemes
					if (defaultValidator.isValid(textField.getText())) {
					    System.out.println("valid");
						
						
						Htmlreader hr = Htmlreader.getInstance();
						html = null;
						System.out.println("Html " + html);
						html = hr.Connect(textField.getText());
						//"https://en.wikipedia.org/wiki/Main_Page"
						//http://www.tutorialspoint.com/java/java_methods.htm
						DataParser parseurl = new DataParser();
						try {
							
							//System.out.println("URL before Parse" + map);
							
							map = parseurl.datacleaner(html);
							//parseurl.setMap(null);
							//System.out.println("URL afther Parse" + map);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Sorter sorturl = new Sorter();
						//System.out.println("URL before sort" + map);
						map = sorturl.sortByFrequency(map,false);
						//System.out.println("URL afther sort" + map);
						
						Paintable painturl = new WordPainter();
						try 
						{
							//System.out.println("URL before paint" + map);
							painturl.Painter(map);
							map = null;
							System.out.println("URL afther paint:" + map);
							JOptionPane.showMessageDialog(null, "Word Cloud PNG complete");
						} catch (IOException e) 
						{
							e.printStackTrace();
						}
					    
					    
					    html = null;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Inavalid URL");
					}
					
				}
			}
		});
		btnNewButton.setBounds(267, 79, 135, 45);
		frmWordCloud.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		
		
		textField.setBounds(10, 36, 651, 33);
		frmWordCloud.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Choice 1. Enter Url in text box below and press Vizuliaze");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, -1, 662, 26);
		frmWordCloud.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choice 2. Pick file txt file");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 138, 184, 14);
		frmWordCloud.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Pick File");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Britannic Bold", Font.ITALIC, 18));
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Before Button" + map);
				
				OpenFile file = new OpenFile();
				
				try 
				{
					file.PickFile();
				} catch (Exception e) 
				{
				
					e.printStackTrace();
				}
				
				//textField_1.setText(file.sb.toString());
				
				DataParser parsetxt = new DataParser();
				try 
				{
					
					//System.out.println("Txt before parse" + map);
					map = parsetxt.datacleaner(file.sb.toString());
					//System.out.println("Txt afther parse" + map);
				} catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Sorter sorttxt = new Sorter();
				//System.out.println("Txt before sort" + map);
				map = sorttxt.sortByFrequency(map,false);
				//System.out.println("Txt afther sort" + map);
				
				Paintable painttxt = new WordPainter();
				try 
				{
					//System.out.println("Txt" + map);
					//System.out.println("Txt before paint" + map);
					painttxt.Painter(map);
					map = null;
					System.out.println("Txt afther paint" + map);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				file.sb = null;
				
			}
		});
		btnNewButton_1.setBounds(268, 166, 134, 45);
		frmWordCloud.getContentPane().add(btnNewButton_1);
	}
}
