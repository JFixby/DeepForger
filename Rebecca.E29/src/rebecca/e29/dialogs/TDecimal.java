package rebecca.e29.dialogs;

import java.math.BigDecimal;

public class TDecimal extends Term<BigDecimal> implements  java.io.Serializable{

	private static final long serialVersionUID = 5009548908329088983L;

	@Override
	public synchronized  BigDecimal parseStringValue(String strVal) throws Throwable {
		// TODO Auto-generated method stub
		return (new BigDecimal(strVal));
	}

}
