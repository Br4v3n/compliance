package compliance;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class deleteProg extends JFrame {

	private static final long serialVersionUID = 1L;
	
	FileOutputStream fos = null;
	 File f = new File("C:\\Users\\Administrador\\Desktop\\testCMD.txt");
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteProg frame = new deleteProg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public deleteProg() {
		setResizable(false);
	
		  try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e3) {
		
			e3.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 586, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("delete");
		btnNewButton.setBounds(10, 28, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("leer");
		btnNewButton_1.setBounds(162, 28, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("check");
		btnNewButton_2.setBounds(311, 28, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 560, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 547, 247);
		
		//JScrollPane scroll = new JScrollPane(textArea);
		panel.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 547, 247);
		panel.add(scrollPane);
		
	//	textArea.setEditable ( false ); 

		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			
  public void actionPerformed(ActionEvent e) {
	  try {
			String cmd = "wmic product where name=\"OpenSCAP\" call uninstall"; 
			Runtime.getRuntime().exec(cmd); 
		} catch (IOException ioe) {
			System.out.println (ioe);
		}
	  System.out.println("borrado correctamente");
  }	
});
		btnNewButton_2.addActionListener(new ActionListener() {
			
			  public void actionPerformed(ActionEvent e) {
				 String check="check app";
				 
				 log(check);
				  				  
				  String str = "wmic product get name,vendor,version"; 
				    Runtime rt = Runtime.getRuntime();
				    
				    try{

				        Process p = rt.exec(str);
				        InputStream is =p.getInputStream();
				      
				        InputStreamReader in = new InputStreamReader(is);

				        StringBuffer sb = new StringBuffer();
				        BufferedReader buff = new BufferedReader(in);
				        String line = buff.readLine();
				        //System.out.println(line);
				        while( line != null )
				        {
				            sb.append(line + "\n");
				      //  System.out.println(line);
				            line = buff.readLine();
				        }
				        System.out.println( sb);
				        
				        textArea.setText(sb.toString());
				       
				        if ( sb.length() != 0 ){
				           
				            fos.write(sb.toString().getBytes());

				            
				        }
				        
				        try {
							fos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }catch( Exception ex )
				    {
				        ex.printStackTrace();
				    }
					
						  
			  }
			  });
	
		btnNewButton_1.addActionListener(new ActionListener() {
			
			  public void actionPerformed(ActionEvent e) {
				  File archivo = null;
			      FileReader fr = null;
			      BufferedReader br = null;
	      try {


	         archivo = new File ("C:\\Users\\Administrador\\Desktop\\testCMD.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);
	      }
	      catch(Exception e1){
	         e1.printStackTrace();
	      }finally{
	     
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
			  }	
		});
		
		
	}
	
	public void log (String string) {
		
		   try {
				fos.write(string.toString().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("imprimir log");
	}
	
	 public void cerrar(){
		
		 
		 addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent e) {
				   Object [] opciones ={"Aceptar","Cancelar"};
					 int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea realizar cerrar la aplicacion","Mensaje de Confirmacion",
					 JOptionPane.YES_NO_OPTION,
					 JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
					 if (eleccion == JOptionPane.YES_OPTION){
						 try {
							fos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					 System.exit(0);
					 }else{
					 }
				
			   }
			 });
		
	
}
}
