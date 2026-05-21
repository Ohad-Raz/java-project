/*
 * Project setup note:
 * Based on Exercise 11-12, JAXB XML helper pattern. Adapted for reusable XML export/import support.
 */

package hr.algebra.utilities;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Generic JAXB helper for XML export/import.
 * Final project XML root types (e.g. archive wrapper) will be added in a later step.
 */
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
