package org.turner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.turner.model.catalog.Item;
import org.turner.xml.ItemContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author turner
 */
public class CatalogItemParser {
  
  
  public Item parse(File inputCatalogFile) throws IOException, SAXException {
    String catalogId = inputCatalogFile.getName().split("\\.")[0].substring(2);
    Item catalogItem = parseXmlStreamToCatalogItem(new FileInputStream(inputCatalogFile));
    catalogItem.setCatalogId(Integer.parseInt(catalogId));
    return catalogItem;
  }  
  
  private Item parseXmlStreamToCatalogItem(InputStream fileInputStream) throws SAXException, IOException {
    XMLReader xmlReader = XMLReaderFactory.createXMLReader();
    Item parsed = new Item();
    xmlReader.setContentHandler(new ItemContentHandler(parsed));
    xmlReader.parse(new InputSource(fileInputStream));
    return parsed;
  }
}
