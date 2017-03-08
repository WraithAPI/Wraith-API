package wraith.construct;

public class IndependantTickManager extends TickManagerBase
{
	private Thread ticker;
	
	public IndependantTickManager(long cycle)
	{
		ticker = new Thread("Ticker")
		{
			@Override
			public void run()
			{
				while(!Thread.interrupted())
				{
					tick();
					
					if(Thread.interrupted())
					{
						return;
					}
					
					try
					{
						Thread.sleep(cycle);
					}
					
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	@Override
	public void start()
	{
		ticker.start();
	}
	
	@Override
	public void stop()
	{
		ticker.interrupt();
	}
}
