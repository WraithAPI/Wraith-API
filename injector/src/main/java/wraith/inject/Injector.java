package wraith.inject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import wraith.WraithAPI;
import wraith.library.Coordinates;
import wraith.library.LibraryInstaller;

public class Injector extends JavaPlugin
{
	public static String WRAITH_VERSION = "${project.version}";
	
	@Override
	public void onLoad()
	{
		try
		{
			InputStream in = Injector.class.getResourceAsStream("/app.wpa");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			WRAITH_VERSION = reader.readLine();
			reader.close();
			File localRepository = new File(new File("wraith"), "repository");
			File jar = new File(localRepository, "wraith/core/" + WRAITH_VERSION + "/core-" + WRAITH_VERSION + ".jar");
			jar.getParentFile().mkdirs();
			URL website = new URL("http://get.volmit.com/wraith/core/" + WRAITH_VERSION + "/core-" + WRAITH_VERSION + ".jar");
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
			WraithAPI.setup();
			LibraryInstaller i = new LibraryInstaller(WraithAPI.REPO_LOCAL, true);
			i.add(Coordinates.WRAITH_MINECRAFT.get());
			i.add(Coordinates.WRAITH_CONCURRENT.get());
			i.add(Coordinates.WRAITH_CONSTRUCT.get());
			i.add(Coordinates.WRAITH_REFLECT.get());
			i.add(Coordinates.WRAITH_MATH.get());
			i.add(Coordinates.WRAITH_LANG.get());
			i.install();
			new File(getDataFolder(), "plugins").mkdirs();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable()
	{
		Bukkit.getServer().getPluginManager().loadPlugins(new File(getDataFolder(), "plugins"));
	}
}
