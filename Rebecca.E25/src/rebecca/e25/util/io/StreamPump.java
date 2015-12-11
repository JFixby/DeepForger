package rebecca.e25.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.ReentrantLock;

import rebecca.e25.exe.Lock;
import rebecca.e25.util.DSSException;

public class StreamPump implements Runnable {

	private InputStream I;
	private OutputStream O;

	public StreamPump(InputStream i, OutputStream o) throws DSSException {
		I = i;
		O = o;
		if (I == null) {
			throw new DSSException("What`s a hell is that?");
		}
		if (O == null) {
			nullWriteMode = true;
		}

		t = new Thread(this);

		t.start();

	}

	Thread t;
	private boolean nullWriteMode = false;

	public void stopPumping() {

		if (t != null) {
			t.interrupt();
			t = null;
		}
	}

	@Override
	public void run() {
		int k = 0;
		if (!nullWriteMode) {
			try {
				while ((-1 != k)) {
					k = I.read();
					O.write(k);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				while ((-1 != k)) {
					k = I.read();
					O.write(k);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}