package org.turner;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.turner.model.catalog.Author;
import org.turner.model.catalog.Item;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author turner
 */
public class CatalogItemProcessor implements Runnable {

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
      Item catalogItem = parseXmlStreamToCatalogItem(new FileInputStream(inputCatalogFile));
      catalogItem.setCatalogId(Integer.parseInt(catalogId));
      catalogItem.setMirrorUrl(mirrorUrl);
      outputQueue.add(catalogItem);
      return;
    } catch (SAXException ex) {
      Logger.getLogger(CatalogItemProcessor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(CatalogItemProcessor.class.getName()).log(Level.SEVERE, null, ex);
      return;
    }
  }

  private Item parseXmlStreamToCatalogItem(FileInputStream fileInputStream) throws SAXException {
    XStream xstream = new XStream();
    xstream.processAnnotations(Arrays.asList(Item.class, Author.class).toArray(new Class[2]));
    return (Item) xstream.fromXML(inputCatalogFile);
  }
  
  
}
