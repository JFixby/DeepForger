package rebecca.e10.datasets;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ObrazC4 extends Obraz {
	private char A[][];
	private char R[][];
	private char G[][];
	private char B[][];
	private int H = 0;
	private int W = 0;
	private byte N = 4;

	public ObrazC4(String Name) {
		super(Name);
		this.setSize(0, 0);
	}

	public ObrazC4() {
		super();
		this.setSize(0, 0);
	}

	public ObrazC4(String Name, int H, int W) {
		super(Name);
		this.A = new char[H][W];
		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];
		this.H = H;
		this.W = W;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				A[i][j] = 0xff;
			}
		}

	}

	public void GrubImage(BufferedImage I) {
		this.H = I.getHeight();
		this.W = I.getWidth();

		this.A = new char[H][W];
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

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				/*
				 * r = (char)((pixels[y*w+x] >> 16) & 0xff); g =
				 * (char)((pixels[y*w+x] >> 8) & 0xff); b = (char)(pixels[y*w+x] &
				 * 0xff); gray = (char)(0.30f*r + 0.59f*g + 0.11f*b);
				 * pixels[y*w+x] = �;
				 */
				t = m[i * W + j];
				A[i][j] = (char) ((t & 0xff000000) >> 24);
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

	public BufferedImage GetBufferedImage() {

		int[] M = new int[this.H * this.W];
		int t = 0;
		byte r, g, b, a;
		for (int i = 0; i < this.H; i++) {
			for (int j = 0; j < this.W; j++) {
				a = (byte) ((A[i][j] & 0xff));
				r = (byte) (R[i][j] & 0xff);
				g = (byte) (G[i][j] & 0xff);
				b = (byte) (B[i][j] & 0xff);

				M[i * this.W + j] = (a << 24) + (r << 16) + (g << 8) + (b);
			}
		}
		return this.Image2BufferedImage(Toolkit.getDefaultToolkit()
				.createImage(
						new MemoryImageSource(this.W, this.H, M, 0, this.W)));

	}

	public Color getPixel(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			return new Color(A[i][j], R[i][j], G[i][j], B[i][j]);
		}
		return new Color(0, 0, 0);

	}

	public ObrazC4 Clone() {
		ObrazC4 oo = new ObrazC4();
		oo.A = this.A.clone();
		oo.R = this.R.clone();
		oo.G = this.G.clone();
		oo.B = this.B.clone();
		oo.H = H;
		oo.W = W;
		return oo;
	}

	public char getA(int i, int j) {
		if (i > -1 & j > -1 & i < H & j < W) {
			// TODO Auto-generated method stub
			return A[i][j];
		}
		return 0;
	}

	public ObrazC4(int H, int W) {
		super();
		this.A = new char[H][W];
		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];
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

		if (C == Chanell.ALPHA) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					o1.setVal(i, j, this.getA(i, j));
				}
			}
			return o1;
		}

		return null;
	};

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

			if (C == Chanell.ALPHA) {
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						this.setA(i, j, o1.getVal(i, j));
					}
				}

			}

		}
	};

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
			this.A[i][j] = (char) c.getAlpha();
			this.R[i][j] = (char) c.getRed();
			this.G[i][j] = (char) c.getGreen();
			this.B[i][j] = (char) c.getBlue();
		}

	}

	public void setA(int i, int j, char r) {
		// TODO Auto-generated method stub
		if (i > -1 & j > -1 & i < H & j < W) {
			this.A[i][j] = r;
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
		this.A = new char[H][W];
		this.R = new char[H][W];
		this.G = new char[H][W];
		this.B = new char[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				A[i][j] = 0xff;
			}
		}
		// TODO Auto-generated method stub

	}

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return this.GetBufferedImage();
	}

}