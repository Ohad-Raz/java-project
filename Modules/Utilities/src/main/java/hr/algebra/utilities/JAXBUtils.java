package hr.algebra.utilities;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

// JAXB marshal and unmarshal helpers (Exercise 11-12 JAXBUtils pattern).
public final class JAXBUtils {

    private JAXBUtils() {
    }

    public static void save(Object object, String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File(filename));
    }

    @SuppressWarnings("unchecked")
    public static <T> T load(Class<T> clazz, String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new File(filename));
    }
}
