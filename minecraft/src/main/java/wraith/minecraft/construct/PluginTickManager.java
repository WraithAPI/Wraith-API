package wraith.minecraft.construct;

import wraith.construct.TickManagerBase;
import wraith.minecraft.concurrent.Task;

public class PluginTickManager extends TickManagerBase
{
	private Task task;
	
	@Override
	public void start()
	{
		task = new Task(0)
		{
			@Override
			public void run()
			{
				tick();
			}
		};
	}
	
	@Override
	public void stop()
	{
		task.cancel();
	}
}
