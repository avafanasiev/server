package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import reflection.ReflectionHelper;

public class SaxHandler extends DefaultHandler {
  private static final String CLASSNAME = "class";
  private String element = null;
  private Object object = null;

  public void startElement (String uri, String localName,
                            String qName, Attributes attributes) throws SAXException
  {
    if (qName.equals(CLASSNAME)){
      String className = attributes.getValue(0);
      object = ReflectionHelper.createInstance(className);
    } else{
      element = qName;
    }
  }

  public void endElement (String uri, String localName, String qName) throws SAXException
  {
    element = null;
  }

  public void characters (char ch[], int start, int length) throws SAXException
  {
    String value = String.valueOf(ch, start, length);
    ReflectionHelper.setFieldValue(object, element, value);
  }

}
