package rebecca.e25.util;

import java.util.RandomAccess;

public interface List<E> extends RandomAccess, Cloneable, java.io.Serializable,
		java.util.List<E> {
	
	public E getLast();
}
