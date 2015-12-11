package rebecca.e10.datasets;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ObrazC2 extends Obraz {
	char D[][];
	char A[][];
	int H = 0;
	int W = 0;
	byte N = 2;

	public ObrazC2(String Name) {
		super(Name);
		this.setSize(0, 0);
	}

	public ObrazC2() {
		super();
		this.setSize(0, 0);
	}

	public ObrazC2(String Name, int H, int W) {
		super();
		this.D = new char[H][W];
		this.A = new char[H][W];
		this.H = H;
		this.W = W;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				A[i][j] = 0xff;
			}
		}

	}

	public ObrazC2(int H, int W) {
		super();
		this.D = new char[H][W];
		this.A = new char[H][W];
		this.H = H;
		this.W = W;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				A[i][j] = 0xff;
			}
		}

	}

	public ObrazC1 getChanell(Chanell C) {
		ObrazC1 o1 = new ObrazC1(H, W);
		if (C == Chanell.ALPHA) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					o1.setVal(i, j, this.getAlpha(i, j));
				}
			}
			return o1;
		}

		if (C == Chanell.VAL) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					o1.setVal(i, j, this.getVal(i, j));
				}
			}
			return o1;
		}

		return null;
	};

	public void putChanell(ObrazC1 o1, Chanell C) {
		if (o1 != null) {

			if (C == Chanell.VAL) {
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						this.setVal(i, j, o1.getVal(i, j));
					}
				}

			}
			if (C == Chanell.ALPHA) {
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						this.setAlpha(i, j, o1.getVal(i, j));
					}
				}

			}

		}
	};

	public ObrazC2 Clone() {
		ObrazC2 oo = new ObrazC2();
		oo.D = this.D.clone();
		oo.A = this.A.clone();
		oo.H = H;
		oo.W = W;
		return oo;
	}

	public void GrubImage(BufferedImage I) {
		this.H = I.getHeight();
		this.W = I.getWidth();

		this.D = new char[H][W];
		this.A = new char[H][W];

		int[] m = new int[H * W];
		PixelGrabber pg = new PixelGrabber(I, 0, 0, W, H, m, 0, W);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			System.err.println("interrupted waiting for pixels!");

		}
		int t = 0;

		char r, g, b, a;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				/*
				 * r = (char)((pixels[y*w+x] >> 16) & 0xff); g =
				 * (char)((pixels[y*w+x] >> 8) & 0xff); b = (char)(pixels[y*w+x] &
				 * 0xff); gray = (char)(0.30f*r + 0.59f*g + 0.11f*b);
				 * pixels[y*w+x] = …;
				 */
				t = m[i * W + j];
				a = (char) (((t & 0xff000000) >> 24));
				r = (char) ((t & 0x00ff0000) >> 16);
				g = (char) ((t & 0x0000ff00) >> 8);
				b = (char) ((t & 0x000000ff));
				this.D[i][j] = (char) (0.30 * r + 0.59 * g + 0.11 * b);
				this.A[i][j] = a;

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
				M[i * this.W + j] = (((A[i][j] & 0xff)) << 24)
						+ ((D[i][j] & 0xff) << 16) + ((D[i][j] & 0xff) << 8)
						+ ((D[i][j] & 0xff));
			}
		}
		return this.Image2BufferedImage(Toolkit.getDefaultToolkit()
				.createImage(
						new MemoryImageSource(this.W, this.H, M, 0, this.W)));

	}

	public Color getPixel(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			return new Color(A[i][j], D[i][j], D[i][j], D[i][j]);
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

	public char getAlpha(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return A[i][j];
		}
		return 0;
	}

	public void setPixel(int i, int j, Color c) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.D[i][j] = (char) (0.30 * c.getRed() + 0.59 * c.getGreen() + 0.11 * c
					.getBlue());
			this.A[i][j] = (char) c.getAlpha();

		}

	}

	public void setVal(int i, int j, char r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.D[i][j] = r;
		}

	}

	public void setAlpha(int i, int j, char r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.A[i][j] = r;
		}

	}

	public void setSize(int H, int W) {
		this.H = H;
		this.W = W;

		this.D = new char[H][W];
		this.A = new char[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				A[i][j] = 0xff;
			}
		}

	}

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return this.GetBufferedImage();
	}

}
