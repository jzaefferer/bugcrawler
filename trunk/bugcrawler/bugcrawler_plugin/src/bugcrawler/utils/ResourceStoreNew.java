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
import org.osgi.framework.Bundle;

import bugcrawler.runtime.Activator;

import static java.lang.System.getProperty;

/**
 * A ResourceStore which easily handles images and colors in plugin-packages
 * 
 * @author TSO
 */
public class ResourceStoreNew {

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

	/**
	 * Plugin-Bundle
	 */
	private Bundle bundle;

	/**
	 * indecates if the user has set the directory with
	 * setImagesPath(imageDirectory);
	 */
	private boolean directoryHasBeenSet = false;

	/**
	 * The imageDirectory
	 */
	private String imageDirectory;

	/**
	 * File-Separator of the System
	 */
	private static final String filesep = getProperty("file.separator");

	/**
	 * Initializes the ResourceStore to handle Images, Colors or Fonts
	 */
	public ResourceStoreNew(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * Set the ImagesPath to get Links which are located in a plugin-folder
	 * 
	 * @param imageDirectory
	 *            where to find the images.
	 */
	public void setImagesPath(String imageDirectory) {
		try {
			this.imageDirectory = pathEndsWithSeperator(imageDirectory) == true ? imageDirectory.substring(0,
					imageDirectory.length() - 1) : imageDirectory;

			// get the bundleDirectoryURL in the jar
			URL bundelDirectoryURL = bundle.getEntry(this.imageDirectory);

			// get all files in the cached directory
			File directory = new File(FileLocator.toFileURL(bundelDirectoryURL).getFile());

			// registering all images in this and all subdirectories
			getAllImages(directory);

		} catch (IOException e) {
			throw new RuntimeException("Error while handling the ImageDirectory \"" + imageDirectory + "\"",
					e);
		} catch (SecurityException sec) {
			throw new RuntimeException("Access to the ImageDirectory \"" + imageDirectory + "\"denied", sec);
		}
		directoryHasBeenSet = true;
	}

	/**
	 * runs through all directory of the given imageDirectory and gets all
	 * Images
	 * 
	 * @param directory
	 *            where to list all files and directory
	 */
	public void getAllImages(File directory) {
		File[] files = directory.listFiles();

		for (File file : files) {
			if (file.isFile()) {
				registerImages(file);
			} else {
				getAllImages(file);
			}
		}
	}

	/**
	 * Check if the given path ends with a file.separator
	 * 
	 * @param imageDirectory
	 *            the Directory where to find the images in the plugin
	 * @return boolean if the path ends with /
	 */
	private boolean pathEndsWithSeperator(String imageDirectory) {
		return imageDirectory.endsWith(filesep);
	}

	/**
	 * registering all images in the ImageRegistry so that user can easily
	 * access them.
	 */
	private void registerImages(File image) {
		System.out.println(relativePath(image.getAbsolutePath()));
		imageRegistry.put(relativePath(image.getAbsolutePath()), ImageDescriptor.createFromURL(Activator.getDefault().getBundle()
				.getEntry(relativePath(image.getAbsolutePath()))));
	}

	/**
	 * Gets the relative imageposition of the imagefolder
	 * 
	 * @param absolutepath
	 *            of the image
	 * @return relativepath of the image
	 */
	private String relativePath(String absolutePath) {
		String regexfilesep = filesep.equals("\\") ? filesep + "\\" : filesep;
		return relativePathBehindImageDirectory(absolutePath).replaceAll(regexfilesep, "/");
	}

	/**
	 * Gets the relative path behind the given imagedirectory
	 * 
	 * @param absolutePath
	 * 
	 * @return the relative path behind the imagedirectory
	 */
	private String relativePathBehindImageDirectory(String absolutePath) {
		return absolutePath.substring(indexBehindImageDirectory(absolutePath));
	}

	/**
	 * Determine the index behind the given imagedirectory
	 * 
	 * @param absolutePath
	 *            of the image
	 * 
	 * @return index behind the imageDirectory
	 */
	private int indexBehindImageDirectory(String absolutePath) {
		return absolutePath.indexOf(imageDirectory) + imageDirectory.length() + filesep.length();
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
	 *            next will be used. If no fontname is found there is fallback
	 *            to default
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
		}
		if (size <= 0) {
			throw new RuntimeException("Size is smaller or equals 0 but has to be greater - called size:"
					+ size);
		}
		if (testFontNames(fontNames)) {
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
