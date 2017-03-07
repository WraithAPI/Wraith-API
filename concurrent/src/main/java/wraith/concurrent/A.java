package wraith.concurrent;

import wraith.lang.GList;
import wraith.lang.GTime;
import wraith.math.M;

public abstract class A
{
	private static GList<A> running = new GList<A>();
	private static long idx = 0;
	private long ns;
	private String name;
	private long id;
	
	public A()
	{
		this("Task");
	}
	
	public A(String name)
	{
		ns = M.ns();
		id = ++idx;
		this.name = name + "-" + id;
		running.add(this);
		
		new Thread()
		{
			@Override
			public void run()
			{
				runAsync();
				running.remove(this);
			}
		};
	}
	
	/**
	 * Get the time since this task has started in nanos
	 * 
	 * @return nanoseconds since started
	 */
	public long timeSinceStarted()
	{
		return M.ns() - ns;
	}
	
	public abstract void runAsync();
	
	public static int getRunningThreadCount()
	{
		return running.size();
	}
	
	public static long getTotalThreadsRan()
	{
		return idx;
	}
	
	public long getStartNanoTime()
	{
		return ns;
	}
	
	public String getName()
	{
		return name;
	}
	
	public long getId()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return name + "-" + id + " (RUNNING for " + new GTime(timeSinceStarted() / 1000000).to() + ")";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (ns ^ (ns >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		
		if(obj == null)
		{
			return false;
		}
		
		if(getClass() != obj.getClass())
		{
			return false;
		}
		
		A other = (A) obj;
		
		if(id != other.id)
		{
			return false;
		}
		
		if(name == null)
		{
			if(other.name != null)
			{
				return false;
			}
		}
		
		else if(!name.equals(other.name))
		{
			return false;
		}
		
		if(ns != other.ns)
		{
			return false;
		}
		
		return true;
	}
}
