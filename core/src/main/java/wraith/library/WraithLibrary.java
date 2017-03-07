package wraith.library;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WraithLibrary implements Library
{
	private LibraryState state;
	private Coordinate coordinate;
	
	public WraithLibrary(Coordinate coord)
	{
		coordinate = coord;
		state = LibraryState.NOT_INSTALLED;
	}
	
	@Override
	public LibraryState getState()
	{
		return state;
	}
	
	@Override
	public boolean isInstalled()
	{
		return state.equals(LibraryState.INSTALLED);
	}
	
	protected void install(File localRepository)
	{
		state = LibraryState.NOT_INSTALLED;
		
		File localTarget = new File(localRepository, getCoordinates().getEffectivePath() + "/" + getCoordinates().getEffectiveName());
		
		if(!localTarget.exists())
		{
			state = LibraryState.DOWNLOADING;
			
			try
			{
				LibraryUtil.download(localTarget, getEffectiveURL());
			}
			
			catch(IOException e)
			{
				e.printStackTrace();
				state = LibraryState.FAILED;
			}
		}
		
		if(!state.equals(LibraryState.FAILED))
		{
			try
			{
				LibraryUtil.install(localTarget);
				state = LibraryState.INSTALLED;
			}
			
			catch(IOException e)
			{
				state = LibraryState.FAILED;
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Coordinate getCoordinates()
	{
		return coordinate;
	}
	
	@Override
	public String getVersion()
	{
		return getCoordinates().getVersion();
	}
	
	@Override
	public String getArtifactId()
	{
		return getCoordinates().getArtifactId();
	}
	
	@Override
	public String getGroupId()
	{
		return getCoordinates().getGroupId();
	}
	
	@Override
	public Repository getRepository()
	{
		return getCoordinates().getRepository();
	}
	
	@Override
	public URL getEffectiveURL()
	{
		return getCoordinates().getEffectiveURL();
	}
}
