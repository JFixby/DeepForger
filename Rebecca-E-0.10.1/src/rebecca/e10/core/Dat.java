package rebecca.e10.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Dat extends NObject {

	public Dat(String Name) {
		super(Name);
		// TODO Auto-generated constructor stub
	}

	public Dat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public abstract Dat Clone();

	public synchronized BufferedImage getImageEmpty() {
		BufferedImage bi = new BufferedImage(100, 100,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, 100 - 1, 100 - 1);
		g.drawLine(0, 0, 100 - 1, 100 - 1);

		return bi;
	}

	public synchronized BufferedImage getImageEmpty(int H, int W) {
		BufferedImage bi = new BufferedImage(H, W, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, 100 - 1, 100 - 1);
		g.drawLine(0, 0, 100 - 1, 100 - 1);

		return bi;
	}

	public abstract BufferedImage getImage();

}
