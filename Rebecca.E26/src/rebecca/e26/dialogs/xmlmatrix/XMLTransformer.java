package rebecca.e26.dialogs.xmlmatrix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rebecca.e26.dialogs.ABoolean;
import rebecca.e26.dialogs.AChooseMultiSet;
import rebecca.e26.dialogs.AChooseOneSet;
import rebecca.e26.dialogs.ADate;
import rebecca.e26.dialogs.ADecimal;
import rebecca.e26.dialogs.AInteger;
import rebecca.e26.dialogs.AString;
import rebecca.e26.dialogs.Axis;
import rebecca.e26.dialogs.DBoolean;
import rebecca.e26.dialogs.DDate;
import rebecca.e26.dialogs.DDecimal;
import rebecca.e26.dialogs.DInteger;
import rebecca.e26.dialogs.DMultiOption;
import rebecca.e26.dialogs.DOption;
import rebecca.e26.dialogs.DString;
import rebecca.e26.dialogs.DefaultSetException;
import rebecca.e26.dialogs.MultiAxis;
import rebecca.e26.dialogs.Option;
import rebecca.e26.dialogs.Passage;
import rebecca.e26.dialogs.Sentence;
import rebecca.e26.util.Set;

public class XMLTransformer {

	public static final String MULTI_OPTION = "multi-option";
	public static final String SINGLE_OPTION = "single-option";
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
	public static final String NULL = "NULL";
	public static final String INTEGER = "INT";
	public static final String DECIMAL = "DEC";
	public static final String STRING = "STR";
	public static final String BOOLEAN = "BOOL";
	public static final String DATE = "DATE";
	public static final String EMPTY = "";
	public static final String TRUE_STR = "true";
	public static final String FALSE_STR = "false";

	public static Passage transform(XPassage p) {
		Passage P = new Passage();

		P.setId(p.getId());
		P.setInfo(p.getInfo());
		P.setName(p.getName());

		for (int i = 0; i < p.getList().size(); i++) {
			Sentence e = transform(p.getList().get(i), Sentence.class);
			P.add(e);
		}
		return P;
	}

	public static Sentence transform(XSentence p, Class<Sentence> class1) {
		Sentence P = new Sentence();

		P.setId(p.getId());
		P.setInfo(p.getInfo());
		P.setName(p.getName());
		P.getAxes().clear();
		Set<Axis> A = P.getAxes();

		for (int i = 0; i < A.size(); i++) {
			Axis e = transform(p.getAxes().get(i), Axis.class);
			A.add(e);
		}
		return P;
	}

	private static Axis transform(XAxis x, Class<Axis> cass) {
		// TODO Auto-generated method stub
		try {
			Axis R = null;
			if (x.getClass().getName().equals(XAString.class.getName())) {
				XAString xx = (XAString) x;

				AString b = new AString();
				b.setDefault(tr(xx.getDefault(), String.class));
				b.setRegex(tr(xx.getRegex(), String.class));

				R = b;
			} else

			if (x.getClass().getName().equals(XABoolean.class.getName())) {
				XABoolean xx = (XABoolean) x;

				ABoolean b = new ABoolean();
				b.setDefault(tr(xx.getDefault(), Boolean.class));

				R = b;
			} else

			if (x.getClass().getName().equals(XAChooseMultiSet.class.getName())) {
				XAChooseMultiSet xx = (XAChooseMultiSet) x;

				AChooseMultiSet b = new AChooseMultiSet();

				b.getOptions().addAll(trO(xx.getOptions(), Set.class, Option.class));
				b.setDefault(trI(xx.getDefault(), Set.class, Integer.class));

				R = b;
			} else

			if (x.getClass().getName().equals(XMultiAxis.class.getName())) {
				XMultiAxis xx = (XMultiAxis) x;
				MultiAxis b = new MultiAxis();

				Set<Axis> A = b.getAxis();
				List<XAxis> L = xx.getAxis();

				for (int i = 0; i < L.size(); i++) {
					Axis e = transform(L.get(i), Axis.class);
					A.add(e);
				}

				R = b;
			} else

			if (x.getClass().getName().equals(XAChooseOneSet.class.getName())) {
				XAChooseOneSet xx = (XAChooseOneSet) x;
				AChooseOneSet b = new AChooseOneSet();

				b.getOptions().addAll(trO(xx.getOptions(), Set.class, Option.class));
				b.setDefault(tr(xx.getDefault(), Integer.class));

				R = b;
			} else

			if (x.getClass().getName().equals(XDOpList.class.getName())) {
				XDOpList xx = (XDOpList) x;

				if (xx.getType().equalsIgnoreCase(MULTI_OPTION)) {
					DMultiOption b = new DMultiOption();
					b.setValue(trO(xx.getVal(), Set.class, Option.class));
					R = b;
				} else if (xx.getType().equalsIgnoreCase(SINGLE_OPTION)) {
					DOption b = new DOption();
					b.setValue(trO(xx.getVal(), Set.class, Option.class)
							.FirstElement());
					R = b;
				} else {
					throw new TransformerException("");
				}
			} else

			if (x.getClass().getName().equals(XDEntry.class.getName())) {
				XDEntry xx = (XDEntry) x;

				if (xx.getType().equalsIgnoreCase(INTEGER)) {
					DInteger d = new DInteger();
					d.setValue(tr(xx.getValue(), BigInteger.class));
					R = d;
				} else if (xx.getType().equalsIgnoreCase(DECIMAL)) {
					DDecimal d = new DDecimal();
					d.setValue(tr(xx.getValue(), BigDecimal.class));
					R = d;
				} else if (xx.getType().equalsIgnoreCase(STRING)) {
					DString d = new DString();
					d.setValue(tr(xx.getValue(), String.class));
					R = d;
				} else if (xx.getType().equalsIgnoreCase(DATE)) {
					DDate d = new DDate();
					d.setValue(tr(xx.getValue(), Date.class));
					R = d;
				} else if (xx.getType().equalsIgnoreCase(BOOLEAN)) {
					DBoolean d = new DBoolean();
					d.setValue(tr(xx.getValue(), Boolean.class));
					R = d;

				} else {
					throw new TransformerException("");
				}
			} else

			if (x.getClass().getName().equals(XARange.class.getName())) {
				XARange xx = (XARange) x;

				if (xx.getType().equalsIgnoreCase(INTEGER)) {
					AInteger b = new AInteger();
					b.setRange(tr(xx.getFrom(), BigInteger.class), tr(xx
							.getTo(), BigInteger.class));
					b.setDefault(tr(xx.getDefault(), BigInteger.class));
					R = b;
				} else

				if (xx.getType().equalsIgnoreCase(DECIMAL)) {
					ADecimal b = new ADecimal();
					b.setRange(tr(xx.getFrom(), BigDecimal.class), tr(xx
							.getTo(), BigDecimal.class));
					b.setDefault(tr(xx.getDefault(), BigDecimal.class));
					R = b;
				} else

				if (xx.getType().equalsIgnoreCase(DATE)) {
					ADate b = new ADate();
					b.setRange(tr(xx.getFrom(), Date.class), tr(xx.getTo(),
							Date.class));
					b.setDefault(tr(xx.getDefault(), Date.class));
					R = b;
				} else {
					throw new TransformerException("");
				}

			}

			//R.setName(x.getName());
			R.setInfo(x.getInfo());
			return R;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new TransformerException("");
		}
	}

	private static int tr(int default1, Class<Integer> class1) {
		return default1;
	}

	private static Date tr(String from, Class<Date> class1) {
		if (from == null) {
			return null;
		} else

		if (from.equalsIgnoreCase(NULL)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return sdf.parse(from);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TransformerException("");
		}
	}

	private static BigDecimal tr(String s, Class<BigDecimal> class1) {
		if (s == null) {
			return (null);
		} else if (s.equalsIgnoreCase(NULL)) {
			return (null);
		} else {
			try {
				return (new BigDecimal(s));
			} catch (Throwable e) {
				e.printStackTrace();
				throw new TransformerException("");
			}
		}
	}

	private static BigInteger tr(String s, Class<BigInteger> class1) {
		if (s == null) {
			return (null);
		} else if (s.equalsIgnoreCase(NULL)) {
			return (null);
		} else {
			try {
				return (new BigInteger(s));
			} catch (Throwable e) {
				e.printStackTrace();
				throw new TransformerException("");
			}
		}
	}

	private static String tr(String s, Class<String> class1) {
		if (s == null) {
			return (EMPTY);
		}
		return s;
	}

	private static Set<Integer> trI(List<Integer> default1, Class<Set> class1,
			Class<Integer> class2) {
		// TODO Auto-generated method stub
		Set<Integer> R = new Set<Integer>();

		for (int i = 0; i < default1.size(); i++) {
			Integer e = (int) (default1.get(i));
			R.add(e);
		}

		return R;
	}

	private static Set<Option> trO(List<XOption> list, Class<Set> class1,
			Class<Option> class2) {

		// TODO Auto-generated method stub
		Set<Option> R = new Set<Option>();

		for (int i = 0; i < list.size(); i++) {
			Option e = tr(list.get(i), Option.class);
			R.add(e);
		}

		return R;
	}

	// private static Set<Option> tr(List<Integer> list, Class<Set> class1,
	// Class<Integer> class2) {
	// // TODO Auto-generated method stub
	// Set<Option> R = new Set<Option>();
	//
	// for (int i = 0; i < list.size(); i++) {
	// Option e = tr(list.get(i));
	// R.add(e);
	// }
	//
	// return R;
	// }

	private static Option tr(XOption x, Class<Option> cl) {
		// TODO Auto-generated method stub
		Option o = new Option();

		o.setID(x.getId());
		o.setInfo(x.getInfo());
		o.setName(x.getName());

		return o;
	}

	private static Boolean tr(String default1, Class<Boolean> class1) {
		if (default1.equalsIgnoreCase(TRUE_STR)) {
			return Boolean.TRUE;
		}
		if (default1.equalsIgnoreCase(FALSE_STR)) {
			return Boolean.FALSE;
		}
		if (default1.equalsIgnoreCase(NULL)) {
			return null;
		}
		throw new TransformerException("");
	}

//	public static XPassage transform(Sentence p) {
//		XPassage P = new XPassage();
//		P.setId(p.getId());
//		P.setInfo(p.getInfo());
//		P.setName(p.getName());
//
//		for (int i = 0; i < p.getAxes().size(); i++) {
//			XAxis e = transform(p.getAxes().get(i), XAxis.class);
//			P.getList()..add(e);
//			
//		}
//
//		return P;
//	}

	private static XAxis transform(Axis x, Class<XAxis> cls) {
		// TODO Auto-generated method stub
		try {
			XAxis R = null;
			if (x.getClass().getName().equals(AString.class.getName())) {
				AString xx = (AString) x;

				XAString b = new XAString();
				b.setDefault(tr(xx.getDefault()));
				b.setRegex(tr(xx.getRegex()));
				R = b;
			} else

			if (x.getClass().getName().equals(ABoolean.class.getName())) {
				ABoolean xx = (ABoolean) x;

				XABoolean b = new XABoolean();
				b.setDefault(tr(xx.getDefault()));

				R = b;
			} else

			if (x.getClass().getName().equals(AChooseMultiSet.class.getName())) {
				AChooseMultiSet xx = (AChooseMultiSet) x;

				XAChooseMultiSet b = new XAChooseMultiSet();

				b.getOptions().addAll(tr(xx.getOptions()));
				b.getDefault().addAll(tri(xx.getDefault()));

				R = b;
			} else

			if (x.getClass().getName().equals(MultiAxis.class.getName())) {
				MultiAxis xx = (MultiAxis) x;

				XMultiAxis b = new XMultiAxis();
				Set<Axis> A = xx.getAxis();
				List<XAxis> L = b.getAxis();
				// A.print();

				for (int i = 0; i < A.size(); i++) {
					XAxis e = transform(A.get(i), XAxis.class);
					L.add(e);
				}

				R = b;
			} else

			if (x.getClass().getName().equals(AChooseOneSet.class.getName())) {
				AChooseOneSet xx = (AChooseOneSet) x;

				XAChooseOneSet b = new XAChooseOneSet();

				b.getOptions().addAll(tr(xx.getOptions()));
				b.setDefault(xx.getDefault());

				R = b;
			} else

			if (x.getClass().getName().equals(DMultiOption.class.getName())) {
				DMultiOption xx = (DMultiOption) x;

				XDOpList b = new XDOpList();

				b.setType("multi-option");
				b.getVal().addAll(tr(xx.getValue()));

				R = b;
			} else

			if (x.getClass().getName().equals(DOption.class.getName())) {
				DOption xx = (DOption) x;

				XDOpList b = new XDOpList();

				b.setType("single-option");
				b.getVal().add(tr(xx.getValue()));

				R = b;
			} else

			if (x.getClass().getName().equals(DString.class.getName())) {
				DString xx = (DString) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType(STRING);
				R = b;
			} else

			if (x.getClass().getName().equals(DBoolean.class.getName())) {
				DBoolean xx = (DBoolean) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType(BOOLEAN);
				R = b;
			} else

			if (x.getClass().getName().equals(DInteger.class.getName())) {
				DInteger xx = (DInteger) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType(INTEGER);
				R = b;
			} else

			if (x.getClass().getName().equals(DDecimal.class.getName())) {
				DDecimal xx = (DDecimal) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType(DECIMAL);
				R = b;
			} else

			if (x.getClass().getName().equals(DDate.class.getName())) {
				DDate xx = (DDate) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType(DATE);
				R = b;
			} else

			if (x.getClass().getName().equals(AInteger.class.getName())) {
				AInteger xx = (AInteger) x;
				XARange b = new XARange();
				b.setFrom(tr(xx.getFrom()));
				b.setTo(tr(xx.getTo()));
				b.setDefault(tr(xx.getDefault()));
				b.setType(INTEGER);
				R = b;
			} else

			if (x.getClass().getName().equals(ADecimal.class.getName())) {
				ADecimal xx = (ADecimal) x;
				XARange b = new XARange();
				b.setFrom(tr((xx.getFrom())));
				b.setTo(tr(xx.getTo()));
				b.setDefault(tr(xx.getDefault()));
				b.setType(DECIMAL);
				R = b;
			} else

			if (x.getClass().getName().equals(ADate.class.getName())) {
				ADate xx = (ADate) x;
				XARange b = new XARange();
				b.setFrom(tr(xx.getFrom()));
				b.setTo(tr(xx.getTo()));
				b.setDefault(tr(xx.getDefault()));
				b.setType(DATE);
				R = b;
			}

			//R.setName(x.getName());
			R.setInfo(x.getInfo());
			return R;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new TransformerException("");
		}
	}

	private static String tr(BigDecimal value) {
		if (value != null) {
			return value.toString();
		}
		return NULL;
	}

	private static String tr(BigInteger value) {
		if (value != null) {
			return value.toString();
		}
		return NULL;
	}

	private static String tr(Date d) {
		// TODO Auto-generated method stub
		// SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		// return sdf.format(from);
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			return sdf.format(d);
		}
		return NULL;

	}

	private static String tr(String s) {
		if (s == null) {
			return (EMPTY);
		}
		return s;
	}

	private static List<XOption> tr(Set<Option> options) {
		// TODO Auto-generated method stub
		List<XOption> R = new ArrayList<XOption>();

		for (int i = 0; i < options.size(); i++) {
			XOption e = tr(options.get(i));
			R.add(e);
		}

		return R;
	}

	private static List<Integer> tri(Set<Integer> options) {
		// TODO Auto-generated method stub
		List<Integer> R = new ArrayList<Integer>();

		for (int i = 0; i < options.size(); i++) {
			int e = (options.get(i));
			R.add(e);
		}

		return R;
	}

	private static XOption tr(Option x) {
		// TODO Auto-generated method stub
		XOption o = new XOption();

		o.setId(x.getID());
		o.setInfo(x.getInfo());
		o.setName(x.getName());

		return o;
	}

	private static String tr(Boolean d) {
		if (d == null)
			return NULL;

		if (d)
			return TRUE_STR;

		return FALSE_STR;

	}

}
