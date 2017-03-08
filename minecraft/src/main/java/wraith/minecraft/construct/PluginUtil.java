package wraith.minecraft.construct;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PluginUtil
{
	public static Plugin findPlugin()
	{
		return Bukkit.getServer().getPluginManager().getPlugins()[0];
	}
}
