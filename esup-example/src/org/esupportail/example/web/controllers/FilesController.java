/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.esupportail.commons.utils.DownloadUtils;
import org.esupportail.commons.web.beans.Paginator;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.web.beans.PrintableFile;
import org.esupportail.example.web.beans.PrintableFilePaginator;

/**
 * A bean to manage files.
 */
public class FilesController extends AbstractContextAwareController {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 5854451657305008065L;

	/**
	 * The size of the buffer to read uploaded files.
	 */
	private static final int UPLOADED_FILES_BUFFER_SIZE = 1024;

    /**
     * The values for pageSizeItems.
     */
    private static final Integer [] PAGE_SIZE_ITEM_VALUES = {5, 10, 15, 20};
    
	/**
	 * The filename to download.
	 */
	private String filenameToDownload;

	/**
	 * The uploaded file.
	 */
    private UploadedFile uploadedFile;
    
    /**
     * The file paginator state.
     */
    private Paginator<PrintableFile> paginator;
    
	/**
	 * The download id.
	 */
	private Long downloadId;
	
	/**
	 * Constructor.
	 */
	public FilesController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		filenameToDownload = null;
		uploadedFile = null;
		paginator = new PrintableFilePaginator(getDirectory(), PAGE_SIZE_ITEM_VALUES);
		downloadId = null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() 
		+ "[filenameToDownload=[" + filenameToDownload + "], uploadedFile=" 
		+ uploadedFile + ", paginator=" + paginator + "]";
	}

	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		User currentUser = getCurrentUser();
		return currentUser != null;
	}
	
	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String enter() {
		if (!isPageAuthorized()) {
			addUnauthorizedActionMessage();
			return null;
		}
		return "navigationFiles";
	}
	
	/**
	 * @return the name of the directory.
	 */
	public String getDirectory() {
		return getDomainService().getUploadDirectory();
	}

	/**
	 * @return the paginator.
	 */
	public Paginator<PrintableFile> getPaginator() {
		return paginator;
	}

	/**
	 * @param filenameToDownload the filenameToDownload to set
	 */
	public void setFilenameToDownload(final String filenameToDownload) {
		this.filenameToDownload = filenameToDownload;
	}
	
	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String downloadFile() {
		downloadId = DownloadUtils.setDownload(
				getDirectory() + "/" + filenameToDownload, "application/octet-stream");
		return null;
	}

	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String uploadFile() {
		if (uploadedFile == null) {
			addErrorMessage(null, "FILES.MESSAGE.NO_FILE_GIVEN");
			return null;
		}
		String name = null;
        try {
        	name = uploadedFile.getName();
        	// a hack for IE
        	if (name.contains("\\")) {
            	name = name.substring(name.lastIndexOf('\\') + 1);
        	}
			OutputStream out = new FileOutputStream(new File(getDirectory() + "/" + name));
			InputStream in = uploadedFile.getInputStream();
			byte[] buf = new byte[UPLOADED_FILES_BUFFER_SIZE];
			int len;
			while ((len = in.read(buf)) > 0) {
			    out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			addErrorMessage(null, "FILES.MESSAGE.UPLOAD_ERROR", name, e.getMessage());
		}
		paginator.forceReload();
		addInfoMessage(null, "FILES.MESSAGE.UPLOAD_OK", name);
		return null;
	}

	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(final UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param downloadId the downloadId to set
	 */
	protected void setDownloadId(final Long downloadId) {
		this.downloadId = downloadId;
	}

	/**
	 * @return the downloadId
	 */
	public Long getDownloadId() {
		Long id = downloadId;
		downloadId = null;
		return id;
	}

}
