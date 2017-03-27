import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;


class HitroExplorer extends JFrame implements ActionListener
{	
	JPanel jpNorth ,jpSouth,jpWest,jpEast;
	static JPanel jpCenter,jpMain;
	CardLayout cl;
	static JButton back;
	static int rootLength;
	static File parentName ;
	int flag =0;
	static JLabel directoryPath;
	 //static GridLayout gl;
	//File ParentNode ;
	
	HitroExplorer(String s)
	{
		super(s);
		jpCenter = new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));		
		//jpCenter = new JPanel();
		jpNorth = new JPanel(new FlowLayout(FlowLayout.LEFT,20,30));
		jpEast = new JPanel();
		jpWest = new JPanel();
		jpSouth = new JPanel();	
		back=new JButton("Back");
		directoryPath = new JLabel("MY COMPUTER");
		//directoryPath.setSize(30,50);
		directoryPath.setOpaque(true);
		directoryPath.setBackground(Color.white);
		back.addActionListener(this);
		jpMain = new JPanel();
		cl= new CardLayout();
		jpMain.setLayout(cl);
		jpNorth.add(back);
		jpNorth.add(directoryPath);
		new DirectoryDisplay();
		
		add(jpNorth,BorderLayout.NORTH);
		add(jpEast,BorderLayout.EAST);
		add(jpWest,BorderLayout.WEST);
		add(jpSouth,BorderLayout.SOUTH);
		jpMain.add(jpCenter,"jp1");
		//JScrollPane pane = new JScrollPane(jpCenter);
		//add(pane,BorderLayout.CENTER);
		add(jpMain,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,700);
		setLocation(150, 20);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e)
		{
			if(WindowMenu.flag==1 )
				{	
					WindowMenu.jw1.setVisible(false);
					WindowMenu.flag=0;
				}
			if(e.getSource()== back)
			{
					HitroExplorer.jpCenter.removeAll();
					HitroExplorer.jpCenter.revalidate();
					HitroExplorer.jpCenter.repaint();
					
					
					for(File f3 :DirectoryDisplay.roots)
					{
						//String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (f3);
						String s2 = FileSystemView.getFileSystemView().getSystemDisplayName (parentName);
						System.out.println(parentName+"   "+f3);
						//if(s1.equals(s2))
						if(s2==null)
						{
							//System.out.println(parentName+"   "+f3);
							flag =1;
							break;
						}
						else
						{
							flag=0;
						}
					}
					//System.out.println(flag);
					if(flag==1)
					{
						directoryPath.setText("MY COMPUTER");
						new DirectoryDisplay();
					}
					else
					{
						String s2 = FileSystemView.getFileSystemView().getSystemDisplayName (parentName);
						directoryPath.setText(s2);
						new DriveDetail(parentName);
					}
					
			}
		}
		
		public static void view (File f)
		{
			new DriveDetail(f);
		}
		
}