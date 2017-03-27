import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class WindowMenu implements MouseListener
{
	
	JLabel computer , documents ,user ,control,shutdown;
	static JWindow jw1;
	static int flag =0;
	
    public WindowMenu() 
	{
		flag=1;
        //setType(javax.swing.JFrame.Type.UTILITY);
		jw1 = new JWindow();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		computer = new JLabel("Computer",new ImageIcon("My Computer_40x40.png"),JLabel.CENTER);
        documents = new JLabel("Documents",new ImageIcon("My Documents_40x40.png"),JLabel.CENTER);
		user = new JLabel("User",new ImageIcon("user_40x40.png"),JLabel.CENTER);
		control = new JLabel("Control",new ImageIcon("Control Panel_40x40.png"),JLabel.CENTER);
		shutdown = new JLabel("Shutdown",new ImageIcon("switchoff _40x40.png"),JLabel.CENTER);
		Font f1 =new Font("Times New Roman",Font.BOLD,20);
		shutdown.setFont(f1);
		shutdown.setForeground(Color.white);
		computer.setFont(f1);
		computer.setForeground(Color.white);
		documents.setFont(f1);
		documents.setForeground(Color.white);
		user.setFont(f1);
		user.setForeground(Color.white);
		control.setFont(f1);
		control.setForeground(Color.white);
		
		computer.addMouseListener(this);
		user.addMouseListener(this);
		documents.addMouseListener(this);
		control.addMouseListener(this);
		shutdown.addMouseListener(this);
		panel.add(user);
		panel.add(documents);
		panel.add(computer);
		panel.add(control);
		panel.add(shutdown);

        jw1.setContentPane(panel);
        jw1.setBackground(new Color(0, 0, 0, 100));
        jw1.setSize(500, 500);
       // setLocationRelativeTo(null);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int x = (int)rect.getMinX();
		int y = (int)rect.getMaxY();//-jw1.getHeight();
		jw1.setLocation(x,y-250);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jw1.setVisible(true);
		jw1.pack();

    }
	public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}

			
		public void mouseEntered(MouseEvent e)
		{
			if(e.getSource()== computer)
			{
				computer.setOpaque(true);
				computer.setBackground(new Color(89,180,255));
			}
			else if(e.getSource()== shutdown)
			{
				shutdown.setOpaque(true);
				shutdown.setBackground(new Color(89,180,255));
			}
			else if(e.getSource()== user)
			{
				//System.out.println("Mouse Entered");
				user.setOpaque(true);
				user.setBackground(new Color(89,180,255));
			}
			else if(e.getSource()== documents)
			{
				//System.out.println("Mouse Entered");
				documents.setOpaque(true);
				documents.setBackground(new Color(89,180,255));
			}
			else if(e.getSource()== control)
			{
				//System.out.println("Mouse Entered");
				control.setOpaque(true);
				control.setBackground(new Color(89,180,255));
			}
			 
		}

		public void mouseExited(MouseEvent e)
		{
			 if(e.getSource()== computer)
			{
				computer.setOpaque(false);
				computer.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== shutdown)
			{
				shutdown.setOpaque(false);
				shutdown.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== user)
			{
				//System.out.println("Mouse Entered");
				user.setOpaque(false);
				user.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== documents)
			{
				//System.out.println("Mouse Entered");
				documents.setOpaque(false);
				documents.setBackground(new Color(0,0,0,0));
			}
			else if(e.getSource()== control)
			{
				//System.out.println("Mouse Entered");
				control.setOpaque(false);
				control.setBackground(new Color(0,0,0,0));
			}
			
		}
		public void mouseClicked(MouseEvent e)
		{
			Runtime rt = Runtime.getRuntime();
			 if(e.getSource()== computer)
			{
				try
				{
					rt.exec("explorer /root,");
				}
				catch(Exception e11){System.out.println(e11);}
			}
			else if(e.getSource()== documents)
		{
			try
			{
				rt.exec("explorer.exe /e");
			}
			catch(Exception e11){System.out.println(e11);}
		}
		else if(e.getSource()== user)
		{
			try
			{
				rt.exec("explorer.exe ");
			}
			catch(Exception e11){System.out.println(e11);}
		}
		else if(e.getSource()== control)
		{
			try
			{
				rt.exec("control");
			}
			catch(Exception e11){System.out.println(e11);}
		}
		else if(e.getSource()== shutdown)
		{
			try
			{
				rt.exec("shutdown -s");
			}
			catch(Exception e11){System.out.println(e11);}
		}
			}
			

   
} 
