package wraith;

import java.io.File;
import wraith.library.Coordinates;
import wraith.library.LibraryInstaller;

public class WraithAPI
{
	public static final String GLOBAL_VERSION = "1.0";
	public static final String REPO_CENTRAL = "https://repo1.maven.org/maven2/";
	public static final String REPO_VOLMIT = "http://get.volmit.com/";
	public static final File REPO_LOCAL = new File(new File("wraith"), "repository");
	private static boolean setup = false;
	
	public static void setup()
	{
		if(setup)
		{
			return;
		}
		
		LibraryInstaller installer = new LibraryInstaller(REPO_LOCAL, true);
		installer.add(Coordinates.COMMON_LANG.get());
		installer.install();
		
		setup = true;
	}
	
	static
	{
		setup();
	}
}
