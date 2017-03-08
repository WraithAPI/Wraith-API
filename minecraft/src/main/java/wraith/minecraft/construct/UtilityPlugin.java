package wraith.minecraft.construct;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import wraith.lang.GList;
import wraith.lang.GListAdapter;

public class UtilityPlugin extends JavaPlugin
{
	public GList<Player> getPlayers()
	{
		GList<Player> p = new GList<Player>();
		
		p.addAll(Bukkit.getOnlinePlayers());
		
		return p;
	}
	
	public GList<Player> getPlayersWithPermission(String withPermission)
	{
		return new GListAdapter<Player, Player>()
		{
			@Override
			public Player onAdapt(Player from)
			{
				if(from.hasPermission(withPermission))
				{
					return from;
				}
				
				return null;
			}
		}.adapt(getPlayers());
	}
	
	public boolean canFindPlayer(String search)
	{
		return findPlayer(search) == null ? false : true;
	}
	
	public Player findPlayer(String search)
	{
		for(Player i : getPlayers())
		{
			if(i.getName().equalsIgnoreCase(search))
			{
				return i;
			}
		}
		
		for(Player i : getPlayers())
		{
			if(i.getName().toLowerCase().contains(search.toLowerCase()))
			{
				return i;
			}
		}
		
		return null;
	}
	
	public void register(Listener listener)
	{
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	public void unRegister(Listener listener)
	{
		HandlerList.unregisterAll(listener);
	}
	
	public int scheduleSyncRepeatingTask(int delay, int interval, Runnable runnable)
	{
		return getServer().getScheduler().scheduleSyncRepeatingTask(this, runnable, delay, interval);
	}
	
	public int scheduleSyncTask(int delay, Runnable runnable)
	{
		return getServer().getScheduler().scheduleSyncDelayedTask(this, runnable, delay);
	}
	
	public void cancelTask(int tid)
	{
		getServer().getScheduler().cancelTask(tid);
	}
}
