package rebecca.e10.pipes;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.ObrazC4;

public class Image2ObrazC4 extends Pipe {
	Slot<BufferedImage> in;
	Slot<ObrazC4> out;
	ObrazC4 o1 = null;

	public Image2ObrazC4(Slot<BufferedImage> i, Slot<ObrazC4> out) {
		super("BufferedImage -> ObrazC4");
		if (i != null & out != null) {

			in = i;
			this.out = out;

			this.registerInSlot(i);
			this.registerOutSlot(out);

		}
	}

	public void Execute() {
		this.o1 = new ObrazC4();
		o1.GrubImage(this.in.GetDATA(this));
		this.out.PushDATA(o1, this);
	}

	public void Init() {

	}

	public void ShutDown() {
		// TODO Auto-generated method stub

	}

}
