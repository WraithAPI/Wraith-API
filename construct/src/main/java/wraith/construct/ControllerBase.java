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
	
	public ControllerBase(Controllable parent)
	{
		this.parent = parent;
		children = new GList<Controllable>();
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
	}
	
	@Override
	public String getName()
	{
		return this.getClass().getSimpleName();
	}
}
