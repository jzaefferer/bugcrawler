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

/**
 * A ResourceStore which easily handles images and colors in plugin-packages
 * 
 * @author TSO
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

	/**
	 * Font-Constants that are normaly cross-platform compatible
	 */
	public static enum DefaultFont {
		Arial, Helvetica, Times, Courier, Symbol
	}

	/**
	 * Interface which should be implementet by enums
	 */
	public interface ImagePath {
		String getPath();
	}

	/**
	 * Color-Constants
	 */
	public static enum DefaultColor {
		Black, White, Red, Green, Blue, Magenta, Cyan, Yellow, Orange, Brown, Gray
	}

	/**
	 * Plugin-Bundle
	 */
	private Bundle bundle;

	/**
	 * The imageDirectory
	 */
	private String imageDirectory;

	/**
	 * Plugin Directory
	 */
	private File directory;

	/**
	 * LazyLoad for Images
	 */
	private boolean lazyLoad;

	/**
	 * File-Separator of the System
	 */
	private static final String filesep = System.getProperty("file.separator");

	/**
	 * Initializes the ResourceStore to handle Images, Colors or Fonts
	 */
	public ResourceStore(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * Set the ImagesPath to get Links which are located in a plugin-folder
	 * 
	 * @param imageDirectory
	 *            where to find the images.
	 * @param boolean
	 *            if images should be loaded lazyly.
	 */
	public void setImagesPath(String imageDirectory, boolean lazyLoad) {
		try {
			this.imageDirectory = pathEndsWithSeperator(imageDirectory) == true ? imageDirectory
					.substring(0, imageDirectory.length() - filesep.length())
					: imageDirectory;

			// get the bundleDirectoryURL in the jar
			URL bundelDirectoryURL = bundle.getEntry(this.imageDirectory);

			// get all files in the cached directory
			directory = new File(FileLocator.toFileURL(bundelDirectoryURL)
					.getFile());

			// lazyLoad as attribut for global availability
			this.lazyLoad = lazyLoad;

			if (!this.lazyLoad) {
				// registering all images in this and all subdirectories
				getAllImages(directory);
			}

		} catch (IOException e) {
			throw new RuntimeException(
					"Error while handling the ImageDirectory \""
							+ imageDirectory + "\"", e);
		} catch (SecurityException sec) {
			throw new RuntimeException("Access to the ImageDirectory \""
					+ imageDirectory + "\"denied", sec);
		}
	}
	
	/**
	 * Set the ImagesPath to get Links which are located in a plugin-folder
	 * 
	 * Images will be loaded lazyly!
	 * 
	 * @param imageDirectory
	 *            where to find the images.
	 */
	public void setImagesPath(String imageDirectory) {
		setImagesPath(imageDirectory, true);
	}

	/**
	 * runs through all directory of the given imageDirectory and gets all
	 * Images
	 * 
	 * @param directory
	 *            where to list all files and directory
	 */
	private void getAllImages(File directory) {
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
	private Image registerImages(File image) {
		imageRegistry.put(relativePath(image.getAbsolutePath()),
				ImageDescriptor.createFromURL(bundle
						.getEntry(relativePathWithImageDirectory(image
								.getAbsolutePath()))));
		return imageRegistry.get(relativePath(image.getAbsolutePath()));
	}

	/**
	 * Gets the relative imageposition with the imagefolder
	 * 
	 * @param absolutepath
	 *            of the image
	 * @return relativepath of the image
	 */
	private String relativePath(String absolutePath) {
		return replaceSeparators(absolutePath.substring(absolutePath
				.indexOf(imageDirectory)
				+ imgDirWithSepLength()));
	}

	/**
	 * length of imagedirectory with fileseparator
	 * 
	 * @return length
	 */
	private int imgDirWithSepLength() {
		return imageDirectory.length() + filesep.length();
	}

	/**
	 * Gets the relative imageposition with the imagefolder
	 * 
	 * @param absolutepath
	 *            of the image
	 * @return relativepath of the image
	 */
	private String relativePathWithImageDirectory(String absolutePath) {
		return replaceSeparators(absolutePath.substring(absolutePath
				.indexOf(imageDirectory)));
	}

	/**
	 * Replaces Separators to "/"
	 * 
	 * @param path
	 *            of which the separators should be replaced
	 * 
	 * @return path with replaced separators
	 */
	private String replaceSeparators(String path) {
		String regexfilesep = filesep.equals("\\") ? filesep + "\\" : filesep;
		return path.replaceAll(regexfilesep, "/");
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param pluginID
	 *            ID of the plugin
	 * @param path
	 *            the path to an image including the imagename
	 * @return the image descriptor of the image to the given path
	 */
	public static ImageDescriptor getImageDescriptor(String pluginID,
			String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(pluginID, path);
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
		if (imageDirectory == null) {
			throw new RuntimeException(
					"Image Directory hasn't been set - set it with store.setImagePath(String).");
		}

		// if lazyLoad register Images when they are needed
		Image image = imageRegistry.get(imageName);
		if (image == null) {
			if (lazyLoad) {
				image = registerImages(new File(replaceSeparators(directory
						.getAbsolutePath()
						+ "/" + imageName)));
			} else {
				throw new RuntimeException("Image not found in the registry - called name:"+ imageName);
			}
		}
		return image;
	}

	/**
	 * Gets an Image out of the ResourceStore.
	 * 
	 * <br>
	 * <br>
	 * Example for enum class:
	 * 
	 * <pre>
	 * enum Enum implements ImagePath {
	 * 
	 * SOLVED(&quot;solved.png&quot;),
	 * NOTSOLVE(&quot;notsolved.png&quot;);
	 * private String path;
	 * 
	 * Images(String path)  {
	 * 	this.path = path;
	 * }
	 * public String getPath() {
	 * 	return path;
	 * }
	 * }
	 * </pre>
	 * 
	 * Example for call:<br>
	 * <br>
	 * <code>resourceStore.getImage(Enum.SOLVED);</code><br>
	 * <br>
	 * 
	 * Warning: It is required to set the ImagePath. The path is the with <code>
	 * ResourceStore store = new ResourceStore();
	 * store.setImagePath("<my_image_directory_in_the_root_of_the_plugin>");
	 * </code>
	 * 
	 * @param enum
	 *            which implements the interface ImagePath
	 * 
	 * @return the image
	 */
	public Image getImage(ImagePath imageName) {
		return getImage(imageName.getPath());
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
		if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255
				|| blue < 0) {
			throw new RuntimeException(
					"Values of RGB are not valid - called values:RED-" + red
							+ ",GREEN-" + green + ",BLUE-" + blue);
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
	 * Get a DefaultColor out of the ResourceStore
	 * 
	 * Example: <code>new ResourceStore().getColor(DefaultColor.Red);</code>
	 * 
	 * @param DefaultColor
	 *            a defaultcolor shared by the ResourceStore
	 * 
	 * @return Color the new color registered in a ColorRegistry
	 */
	public Color getDefaultColor(ResourceStore.DefaultColor defaultColor) {
		switch (defaultColor) {
		case Black:
			return getColor(0, 0, 0);
		case White:
			return getColor(255, 255, 255);
		case Red:
			return getColor(255, 0, 0);
		case Green:
			return getColor(0, 255, 0);
		case Blue:
			return getColor(0, 0, 255);
		case Magenta:
			return getColor(255, 0, 255);
		case Cyan:
			return getColor(0, 255, 255);
		case Yellow:
			return getColor(255, 255, 0);
		case Orange:
			return getColor(255, 165, 0);
		case Brown:
			return getColor(165, 42, 42);
		case Gray:
			return getColor(128, 128, 128);
		default:
			return getColor(0, 0, 0);
		}
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
	 *            or a combination SWT.BOLD|SWT.ITALIC
	 * @return Font configured by the given values
	 */
	public Font getFont(String[] fontNames, int size, int style) {
		if (!(style == SWT.BOLD || style == SWT.ITALIC || style == SWT.NORMAL)) {
			throw new RuntimeException(
					"No valid style, please use SWT.BOLD,SWT.ITALIC or SWT.NORMAL - called style as int:"
							+ style);
		}
		if (size <= 0) {
			throw new RuntimeException(
					"Size is smaller or equals 0 but has to be greater - called size:"
							+ size);
		}
		if (testFontNames(fontNames)) {
			throw new RuntimeException(
					"One or more Fontnames are null - called fontnames:"
							+ fontNames);
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
	 * Get a DefaultFont out of the ResourceStore
	 * 
	 * Example:
	 * <code>new ResourceStore().getFont(DefaultFont.Arial, 20, SWT.NORMAL);</code>
	 * 
	 * @param DefaultFont
	 *            A defaultfont shared by the ResourceStore
	 * @param size
	 *            the size of the font in points
	 * @param style
	 *            the style of the font use SWT.BOLD, SWT.ITALIC or SWT.NORMAL
	 *            or a combination SWT.BOLD|SWT.ITALIC
	 * @return Font configured by the given values
	 */
	public Font getDefaultFont(ResourceStore.DefaultFont defaultFont, int size,
			int style) {
		return getFont(new String[] { defaultFont.toString() }, size, style);
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
