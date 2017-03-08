package wraith.construct;

import wraith.lang.GList;

/**
 * The base controller
 * 
 * @author cyberpwn
 */
public abstract class ControllerBase implements Controllable, Tickable
{
	private Controllable parent;
	private GList<Controllable> children;
	private ControllableHost host;
	
	public ControllerBase(Controllable parent)
	{
		this.parent = parent;
		children = new GList<Controllable>();
		
		if(this instanceof ControllableHost && parent == null)
		{
			host = null;
		}
		
		else if(parent != null)
		{
			Controllable cx = this;
			
			while((cx = cx.getParent()) != null)
			{
				
			}
			
			if(cx instanceof ControllableHost && cx.getParent() == null)
			{
				host = (ControllableHost) cx;
			}
		}
	}
	
	@Override
	public void start()
	{
		onStart();
		
		for(Controllable i : children)
		{
			i.start();
		}
	}
	
	@Override
	public void tick()
	{
		onTick();
	}
	
	@Override
	public void stop()
	{
		for(Controllable i : children)
		{
			i.stop();
		}
		
		onStop();
	}
	
	public abstract void onStart();
	
	public abstract void onStop();
	
	public abstract void onTick();
	
	@Override
	public GList<Controllable> getChildren()
	{
		return children.copy();
	}
	
	@Override
	public Controllable getParent()
	{
		return parent;
	}
	
	@Override
	public void registerChild(Controllable child)
	{
		children.add(child);
		registerTicked((Tickable) child);
	}
	
	@Override
	public void unregisterChild(Controllable child)
	{
		unregisterTicked((Tickable) child);
		children.remove(child);
	}
	
	@Override
	public String getName()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public boolean isHost()
	{
		return getHost() == null;
	}
	
	@Override
	public ControllableHost getHost()
	{
		return host;
	}
	
	@Override
	public void registerTicked(Tickable tickable)
	{
		if(!isHost())
		{
			getHost().getTickManager().registerTicked(tickable);
		}
	}
	
	@Override
	public void unregisterTicked(Tickable tickable)
	{
		if(!isHost())
		{
			getHost().getTickManager().unregisterTicked(tickable);
		}
	}
}
