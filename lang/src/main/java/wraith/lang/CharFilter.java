package wraith.lang;

public class CharFilter
{
	private GMap<Character, Character> mapping;
	
	public CharFilter(char... mappings)
	{
		mapping = new GMap<Character, Character>();
		putMappings(mappings);
	}
	
	public void putMappings(char... mappings)
	{
		for(int i = 0; i < mappings.length; i += 2)
		{
			if(mappings.length >= mappings.length)
			{
				mapping.put(mappings[i], mappings[i + 1]);
			}
		}
	}
	
	public String filter(String toProcess)
	{
		for(Character i : mapping.k())
		{
			toProcess = toProcess.replaceAll("" + i, "" + mapping.get(i));
		}
		
		return toProcess;
	}
}
