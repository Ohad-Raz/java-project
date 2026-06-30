package hr.algebra.factory;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

// StAX XML reader factory (Exercise 07-08 ParserFactory pattern).
public final class ParserFactory {

    private ParserFactory() {
    }

    public static XMLEventReader createStaxParser(InputStream stream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLEventReader(stream);
    }
}
