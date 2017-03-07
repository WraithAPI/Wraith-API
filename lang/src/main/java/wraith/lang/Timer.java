package wraith.lang;

/**
 * Timer
 * 
 * @author cyberpwn
 */
public class Timer
{
	private long tns;
	private long cns;
	
	/**
	 * Create a new timer
	 */
	public Timer()
	{
		tns = 0;
		cns = 0;
	}
	
	/**
	 * Start the timer
	 */
	public void start()
	{
		cns = System.nanoTime();
	}
	
	/**
	 * Stop the timer
	 */
	public void stop()
	{
		tns = System.nanoTime() - cns;
		cns = System.nanoTime();
	}
	
	/**
	 * Get time in nanoseconds
	 * 
	 * @return the time
	 */
	public long getTime()
	{
		return tns;
	}
	
	/**
	 * Get the time in nanoseconds the timer last stopped
	 * 
	 * @return
	 */
	public long getLastRun()
	{
		return cns;
	}
}
