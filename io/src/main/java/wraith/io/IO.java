package wraith.io;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class IO
{
	public static boolean makeFile(File file)
	{
		try
		{
			file.createNewFile();
			return true;
		}
		
		catch(IOException e)
		{
			return false;
		}
	}
	
	public static boolean delete(File file)
	{
		if(file.isDirectory())
		{
			try
			{
				FileUtils.deleteDirectory(file);
				return true;
			}
			
			catch(IOException e)
			{
				return false;
			}
		}
		
		else
		{
			return file.delete();
		}
	}
	
	public static boolean makeDir(File file)
	{
		return file.mkdirs();
	}
	
	public static boolean verifyFile(File file)
	{
		return makeDir(file.getParentFile()) && makeFile(file);
	}
	
	public static boolean copy(File a, File b)
	{
		if(a.isDirectory())
		{
			try
			{
				FileUtils.copyDirectory(a, b);
				return true;
			}
			
			catch(IOException e)
			{
				return false;
			}
		}
		
		else
		{
			try
			{
				FileUtils.copyFile(a, b);
				return true;
			}
			
			catch(IOException e)
			{
				return false;
			}
		}
	}
	
	public static boolean move(File a, File b)
	{
		if(a.isDirectory())
		{
			try
			{
				FileUtils.copyDirectory(a, b);
				delete(a);
				return true;
			}
			
			catch(IOException e)
			{
				return false;
			}
		}
		
		else
		{
			try
			{
				FileUtils.copyFile(a, b);
				delete(a);
				return true;
			}
			
			catch(IOException e)
			{
				return false;
			}
		}
	}
}
