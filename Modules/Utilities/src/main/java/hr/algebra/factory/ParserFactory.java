/*
 * Project setup note:
 * Based on Exercise 07-08, StAX ParserFactory pattern. Adapted for RSS parsing infrastructure.
 */

package hr.algebra.factory;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public final class ParserFactory {

    private ParserFactory() {
    }

    public static XMLEventReader createStaxParser(InputStream stream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLEventReader(stream);
    }
}
