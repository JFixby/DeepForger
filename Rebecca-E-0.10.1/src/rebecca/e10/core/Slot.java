package rebecca.e10.core;

import java.io.Serializable;

public class Slot<T> extends NObject {

	private Object l = new Object();
	// private box b =new box();
	private T o;
	private Boolean fresh = false;
	private Boolean AlwaysFresh = false;
	private Boolean AlwaysNotFresh = false;
	private Pipe R;
	private Pipe W;

	/*
	 * // Serialize to a byte array ByteArrayOutputStream bos = new
	 * ByteArrayOutputStream() ; out = new ObjectOutputStream(bos) ;
	 * out.writeObject(object); out.close(); // Get the bytes of the serialized
	 * object byte[] buf = bos.toByteArray(); } catch (IOException e) {
	 */

	public Slot(T o) {

		if (o != null) {
			// this.b.put(o);
			this.o = o;
		}
	}

	public Slot() {
		this.o = null;

	}

	public T GetDATA(Pipe Client) {
		synchronized (l) {
			if (Client == this.getReader()) { // if(o.getClass().getSuperclass()==Obraz.class){return
				// (Obraz)o;}
				/*
				 * Object o; o. box ob = null; try { ByteArrayOutputStream bos =
				 * new ByteArrayOutputStream() ; ObjectOutputStream out = new
				 * ObjectOutputStream(bos); out.writeObject(b); out.close();
				 * byte[] buf = bos.toByteArray(); byte[] buf2=buf.clone();
				 * 
				 * ObjectInputStream in = new ObjectInputStream(new
				 * ByteArrayInputStream(buf2)); try { ob = (box)in.readObject();
				 * in.close(); } catch (ClassNotFoundException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 * 
				 *  } catch (IOException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); }
				 *  // Get the bytes of the serialized object
				 * 
				 * 
				 * 
				 * return (T)ob.get();
				 */
				return o;
			} else {
				System.out.println(this.GetName() + " Slot violation! " + " "
						+ this.GetID());
				System.out.println(this.GetName() + " Reader: " + " "
						+ this.getReader().GetName());
				System.out.println(this.GetName() + " Writer: " + " "
						+ this.getWriter().GetName());
				System.out.println(this.GetName() + " Intruder: " + " "
						+ Client.GetName());
				this.getWriter().setDamage();
				return null;
			}
		}

	}

	public void PushDATA(T t, Pipe Client) {
		synchronized (l) {
			if (Client == this.getWriter()) {
				// this.b.put(t);
				this.o = t;
			} else {
				System.out.println("Slot violation! " + this.GetName() + " "
						+ this.GetID());
				this.getWriter().setDamage();
			}

		}
	}

	public Pipe getReader() {
		return this.R;
	}

	public void setReader(Pipe r) {
		this.R = r;
	}

	public Pipe getWriter() {
		return this.W;
	}

	public void setWriter(Pipe w) {
		this.W = w;
	}

	public void SetAlwaysFresh() {
		this.SetAlwaysFresh(true);
	}

	public void SetAlwaysNotFresh() {
		this.SetAlwaysNotFresh(true);
	}

	public boolean GetFresh() {
		synchronized (l) {
			if (this.AlwaysFresh) {
				return true;
			}
			if (this.AlwaysNotFresh) {
				return false;
			}
			return this.fresh;
		}

	}

	public void SetAlwaysFresh(boolean b) {
		synchronized (l) {
			this.AlwaysFresh = b;
		}
	}

	public void SetAlwaysNotFresh(boolean b) {
		synchronized (l) {
			this.AlwaysNotFresh = b;
		}

	}

	public void SetFresh() {
		this.SetFresh(true);
	}

	public void SetFresh(boolean b) {
		synchronized (l) {
			this.fresh = b;
		}

	}

	class box implements Serializable {
		Object o = null;

		void put(Object o) {
			this.o = o;
		}

		Object get() {
			return this.o;
		}
	}

}
