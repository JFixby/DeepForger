package rebecca.e10.pipes;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.SuperPixel;

public class SuperPixel2Image extends Pipe {
	Slot<BufferedImage> out;
	Slot<SuperPixel> in;

	public SuperPixel2Image(Slot<SuperPixel> in, Slot<BufferedImage> bi) {
		super("SuperPixel -> BufferedImage");
		if (in != null & bi != null) {

			this.in = in;
			out = bi;

			this.registerInSlot(in);
			this.registerOutSlot(bi);

		}
	}

	public void Execute() {
		this.out.PushDATA(this.in.GetDATA(this).GetBufferedImage(), this);
	}

	public void Init() {

	}

	public void ShutDown() {

	}

}
