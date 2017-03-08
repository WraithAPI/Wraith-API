package wraith.construct;

import wraith.lang.GMap;

public abstract class TickManagerBase implements TickManager
{
	private GMap<Tickable, Integer> tickables;
	private GMap<Tickable, Integer> tickableReference;
	
	public TickManagerBase()
	{
		tickables = new GMap<Tickable, Integer>();
		tickableReference = new GMap<Tickable, Integer>();
	}
	
	@Override
	public void tick()
	{
		for(Tickable i : tickables.k())
		{
			tickables.put(i, tickables.get(i) + 1);
			
			if(tickables.get(i) >= tickableReference.get(i))
			{
				tickables.put(i, 0);
				i.tick();
			}
		}
	}
	
	public int getInterval(Tickable tickable)
	{
		if(tickable.getClass().isAnnotationPresent(Ticked.class))
		{
			return tickable.getClass().getDeclaredAnnotation(Ticked.class).value();
		}
		
		return -1;
	}
	
	public boolean isTicked(Tickable tickable)
	{
		return getInterval(tickable) > -1;
	}
	
	private void registerTicked(Tickable tickable, int interval)
	{
		tickableReference.put(tickable, interval);
		tickables.put(tickable, interval);
	}
	
	protected void registerTicked(Tickable tickable)
	{
		if(isTicked(tickable))
		{
			registerTicked(tickable, getInterval(tickable));
		}
	}
	
	protected void unregisterTicked(Tickable tickable)
	{
		tickableReference.remove(tickable);
		tickables.remove(tickable);
	}
}
