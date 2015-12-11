/*
Rebecca-E
    Copyright (C) 2008  by Sergey Stankevich (robotics@icstweb.org),

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


 */

package rebecca.e10.datasets;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import rebecca.e10.core.Dat;

public class SuperPixel extends Dat {
	private char D[][];
	private int H = 0;
	private int W = 0;
	private byte N = 1;

	public SuperPixel(String Name) {
		super(Name);
		this.setSize(0, 0);
	}

	public SuperPixel() {
		super();
		this.setSize(0, 0);
	}

	public SuperPixel(String Name, int H, int W) {
		super();
		this.D = new char[H][W];
		this.H = H;
		this.W = W;

	}

	public SuperPixel(int H, int W) {
		super();
		this.D = new char[H][W];
		this.H = H;
		this.W = W;
	}

	public SuperPixel Clone() {
		SuperPixel oo = new SuperPixel();
		oo.D = this.D.clone();
		oo.H = H;
		oo.W = W;
		return oo;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return H;
	}

	public byte getLayersAmount() {
		// TODO Auto-generated method stub
		return N;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return W;
	}

	public BufferedImage GetBufferedImage() {

		int[] M = new int[this.H * this.W];
		int m = 0, n = 0, p = 0, t = 0;
		for (int i = 0; i < this.H; i++) {
			for (int j = 0; j < this.W; j++) {
				t = D[i][j];
				m = 0;
				n = 0;
				p = 0;
				while (t >= 64 * 8) {
					t = t - 64;
				}
				while (t >= 64) {
					t = t - 64;
					m = m + 1;
				}
				while (t >= 8) {
					t = t - 8;
					n = n + 1;
				}
				p = t;

				// m=0;
				// p=2;
				// n=2;
				if (m == 0 & n == 0 & p == 0) {
					M[i * this.W + j] = 0;
				} else {
					M[i * this.W + j] = ((255) << 24)
							+ ((0xff & (n * 36)) << 16)
							+ ((0xff & (p * 36)) << 8) + ((0xff & (m * 36)));
				}

			}
		}
		return this.Image2BufferedImage(Toolkit.getDefaultToolkit()
				.createImage(
						new MemoryImageSource(this.W, this.H, M, 0, this.W)));

	}

	public char getVal(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return D[i][j];
		}
		return 0;
	}

	public void setVal(int i, int j, char r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.D[i][j] = r;
		}

	}

	public void setVal(int i, int j, int r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.D[i][j] = (char) r;
		}

	}

	public void setSize(int H, int W) {
		this.H = H;
		this.W = W;

		this.D = new char[H][W];

	}

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return this.GetBufferedImage();
	}

	public BufferedImage Image2BufferedImage(Image image) {
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
				transparency = Transparency.TRANSLUCENT;
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
				type = BufferedImage.TYPE_INT_RGB;
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
