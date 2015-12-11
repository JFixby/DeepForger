package rebecca.e10.pipes;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.ObrazC2;

public class Image2ObrazC2 extends Pipe {
	Slot<BufferedImage> in;
	Slot<ObrazC2> out;
	ObrazC2 o1 = null;

	public Image2ObrazC2(Slot<BufferedImage> i, Slot<ObrazC2> out) {
		super("BufferedImage -> ObrazC2");
		if (i != null & out != null) {

			in = i;
			this.out = out;

			this.registerInSlot(i);
			this.registerOutSlot(out);

		}
	}

	public void Execute() {
		o1.GrubImage(this.in.GetDATA(this));
		this.out.PushDATA(o1, this);
	}

	public void Init() {
		this.o1 = new ObrazC2();
	}

	public void ShutDown() {
		// TODO Auto-generated method stub

	}

}
