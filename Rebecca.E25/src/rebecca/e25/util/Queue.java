package rebecca.e25.util;

import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import rebecca.e25.mail.Message;

public class Queue<T> implements Box<T> {

	private Vector<T> mail = new Vector<T>();
	private Lock l = new ReentrantLock(true);

	@Override
	public boolean check_availability() {
		boolean i = false;
		l.lock();
		try {
			i = mail.size() > 0;
			return i;
		} finally {
			l.unlock();
			
		}
		
	}

	@Override
	public T get() {
		T t = null;
		l.lock();
		try {
			t = mail.get(0);
			if (t != null) {
				mail.removeElementAt(0);
				
			}
		} finally {
			l.unlock();
		}
		return t;

		
	}

	@Override
	public void put(T t) {
		l.lock();
		try {
			mail.add(t);
		} finally {
			l.unlock();
		}

	}

	@Override
	public void putBack(T t) {
		l.lock();
		try {
			// mail.add(t);
			mail.insertElementAt(t, 0);
		} finally {
			l.unlock();
		}
	}

}
