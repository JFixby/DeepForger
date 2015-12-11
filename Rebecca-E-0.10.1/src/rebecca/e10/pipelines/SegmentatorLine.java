package rebecca.e10.pipelines;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.PipeLine;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.ObrazC1;
import rebecca.e10.datasets.SuperPixel;
import rebecca.e10.pipes.GUI;
import rebecca.e10.pipes.Image2ObrazC1;
import rebecca.e10.pipes.Screen;
import rebecca.e10.pipes.SegmentatorC1;
import rebecca.e10.pipes.SuperPixel2Image;

public class SegmentatorLine extends PipeLine {
	public SegmentatorLine(Slot<BufferedImage> input) {
		super("Segmentator");

		Slot<ObrazC1> s = new Slot<ObrazC1>();
		Slot<SuperPixel> ss = new Slot<SuperPixel>();
		Slot<BufferedImage> s5 = new Slot<BufferedImage>();
		Slot<String[]> st = new Slot<String[]>();

		Pipe p = new Image2ObrazC1(input, s);
		Pipe r = new GUI(st);
		Pipe q = new SegmentatorC1(s, st, ss);

		/*
		 * Slot<ObrazC3> s=new Slot<ObrazC3>(); Slot<SuperPixel> ss=new Slot<SuperPixel>();
		 * Slot<BufferedImage> s5=new Slot<BufferedImage>();
		 * 
		 * Pipe p=new Image2ObrazC3(input,s); Pipe q=new SegmentatorC3(s,ss);
		 */

		Pipe m = new SuperPixel2Image(ss, s5);
		Pipe f = new Screen("Result", s5);
		int i = 0;
		p.SetName("#Pipe " + (i + 5000) + "#");
		i++;
		q.SetName("#Pipe " + (i + 5000) + "#");
		i++;
		m.SetName("#Pipe " + (i + 5000) + "#");
		i++;
		f.SetName("#Pipe " + (i + 5000) + "#");

		// this.registerInternalSlot(S);
		this.registerPipe(p);
		this.registerPipe(q);
		this.registerPipe(r);
		this.registerPipe(m);
		this.registerPipe(f);
	}

}
