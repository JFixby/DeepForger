package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import rebecca.e26.util.Map;
import rebecca.e26.util.Set;

public class t2 {

	/**
	 * @param args
	 */
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		System.out.println(sdf.parse(""));

	}

}
