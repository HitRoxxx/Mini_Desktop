import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

class Display extends Thread implements MouseListener
{
	File f ;
	int rootLength;
	JPanel drivePanel,centerPanel;
	JLabel driveName,driveSpace,driveImage,driveSpaceImage;
	int l;
	int driveFreeSpaceText;
	int driveTotalSpaceText;
	String driveNameText ;
	
	Display(File f, int rootLength)
	{
		this.f=f;
		this.rootLength=rootLength;
		l=1024*1024*1024;
	}
	public void run()
	{
		
		drivePanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new FlowLayout());
		driveImage = new JLabel("", new ImageIcon("driveImage.png"), JLabel.CENTER);
		driveNameText = FileSystemView.getFileSystemView().getSystemDisplayName (f);
		long freeSpace = f.getFreeSpace()/l;
		long totalSpace = f.getTotalSpace()/l;
		
		driveName = new JLabel(driveNameText);
		driveSpace = new JLabel(freeSpace+"GB Free of "+ totalSpace +"GB");
		
			try
			{
				switch(10/(int)(totalSpace/freeSpace))
				{
					 case 1:  driveSpaceImage = new JLabel("", new ImageIcon("9.png"), JLabel.CENTER);
							 break;
					case 2:  driveSpaceImage = new JLabel("", new ImageIcon("8.png"), JLabel.CENTER);
							 break;
					case 3:  driveSpaceImage = new JLabel("", new ImageIcon("7.png"), JLabel.CENTER);
							 break;
					case 4: driveSpaceImage = new JLabel("", new ImageIcon("6.png"), JLabel.CENTER);
							 break;
					case 5:  driveSpaceImage = new JLabel("", new ImageIcon("5.png"), JLabel.CENTER);
							 break;
					case 6: driveSpaceImage = new JLabel("", new ImageIcon("4.png"), JLabel.CENTER);
							 break;
					case 7:  driveSpaceImage = new JLabel("", new ImageIcon("3.png"), JLabel.CENTER);
							 break;
					case 8:  driveSpaceImage = new JLabel("", new ImageIcon("2.png"), JLabel.CENTER);
							 break;
					case 9:  driveSpaceImage = new JLabel("", new ImageIcon("1.png"), JLabel.CENTER);
							 break;
					case 10: driveSpaceImage = new JLabel("", new ImageIcon("0.png"), JLabel.CENTER);
							 break;
				}
			}
			catch(Exception e)
			{
				driveSpaceImage = new JLabel("", new ImageIcon("10.png"), JLabel.CENTER);
			}
		centerPanel.add(driveImage);
		centerPanel.add(driveSpaceImage);
		drivePanel.add(centerPanel,BorderLayout.CENTER);
		drivePanel.add(driveName,BorderLayout.NORTH);
		drivePanel.add(driveSpace,BorderLayout.SOUTH);
		HitroExplorer.jpCenter.add(drivePanel);
		HitroExplorer.jpCenter.revalidate();
		HitroExplorer.jpCenter.repaint();
		drivePanel.addMouseListener(this);
				
	}
			public void mouseClicked(MouseEvent e)
			{
				if(WindowMenu.flag==1 )
				{	
					WindowMenu.jw1.setVisible(false);
					WindowMenu.flag=0;
				}
				HitroExplorer.view(f);				
			}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}

			
			public void mouseEntered(MouseEvent e)
			{
				//System.out.println("Mouse Entered");
				drivePanel.setBackground(new Color(89,180,255));

			}

			public void mouseExited(MouseEvent e)
			{
				Color color = UIManager.getColor ( "Panel.background" );
				//System.out.println("Mouse Exited");
				drivePanel.setBackground(color);
			}
			
}