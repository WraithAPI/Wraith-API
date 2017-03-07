package wraith.concurrent;

import wraith.lang.GList;

public abstract class AsyncBlockingQueue
{
	private int maxThreads;
	private int currentThreads;
	private GList<Runnable> queue;
	
	public AsyncBlockingQueue(int maxThreads)
	{
		this.maxThreads = maxThreads;
		currentThreads = 0;
		queue = new GList<Runnable>();
	}
	
	public void queue(Runnable r)
	{
		queue.add(r);
	}
	
	public void start()
	{
		boolean[] c = {false};
		
		new A()
		{
			@Override
			public void runAsync()
			{
				while(!queue.isEmpty())
				{
					chomp();
				}
				
				c[0] = true;
			}
		};
		
		while(!c[0])
		{
			try
			{
				Thread.sleep(10);
			}
			
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
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
