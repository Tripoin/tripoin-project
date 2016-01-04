package com.tripoin.web.view.profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripoin.web.common.EWebUIConstant;
import com.vaadin.ui.Upload.Receiver;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ProfileImageUploader implements Receiver {

	private static final long serialVersionUID = -2352906512234981812L;
	private File file;
	private String extensionFile;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileImageUploader.class);
    
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null;
        try {
        	String typeFile = mimeType.split("/")[0];
        	extensionFile = filename.substring(filename.lastIndexOf("."));
        	if(EWebUIConstant.TYPE_FILE_IMAGE.toString().equals(typeFile))
                file = File.createTempFile(filename.replace(extensionFile, ""), extensionFile);
        	try {
				fos = new FileOutputStream(file);
			} catch (final FileNotFoundException e) {
				LOGGER.warn("File not found Exception");
				return null;
			}
        } catch (IOException e) {
        	LOGGER.warn("IOException",e);
        	return null;
        }
        return fos;
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getExtensionFile() {
		return extensionFile;
	}

	public void setExtensionFile(String extensionFile) {
		this.extensionFile = extensionFile;
	}

}