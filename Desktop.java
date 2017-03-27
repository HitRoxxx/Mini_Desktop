import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Desktop extends JFrame implements ActionListener , MouseListener
{
	 
	static JPanel jpos1,jpos2,jpos3 ,jpMainDesktop;
	JLabel background,sticky,snipping,systeminfo,javaIde,calculator,game,chat;
	JButton changebackground;
	BorderLayout bl;
	int i= 0;
	Desktop(String s)
	{
		super(s);
		jpMainDesktop = new JPanel(new BorderLayout());
		changebackground = new JButton("Next Background");
		changebackground.setPreferredSize(new Dimension(135, 20));
		sticky = new JLabel("Sticky Note", new ImageIcon("Sticky Note_40x40.png"), JLabel.CENTER);
		sticky.setForeground(Color.white);
		snipping = new JLabel("Snipping Tool", new ImageIcon("Snipping Tool_40x40.png"), JLabel.CENTER);
		snipping.setForeground(Color.white);
		systeminfo = new JLabel("System Info", new ImageIcon("System Information_40x40.png"), JLabel.CENTER);
		systeminfo.setForeground(Color.white);
		javaIde = new JLabel("Java IDE ", new ImageIcon("Ide.png"), JLabel.CENTER);
		javaIde.setForeground(Color.white);
		calculator = new JLabel("Calculator ", new ImageIcon("calculator_40x40.png"), JLabel.CENTER);
		calculator.setForeground(Color.white);
		game = new JLabel("2048 Game", new ImageIcon("2048.png"), JLabel.CENTER);
		game.setForeground(Color.white);
		chat = new JLabel("Chit Chat ", new ImageIcon("o_40x40.png"), JLabel.CENTER);
		chat.setForeground(Color.white);
		
		changebackground.addActionListener(this);
		sticky.addMouseListener(this);
		snipping.addMouseListener(this);
		systeminfo.addMouseListener(this);
		javaIde.addMouseListener(this);
		calculator.addMouseListener(this);
		game.addMouseListener(this);
		chat.addMouseListener(this);
		setDesktopBackground();
	}
	
	public void setDesktop()
	{
		String str[]={"My Computer.png","Recycle bin.png","My Documents.png","Command Prompt.png","Control Panel.png","Paint.png","Microsoft Word.png","Calculator.png","windows media player.png",};
		String str1[]={"Start Button_.png","Internet Explorer_40x40.png","windows media player_40x40.png","notepad_40x40.png","disk_40x40.png","Task Manager_.png","volume_.png","switchoff _40x40.png"};
		for(String img:str)
		{
			try{
			DesktopDisplay dd=new DesktopDisplay(img);
			dd.start();
			dd.join();
			}catch(Exception e){};
		
		}
		for(String img:str1)
		{
			try{
			
			DesktopTaskbar dt=new DesktopTaskbar(img);
			dt.start();
			dt.join();
			}catch(Exception e1){};
		}
	}
	public void setDesktopBackground()
	{
		jpos1 = new JPanel(new FlowLayout (FlowLayout.LEFT,20,50));
		jpos2 = new JPanel(new FlowLayout (FlowLayout.LEFT,5,0));
		jpos3 = new JPanel();
		jpos3.setLayout(new BoxLayout(jpos3, BoxLayout.Y_AXIS));
		bl = new BorderLayout();
		setDesktop();
		
		//jpos1.setBorder(BorderFactory.createEmptyBorder());
		//jpos1.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		//jpos2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 235, Color.BLACK));
		
		jpos3.add(changebackground);
		jpos3.add(sticky);
		jpos3.add(snipping);
		jpos3.add(systeminfo);
		jpos3.add(javaIde);
		jpos3.add(calculator);
		jpos3.add(game);
		jpos3.add(chat);
		//jpos3.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.BLACK));
		jpos1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK));
		setLayout(new BorderLayout());
		if(i==0)
		{
			background=new JLabel("",new ImageIcon("windows1.jpg"),JLabel.CENTER);
			//add(background);
			background.setOpaque(false);
			background.setLayout(bl);
			i++;
			
		}
		else if(i==1)
		{
			background=new JLabel("",new ImageIcon("windows2.jpg"),JLabel.CENTER);
			//add(background);
			background.setOpaque(false);
			background.setLayout(bl);
			i++;
		}
		else if(i==2)
		{
			background=new JLabel("",new ImageIcon("windows3.jpg"),JLabel.CENTER);
			//add(background);
			background.setOpaque(false);
			background.setLayout(bl);
			i++;
		}
		else if(i==3)
		{
			background=new JLabel("",new ImageIcon("windows4.jpg"),JLabel.CENTER);
			//add(background);
			background.setOpaque(false);
			background.setLayout(bl);
			i=0;
		}
		
		jpos1.setOpaque(false);
		//jpos1.setBackground(new Color(0,0,0,0));
		jpos2.setOpaque(false);
		
		
		//jpos21.setBackground(new Color(0,0,0,100));
		jpos3.setOpaque(false);
		//jpos3.setBackground(new Color(0,0,0,0));
		//System.out.println("fine");
		//jpos3.setBackground(Color.red);
		
		background.add(jpos1,BorderLayout.CENTER);
		background.add(jpos2,BorderLayout.SOUTH);
		background.add(jpos3,BorderLayout.EAST);
		
		jpMainDesktop.add(background);
		add(jpMainDesktop,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		dispose();
		setUndecorated(true);
		setVisible(true);
		System.out.println("end");
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()== changebackground)
		{	
			jpMainDesktop.removeAll();
			jpMainDesktop.revalidate();
			jpMainDesktop.repaint();
			setDesktopBackground();
			//System.out.println("sadss"+i);
		}
		
	}
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}

			
		public void mouseEntered(MouseEvent e)
		{
			 if(e.getSource()== snipping)
			{
				snipping.setOpaque(true);
				snipping.setBackground(new Color(89,180,255));
			}
			else if(e.getSource()== systeminfo)
			{
				systeminfo.setOpaque(true);
				systeminfo.setBackground(new Color(89,180,255));
			}
			else if(e.getSource()== sticky)
			{
				//System.out.println("Mouse Entered");
				sticky.setOpaque(true);
				sticky.setBackground(new Color(89,180,255));
			
			}
			else if(e.getSource()== javaIde)
			{
				//System.out.println("Mouse Entered");
				javaIde.setOpaque(true);
				javaIde.setBackground(new Color(89,180,255));
			
			}
			else if(e.getSource()== calculator)
			{
				//System.out.println("Mouse Entered");
				calculator.setOpaque(true);
				calculator.setBackground(new Color(89,180,255));
			
			}
			else if(e.getSource()== game)
			{
				//System.out.println("Mouse Entered");
				game.setOpaque(true);
				game.setBackground(new Color(89,180,255));
			
			}
			else if(e.getSource()== chat)
			{
				//System.out.println("Mouse Entered");
				chat.setOpaque(true);
				chat.setBackground(new Color(89,180,255));
			
			}
		}

		public void mouseExited(MouseEvent e)
		{
			 if(e.getSource()== snipping)
			{
				snipping.setOpaque(false);
			snipping.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== systeminfo)
			{
				systeminfo.setOpaque(false);
				systeminfo.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== sticky)
			{
				//System.out.println("Mouse Entered");
				sticky.setOpaque(false);
				sticky.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== javaIde)
			{
				//System.out.println("Mouse Entered");
				javaIde.setOpaque(false);
				javaIde.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== calculator)
			{
				//System.out.println("Mouse Entered");
				calculator.setOpaque(false);
				calculator.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== game)
			{
				//System.out.println("Mouse Entered");
				game.setOpaque(false);
				game.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== chat)
			{
				//System.out.println("Mouse Entered");
				chat.setOpaque(false);
				chat.setBackground(new Color(0,0,0,0));
			}
			
		}
		public void mouseClicked(MouseEvent e)
		{
			if(WindowMenu.flag==1 )
				{	
					WindowMenu.jw1.setVisible(false);
					WindowMenu.flag=0;
				}
			
			Runtime rt = Runtime.getRuntime();
			 if(e.getSource()== snipping)
			{
				try
				{
					rt.exec("snippingtool");
				}
				catch(Exception e11){System.out.println(e11);}
			}
			else if(e.getSource()== systeminfo)
			{
				try
				{
					rt.exec("msinfo32");
				}
				catch(Exception e11){System.out.println(e11);}
			}
			else if(e.getSource()== sticky)
			{
				try
				{
					rt.exec("stikynot");
				}
				catch(Exception e11){System.out.println(e11);}
			}
			
			else if(e.getSource()== javaIde)
			{
				new IDE("Java Integrated Development Environment");
			}
			else if(e.getSource()== calculator)
			{
				new DemoFrame("Calculator");
			}
			else if(e.getSource()== game)
			{
				new TwentyFourty("2048");
			}
			else if(e.getSource()== chat)
			{
				new Login("LogIn");
			}
		}
	

}
