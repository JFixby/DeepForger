package rebecca.e29.dialogs;


public abstract class Axis<T> extends Atomic implements  java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9209179892968852044L;

	public  abstract String getStringValue();

	public abstract void setValueByString(String str_val) throws ParsingException;

	public abstract T getValue();

	public abstract void setValue(T val);

}
