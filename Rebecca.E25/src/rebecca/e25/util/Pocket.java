package rebecca.e25.util;

import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import rebecca.e25.mail.Message;

public class Pocket<T> implements Box<T> {

	T mail = null;
	private Lock l = new ReentrantLock(true);

	@Override
	public boolean check_availability() {
		boolean i = false;
		l.lock();
		try {
			i = mail != null;
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
			t = mail;
		} finally {
			l.unlock();
		}
		return t;

	}

	@Override
	public void put(T t) {
		l.lock();
		try {
			mail = t;
		} finally {
			l.unlock();
		}

	}

	@Override
	public void putBack(T t) {
		this.put(t);
	}
}
