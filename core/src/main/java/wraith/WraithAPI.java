package wraith;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import wraith.library.Coordinates;
import wraith.library.LibraryInstaller;

public class WraithAPI
{
	public static String GLOBAL_VERSION = "${project.version}";
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
		
		updateVersion();
		LibraryInstaller installer = new LibraryInstaller(REPO_LOCAL);
		installer.add(Coordinates.COMMON_LANG.get());
		installer.install();
		
		setup = true;
	}
	
	private static void updateVersion()
	{
		try
		{
			InputStream in = WraithAPI.class.getResourceAsStream("/app.wpa");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			GLOBAL_VERSION = reader.readLine();
			reader.close();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	static
	{
		setup();
	}
}
