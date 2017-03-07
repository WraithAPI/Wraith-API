package wraith;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.apache.commons.lang3.StringUtils;
import wraith.lang.GList;
import wraith.library.Coordinate;
import wraith.library.Coordinates;
import wraith.library.LibraryInstaller;
import wraith.library.LibraryManager;

public class Wraith
{
	public static void installWraith(String version) throws Exception
	{
		File localRepository = new File(new File("wraith"), "repository");
		File jar = new File(localRepository, "wraith/core/" + version + "/core-" + version + ".jar");
		URL website = new URL("http://get.volmit.com/wraith/core/" + version + "/core-" + version + ".jar");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(jar);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		URL u = new URL("jar:file:" + jar.toString() + "!/");
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class<?> sysclass = URLClassLoader.class;
		Method method = sysclass.getDeclaredMethod("addURL", new Class[] {URL.class});
		method.setAccessible(true);
		method.invoke(sysloader, new Object[] {u});
	}
	
	public void run()
	{
		try
		{
			// Install Wraith (ensure version matches pom dependency)
			installWraith("1.0");
			
			// Default local Repository location
			File localRepository = WraithAPI.REPO_LOCAL;
			
			// Create an installer and add repositories to it
			LibraryInstaller installer = new LibraryInstaller(localRepository);
			
			// This will add coordinates for Wraith LANG (at the core version)
			installer.add(Coordinates.WRAITH_LANG.get());
			
			// You can also manually add artifacts not included
			installer.add(Coordinate.create("org.apache.commons", "commons-lang3", "3.5", "https://repo1.maven.org/maven2/"));
			
			// Finally install the queue
			// You can also use install(Runnable callback) for a non blocking
			// method
			installer.install();
			
			// You could also ensure something is installed
			LibraryManager.ensureInstalled(Coordinates.COMMON_IO.get());
			
			// It is now safe to use Wraith Lang and Commons Lang
			System.out.println(new GList<String>().qadd(StringUtils.capitalize("cap")).shuffleCopy().toString(", "));
		}
		
		catch(Exception e)
		{
			// Something happened
			// It is not safe to use the library manager as wraith may have
			// failed to install
			e.printStackTrace();
		}
	}
}
