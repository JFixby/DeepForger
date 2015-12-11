package rebecca.e26.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLTransmitter {

	public static Object UnMarshall(byte[] xml, String packageName)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(packageName);
		Unmarshaller u = jc.createUnmarshaller();
		InputStream is = new ByteArrayInputStream(xml);
		return u.unmarshal(is);
	}

	public static byte[] Marshall(Object xmlObject) throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(xmlObject.getClass()
				.getPackage().getName());
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		ByteArrayOutputStream is = new ByteArrayOutputStream();
		m.marshal(xmlObject, is);
		return is.toByteArray();
	}

	public static <T> T UnMarshall(byte[] d, Class<T> class1)
			throws JAXBException {
		// TODO Auto-generated method stub
		return (T) UnMarshall(d, class1.getPackage().getName());
	}

}
