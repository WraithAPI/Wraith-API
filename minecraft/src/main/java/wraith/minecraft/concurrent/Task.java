package wraith.minecraft.concurrent;

import org.bukkit.Bukkit;
import wraith.lang.FinalInteger;
import wraith.minecraft.construct.PluginUtil;

public abstract class Task
{
	private int id;
	
	public Task(int interval)
	{
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginUtil.findPlugin(), new Runnable()
		{
			@Override
			public void run()
			{
				Task.this.run();
			}
		}, 0, interval);
	}
	
	public Task(int interval, int times)
	{
		FinalInteger i = new FinalInteger(times);
		
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginUtil.findPlugin(), new Runnable()
		{
			@Override
			public void run()
			{
				Task.this.run();
				i.sub(1);
				
				if(i.get() <= 0)
				{
					cancel();
				}
			}
		}, 0, interval);
	}
	
	public void cancel()
	{
		Bukkit.getScheduler().cancelTask(id);
	}
	
	public abstract void run();
}
