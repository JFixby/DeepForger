package rebecca.e25.exe;

import java.util.concurrent.locks.ReentrantLock;

public class Lock extends ReentrantLock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3811839506413772187L;

	public Lock() {
		super(true);
	}

}