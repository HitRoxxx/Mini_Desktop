import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DisplayFolder extends Thread implements MouseListener
{
	
	File f;
	JPanel folderView ;
	JLabel folderImage , folderName;
	int rootLength;
	String folderNameText;
	
		
	DisplayFolder(File f, int rootLength)
	{
		this.f=f;
		this.rootLength = rootLength;
		HitroExplorer.back.setVisible(true);
	}
	public void run()
	{
		if(rootLength!=0)
		{
			folderView = new JPanel(new BorderLayout());
			folderNameText = FileSystemView.getFileSystemView().getSystemDisplayName (f);
			imageFix(folderNameText);
			
			if(folderNameText.length()>=16)
			{	
				char ch [] =folderNameText.toCharArray();
				folderNameText = "";
				for(int i=0;i<16;i++)
				{	
					folderNameText = folderNameText +ch[i];
				}
			}
			folderName = new JLabel(folderNameText);
			folderView.add(folderImage,BorderLayout.CENTER);
			folderView.add(folderName,BorderLayout.SOUTH);
			HitroExplorer.jpCenter.add(folderView,BorderLayout.CENTER);
			HitroExplorer.jpCenter.revalidate();
			HitroExplorer.jpCenter.repaint();
			folderView.addMouseListener(this);
			}
		
	}
	public void mouseClicked(MouseEvent e)
			{
				if(WindowMenu.flag==1 )
				{	
					WindowMenu.jw1.setVisible(false);
					WindowMenu.flag=0;
				}
				
				String folderN = FileSystemView.getFileSystemView().getSystemDisplayName (f);
				Runtime rs = Runtime.getRuntime();
				
				if(f.isDirectory())
				{
					HitroExplorer.view(f); 
				}
				else
				{
					
					String[] splited = folderN.split("\\.");
					int i = splited.length;
					String charc = splited[i-1].toLowerCase();
					if(charc.equals("exe"))
					{
						try 
						{
							rs.exec(folderN);
							System.out.println(f);
						}
						catch (Exception ex) {  System.out.println(ex); System.out.println(f);	}   
					}
					else if(charc.equals("class"))
					{
						try 
						{
							rs.exec("C:\\Users\\H\\Desktop\\java Decompiler.exe "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else if(charc.equals("doc")||charc.equals("docx"))
					{
						try 
						{
							System.out.println(f);
							rs.exec("C:\\Program Files\\Microsoft Office\\Office15\\WINWORD.EXE "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else if(charc.equals("mp3"))
					{
						try 
						{
							rs.exec("c:\\Program Files\\Windows Media Player\\wmplayer.exe  "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else if(charc.equals("mp4")||charc.equals("mkv"))
					{
						try 
						{
							rs.exec("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else if(charc.equals("txt"))
					{
						try 
						{
							rs.exec("notepad "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else if(charc.equals("jpg")||charc.equals("png"))
					{
						try 
						{
							//rs.exec("C:\\Program Files (x86)\\Google\\Picasa3C:\\Program Files (x86)\\Google\\Picasa3\\PicasaPhotoViewer.exe "+f);
							//System.out.println("notepad"+folderN);
							rs.exec("C:\\Users\\H\\Desktop\\PicasaPhotoViewer.exe "+f);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else if(charc.equals("zip")||charc.equals("rar"))
					{
						try 
						{
							rs.exec("C:\\Program Files\\WinRAR\\WinRAR.exe "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					else
					{
						try 
						{
							rs.exec("notepad "+f);
							//System.out.println("notepad"+folderN);
						}
						catch (Exception ex) {  System.out.println(ex);	}   
					}
					
				}
				
				
			}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}

			
			public void mouseEntered(MouseEvent e)
			{
				//System.out.println("Mouse Entered");
				folderView.setBackground(new Color(89,180,255));

			}

			public void mouseExited(MouseEvent e)
			{
				Color color = UIManager.getColor ( "Panel.background" );
				//System.out.println("Mouse Exited");
				folderView.setBackground(color);
			}
			public void imageFix(String ext)
			{
				if(f.isDirectory())
				{
					//System.out.println(ext +"Dir");
					try{
						String[] str = f.list(); 
						if(str.length > 0)
						{
							folderImage = new JLabel("", new ImageIcon("folderfull_100x100.png"), JLabel.CENTER);
						}
						else
						{
							folderImage = new JLabel("", new ImageIcon("foldersempty_100x100.png"), JLabel.CENTER);
						}
					}catch(Exception e){folderImage = new JLabel("", new ImageIcon("Unknown_100x100.png"), JLabel.CENTER);}
				}
				else
				{
					
					String[] splited = ext.split("\\.");
					int i = splited.length;
					String charc = splited[i-1].toLowerCase();
					if(charc.equals("exe"))
					{
						folderImage = new JLabel("", new ImageIcon("exe_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("class"))
					{
						folderImage = new JLabel("", new ImageIcon("class_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("doc")||charc.equals("docx"))
					{
						folderImage = new JLabel("", new ImageIcon("doc_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("mp3"))
					{
						folderImage = new JLabel("", new ImageIcon("mp3_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("mp4")||charc.equals("mkv"))
					{
						folderImage = new JLabel("", new ImageIcon("mp4_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("txt"))
					{
						folderImage = new JLabel("", new ImageIcon("text_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("jpg")||charc.equals("png"))
					{
						folderImage = new JLabel("", new ImageIcon("jpg_100x100.png"), JLabel.CENTER);
					}
					else if(charc.equals("zip")||charc.equals("rar"))
					{
						folderImage = new JLabel("", new ImageIcon("zip_100x100.png"), JLabel.CENTER);
					}
					else
					{
						folderImage = new JLabel("", new ImageIcon("Unknown_100x100.png"), JLabel.CENTER);
					}
					
				}
				
			}
}
