import java.io.File;

class Root extends Thread
{
	Root()
	{
		
	}
	public void run()
	{
		//System.out.println("f");
		DirectoryDisplay.roots = File.listRoots();
		HitroExplorer.directoryPath.setText("MY COMPUTER");
		//HitroExplorer.flag =0;
		//System.out.println(DirectoryDisplay.roots[0]);
	}
}