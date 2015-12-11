package rebecca.e29.dialogs;

public class VChooseOneSet extends VSet implements Filter<String>,
		java.io.Serializable {

	private static final long serialVersionUID = -2743869678394818715L;

	@Override
	public synchronized boolean valueFits(String t) {
		// TODO Auto-generated method stub
		return s.contains(t);
	}

}
