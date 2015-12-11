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

public class ReXMLTransformer {

	public static XSentence transform(Sentence p) {
		XSentence P = new XSentence();
		P.setId(p.getId());
		P.setInfo(p.getInfo());
		P.setName(p.getName());

		for (int i = 0; i < p.getAxes().size(); i++) {
			XAxis e = transform(p.getAxes().get(i));
			P.getAxes().add(e);
		}

		return P;
	}

	public static XAxis transform(Axis x) {
		// TODO Auto-generated method stub
		try {
			XAxis R = null;
			if (x.getClass().getSimpleName().equals(
					AString.class.getSimpleName())) {
				AString xx = (AString) x;

				XAString b = new XAString();
				b.setDefault(xx.getDefault());
				b.setRegex(xx.getRegex());
				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					ABoolean.class.getSimpleName())) {
				ABoolean xx = (ABoolean) x;

				XABoolean b = new XABoolean();
				b.setDefault(tr(xx.getDefault()));

				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					AChooseMultiSet.class.getSimpleName())) {
				AChooseMultiSet xx = (AChooseMultiSet) x;

				XAChooseMultiSet b = new XAChooseMultiSet();

				b.getOptions().addAll(tr(xx.getOptions()));
				b.getDefault().addAll(tri(xx.getDefault()));

				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					MultiAxis.class.getSimpleName())) {
				MultiAxis xx = (MultiAxis) x;

				XMultiAxis b = new XMultiAxis();
				Set<Axis> A = xx.getAxis();
				List<XAxis> L = b.getAxis();

				for (int i = 0; i < A.size(); i++) {
					XAxis e = transform(A.get(i));
					L.add(e);
				}

				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					AChooseOneSet.class.getSimpleName())) {
				AChooseOneSet xx = (AChooseOneSet) x;

				XAChooseOneSet b = new XAChooseOneSet();

				b.getOptions().addAll(tr(xx.getOptions()));
				b.setDefault(xx.getDefault());

				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					DMultiOption.class.getSimpleName())) {
				DMultiOption xx = (DMultiOption) x;

				XDOpList b = new XDOpList();

				b.setType("multi-option");
				b.getVal().addAll(tr(xx.getValue()));

				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					DOption.class.getSimpleName())) {
				DOption xx = (DOption) x;

				XDOpList b = new XDOpList();

				b.setType("single-option");
				b.getVal().add(tr(xx.getValue()));

				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					DString.class.getSimpleName())) {
				DString xx = (DString) x;
				XDEntry b = new XDEntry();
				b.setValue(xx.getValue());
				b.setType("string");
				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					DInteger.class.getSimpleName())) {
				DInteger xx = (DInteger) x;
				XDEntry b = new XDEntry();
				b.setValue("" + xx.getValue());
				b.setType("integer");
				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					DDecimal.class.getSimpleName())) {
				DDecimal xx = (DDecimal) x;
				XDEntry b = new XDEntry();
				b.setValue("" + xx.getValue());
				b.setType("decimal");
				R = b;
			} else

			if (x.getClass().getSimpleName()
					.equals(DDate.class.getSimpleName())) {
				DDate xx = (DDate) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType("date");
				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					DBoolean.class.getSimpleName())) {
				DBoolean xx = (DBoolean) x;
				XDEntry b = new XDEntry();
				b.setValue(tr(xx.getValue()));
				b.setType("boolean");
				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					AInteger.class.getSimpleName())) {
				AInteger xx = (AInteger) x;
				XARange b = new XARange();
				b.setFrom("" + xx.getFrom());
				b.setTo("" + xx.getTo());
				b.setDefault("" + xx.getDefault());
				b.setType("integer");
				R = b;
			} else

			if (x.getClass().getSimpleName().equals(
					ADecimal.class.getSimpleName())) {
				ADecimal xx = (ADecimal) x;
				XARange b = new XARange();
				b.setFrom("" + xx.getFrom());
				b.setTo("" + xx.getTo());
				b.setDefault("" + xx.getDefault());
				b.setType("decimal");
				R = b;
			} else

			if (x.getClass().getSimpleName()
					.equals(ADate.class.getSimpleName())) {
				ADate xx = (ADate) x;
				XARange b = new XARange();
				b.setFrom("" + xx.getFrom());
				b.setTo("" + xx.getTo());
				b.setDefault("" + xx.getDefault());
				b.setType("date");
				R = b;
			}

			//R.setName(x.getName());
			R.setID(x.getId());
			R.setInfo(x.getInfo());
			return R;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new TransformerException("");
		}
	}

	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

	private static String tr(Date d) {
		// TODO Auto-generated method stub
		// SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		// return sdf.format(from);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(d);

	}

	private static String tr(String s) {
		if (s == null) {
			return ("");
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
		if (x != null) {
			XOption o = new XOption();

			o.setId(x.getID());
			o.setInfo(x.getInfo());
			o.setName(x.getName());

			return o;
		}
		return null;
	}

	private static String tr(Boolean d) {
		if (d != null)
			return d.toString();
		return "";
	}

	public static XPassage transform(Passage p) {
		XPassage P = new XPassage();
		P.setId(p.getId());
		P.setInfo(p.getInfo());
		P.setName(p.getName());

		for (int i = 0; i < p.size(); i++) {
			XSentence e = transform(p.get(i));
			P.getList().add(e);
		}

		return P;
	}

}
