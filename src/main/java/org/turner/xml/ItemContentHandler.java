package org.turner.xml;

import java.util.ArrayList;
import java.util.Arrays;
import org.turner.model.catalog.Item;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author turner
 */
public class ItemContentHandler implements ContentHandler {

  private enum ItemContentHandlerParseState {
    UNKNOWN,
    DCTITLE,
    PGNAME
  }
  
  Item itemResult;
  ItemContentHandlerParseState parseState = ItemContentHandlerParseState.UNKNOWN;
  StringBuilder stringBuilder = new StringBuilder();
  
  public ItemContentHandler(Item itemResult) {
    this.itemResult = itemResult;
  }
  
  @Override
  public void setDocumentLocator(Locator arg0) {  }

  @Override
  public void startDocument() throws SAXException {  }

  @Override
  public void endDocument() throws SAXException {  }

  @Override
  public void startPrefixMapping(String arg0, String arg1) throws SAXException {  }

  @Override
  public void endPrefixMapping(String arg0) throws SAXException {  }

  @Override
  public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
    assert itemResult != null;
    if ("dcterms:title".equals(arg2)) {
      parseState = ItemContentHandlerParseState.DCTITLE;
      stringBuilder = new StringBuilder();
    } 
    if ("pgterms:name".equals(arg2)) {
      parseState = ItemContentHandlerParseState.PGNAME;
      stringBuilder = new StringBuilder();
    }
  }

  @Override
  public void endElement(String arg0, String arg1, String arg2) throws SAXException {
    if (ItemContentHandlerParseState.DCTITLE.equals(parseState)) {
      if (itemResult.getTitle() == null) {
        itemResult.setTitle(new ArrayList<String>());
      }
      itemResult.getTitle().add(stringBuilder.toString());
    }
    if (ItemContentHandlerParseState.PGNAME.equals(parseState)) {
      if (itemResult.getAuthors() == null) {
        itemResult.setAuthors(new ArrayList<String>());
      }
      itemResult.getAuthors().add(stringBuilder.toString());
    }
    if ("dcterms:title".equals(arg2)) {
      parseState = ItemContentHandlerParseState.UNKNOWN;
    }
    if ("pgterms:name".equals(arg2)) {
      parseState = ItemContentHandlerParseState.UNKNOWN;
    }
  }

  @Override
  public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
    if (ItemContentHandlerParseState.DCTITLE.equals(parseState) ||
            ItemContentHandlerParseState.PGNAME.equals(parseState)) {
      stringBuilder.append(Arrays.copyOfRange(arg0, arg1, arg1+arg2));
    }
  }

  @Override
  public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {  }

  @Override
  public void processingInstruction(String arg0, String arg1) throws SAXException {  }

  @Override
  public void skippedEntity(String arg0) throws SAXException {  }
  
}
