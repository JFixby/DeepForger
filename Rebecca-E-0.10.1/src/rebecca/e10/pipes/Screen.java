package rebecca.e10.pipes;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Date;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;

public class Screen extends Pipe {
	private Slot<BufferedImage> in;
	private IV View = null;
	private String WindowName;

	public Screen(String WindowName, Slot<BufferedImage> in) {
		super("View Screen");
		this.WindowName = WindowName;
		if (in != null) {

			this.in = in;
			this.registerInSlot(in);

		} else {
			this.setDamage();
		}

	}

	public void Execute() {
		// View.I=this.I;
		View.I = this.in.GetDATA(this);
		this.View.setSize(View.I.getWidth(), View.I.getHeight());
		Date dat = new Date();
		View.setTitle(this.WindowName + " - " + dat.toLocaleString());
		View.repaint();

	}

	public void Init() {
		View = new IV(this);
		View.addWindowListener(new WindowAdapter(this));
		;

		View.addKeyListener(null);

	}

	class WindowAdapter implements WindowListener {
		Screen s;

		WindowAdapter(Screen s) {
			this.s = s;
		}

		public void windowClosing(WindowEvent ev) {
			this.s.ShutDown();
		}

		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void ShutDown() {
		View.dispose();
		System.exit(0);
	}

	class IV extends Frame {
		BufferedImage I = null;
		Screen s = null;

		IV(Screen s) {
			this.s = s;
			setVisible(true);
		}

		public void setSize(int W, int H) {
			super.setSize(W + 10, H + 36);
		};

		public void paint(Graphics g) {
			g.drawImage(I, 5, 31, this);

		}

		public void update(Graphics g) {
			paint(g);
		}

	}

}