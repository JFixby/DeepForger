package rebecca.e29.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

import rebecca.e29.util.Vector;

public class MultilevelException extends Exception implements
		java.io.Serializable {

	private static final long serialVersionUID = -2929125411410038283L;

	public MultilevelException(String Message) {
		super(Message);
	}

	public synchronized Vector<Throwable> getExceptionsList() {
		return ex;
	}

	Vector<Throwable> ex = new Vector<Throwable>();

	@Override
	public synchronized void printStackTrace(PrintStream s) {
		// TODO Auto-generated method stub
		super.printStackTrace(s);
		s.println();
		for (int i = 0; i < this.ex.size(); i++) {
			s.println("------- [" + i + "]");
			ex.get(i).printStackTrace(s);
			s.println();
		}
	}

	@Override
	public synchronized void printStackTrace(PrintWriter s) {
		// TODO Auto-generated method stub
		super.printStackTrace(s);
		s.println();
		for (int i = 0; i < this.ex.size(); i++) {
			s.println("------- [" + i + "]");
			ex.get(i).printStackTrace(s);
			s.println();
		}
	}

}
