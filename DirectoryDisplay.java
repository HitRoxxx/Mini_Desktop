import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;

class DirectoryDisplay  
{
	JLabel directoryName , directorySpace ;
	static File roots[] ;
	DirectoryDisplay() 
	{
		HitroExplorer.back.setVisible(false);
		//System.out.println("f");
		Root rootDirectory = new Root();
		rootDirectory.start();
		try
		{
			rootDirectory.join();
		}
		catch(Exception e){}
		for(File f:roots)
		{
			String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (f);
			
			if(s1.length()!= 0)
			{	Display drive = new Display(f,roots.length);
				drive.start();
			}
		}
		
	}
}