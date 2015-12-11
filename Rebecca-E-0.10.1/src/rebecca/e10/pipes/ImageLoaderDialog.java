package rebecca.e10.pipes;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;

public class ImageLoaderDialog extends Pipe {
	private String Path;

	private Slot<BufferedImage> out;
	Image LI;
	boolean l = false;

	public ImageLoaderDialog(Slot<BufferedImage> out) {
		super("Load File");

		if ((out != null)) {

			this.out = out;

			this.registerOutSlot(out);
		} else {
			this.setDamage();
		}

	}

	public void Init() {
		while (!l) {

			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());

			} catch (Throwable error) {
				System.out.println(error.getMessage());
				error.printStackTrace(System.out);
			}
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setDialogTitle("Choose any image File");

			int result = chooser.showOpenDialog(null);

			if (result == JFileChooser.CANCEL_OPTION) {
				System.exit(0);
			}

			if (result == JFileChooser.APPROVE_OPTION) {
				String Fname = chooser.getSelectedFile().getPath();
				this.Path = Fname;

				Image LI = Toolkit.getDefaultToolkit().createImage(Path);
				if (LI != null) {
					l = !l;
				}

			}
		}
		// Here you should check your path;
	}

	public void Execute() {
		Image LI = Toolkit.getDefaultToolkit().createImage(Path);
		if (LI != null) {
			BufferedImage bi = this.toBufferedImage(LI);
			this.out.PushDATA(bi, this);
		}
	}

	public void ShutDown() {

	}

	public BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels
		boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha == true) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image
					.getHeight(null), transparency);
		} catch (HeadlessException e) {
		} // No screen

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha == true) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null), image
					.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	public boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			return ((BufferedImage) image).getColorModel().hasAlpha();
		}

		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
		}

		// Get the image's color model
		return pg.getColorModel().hasAlpha();
	}

}
