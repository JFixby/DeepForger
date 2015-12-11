package rebecca.e25.exe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Rotor implements Runnable, Player {

	Executor E = null;

	public int bindExecutor(Executor E) {
		this.E = E;
		return 0;
	}

	private Lock l = new ReentrantLock(true);
	private boolean Ps = true;
	Thread t = null;
	private boolean ON = false;
	private Lock active = new ReentrantLock(true);

	public int Pause() {
		l.lock();
		try {
			Ps = true;
		} finally {
			l.unlock();
		}
		return 0;

	}

	public int Play() {
		l.lock();
		try {
			Ps = false;

			if (!ON) {
				active.lock();
				try {
					ON = true;
					t = new Thread(this);
					t.start();

				} finally {
					active.unlock();
				}

			}
		} finally {
			l.unlock();
		}
		return 0;
	}

	public int Stop() {
		// if (inside) {
		// / t.interrupt();
		// }
		l.lock();
		try {
			Ps = false;
			ON = false;
		} finally {
			l.unlock();
		}
		return 0;

	}

	boolean x = false;
	boolean inside = false;
	private long period = 1000;

	public void run() {
		E.Init();
		active.lock();
		try {
			while (ON) {

				x = !Ps && ON && E.ExecuteCondition();
				if (x) {
					E.Execute();
				}

				if (!x) {
					Sleep();
				}

			}
		} finally {
			active.unlock();
		}
		E.DeInit();

	}

	public void Sleep() {
		try {
			Thread.yield();
			Thread.sleep(this.period);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// this.Stop();
		}
	}

	public void setPeriod(long p) {
		// TODO Auto-generated method stub
		this.period = p;
	}

}
