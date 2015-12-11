package test;

import java.io.*;

public class dfdf {

	public static void main(String[] args) {

		Runtime r = Runtime.getRuntime();
		try {
			Process p = r.exec("ls / -la");
			InputStream in = p.getInputStream();
			StreamRedirect redirect = new dfdf.StreamRedirect(in, System.err);
			redirect.start();
			redirect.stopRedirect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class StreamRedirect extends Thread {
		private InputStream in;
		private OutputStream out;
		private boolean stopped = false;

		public StreamRedirect(InputStream in, OutputStream out) {
			this.in = in;
			this.out = out;
		}

		public void run() {
			try {
				int len;
				byte[] buf = new byte[1024];
				while ((len = in.read(buf)) != -1 && !stopped) {
					out.write(buf, 0, len);
					if (len == 0) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void stopRedirect() {
			this.stopped = true;
		}
	}

}
