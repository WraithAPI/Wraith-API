package wraith.minecraft.construct;

import wraith.construct.Controllable;
import wraith.construct.ControllableHost;
import wraith.construct.TickManager;
import wraith.construct.Tickable;
import wraith.lang.GList;

public abstract class ControllablePlugin extends UtilityPlugin implements ControllableHost, Controllable
{
	private TickManager tickManager;
	private GList<Controllable> children;
	
	@Override
	public void onEnable()
	{
		launch();
	}
	
	@Override
	public void onDisable()
	{
		hault();
	}
	
	@Override
	public void launch()
	{
		children = new GList<Controllable>();
		tickManager = new PluginTickManager();
		onLaunch();
		tickManager.start();
		start();
	}
	
	@Override
	public void hault()
	{
		onHault();
		tickManager.stop();
		stop();
	}
	
	@Override
	public TickManager getTickManager()
	{
		return tickManager;
	}
	
	@Override
	public void registerTicked(Tickable tickable)
	{
		tickManager.registerTicked(tickable);
	}
	
	@Override
	public void unregisterTicked(Tickable tickable)
	{
		tickManager.registerTicked(tickable);
	}
	
	@Override
	public void start()
	{
		for(Controllable i : children)
		{
			i.start();
		}
		
		onStart();
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
	
	@Override
	public GList<Controllable> getChildren()
	{
		return children;
	}
	
	@Override
	public Controllable getParent()
	{
		return null;
	}
	
	public abstract void onStart();
	
	public abstract void onStop();
	
	public abstract void onLaunch();
	
	public abstract void onHault();
	
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
	public boolean isHost()
	{
		return getHost() == null;
	}
	
	@Override
	public ControllableHost getHost()
	{
		return null;
	}
}
