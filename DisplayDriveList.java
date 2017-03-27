import javax.swing.filechooser.FileSystemView;
import java.io.File;

class DisplayDriveList extends Thread
{
	File f;
	DisplayDriveList(File f)
	{
		this.f=f;		
	}
	public void run()
	{	
		DriveDetail.list = f.listFiles();
		//for(File fo : DriveDetail.list)
		//{
		//	if(fo.isFile())
		//	{
		//		System.out.println(fo.getPath()+" File ");
		//	}
		//	else if(fo.isDirectory())
		//	{
		//		System.out.println(fo.getPath()+" Directory ");
		//	}
			
		//}
		HitroExplorer.parentName = f.getParentFile();
		//HitroExplorer.flag =1;
		String s2 = FileSystemView.getFileSystemView().getSystemDisplayName (f);
		HitroExplorer.directoryPath.setText(s2);
		HitroExplorer.jpCenter.removeAll();
		HitroExplorer.jpCenter.revalidate();
		HitroExplorer.jpCenter.repaint();
	}
}
