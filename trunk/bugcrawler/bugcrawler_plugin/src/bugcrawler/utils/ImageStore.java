package bugcrawler.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import bugcrawler.runtime.Activator;

public class ImageStore {

    // creating a new ImageRegistry
    private ImageRegistry imageRegistry = new ImageRegistry();
    
    /**
     * Creates an ImageStore to get Links which are located in a
     * plugins-folder
     * 
     * @param imageDirectory
     *                where to find the images.
     */
    public ImageStore(String imageDirectory) {
	try {

	    imageDirectory = pathEndsWithSeperator(imageDirectory) == true ? imageDirectory.substring(0,
		    imageDirectory.length() - 1) : imageDirectory;

	    // get the bundleDirectoryURL in the jar
	    URL bundelDirectoryURL = Activator.getDefault().getBundle().getEntry(imageDirectory);

	    // get all files in the cached directory
	    String[] imageNames = new File(FileLocator.toFileURL(bundelDirectoryURL).getFile()).list();

	    // registering all images
	    registerImages(imageDirectory,imageNames);
	} catch (IOException e) {
	    throw new RuntimeException ("Error while handling the ImageDirectory \"" + imageDirectory + "\"",e);
	} catch (SecurityException sec) {
	    throw new RuntimeException ("Access to the ImageDirectory \"" + imageDirectory + "\"denied",sec);
	}
    }

    private boolean pathEndsWithSeperator(String imageDirectory) {
	return imageDirectory.endsWith(System.getProperty("file.separator"));
    }

    /**
     * registering all Images in the ImageRegistry so that user can easily
     * access them.
     */
    private void registerImages(String imageDirectory,String[] imageNames) {
	for (int i = 0; i < imageNames.length; i++) {
	    imageRegistry.put(imageNames[i], ImageDescriptor.createFromURL(Activator.getDefault().getBundle()
		    .getEntry(imageDirectory + "/" + imageNames[i])));
	}
    }

    /**
     * Gets an Image out of the ImageStore.
     * 
     * @param imageName
     *                of the Image for <u>Example:</u> myImage.gif
     * 
     * @return the image
     */
    public Image get(String imageName) {
	return imageRegistry.get(imageName);
    }
}
