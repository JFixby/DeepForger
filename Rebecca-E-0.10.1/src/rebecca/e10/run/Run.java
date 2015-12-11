package rebecca.e10.run;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Slot;
//import rebecca.e10.pipelines.CameraCaptureLine;
import rebecca.e10.pipelines.SegmentatorLine;
import rebecca.e10.pipes.ImageLoader;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Slot<BufferedImage> iii = new Slot<BufferedImage>();
		//CameraCaptureLine CC = new CameraCaptureLine(iii);
		Slot<String> in = new Slot<String>("1247641972_podborka_643_95.jpg");
		in.SetAlwaysFresh();
		ImageLoader CC=new ImageLoader(in,iii);
		SegmentatorLine S = new SegmentatorLine(iii);

		CC.Init();
		S.Init();
		CC.Start();
		S.Start();
		/*
		 * ObrazC1 o=new ObrazC1(); //o.LoadFile("D:\\2.JPG"); //1.bmp
		 * o.LoadFile("D:\\1.PNG");
		 * 
		 * Slot<ObrazC1> i=new Slot<ObrazC1>(o); Slot<SuperPixel> ii=new Slot<SuperPixel>();
		 * Slot<BufferedImage> iii=new Slot<BufferedImage>();
		 * 
		 * Pipe P = new SpectralClustererPipe(i,ii);
		 * 
		 * Pipe P1 = new SuperPixel2Image(ii,iii); Pipe P2 = new
		 * Screen("?",iii); P.Init(); P1.Init(); P2.Init(); P.Execute();
		 * P1.Execute(); P2.Execute();
		 */

	}

}
