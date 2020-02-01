package afanasievald.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ReadXMLFileSAX {
  public static Object readXML(String xmlFile) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      SaxHandler saxHandler = new SaxHandler();
      saxParser.parse(xmlFile, saxHandler);

      return saxHandler.getObject();
    }
    catch(SAXException | ParserConfigurationException | IOException e){
    }
    return null;
  }
}
