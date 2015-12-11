package rebecca.e29.dialogs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TDate extends Term<Date> implements java.io.Serializable{

	private static final long serialVersionUID = -1730929210867352310L;
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

	public TDate() {
		super();
	}

	public TDate(DateFormat f) {
		super();
		fr = f;
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized Date parseStringValue(String strVal)
			throws ParseException {
		// TODO Auto-generated method stub
		return getDateFormat().parse(strVal);
	}

	DateFormat fr;

	public synchronized DateFormat getDateFormat() {
		if (fr == null) {
			fr = new SimpleDateFormat(DATE_FORMAT);
		}
		return fr;
	}

	public synchronized void setDateFormat(DateFormat fr) {
		this.fr = fr;
	}

}
