import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class XMLReader {
    public static void main(String[] args) {
        try {
            // Create SAXParserFactory instance
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Create a handler for SAX events
            DefaultHandler handler = new DefaultHandler() {
                boolean fieldFound = false;

                // Called when the parser encounters the start of an element
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase(field)) {
                        fieldFound = true;
                    }
                }

                // Called when the parser encounters the end of an element
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // Reset the fieldFound flag
                    fieldFound = false;
                }

                // Called when the parser encounters character data inside an element
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (fieldFound) {
                        String fieldValue = new String(ch, start, length);
                        System.out.println(fieldValue);
                    }
                }
            };

            // Parse the XML input stream using the handler
            InputStream inputStream = XMLReader.class.getResourceAsStream("input.xml");
            saxParser.parse(inputStream, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}


