package rebecca.e29.dialogs;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ParsingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -779130492571546286L;
	Throwable r;

	public ParsingException(Throwable reason) {
		r = reason;
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		// TODO Auto-generated method stub
		super.printStackTrace(s);
		s.println("        reason:");
		if (r != null) {
			r.printStackTrace(s);
		} else {
			s.println("null");
		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
		s.println("        reason:");
		if (r != null) {
			r.printStackTrace(s);
		} else {
			s.println("null");
		}
	}

}
