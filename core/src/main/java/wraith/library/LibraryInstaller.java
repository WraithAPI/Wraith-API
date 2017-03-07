package wraith.library;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryInstaller
{
	private List<Library> libraries;
	private File repository;
	
	public LibraryInstaller(File repository)
	{
		libraries = new ArrayList<Library>();
		this.repository = repository;
	}
	
	public LibraryInstaller add(Coordinate coord)
	{
		libraries.add(new WraithLibrary(coord));
		
		return this;
	}
	
	public List<Library> getLibraries()
	{
		return libraries;
	}
	
	public File getRepository()
	{
		return repository;
	}
	
	public void install()
	{
		LibraryManager.installBlocking(this);
	}
	
	public void install(Runnable onCompleted)
	{
		LibraryManager.installNonBlocking(this, onCompleted);
	}
}
