package rebecca.e10.datasets;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ObrazC3 extends Obraz {
	private char R[][];
	private char G[][];
	private char B[][];
	private int H = 0;
	private int W = 0;
	private byte N = 3;

	public ObrazC3(String Name) {
		super(Name);
		this.setSize(0, 0);
	}

	public ObrazC3() {
		super();
		this.setSize(0, 0);
	}

	public ObrazC3 Clone() {
		ObrazC3 oo = new ObrazC3();
		oo.R = this.R.clone();
		oo.G = this.G.clone();
		oo.B = this.B.clone();
		oo.H = H;
		oo.W = W;
		return oo;
	}

	public ObrazC3(String Name, int H, int W) {
		super(Name);
		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];
		this.H = H;
		this.W = W;

	}

	public ObrazC3(int H, int W) {
		super();
		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];
		this.H = H;
		this.W = W;

	}

	public void GrubImage(BufferedImage I) {
		this.H = I.getHeight();
		this.W = I.getWidth();

		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];

		int[] m = new int[H * W];
		PixelGrabber pg = new PixelGrabber(I, 0, 0, W, H, m, 0, W);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			System.err.println("interrupted waiting for pixels!");

		}
		int t = 0;
		char by = 0;
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
				R[i][j] = (char) ((t & 0x00ff0000) >> 16);
				G[i][j] = (char) ((t & 0x0000ff00) >> 8);
				B[i][j] = (char) ((t & 0x000000ff));

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

	public void putChanell(ObrazC1 o1, Chanell C) {
		if (o1 != null) {

			if (C == Chanell.RED) {
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						this.setR(i, j, o1.getVal(i, j));
					}
				}

			}
			if (C == Chanell.GREEN) {
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						this.setG(i, j, o1.getVal(i, j));
					}
				}

			}
			if (C == Chanell.BLUE) {
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						this.setB(i, j, o1.getVal(i, j));
					}
				}

			}

		}
	};

	public ObrazC1 getChanell(Chanell C) {
		ObrazC1 o1 = new ObrazC1(H, W);
		if (C == Chanell.RED) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					o1.setVal(i, j, this.getR(i, j));
				}
			}
			return o1;
		}
		if (C == Chanell.GREEN) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					o1.setVal(i, j, this.getG(i, j));
				}
			}
			return o1;
		}
		if (C == Chanell.BLUE) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					o1.setVal(i, j, this.getB(i, j));
				}
			}
			return o1;
		}
		return null;
	};

	public BufferedImage GetBufferedImage() {

		int[] M = new int[this.H * this.W];
		int t = 0;

		for (int i = 0; i < this.H; i++) {
			for (int j = 0; j < this.W; j++) {
				M[i * this.W + j] = ((0xff) << 24) + ((R[i][j] & 0xff) << 16)
						+ ((G[i][j] & 0xff) << 8) + ((B[i][j] & 0xff));
			}
		}
		return this.Image2BufferedImage(Toolkit.getDefaultToolkit()
				.createImage(
						new MemoryImageSource(this.W, this.H, M, 0, this.W)));

	}

	public Color getPixel(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			return new Color(R[i][j], G[i][j], B[i][j]);
		}
		return new Color(0, 0, 0);

	}

	public char getR(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return R[i][j];
		}
		return 0;
	}

	public char getG(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return G[i][j];
		}
		return 0;
	}

	public char getB(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return B[i][j];
		}
		return 0;
	}

	public void setPixel(int i, int j, Color c) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.R[i][j] = (char) c.getRed();
			this.G[i][j] = (char) c.getGreen();
			this.B[i][j] = (char) c.getBlue();
		}

	}

	public void setR(int i, int j, char r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.R[i][j] = r;
		}

	}

	public void setG(int i, int j, char g) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.G[i][j] = g;
		}

	}

	public void setB(int i, int j, char b) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.B[i][j] = b;
		}

	}

	public void setSize(int H, int W) {
		this.H = H;
		this.W = W;

		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];
		// TODO Auto-generated method stub

	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return this.GetBufferedImage();
	}

}
