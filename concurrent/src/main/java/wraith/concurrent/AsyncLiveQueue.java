package wraith.concurrent;

import wraith.lang.GList;

public abstract class AsyncLiveQueue
{
	private int maxThreads;
	private int currentThreads;
	private boolean alive;
	private GList<Runnable> queue;
	
	public AsyncLiveQueue(int maxThreads)
	{
		alive = true;
		this.maxThreads = maxThreads;
		currentThreads = 0;
		queue = new GList<Runnable>();
	}
	
	public void close()
	{
		alive = false;
	}
	
	public void queue(Runnable r)
	{
		queue.add(r);
	}
	
	public void start()
	{
		new A()
		{
			@Override
			public void runAsync()
			{
				while(!queue.isEmpty() && alive)
				{
					chomp();
				}
			}
		};
	}
	
	public void chomp()
	{
		if(maxThreads - currentThreads > 0)
		{
			while(currentThreads < maxThreads && !queue.isEmpty())
			{
				Runnable r = queue.pop();
				
				currentThreads++;
				
				new A()
				{
					@Override
					public void runAsync()
					{
						r.run();
						currentThreads--;
					}
				};
			}
		}
	}
	
	public int getMaxThreads()
	{
		return maxThreads;
	}
	
	public int getCurrentThreads()
	{
		return currentThreads;
	}
	
	public GList<Runnable> getQueue()
	{
		return queue;
	}
}
