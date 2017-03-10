package wraith.minecraft.concurrent;

import org.bukkit.Bukkit;
import wraith.minecraft.construct.PluginUtil;

public abstract class TaskLater
{
	public TaskLater()
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(PluginUtil.findPlugin(), new Runnable()
		{
			@Override
			public void run()
			{
				TaskLater.this.run();
			}
		});
	}
	
	public abstract void run();
}
