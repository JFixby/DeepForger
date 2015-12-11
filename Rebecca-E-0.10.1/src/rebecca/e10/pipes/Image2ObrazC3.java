package rebecca.e10.pipes;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.ObrazC3;

public class Image2ObrazC3 extends Pipe {
	Slot<BufferedImage> in;
	Slot<ObrazC3> out;
	ObrazC3 o1 = null;

	public Image2ObrazC3(Slot<BufferedImage> i, Slot<ObrazC3> out) {
		super("BufferedImage -> ObrazC3");
		if (i != null & out != null) {

			in = i;
			this.out = out;

			this.registerInSlot(i);
			this.registerOutSlot(out);

		}
	}

	public void Execute() {
		this.o1 = new ObrazC3();
		o1.GrubImage(this.in.GetDATA(this));
		this.out.PushDATA(o1, this);
	}

	public void Init() {

	}

	public void ShutDown() {
		// TODO Auto-generated method stub

	}

}
