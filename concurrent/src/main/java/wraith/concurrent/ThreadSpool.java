package wraith.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSpool
{
	private ExecutorService e;
	
	public ThreadSpool(int threads)
	{
		e = Executors.newFixedThreadPool(threads);
	}
	
	public ExecutorService getService()
	{
		return e;
	}
}
