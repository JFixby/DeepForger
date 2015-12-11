//package rebecca.e10.pipelines;
//
//import java.awt.image.BufferedImage;
//
//import rebecca.e10.core.Pipe;
//import rebecca.e10.core.PipeLine;
//import rebecca.e10.core.Slot;
//import rebecca.e10.pipes.CameraCapture;
//
//public class CameraCaptureLine extends PipeLine {
//
//	public CameraCaptureLine(Slot<BufferedImage> bi) {
//		super("Camera Capturing PipeLine");
//		Pipe L = new CameraCapture(bi);
//		this.registerOutSlot(bi);
//		this.registerPipe(L);
//	}
//
//}
