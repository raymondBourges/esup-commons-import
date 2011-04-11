/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans; 

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.esupportail.commons.exceptions.ConfigException;
import org.esupportail.commons.web.beans.ListPaginator;

/** 
 * A paginator for files.
 */ 
public class PrintableFilePaginator extends ListPaginator<PrintableFile> {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -5417403291852064862L;

	/**
	 * The path.
	 */
	private String path;
	
	/**
	 * Constructor.
	 * @param path 
	 * @param pageSizeValues 
	 */
	public PrintableFilePaginator(
			final String path,
			final Integer [] pageSizeValues) {
		super(Arrays.asList(pageSizeValues), 0);
		this.path = path;
	}

	/**
	 * @see org.esupportail.commons.web.beans.ListPaginator#getData()
	 */
	@Override
	protected List<PrintableFile> getData() {
		File dir = new File(path);
	    List<PrintableFile> result = new ArrayList<PrintableFile>();
		if (!dir.exists()) {
			throw new ConfigException("Directory [" + path + "] not found");
		}
		if (!dir.isDirectory()) {
			throw new ConfigException("[" + path + "] is not a directory");
		}
		if (!dir.canRead()) {
			throw new ConfigException("Directory [" + path + "] is not readable");
		}
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(final File file) {
				return !file.isDirectory();
			}
		};
		for (File file : dir.listFiles(fileFilter)) {
			result.add(new PrintableFile(file));
		}
		return result;
	}

} 

