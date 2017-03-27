import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;

class DriveDetail
{
	File f;
	static File[] list;
	DriveDetail(File f)
	{
		this.f=f;
		//System.out.println("hello");
		DisplayDriveList child = new DisplayDriveList(f);
		child.start();
		try
		{
			child.join();
		}
		catch(Exception e){System.out.println("ERRor");}
		for(File fo:list)
		{
			String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (fo);
			
			if(s1.length()!= 0)
			{	DisplayFolder drive = new DisplayFolder(fo,list.length);
				drive.start();
			}
		}
		
	}
}