import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.util.*;
import java.awt.datatransfer.*;
import javax.swing.text.*;
import javax.swing.table.*;


class IDE extends JFrame implements ActionListener
{
	JPanel jMain, jCenter, jSouth,jWest;
	JButton newClass ,compile , run;
	JTextArea programArea , resultArea ;
	//JTabbedPane tabbedPane;
	Font fo1,fo2;
	String className ,str="",result="",fName="",result1="";
	Runtime rt;
	JPopupMenu menu;
	
	
	
	IDE(String s)
	{
		super(s);
		rt= Runtime.getRuntime();
		jMain = new JPanel(new BorderLayout());
		jCenter = new JPanel(new BorderLayout());
		jSouth = new JPanel(new BorderLayout());
		jWest = new JPanel();
		jWest.setLayout(new BoxLayout(jWest,BoxLayout.Y_AXIS));
		//tabbedPane = new JTabbedPane();
		jCenter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		
		newClass = new JButton ("New Class ");
		compile = new JButton("Compile      ");
		run = new JButton("Run              ");
		newClass.addActionListener(this);
		compile.addActionListener(this);
		run.addActionListener(this);
		
		newClass.setPreferredSize(new Dimension(120, 50));
		compile.setPreferredSize(new Dimension(120, 50));
		run.setPreferredSize(new Dimension(120, 50));
		run.setVisible(false);
		newClass.setAlignmentX(Component.CENTER_ALIGNMENT);
		compile.setAlignmentX(Component.CENTER_ALIGNMENT);
		run.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		programArea = new JTextArea();
		resultArea = new JTextArea(8,5);
		fo1 =new Font("Times New Roman",Font.BOLD,20);
		fo2 =new Font("Times New Roman",Font.BOLD,20);
		programArea.setFont(fo1);
		resultArea.setFont(fo2);
		
		menu = new JPopupMenu();
		
		Action cut = new DefaultEditorKit.CutAction();
        cut.putValue(Action.NAME, "Cut");
        cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        menu.add( cut );

        Action copy = new DefaultEditorKit.CopyAction();
        copy.putValue(Action.NAME, "Copy");
        copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        menu.add( copy );

        Action paste = new DefaultEditorKit.PasteAction();
        paste.putValue(Action.NAME, "Paste");
        paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        menu.add( paste );

       // Action selectAll = new SelectAll();
        //menu.add( selectAll );
		//programArea.setComponentPopupMenu( menu );
		
		TitledBorder border3 = new TitledBorder("Output");
		border3.setTitleJustification(TitledBorder.LEFT);
		border3.setTitlePosition(TitledBorder.TOP);
		
		jSouth.setBorder(border3);
		
		JScrollPane scrollPane = new JScrollPane(programArea); 
		JScrollPane scrollPane1 = new JScrollPane(resultArea); 
		resultArea.setEditable(false);
		programArea.setBackground(Color.white);
		resultArea.setBackground(Color.gray);
		
		
		jWest.add(Box.createRigidArea(new Dimension(0,20)));
		jWest.add(newClass);
		jWest.add(Box.createRigidArea(new Dimension(0,20)));
		jWest.add(compile);
		jWest.add(Box.createRigidArea(new Dimension(0,20)));
		jWest.add(run);
		
		jCenter.add(scrollPane,BorderLayout.CENTER);
		jCenter.setBackground(Color.blue);
		jSouth.add(scrollPane1,BorderLayout.CENTER);
		//jSouth.add(resultArea,FlowLayout.LEFT);		
		//jCenter.add(programArea,FlowLayout.LEFT);
		jMain.add(jCenter,BorderLayout.CENTER);
		jMain.add(jWest,BorderLayout.WEST);
		jMain.add(jSouth,BorderLayout.SOUTH);
		
		add(jMain);
		//menu.setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		//seSize()
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()== newClass)
		{
			run.setVisible(false);
			
			resultArea.setText("");
			try
			{
				className = JOptionPane.showInputDialog("Class Name");
				while(className.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Class name is needed","Error",JOptionPane.ERROR_MESSAGE);
					className = JOptionPane.showInputDialog("Class Name");
				}
			}catch(Exception e11){System.out.println(e11+"11");}
			//System.out.println(className);
			setDefaultProgram();
			
			
		}
		else if(ae.getSource()== compile)
		{
			if(!programArea.getText().equals(""))
			{
				try
				{
					resultArea.setText("");
					fName = className+".java";
					//new File(className).mkdir();
					//FileWriter fw = new FileWriter(new File(className, fName));
					FileWriter fw = new FileWriter(fName);
					String s1= programArea.getText();
					PrintWriter pw = new PrintWriter(fw);
					pw.println(s1);
					pw.flush();
					//System.out.println("half"+fName);
					
					Process error = rt.exec("javac  "+fName);
					System.out.println("half.5");
					BufferedReader err = new BufferedReader(new InputStreamReader(error.getErrorStream()));
					System.out.println("half1");
					
					//setOutput(err);
					while(true)
					{
						String temp = err.readLine();
						if(temp!= null)
						{
							result += temp;
							result +="\n";
							System.out.println("half2");
						}
						else break;
					}	
					if(result.equals(""))
					{
						resultArea.setText("Compilation Successful : "+ fName );
						System.out.println("half3");
						err.close();
						run.setVisible(true);
					}
					else
					{
							resultArea.setText(result);
							System.out.println("half4");
							result="";
					}
					
				}catch(Exception e12){System.out.println(e12+"12");}
				
			}
			else
			{
				try
				{
					JOptionPane.showMessageDialog(this,"No Class Defined","Error",JOptionPane.ERROR_MESSAGE);
					className = JOptionPane.showInputDialog("Class Name");
					while(className.length()==0)
					{
						JOptionPane.showMessageDialog(this,"Class name is needed","Error",JOptionPane.ERROR_MESSAGE);
						className = JOptionPane.showInputDialog("Class Name");
					}
				}catch(Exception e13){System.out.println(e13+"13");}
				setDefaultProgram();
			}
		}
		else if(ae.getSource()== run)
		{
			int start =0;
			resultArea.setText("");
			try
			{
				Process runn = rt.exec("java    "+className);
				BufferedReader output = new BufferedReader(new InputStreamReader(runn.getInputStream()));
				BufferedReader error = new BufferedReader(new InputStreamReader(runn.getErrorStream()));
				
				//setOutput(output);
				while(true)
					{
						String temp = output.readLine();
						if(temp!= null)
						{
							result += temp;
							result +="\n";
							System.out.println("half2");
						}
						else break;
					}	
				while(true)
				{
					String temp = error.readLine();
					if(temp!= null)
					{
						result1 += temp;
						result1 +="\n";
					}
					else break;
				}
				output.close();
				error.close();
				System.out.println("output");
				resultArea.setText(result+"\n\n"+result1);
				result="";
				result1="";
			}catch(Exception e14){System.out.println(e14+"e14");}
		}
		
		
		
	}
	public void setOutput(BufferedReader br)
	{
		try
		{
			while(true)
			{
				String temp = br.readLine();
				if(temp!= null)
				{
					result += temp;
					result +="\n";
					System.out.println("half2");
				}
				else break;
			}
		}catch(Exception e15){System.out.println("e15"+e15);}
	}
	
	public void setDefaultProgram()
	{
		if(className!=null)
			{
				className = className.trim();
				programArea.setText("public class "+className+"\n"+
								"{"+"\n"+"\t"+
									"public static void main (String args[])"+"\n"+
									"\t"+"{"+"\n"+
									"\t"+"\t"+"System.out.println(\"Hello World\");"+"\n"+
									"\t"+"}"+"\n"+
								"}"			
								);
				programArea.setComponentPopupMenu( menu );
		
			}
			else
			{
				programArea.setText("");
				
			}
	}
	
	public static void main(String... args)
	{
		new IDE("Java Integrated Development Environment");
	}

	//static class SelectAll extends TextAction
    //{
      //  public SelectAll()
        //{
          //  super("Select All");
            //putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control a"));
        //}

        //public void actionPerformed(ActionEvent e)
      //  {
        //    JTextComponent component = getFocusedComponent();
          //  if(component.getText()!="")
		//	{	component.selectAll();
			//	component.requestFocusInWindow();
			//}
        //}
    //}


}