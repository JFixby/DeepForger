package rebecca.e30.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamPump implements Runnable {

	private InputStream I;
	private OutputStream O;

	public StreamPump(InputStream i, OutputStream o) throws Exception {
		I = i;
		O = o;
		if (I == null || O == null) {
			throw new Exception("Pump explosion!");
		}

		t = new Thread(this);

		t.start();

	}

	Thread t;

	public void stopPumping() {

		if (t != null) {
			t.interrupt();
			t = null;
		}
	}

	@Override
	public void run() {
		try {
			int k = 0;

			while ((-1 != k)) {
				k = I.read();
				O.write(k);
			}

		} catch (IOException e) {
			e.printStackTrace();
			stopPumping();
		}
	}
}