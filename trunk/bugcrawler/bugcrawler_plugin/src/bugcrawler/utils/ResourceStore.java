package bugcrawler.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import bugcrawler.runtime.Activator;

/**
 * A ResourceStore which easily handles images and colors in plugin-packages
 * 
 * @author TSO
 */
/**
 * @author TSO
 *
 */
public class ResourceStore {

	
	/**
	 * ImageRegistry of the Plugin
	 */
	private ImageRegistry imageRegistry = new ImageRegistry();
	
	/**
	 * ColorRegistry of the Plugin
	 */
	private ColorRegistry colorRegistry = new ColorRegistry();
	
	private FontRegistry fontRegistry = new FontRegistry();
	

	/**
	 * Creates an ResourceStore to get Links which are located in a plugins-folder
	 * 
	 * @param imageDirectory
	 *            where to find the images.
	 */
	public ResourceStore(String imageDirectory) {
		try {

			imageDirectory = pathEndsWithSeperator(imageDirectory) == true ? imageDirectory.substring(0,
					imageDirectory.length() - 1) : imageDirectory;

			// get the bundleDirectoryURL in the jar
			URL bundelDirectoryURL = Activator.getDefault().getBundle().getEntry(imageDirectory);

			// get all files in the cached directory
			String[] imageNames = new File(FileLocator.toFileURL(bundelDirectoryURL).getFile()).list();

			// registering all images
			registerImages(imageDirectory, imageNames);
		} catch (IOException e) {
			throw new RuntimeException("Error while handling the ImageDirectory \"" + imageDirectory + "\"",
					e);
		} catch (SecurityException sec) {
			throw new RuntimeException("Access to the ImageDirectory \"" + imageDirectory + "\"denied", sec);
		}
	}

	/**
	 * Check if the given path ends with a file.separator
	 * 
	 * @param imageDirectory
	 * 				the Directory where to find the images in the plugin
	 * @return boolean
	 * 			if the path ends with /
	 */
	private boolean pathEndsWithSeperator(String imageDirectory) {
		return imageDirectory.endsWith(System.getProperty("file.separator"));
	}

	/**
	 * registering all Images in the ImageRegistry so that user can easily
	 * access them.
	 */
	private void registerImages(String imageDirectory, String[] imageNames) {
		for (int i = 0; i < imageNames.length; i++) {
			imageRegistry.put(imageNames[i], ImageDescriptor.createFromURL(Activator.getDefault().getBundle()
					.getEntry(imageDirectory + "/" + imageNames[i])));
		}
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("bugcrawler.plugin", path);
	}

	/**
	 * Gets an Image out of the ResourceStore.
	 * 
	 * @param imageName
	 *            of the Image for <u>Example:</u> myImage.gif
	 * 
	 * @return the image
	 */
	public Image getImage(String imageName) {
		Image image = imageRegistry.get(imageName);
		if(image == null){
			throw new RuntimeException("Image not found in the registry - called name:"+imageName);
		}
		return image;
	}
	

	/**
	 * Get a Color out of the ResourceStore
	 * 
	 * @param red
	 * 			the red component of the new instance
	 * @param green
	 * 			the green component of the new instance
	 * @param blue
	 * 			the blue component of the new instance
	 * @return Color
	 * 			the new color registered in a ColorRegistry
	 */
	public Color getColor(int red, int green, int blue){
		if(red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0){
			throw new RuntimeException("Values of RGB are not valid - called values: RED-"+red+",GREEN-"+green+",BLUE-"+blue);
		}
		String symbolicName = red+";"+green+";"+blue;
		Color newColor = colorRegistry.get(symbolicName);
		if(newColor == null){
			colorRegistry.put(symbolicName,new RGB(red,green,blue));
			newColor = colorRegistry.get(symbolicName);
		}
		return newColor;
	}
	
}
