package rebecca.e29.dialogs;


public abstract class Term<T> extends Axis<T> implements  java.io.Serializable {

	private static final long serialVersionUID = 3311360638191729240L;
	T val = null;

	@Override
	public synchronized  T getValue() {
		// TODO Auto-generated method stub
		return val;
	}

	@Override
	public synchronized  void setValue(T val) {
		// TODO Auto-generated method stub
		this.val = val;

	}

	@Override
	public synchronized  String getStringValue() {
		// TODO Auto-generated method stub
		return val + "";
	}

	@Override
	public  synchronized void setValueByString(String strVal) throws ParsingException {
		try {
			setValue(parseStringValue(strVal));
		} catch (Throwable x) {
			throw new ParsingException(x);
		}

	}

	public abstract T parseStringValue(String strVal) throws Throwable;

}
