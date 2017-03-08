package wraith.construct;

public interface TickManager
{
	public void start();
	
	public void stop();
	
	public void tick();
	
	public void unregisterTicked(Tickable tickable);
	
	public void registerTicked(Tickable tickable);
}
