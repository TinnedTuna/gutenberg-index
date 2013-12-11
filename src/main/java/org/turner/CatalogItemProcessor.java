package org.turner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turner.model.catalog.Item;
import org.turner.xml.ItemContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author turner
 */
public class CatalogItemProcessor implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(CatalogItemProcessor.class);
  
  private Queue<Item> outputQueue;
  private String mirrorUrl;
  private File inputCatalogFile;

  public CatalogItemProcessor(
          Queue<Item> outputQueue, 
          File inputCatalogFile,
          String mirrorUrl) {
    this.outputQueue = outputQueue;
    this.inputCatalogFile = inputCatalogFile;
    this.mirrorUrl = mirrorUrl;
  }
  
  public void run() {
    // Open file cache/epub/<itemID>/pg<itemID>.pdf
    // parse the damned file
    // put a new Catalog item on the queue.
    String catalogId = inputCatalogFile.getName().split("\\.")[0].substring(2);
    try {
      Item catalogItem = null;;
      try {
        catalogItem = parseXmlStreamToCatalogItem(new FileInputStream(inputCatalogFile));
      } catch (IOException ex) {
        logger.error("IOException parsing catalogId: {}", catalogId, ex);
      }
      catalogItem.setCatalogId(Integer.parseInt(catalogId));
      catalogItem.setMirrorUrl(mirrorUrl);
      outputQueue.add(catalogItem);
      return;
    } catch (SAXException ex) {
      logger.error("SAXException parsing catalogId: {}", catalogId, ex);
    }
  }

  private Item parseXmlStreamToCatalogItem(InputStream fileInputStream) throws SAXException, IOException {
    XMLReader xmlReader = XMLReaderFactory.createXMLReader();
    Item parsed = new Item();
    xmlReader.setContentHandler(new ItemContentHandler(parsed));
    xmlReader.parse(new InputSource(fileInputStream));
    return parsed;
  }
  
  
}
