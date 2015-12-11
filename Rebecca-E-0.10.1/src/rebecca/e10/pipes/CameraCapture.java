//package rebecca.e10.pipes;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.util.StringTokenizer;
//
//import javax.media.Buffer;
//import javax.media.CaptureDeviceInfo;
//import javax.media.CaptureDeviceManager;
//import javax.media.Codec;
//import javax.media.ConfigureCompleteEvent;
//import javax.media.ControllerEvent;
//import javax.media.ControllerListener;
//import javax.media.DataSink;
//import javax.media.EndOfMediaEvent;
//import javax.media.Format;
//import javax.media.Manager;
//import javax.media.MediaLocator;
//import javax.media.PrefetchCompleteEvent;
//import javax.media.Processor;
//import javax.media.RealizeCompleteEvent;
//import javax.media.ResourceUnavailableEvent;
//import javax.media.UnsupportedPlugInException;
//import javax.media.control.FormatControl;
//import javax.media.control.TrackControl;
//import javax.media.datasink.DataSinkEvent;
//import javax.media.datasink.DataSinkListener;
//import javax.media.format.AudioFormat;
//import javax.media.format.RGBFormat;
//import javax.media.format.VideoFormat;
//import javax.media.protocol.CaptureDevice;
//import javax.media.protocol.DataSource;
//import javax.media.util.BufferToImage;
//
//import rebecca.e10.core.Pipe;
//import rebecca.e10.core.Slot;
//
///**
//
// 0. javax.media.format.RGBFormat
// RGB, 352x288, Length=304128, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=1056, Flipped
// 1. javax.media.format.RGBFormat
// RGB, 160x120, Length=57600, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=480, Flipped
// 2. javax.media.format.RGBFormat
// RGB, 176x144, Length=76032, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=528, Flipped
// 3. javax.media.format.RGBFormat
// RGB, 320x240, Length=230400, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=960, Flipped
// 4. javax.media.format.RGBFormat
// RGB, 640x480, Length=921600, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=1920, Flipped
// 5. javax.media.format.YUVFormat
// YUV Video Format: Size = java.awt.Dimension[width=160,height=120] MaxDataLength = 28800 DataType = class [B yuvType = 2 StrideY = 160 StrideUV = 80 OffsetY = 0 OffsetU = 19200 OffsetV = 24000
//
// 6. javax.media.format.YUVFormat
// YUV Video Format: Size = java.awt.Dimension[width=176,height=144] MaxDataLength = 38016 DataType = class [B yuvType = 2 StrideY = 176 StrideUV = 88 OffsetY = 0 OffsetU = 25344 OffsetV = 31680
//
// 7. javax.media.format.YUVFormat
// YUV Video Format: Size = java.awt.Dimension[width=320,height=240] MaxDataLength = 115200 DataType = class [B yuvType = 2 StrideY = 320 StrideUV = 160 OffsetY = 0 OffsetU = 76800 OffsetV = 96000
//
// 8. javax.media.format.YUVFormat
// YUV Video Format: Size = java.awt.Dimension[width=352,height=288] MaxDataLength = 152064 DataType = class [B yuvType = 2 StrideY = 352 StrideUV = 176 OffsetY = 0 OffsetU = 101376 OffsetV = 126720
//
// 9. javax.media.format.YUVFormat
// YUV Video Format: Size = java.awt.Dimension[width=640,height=480] MaxDataLength = 460800 DataType = class [B yuvType = 2 StrideY = 640 StrideUV = 320 OffsetY = 0 OffsetU = 307200 OffsetV = 384000
//
// */
//
//public class CameraCapture extends Pipe implements ControllerListener {
//	Slot<BufferedImage> out;
//
//	private boolean ready = false;
//	private static boolean debugDeviceList = false;
//
//	private static String defaultVideoDeviceName = "vfw:Microsoft WDM Image Capture (Win32):0";
//	// private static String defaultAudioDeviceName = "DirectSoundCapture";
//	// private static String defaultVideoFormatString = "RGB, 160x120,
//	// Length=57600, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=480,
//	// Flipped";
//	private static String defaultVideoFormatString = "RGB, 640x480, Length=921600, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=1920, Flipped";
//	// private static String defaultAudioFormatString = "linear, 22050.0 hz,
//	// 16-bit, mono, littleendian, signed";
//
//	private static CaptureDeviceInfo captureVideoDevice = null;
//	// private static CaptureDeviceInfo captureAudioDevice = null;
//	private static VideoFormat captureVideoFormat = null;
//	// private static AudioFormat captureAudioFormat = null;
//
//	// Processor processor = null;
//	MyDataSinkListener dataSinkListener = null;
//	DataSink dataSink = null;
//	BufferedImage BI; // the last frame
//
//	public CameraCapture(Slot<BufferedImage> B) {
//		super("Usb-cam Capturing...");
//		if (B != null) {
//			this.out = B;
//			this.registerOutSlot(B);
//		}
//
//	}
//
//	public void Execute() {
//		synchronized (this.L) {
//
//			if (this.BI == null) {
//				int H = 20;
//				int W = 30;
//				BI = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
//				Graphics2D g = BI.createGraphics();
//				g.setColor(new Color(0, 0, 0));
//				g.setBackground(new Color(0, 0, 0));
//
//				g.drawRect(0, 0, W - 1, H - 1);
//				g.drawLine(W / 4, H / 4, 3 * W / 4 - 1, 3 * H / 4 - 1);
//				g.drawLine(W / 4, 3 * H / 4 - 1, 3 * W / 4 - 1, H / 4);
//
//			}
//
//			this.out.PushDATA(this.BI, this); // // Here you can get your
//			// frame (this.BI)
//
//		}
//	}
//
//	Object L = new Object();
//
//	public void Init() {
//
//		Stdout.log("get list of all media devices ...");
//		java.util.Vector deviceListVector = CaptureDeviceManager
//				.getDeviceList(null);
//		if (deviceListVector == null) {
//			Stdout
//					.log("... error: media device list vector is null, program aborted");
//			System.exit(0);
//		}
//		if (deviceListVector.size() == 0) {
//			Stdout
//					.log("... error: media device list vector size is 0, program aborted");
//			System.exit(0);
//		}
//
//		for (int x = 0; x < deviceListVector.size(); x++) {
//			// display device name
//			CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector
//					.elementAt(x);
//			String deviceInfoText = deviceInfo.getName();
//			if (debugDeviceList)
//				Stdout.log("device " + x + ": " + deviceInfoText);
//
//			// display device formats
//			Format deviceFormat[] = deviceInfo.getFormats();
//			for (int y = 0; y < deviceFormat.length; y++) {
//				// serach for default video device
//				if (captureVideoDevice == null)
//					if (deviceFormat[y] instanceof VideoFormat)
//						if (deviceInfo.getName()
//								.indexOf(defaultVideoDeviceName) >= 0) {
//							captureVideoDevice = deviceInfo;
//							Stdout.log(">>> capture video device = "
//									+ deviceInfo.getName());
//						}
//
//				// search for default video format
//				if (captureVideoDevice == deviceInfo)
//					if (captureVideoFormat == null)
//						if (DeviceInfo.formatToString(deviceFormat[y]).indexOf(
//								defaultVideoFormatString) >= 0) {
//							captureVideoFormat = (VideoFormat) deviceFormat[y];
//							Stdout.log(">>> capture video format = "
//									+ DeviceInfo
//											.formatToString(deviceFormat[y]));
//						}
//
//				if (debugDeviceList)
//					Stdout.log(" - format: "
//							+ DeviceInfo.formatToString(deviceFormat[y]));
//			}
//		}
//		Stdout.log("... list completed.");
//
//		// if args[x] = "-dd" terminate now
//		// --------------------------------
//		if (debugDeviceList)
//			System.exit(0);
//
//		// setup video data source
//		// -----------------------
//		MediaLocator videoMediaLocator = captureVideoDevice.getLocator();
//
//		try {
//			p = Manager.createProcessor(videoMediaLocator);
//		} catch (Exception e) {
//			System.err
//					.println("Failed to create a processor from the given url: "
//							+ e);
//
//		}
//
//		p.addControllerListener(this);
//
//		// Put the Processor into configured state.
//		p.configure();
//		if (!waitForState(Processor.Configured)) {
//			System.err.println("Failed to configure the processor.");
//
//		}
//
//		// So I can use it as a player.
//		p.setContentDescriptor(null);
//
//		// Obtain the track controls.
//		TrackControl tc[] = p.getTrackControls();
//
//		if (tc == null) {
//			System.err
//					.println("Failed to obtain track controls from the processor.");
//
//		}
//
//		// Search for the track control for the video track.
//		TrackControl videoTrack = null;
//
//		for (int i = 0; i < tc.length; i++) {
//			if (tc[i].getFormat() instanceof VideoFormat)
//				videoTrack = tc[i];
//			else
//				tc[i].setEnabled(false);
//		}
//
//		if (videoTrack == null) {
//			System.err
//					.println("The input media does not contain a video track.");
//
//		}
//		String videoFormat = this.defaultVideoFormatString;// videoTrack.getFormat().toString();
//		Dimension videoSize = parseVideoSize(videoFormat);
//		System.err.println("Video format: " + videoFormat);
//
//		// Instantiate and set the frame access codec to the data flow path.
//		try {
//			Codec codec[] = { new PostAccessCodec(videoSize, this) };
//			videoTrack.setCodecChain(codec);
//		} catch (UnsupportedPlugInException e) {
//			System.err.println("The process does not support effects.");
//		}
//
//		// Realize the processor.
//		p.prefetch();
//		if (!waitForState(Processor.Prefetched)) {
//			System.err.println("Failed to realise the processor.");
//
//		}
//		// implements ControllerListener
//		Stdout.log("Start ...");
//
//		p.start();
//
//		Stdout.log("Starting Camera ...");
//
//	}
//
//	public void controllerUpdate(ControllerEvent evt) {
//
//		if (evt instanceof ConfigureCompleteEvent
//				|| evt instanceof RealizeCompleteEvent
//				|| evt instanceof PrefetchCompleteEvent) {
//			synchronized (waitSync) {
//				stateTransitionOK = true;
//				waitSync.notifyAll();
//			}
//		} else if (evt instanceof ResourceUnavailableEvent) {
//			synchronized (waitSync) {
//				stateTransitionOK = false;
//				waitSync.notifyAll();
//			}
//		} else if (evt instanceof EndOfMediaEvent) {
//			p.close();
//			System.exit(0);
//		}
//	}
//
//	public Dimension parseVideoSize(String videoSize) {
//		int x = 352, y = 288;
//
//		StringTokenizer strtok = new StringTokenizer(videoSize, ", ");
//		strtok.nextToken();
//		String size = strtok.nextToken();
//		StringTokenizer sizeStrtok = new StringTokenizer(size, "x");
//		try {
//			x = Integer.parseInt(sizeStrtok.nextToken());
//			y = Integer.parseInt(sizeStrtok.nextToken());
//		} catch (NumberFormatException e) {
//			System.out
//					.println("unable to find video size, assuming default of 300x200");
//		}
//		System.out.println("Image width  = " + String.valueOf(x)
//				+ "\nImage height = " + String.valueOf(y));
//		return new Dimension(x, y);
//	}
//
//	Processor p;
//	Object waitSync = new Object();
//	boolean stateTransitionOK = true;
//	public boolean alreadyPrnt = false;
//
//	boolean waitForState(int state) {
//		synchronized (waitSync) {
//			try {
//				while (p.getState() != state && stateTransitionOK)
//					waitSync.wait();
//			} catch (Exception e) {
//			}
//		}
//		return stateTransitionOK;
//	}
//
//	public void ShutDown() {
//		Stdout.log("... capturing done");
//
//		// stop and close the processor when done capturing...
//		// close the datasink when EndOfStream event is received...
//		p.stop();
//		p.close();
//
//		Stdout.log("[all done]");
//	}
//
//	class PreAccessCodec implements Codec {
//
//		void accessFrame(Buffer frame) {
//
//		}
//
//		protected Format supportedIns[] = new Format[] { new VideoFormat(null) };
//		protected Format supportedOuts[] = new Format[] { new VideoFormat(null) };
//		Format input = null, output = null;
//
//		public String getName() {
//			return "Pre-Access Codec";
//		}
//
//		public void open() {
//		}
//
//		public void close() {
//		}
//
//		public void reset() {
//		}
//
//		public Format[] getSupportedInputFormats() {
//			return supportedIns;
//		}
//
//		public Format[] getSupportedOutputFormats(Format in) {
//			if (in == null)
//				return supportedOuts;
//			else {
//
//				Format outs[] = new Format[1];
//				outs[0] = in;
//				return outs;
//			}
//		}
//
//		public Format setInputFormat(Format format) {
//			input = format;
//			return input;
//		}
//
//		public Format setOutputFormat(Format format) {
//			output = format;
//			return output;
//		}
//
//		public int process(Buffer in, Buffer out) {
//
//			// This is the "Callback" to access individual frames.
//			accessFrame(in);
//
//			// Swap the data between the input & output.
//			Object data = in.getData();
//			in.setData(out.getData());
//			out.setData(data);
//
//			// Copy the input attributes to the output
//			out.setFlags(Buffer.FLAG_NO_SYNC);
//			out.setFormat(in.getFormat());
//			out.setLength(in.getLength());
//			out.setOffset(in.getOffset());
//
//			return BUFFER_PROCESSED_OK;
//		}
//
//		public Object[] getControls() {
//			return new Object[0];
//		}
//
//		public Object getControl(String type) {
//			return null;
//		}
//	}
//
//	class PostAccessCodec extends PreAccessCodec {
//		// We'll advertize as supporting all video formats.
//		CameraCapture c;
//
//		public PostAccessCodec(Dimension size, CameraCapture cameraCapture) {
//			supportedIns = new Format[] { new RGBFormat() };
//			this.size = size;
//			this.c = cameraCapture;
//		}
//
//		/**
//		 * Callback to access individual video frames.
//		 */
//		void accessFrame(Buffer frame) {
//
//			if (!alreadyPrnt) {
//				BufferToImage stopBuffer = new BufferToImage(
//						(VideoFormat) frame.getFormat());
//				Image stopImage = stopBuffer.createImage(frame);
//
//				BufferedImage outImage = new BufferedImage(size.width,
//						size.height, BufferedImage.TYPE_INT_RGB);
//				Graphics og = outImage.getGraphics();
//				og.drawImage(stopImage, 0, 0, size.width, size.height, null);
//				this.c.setBI(outImage);
//			}
//		}
//
//		public String getName() {
//			return "Post-Access Codec";
//		}
//
//		private Dimension size;
//	}
//
//	public void setBI(BufferedImage outImage) {
//		synchronized (this.L) {
//			BI = outImage;
//
//		}
//
//	}
//
//}
//
//class DeviceInfo {
//	/***************************************************************************
//	 * File: DeviceInfo.java.java created 24.07.2001 21:44:12 by David Fischer,
//	 * fischer@d-fischer.com
//	 */
//
//	public static Format formatMatches(Format format, Format supported[]) {
//		if (supported == null)
//			return null;
//		for (int i = 0; i < supported.length; i++)
//			if (supported[i].matches(format))
//				return supported[i];
//		return null;
//	}
//
//	public static boolean setFormat(DataSource dataSource, Format format) {
//		boolean formatApplied = false;
//
//		FormatControl formatControls[] = null;
//		formatControls = ((CaptureDevice) dataSource).getFormatControls();
//		for (int x = 0; x < formatControls.length; x++) {
//			if (formatControls[x] == null)
//				continue;
//
//			Format supportedFormats[] = formatControls[x].getSupportedFormats();
//			if (supportedFormats == null)
//				continue;
//
//			if (DeviceInfo.formatMatches(format, supportedFormats) != null) {
//				formatControls[x].setFormat(format);
//				formatApplied = true;
//			}
//		}
//
//		return formatApplied;
//	}
//
//	public static boolean isVideo(Format format) {
//		return (format instanceof VideoFormat);
//	}
//
//	public static boolean isAudio(Format format) {
//		return (format instanceof AudioFormat);
//	}
//
//	public static String formatToString(Format format) {
//		if (isVideo(format))
//			return videoFormatToString((VideoFormat) format);
//
//		if (isAudio(format))
//			return audioFormatToString((AudioFormat) format);
//
//		return ("--- unknown media device format ---");
//	}
//
//	public static String videoFormatToString(VideoFormat videoFormat) {
//		StringBuffer result = new StringBuffer();
//
//		// add width x height (size)
//		Dimension d = videoFormat.getSize();
//		result.append("size=" + (int) d.getWidth() + "x" + (int) d.getHeight()
//				+ ", ");
//
//		// add encoding
//		result.append("encoding=" + videoFormat.getEncoding() + ", ");
//
//		// add max data length
//		result.append("maxdatalength=" + videoFormat.getMaxDataLength() + "");
//
//		return result.toString();
//	}
//
//	public static String audioFormatToString(AudioFormat audioFormat) {
//		StringBuffer result = new StringBuffer();
//
//		// short workaround
//		result.append(audioFormat.toString().toLowerCase());
//
//		return result.toString();
//	}
//}
//
//class Stdout {
//
//	/***************************************************************************
//	 * File: Stdout.java.java created 24.07.2001 21:44:46 by David Fischer,
//	 * fischer@d-fischer.com Description: utility class for standard output
//	 */
//
//	public static void log(String msg) {
//		System.out.println(msg);
//	}
//
//	public static void logAndAbortException(Exception e) {
//		log("" + e);
//		flush();
//		System.exit(0);
//	}
//
//	public static void logAndAbortError(Error e) {
//		log("" + e);
//		flush();
//		System.exit(0);
//	}
//
//	public static void flush() {
//		System.out.flush();
//	}
//
//}
//
//class MyDataSinkListener implements DataSinkListener {
//	/*
//	 * File: MyDataSinkListener.java created 24.07.2001 21:41:47 by David
//	 * Fischer, fischer@d-fischer.com Decription: simple data sink listener,
//	 * used to check for end of stream
//	 */
//
//	boolean endOfStream = false;
//
//	public void dataSinkUpdate(DataSinkEvent event) {
//		if (event instanceof javax.media.datasink.EndOfStreamEvent)
//			endOfStream = true;
//	}
//
//	public void waitEndOfStream(long checkTimeMs) {
//		while (!endOfStream) {
//			Stdout.log("datasink: waiting for end of stream ...");
//			try {
//				Thread.currentThread().sleep(checkTimeMs);
//			} catch (InterruptedException ie) {
//			}
//		}
//		Stdout.log("datasink: ... end of stream reached.");
//	}
//}
