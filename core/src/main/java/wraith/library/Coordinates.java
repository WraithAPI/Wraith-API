package wraith.library;

import static wraith.WraithAPI.*;
import java.net.MalformedURLException;

public enum Coordinates
{
	WRAITH_CORE("wraith", "core", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_LANG("wraith", "lang", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_MATH("wraith", "math", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_CONSTRUCT("wraith", "construct", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_MINECRAFT("wraith", "minecraft", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_IO("wraith", "io", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_REFLECT("wraith", "reflect", GLOBAL_VERSION, REPO_VOLMIT),
	WRAITH_CONCURRENT("wraith", "concurrent", GLOBAL_VERSION, REPO_VOLMIT),
	COMMON_LANG("org.apache.commons", "commons-lang3", "3.5", REPO_CENTRAL),
	COMMON_IO("org.apache.commons", "commons-io", "1.3.2", REPO_CENTRAL),
	COMMON_MATH("org.apache.commons", "commons-math3", "3.5", REPO_CENTRAL),
	COMMON_COMPRESS("org.apache.commons", "commons-compress", "1.9", REPO_CENTRAL);
	
	private Coordinate coord;
	
	private Coordinates(String g, String a, String v, String r)
	{
		try
		{
			coord = Coordinate.create(g, a, v, r);
		}
		
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Coordinate get()
	{
		return coord;
	}
}
