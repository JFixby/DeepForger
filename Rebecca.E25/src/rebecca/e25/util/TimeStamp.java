package rebecca.e25.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class TimeStamp implements Cloneable, Serializable, Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3893496309556112676L;

	public int day;
	public int month;
	public int year;
	public int hour;
	public int minute;
	public int second;
	public int msecond;
	public int dweek;

	public TimeStamp(Date D) {
		Calendar C = Calendar.getInstance();
		C.setTime(D);

		day = C.get(Calendar.DAY_OF_MONTH);
		month = C.get(Calendar.MONTH) + 1;
		year = C.get(Calendar.YEAR);
		hour = C.get(Calendar.HOUR_OF_DAY);
		minute = C.get(Calendar.MINUTE);
		second = C.get(Calendar.SECOND);
		msecond = C.get(Calendar.MILLISECOND);
		dweek = C.get(Calendar.DAY_OF_WEEK);

	}

	@Override
	public void print() {

		System.out.println(day + "." + month + "." + year + " " + dweek() + " "
				+ hour + ":" + minute + ":" + second + ":" + msecond);
		// TODO Auto-generated method stub

	}

	private String dweek() {
		// TODO Auto-generated method stub
		if (dweek == Calendar.SUNDAY) {
			return "SUNDAY";
		}
		if (dweek == Calendar.MONDAY) {
			return "MONDAY";
		}
		if (dweek == Calendar.TUESDAY) {
			return "TUESDAY";
		}
		if (dweek == Calendar.WEDNESDAY) {
			return "WEDNESDAY";
		}
		if (dweek == Calendar.THURSDAY) {
			return "THURSDAY";
		}
		if (dweek == Calendar.FRIDAY) {
			return "FRIDAY";
		}
		if (dweek == Calendar.SATURDAY) {
			return "SATURDAY";
		}
		return "%ERROR%";

	}
}
