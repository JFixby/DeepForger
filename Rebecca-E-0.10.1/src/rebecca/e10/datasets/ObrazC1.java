package rebecca.e10.datasets;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ObrazC1 extends Obraz {
	char D[][];
	int H = 0;
	int W = 0;
	byte N = 1;

	public ObrazC1(String Name) {
		super(Name);
		this.setSize(0, 0);
	}

	public ObrazC1() {
		super();
		this.setSize(0, 0);
	}

	public ObrazC1(String Name, int H, int W) {
		super();
		this.D = new char[H][W];
		this.H = H;
		this.W = W;

	}

	public ObrazC1(int H, int W) {
		super();
		this.D = new char[H][W];
		this.H = H;
		this.W = W;
	}

	public ObrazC1 Clone() {
		ObrazC1 oo = new ObrazC1();
		oo.D = this.D.clone();
		oo.H = H;
		oo.W = W;
		return oo;
	}

	public void GrubImage(BufferedImage I) {
		this.H = I.getHeight();
		this.W = I.getWidth();

		this.D = new char[H][W];

		int[] m = new int[H * W];
		PixelGrabber pg = new PixelGrabber(I, 0, 0, W, H, m, 0, W);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			System.err.println("interrupted waiting for pixels!");

		}
		int t = 0;

		char r, g, b;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				/*
				 * r = (char)((pixels[y*w+x] >> 16) & 0xff); g =
				 * (char)((pixels[y*w+x] >> 8) & 0xff); b = (char)(pixels[y*w+x] &
				 * 0xff); gray = (char)(0.30f*r + 0.59f*g + 0.11f*b);
				 * pixels[y*w+x] = …;
				 */
				t = m[i * W + j];
				r = (char) ((t & 0x00ff0000) >> 16);
				g = (char) ((t & 0x0000ff00) >> 8);
				b = (char) ((t & 0x000000ff));
				this.D[i][j] = (char) (0.30 * r + 0.59 * g + 0.11 * b);

			}
		}

		// this.D=D;
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
		int t = 0;

		for (int i = 0; i < this.H; i++) {
			for (int j = 0; j < this.W; j++) {
				M[i * this.W + j] = ((0xff) << 24) + ((D[i][j] & 0xff) << 16)
						+ ((D[i][j] & 0xff) << 8) + ((D[i][j] & 0xff));
			}
		}
		return this.Image2BufferedImage(Toolkit.getDefaultToolkit()
				.createImage(
						new MemoryImageSource(this.W, this.H, M, 0, this.W)));

	}

	public Color getPixel(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			return new Color(D[i][j], D[i][j], D[i][j]);
		}
		return new Color(0, 0, 0);

	}

	public char getVal(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return D[i][j];
		}
		return 0;
	}

	public void setPixel(int i, int j, Color c) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.D[i][j] = (char) (0.30 * c.getRed() + 0.59 * c.getGreen() + 0.11 * c
					.getBlue());

		}

	}

	public void setVal(int i, int j, char r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.D[i][j] = r;
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

	public ObrazC2 toObrazC2() {
		ObrazC2 o = new ObrazC2(this.H, this.W);

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				o.setAlpha(i, j, (char) 255);
				o.setVal(i, j, this.getVal(i, j));
			}
		}

		return o;
	}

	public ObrazC1 CopyRect(int x, int y, int h, int w) {
		ObrazC1 o = new ObrazC1(h, w);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				o.setVal(i, j, this.getVal(i + x, j + y));
			}
		}

		return o;

	}

}
