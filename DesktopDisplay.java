import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class DesktopDisplay extends Thread implements MouseListener
{
	String b;
	JPanel desktopitem ;
	String[] name;
	DesktopDisplay(String b)
	{
		this.b=b;
		
		//	Desktop.jpMainDesktop.revalidate();
		//	Desktop.jpMainDesktop.repaint();
		
	}
	public void run()
	{
		name = b.split("\\.");
		desktopitem = new JPanel(new BorderLayout());
		//desktopitem.setOpaque(false);
		desktopitem.setBackground(new Color(0,0,0,0));
		JLabel jl1 = new JLabel("", new ImageIcon(b), JLabel.CENTER);
		JLabel jl2 = new JLabel(name[0]);
		Font fo2 =new Font("Times New Roman",Font.BOLD,30);
		jl2.setFont(fo2);
		jl2.setForeground(Color.white);
		desktopitem.add(jl1,BorderLayout.CENTER);
		desktopitem.add(jl2,BorderLayout.SOUTH);
		desktopitem.addMouseListener(this);
		Desktop.jpos1.add(desktopitem);		
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
				if(WindowMenu.flag==1 )
				{	
					WindowMenu.jw1.setVisible(false);
					WindowMenu.flag=0;
				}
				Runtime rs = Runtime.getRuntime();
				System.out.println("Mouse clicked");
				if(name[0].equals("windows media player"))
				{
					try
					{
						rs.exec("c:\\Program Files\\Windows Media Player\\wmplayer.exe");
						
					}
					catch(Exception e11){}
				}
				else if(name[0].equals("My Computer"))
				{
					new HitroExplorer("Hitro Explorer");
					
				//	try
				//	{
						//rs.exec("cmd.exe /c cd \""+new_dir+"\" & start cmd.exe");
						//rs.exec(new String[]{"cmd.exe","/c","start"});
						//rs.exec("cmd.exe /c start ");
						//rs.exec("explorer.exe /e ");
				//	}
					//catch(Exception e11){System.out.println(e11);}
					
				}else if (name[0].equals("Recycle bin"))
				{
					try
					{
						rs.exec("cmd /c start shell:RecycleBinFolder");
					}
					catch(Exception e11){System.out.println(e11);}
				}
				else if (name[0].equals("My Documents"))
				{
					try
					{
						rs.exec("explorer.exe /e ");
					}
					catch(Exception e11){System.out.println(e11);}
				}else if (name[0].equals("Command Prompt"))
				{
					try
					{
						rs.exec("cmd.exe /c start");
					}
					catch(Exception e11){System.out.println(e11);}
				}else if (name[0].equals("Control Panel"))
				{
					try
					{
						rs.exec("control");
					}
					catch(Exception e11){System.out.println(e11);}
				}else if (name[0].equals("Paint"))
				{
					try
					{
						rs.exec("mspaint");
					}
					catch(Exception e11){System.out.println(e11);}
				}else if (name[0].equals("Microsoft Word"))
				{
					try
					{
						rs.exec("C:\\Program Files\\Microsoft Office\\Office15\\WINWORD.EXE");
					}
					catch(Exception e11){System.out.println(e11);}
				}else if (name[0].equals("Calculator"))
				{
					try
					{
						rs.exec("calc");
					}
					catch(Exception e11){System.out.println(e11);}
				}
			}

}
