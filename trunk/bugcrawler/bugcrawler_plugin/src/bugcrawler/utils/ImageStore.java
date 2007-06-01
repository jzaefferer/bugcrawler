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

    // all imageNames that will be found in the directory
    private String[] imageNames;

    // the given imageDirectory where to search for images
    private String imageDirectory;

    /**
     * Creates an ImageStore to get Links which are located in a
     * plugins-folder
     * 
     * @param imageDirectory
     *                where to find the images.
     */
    public ImageStore(String imageDirectory) {
	try {

	    this.imageDirectory = pathEndsWithSeperator(imageDirectory) == true ? imageDirectory.substring(0,
		    imageDirectory.length() - 1) : imageDirectory;

	    // get the bundleDirectoryURL in the jar
	    URL bundelDirectoryURL = Activator.getDefault().getBundle().getEntry(imageDirectory);

	    // get all files in the cached directory
	    imageNames = new File(FileLocator.toFileURL(bundelDirectoryURL).getFile()).list();

	    // registering all images
	    registerImages();
	} catch (NullPointerException ne) {
	    System.err.println("Error while handling the ImageDirectory \"" + imageDirectory
		    + "\" may it doesn't exist.");
	} catch (IOException e) {
	    System.err.println("Error while handling the ImageDirectory \"" + imageDirectory + "\"");
	} catch (SecurityException sec) {
	    System.err.println("Access to the ImageDirectory \"" + imageDirectory + "\"denied");
	}
    }

    private boolean pathEndsWithSeperator(String imageDirectory) {
	return imageDirectory.endsWith(System.getProperty("file.separator"));
    }

    /**
     * registering all Images in the ImageRegistry so that user can easily
     * access them.
     */
    private void registerImages() {
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
