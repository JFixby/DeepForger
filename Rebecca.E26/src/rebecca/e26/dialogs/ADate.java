package rebecca.e26.dialogs;

import java.io.Serializable;
import java.util.Date;

import rebecca.e26.HollyShitException;

public class ADate extends ARange<Date> implements Serializable {

	public static final long MAX_DATE = 32513616000000L;
	public static final long MIN_DATE = -3784320000000L;
	
	

	public ADate() {
		super();
		// Date d1 = new Date(0);
		// Date d2 = new Date(Long.MAX_VALUE);
		setRange(null, null);

	}

	public void setRange(Date from, Date to) {
		// this.Value = null;
		this.Defv = null;

		if (from == null) {
			from = new Date(MIN_DATE);

		} else if (from.getTime() < MIN_DATE) {
			from = new Date(MIN_DATE);
		} else if (from.getTime() > MAX_DATE) {
			from = new Date(MAX_DATE);
		}

		if (to == null) {
			to = new Date(MAX_DATE);
		} else if (to.getTime() > MAX_DATE) {
			to = new Date(MAX_DATE);
		} else if (to.getTime() < MIN_DATE) {
			to = new Date(MIN_DATE);
		}

		super.setRange(from, to);

	}
}
