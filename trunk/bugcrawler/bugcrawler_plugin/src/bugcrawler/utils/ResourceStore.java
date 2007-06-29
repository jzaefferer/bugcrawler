package bugcrawler.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
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
	 * ImageRegistry to store Images
	 */
	private ImageRegistry imageRegistry = new ImageRegistry();

	/**
	 * ColorRegistry to store Colors
	 */
	private ColorRegistry colorRegistry = new ColorRegistry();

	/**
	 * FontRegistry to store Fonts
	 */
	private FontRegistry fontRegistry = new FontRegistry();

	private boolean directoryHasBeenSet = false;

	/**
	 * Initializes the ResourceStore to handle Images, Colors or Fonts
	 */
	public ResourceStore() {
	}

	/**
	 * Set the ImagesPath to get Links which are located in a plugin-folder
	 * 
	 * @param imageDirectory
	 *            where to find the images.
	 */
	public void setImagesPath(String imageDirectory) {
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
		directoryHasBeenSet = true;
	}

	/**
	 * Check if the given path ends with a file.separator
	 * 
	 * @param imageDirectory
	 *            the Directory where to find the images in the plugin
	 * @return boolean if the path ends with /
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
	 *            the path to an image including the imagename
	 * @return the image descriptor of the image to the given path
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("bugcrawler.plugin", path);
	}

	/**
	 * Gets an Image out of the ResourceStore.
	 * 
	 * Example: <code>resourceStore.getImage("myPicture.png");</code>
	 * 
	 * Warning: It is required to set the ImagePath. The path is the with <code>
	 * ResourceStore store = new ResourceStore();
	 * store.setImagePath("<my_image_directory_in_the_root_of_the_plugin>");
	 * </code>
	 * 
	 * 
	 * @param imageName
	 *            of the Image for <u>Example:</u> myImage.gif
	 * 
	 * @return the image
	 */
	public Image getImage(String imageName) {
		if (!directoryHasBeenSet) {
			throw new RuntimeException(
					"Image Directory hasn't been set - set it with store.setImagePath(String).");
		}

		Image image = imageRegistry.get(imageName);
		if (image == null) {
			throw new RuntimeException("Image not found in the registry - called name:" + imageName);
		}
		return image;
	}

	/**
	 * Get a Color out of the ResourceStore
	 * 
	 * Example: <code>new ResourceStore().getColor(210,204,110);</code>
	 * 
	 * @param red
	 *            the red component of the new instance
	 * @param green
	 *            the green component of the new instance
	 * @param blue
	 *            the blue component of the new instance
	 * @return Color the new color registered in a ColorRegistry
	 */
	public Color getColor(int red, int green, int blue) {
		if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
			throw new RuntimeException("Values of RGB are not valid - called values:RED-" + red + ",GREEN-"
					+ green + ",BLUE-" + blue);
		}
		String symbolicName = red + ";" + green + ";" + blue;
		Color newColor = colorRegistry.get(symbolicName);
		if (newColor == null) {
			colorRegistry.put(symbolicName, new RGB(red, green, blue));
			newColor = colorRegistry.get(symbolicName);
		}
		return newColor;
	}

	/**
	 * Get a Font out of the ResourceStore
	 * 
	 * Example:
	 * <code>new ResourceStore().getFont(new String[]{"Times New Roman" , "Courier New"}, 20, SWT.NORMAL);</code>
	 * 
	 * @param fontNames
	 *            array with fontnames. If the first fontname isn't found the
	 *            next will be used. If no fontname is found the callback
	 * @param size
	 *            the size of the font in points
	 * @param style
	 *            the style of the font use SWT.BOLD, SWT.ITALIC or SWT.NORMAL
	 * @return Font configured by the given values
	 */
	public Font getFont(String[] fontNames, int size, int style) {
		if (!(style == SWT.BOLD || style == SWT.ITALIC || style == SWT.NORMAL)) {
			throw new RuntimeException(
					"No valid style, please use SWT.BOLD,SWT.ITALIC or SWT.NORMAL - called style as int:"
							+ style);
		} else if (size <= 0) {
			throw new RuntimeException("Size is smaller or equals 0 but has to be greater - called size:"
					+ size);
		} else if (testFontNames(fontNames)) {
			throw new RuntimeException("One or more Fontnames are null - called fontnames:" + fontNames);
		}
		FontData[] fontData = new FontData[fontNames.length];
		StringBuilder symbolicName = new StringBuilder();
		for (int i = 0; i < fontNames.length; i++) {
			fontData[i] = new FontData(fontNames[i], size, style);
			symbolicName.append(fontNames[i]);
		}

		// Put the given Fonts to the registry
		fontRegistry.put(symbolicName.toString(), fontData);

		// Get the given Font or if no Font of the fontNames exists load default
		return fontRegistry.get(symbolicName.toString());
	}

	/**
	 * Test if one of the given fontNames is null
	 * 
	 * @param fontNames
	 *            the fontNames given to getFont
	 * @return boolean if one of the fontNames is null
	 */
	private boolean testFontNames(String[] fontNames) {
		for (int i = 0; i < fontNames.length; i++) {
			if (fontNames[i] == null) {
				return true;
			}
		}
		return false;
	}

}
