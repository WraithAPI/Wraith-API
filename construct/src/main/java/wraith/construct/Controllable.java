package wraith.construct;

import wraith.lang.GList;

/**
 * A controllable object
 * 
 * @author cyberpwn
 */
public interface Controllable
{
	/**
	 * Start this controller, starting depending controllers beforehand
	 */
	public void start();
	
	/**
	 * Stop this controller, then stop children
	 */
	public void stop();
	
	/**
	 * Get children controllers
	 */
	public GList<Controllable> getChildren();
	
	/**
	 * Get the parent controller.
	 * 
	 * @return The parent or null
	 */
	public Controllable getParent();
	
	/**
	 * Register the child controllable object under this controller (parent)
	 * 
	 * @param child
	 *            the child to add under this parent
	 */
	public void registerChild(Controllable child);
	
	/**
	 * Get the controller name
	 * 
	 * @return the name of this controller
	 */
	public String getName();
}
