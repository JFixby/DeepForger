package rebecca.e29.dialogs;

import java.math.BigInteger;

public class TInteger extends Term<BigInteger> implements java.io.Serializable {

	private static final long serialVersionUID = 6316547652962146768L;

	@Override
	public synchronized BigInteger parseStringValue(String strVal)
			throws Throwable {
		// TODO Auto-generated method stub
		return (new BigInteger(strVal));
	}

}
