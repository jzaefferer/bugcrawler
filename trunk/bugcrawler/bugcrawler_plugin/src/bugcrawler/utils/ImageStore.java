package bugcrawler.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import bugcrawler_plugin.Activator;

public class ImageStore {

	private URL bundelDirectoryURL = null;

	private String directoryURL = null;

	private ImageRegistry imageRegistry = null;

	private String[] imageNames = null;

	private String imageDirectory = null;

	/**
	 * Creates an ImageStore to get Links which are located in a plugins-folder
	 * 
	 * @param imageDirectory
	 *            where to find the images.
	 */
	public ImageStore(String imageDirectory) {
		try {
			boolean pathEndsWidthSeperator = imageDirectory.endsWith(System
					.getProperty("file.separator"));

			this.imageDirectory = pathEndsWidthSeperator ? imageDirectory
					.substring(0, imageDirectory.length() - 1) : imageDirectory;

			// creating a new ImageRegistry
			this.imageRegistry = new ImageRegistry();

			// get the bundleDirectoryURL in the jar
			bundelDirectoryURL = Activator.getDefault().getBundle().getEntry(
					imageDirectory);

			// get the directoryURL which may cached. This is the real directory
			// on disc
			directoryURL = FileLocator.toFileURL(bundelDirectoryURL).getFile();

			// making a directory for listing files
			File directoryFile = new File(directoryURL);

			// get all files in the cached directory
			imageNames = directoryFile.list();

			// registering all images
			registerImages();
		} catch (NullPointerException ne) {
			System.err.println("Error while handling the ImageDirectory \""
					+ imageDirectory + "\" may it doesn't exist.");
		} catch (IOException e) {
			System.err.println("Error while handling the ImageDirectory \""
					+ imageDirectory + "\"");
		} catch (SecurityException sec) {
			System.err.println("Access to the ImageDirectory \""
					+ imageDirectory + "\"denied");
		}
	}

	/**
	 * registering all Images in the ImageRegistry so that user can easily
	 * access them.
	 */
	private void registerImages() {
		for (int i = 0; i < imageNames.length; i++) {
			imageRegistry.put(imageNames[i], ImageDescriptor
					.createFromURL(Activator.getDefault().getBundle().getEntry(
							imageDirectory + "/" + imageNames[i])));
		}
	}

	/**
	 * Gets an Image out of the ImageStore.
	 * 
	 * @param imageName
	 *            of the Image for <u>Example:</u> myImage.gif
	 * 
	 * @return the image
	 */
	public Image getImage(String imageName) {
		return imageRegistry.get(imageName);
	}
}
