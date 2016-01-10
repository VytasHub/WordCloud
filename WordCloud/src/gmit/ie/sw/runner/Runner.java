package gmit.ie.sw.runner;

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

import gmit.ie.sw.file.OpenFile;
import ie.gmit.drawing.WordPainter;
import ie.gmit.sw.inputoutput.DataParser;
import ie.gmit.sw.inputoutput.Htmlreader;
import ie.gmit.sw.inputoutput.Sorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Runner {

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
						
						Htmlreader reader = new Htmlreader();
						
						html = reader.Connect(textField.getText());
						//"https://en.wikipedia.org/wiki/Main_Page"
						//http://www.tutorialspoint.com/java/java_methods.htm
						DataParser parseurl = new DataParser();
						try {
							map = null;
							map = parseurl.datacleaner(html);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Sorter sorturl = new Sorter();
						map = sorturl.sortByFrequency(map,false);
						
						WordPainter painturl = new WordPainter();
						try {
							painturl.Painter(map);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    
					    JOptionPane.showMessageDialog(null, "Word Cloud PNG complete");
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
					map = null;
					map = parsetxt.datacleaner(file.sb.toString());
				} catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Sorter sorttxt = new Sorter();
				map = sorttxt.sortByFrequency(map,false);
				
				WordPainter painttxt = new WordPainter();
				try 
				{
					painttxt.Painter(map);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Word Cloud PNG complete");
				file.sb = null;
				
			}
		});
		btnNewButton_1.setBounds(268, 166, 134, 45);
		frmWordCloud.getContentPane().add(btnNewButton_1);
	}
}
