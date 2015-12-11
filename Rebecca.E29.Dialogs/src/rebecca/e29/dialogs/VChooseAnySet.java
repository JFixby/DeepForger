package rebecca.e29.dialogs;

import rebecca.e29.util.Set;

public class VChooseAnySet extends VSet implements Filter<Set<String>>,
		java.io.Serializable {

	private static final long serialVersionUID = -3005483557268016485L;

	@Override
	public synchronized boolean valueFits(Set<String> t) {
		// TODO Auto-generated method stub
		return s.containsAll(t);
	}

}
