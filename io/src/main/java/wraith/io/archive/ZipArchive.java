package wraith.io.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;
import wraith.lang.GMap;

public class ZipArchive implements Archive
{
	private GMap<File, File> put;
	private File archive;
	
	public ZipArchive(File archive)
	{
		this.archive = archive;
		put = new GMap<File, File>();
	}
	
	@Override
	public void extractTo(File dest) throws IOException
	{
		ZipFile z = new ZipFile(archive);
		Enumeration<ZipArchiveEntry> i = z.getEntries();
		
		while(i.hasMoreElements())
		{
			ZipArchiveEntry e = i.nextElement();
			FileOutputStream fos = new FileOutputStream(new File(dest, e.getName()));
			IOUtils.write(IOUtils.toByteArray(z.getInputStream(e)), fos);
			fos.close();
		}
		
		z.close();
	}
	
	@Override
	public void put(File file, File source)
	{
		put.put(file, source);
	}
	
	@Override
	public void compress() throws FileNotFoundException, IOException
	{
		ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(archive);
		zipOutput.setLevel(9);
		
		for(File i : put.k())
		{
			ZipArchiveEntry entry = new ZipArchiveEntry(i.toString());
			File s = put.get(i);
			entry.setSize(s.length());
			zipOutput.putArchiveEntry(entry);
			zipOutput.write(IOUtils.toByteArray(new FileInputStream(s)));
			zipOutput.closeArchiveEntry();
		}
		
		zipOutput.close();
	}
}
