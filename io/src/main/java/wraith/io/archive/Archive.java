package wraith.io.archive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Archive
{
	public void extractTo(File dest) throws IOException;
	
	public void put(File file, File source);
	
	public void compress() throws FileNotFoundException, IOException;
}
