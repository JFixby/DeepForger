package rebecca.e30.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLMarshal {

	public static Object UnMarshall(byte[] xml_bytes, String packageName)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(packageName);
		Unmarshaller u = jc.createUnmarshaller();
		InputStream is = new ByteArrayInputStream(xml_bytes);
		return u.unmarshal(is);
	}

	@SuppressWarnings("unchecked")
	public static <T> T UnMarshall(byte[] xml_bytes, Class<T> clazz)
			throws JAXBException {
		return (T) UnMarshall(xml_bytes, clazz.getPackage().getName());
	}

	public static byte[] Marshall(Object xmlObject, String packageName)
			throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(packageName);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		ByteArrayOutputStream is = new ByteArrayOutputStream();
		m.marshal(xmlObject, is);
		return is.toByteArray();
	}

	public static byte[] Marshall(Object xmlObject) throws JAXBException {
		return Marshall(xmlObject, xmlObject.getClass().getPackage().getName());
	}

}
