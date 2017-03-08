package wraith.construct;

public abstract class ControllerHostBase extends ControllerBase implements ControllableHost
{
	private TickManager tickManager;
	
	public ControllerHostBase(TickManager tickManager)
	{
		super(null);
		
		this.tickManager = tickManager;
	}
	
	@Override
	public void launch()
	{
		tickManager.start();
		onLaunch();
		start();
	}
	
	@Override
	public void hault()
	{
		tickManager.stop();
		stop();
		onHault();
	}
	
	@Override
	public TickManager getTickManager()
	{
		return tickManager;
	}
	
	public abstract void onLaunch();
	
	public abstract void onHault();
	
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
}
