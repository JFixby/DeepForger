package rebecca.e27.util.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class XMLMarshal {

	public static Object UnMarshall(String xml, String packageName)
			throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(packageName);

		Unmarshaller u = jc.createUnmarshaller();

		InputStream is = new ByteArrayInputStream(xml.getBytes());

		return u.unmarshal(is);
	}

	public static <T> T UnMarshall(String d, Class<T> class1)
			throws JAXBException {
		return (T) UnMarshall(d, class1.getPackage().getName());
	}

	public static String Marshall(Object xmlObject, String packageName)
			throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(packageName);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		ByteArrayOutputStream is = new ByteArrayOutputStream();
		m.marshal(xmlObject, is);
		return is.toString();
	}

	public static String Marshall(Object xmlObject) throws JAXBException {
		return Marshall(xmlObject, xmlObject.getClass().getPackage().getName());
	}

}
