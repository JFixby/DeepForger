package rebecca.e26.util;

import java.util.StringTokenizer;

public class Parser {

	public static Vector<String> TokenizeString(String message){
		Vector<String> v = new Vector<String>();
		StringTokenizer st = new StringTokenizer(message);
		int i = 0;

		while (st.hasMoreTokens()) // is there stuff to get?
		{
			v.add(st.nextToken());
			i++;
		}
		return v;
	}
}
