package com.tripoin.web.view.profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.tripoin.web.common.EWebUIConstant;
import com.vaadin.ui.Upload.Receiver;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ProfileImageUploader implements Receiver {

	private static final long serialVersionUID = -2352906512234981812L;
	private File file;
    
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null;
        try {
        	String typeFile = mimeType.split("/")[0];
        	String extensionFile = filename.substring(filename.lastIndexOf("."));
        	if(EWebUIConstant.TYPE_FILE_IMAGE.toString().equals(typeFile))
                file = File.createTempFile(filename, extensionFile);
        	try {
				fos = new FileOutputStream(file);
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
        } catch (IOException e) {
        	e.printStackTrace();
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

}