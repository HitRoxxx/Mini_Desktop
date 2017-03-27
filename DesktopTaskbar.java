import java.awt.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DesktopTaskbar extends Thread implements MouseListener
{
	String b ,name[];
	JPanel desktopitem;
	DesktopTaskbar(String b)
	{
		this.b= b;		
			//Desktop.jpMainDesktop.revalidate();
			//Desktop.jpMainDesktop.repaint();
		
	}
	public void run()
	{
		name = b.split("\\.");
		desktopitem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		desktopitem.setOpaque(false);
		desktopitem.setBackground(new Color(0,0,0,0));
		JLabel jl1 = new JLabel("", new ImageIcon(b), JLabel.CENTER);
		desktopitem.add(jl1);
		desktopitem.addMouseListener(this);
			Desktop.jpos2.add(desktopitem);	
		
			
	}
	public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}

			
		public void mouseEntered(MouseEvent e)
		{
			//System.out.println("Mouse Entered");
			desktopitem.setOpaque(true);
			desktopitem.setBackground(new Color(89,180,255));
			
		}

		public void mouseExited(MouseEvent e)
		{
			desktopitem.setOpaque(false);
			desktopitem.setBackground(new Color(0,0,0,0));
		}
		
		public void mouseClicked(MouseEvent e)
		{
				
				Runtime rs = Runtime.getRuntime();
				System.out.println("Mouse clicked");
				if(name[0].equals("Start Button_"))
				{
					try
					{
						if(WindowMenu.flag==1 )
						{	
							WindowMenu.jw1.setVisible(false);
							WindowMenu.flag=0;
						}
						else
						{	
							new WindowMenu();
						}
					}
					catch(Exception e11){}
				}
				else
				{
					if(WindowMenu.flag==1 )
					{	
						WindowMenu.jw1.setVisible(false);
						WindowMenu.flag=0;
					}
				}
				
				 if (name[0].equals("Internet Explorer_40x40"))
				{
					try
					{
						rs.exec("cmd /c start http://google.com/");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("windows media player_40x40"))
				{
					try
					{
						rs.exec("c:\\Program Files\\Windows Media Player\\wmplayer.exe");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("notepad_40x40"))
				{
					try
					{
						rs.exec("notepad");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("disk_50x50"))
				{
					try
					{
						rs.exec("explorer /root,");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("volume_"))
				{
					try
					{
						rs.exec("sndvol");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("switchoff _40x40"))
				{
					try
					{
						//rs.exec("shutdown -s");
						//rs,exec("shutdown -a"); try it 
						//rs.exec("shutdown -m");
						
						System.exit(0);
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("Task Manager_"))
				{
					try
					{
						//rs.exec("shutdown -s");
						rs.exec("taskmgr");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				
			
		}
}
