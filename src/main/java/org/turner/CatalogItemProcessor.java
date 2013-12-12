package org.turner;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turner.model.catalog.Book;
import org.turner.model.catalog.Item;
import org.xml.sax.SAXException;

/**
 *
 * @author turner
 */
public class CatalogItemProcessor implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(CatalogItemProcessor.class);
  
  private BlockingQueue<Book> indexingQueue;
  private String mirrorUrl;
  private File inputCatalogFile;

  public CatalogItemProcessor(
          BlockingQueue<Book> indexingQueue, 
          File inputCatalogFile,
          String mirrorUrl) {
    this.indexingQueue = indexingQueue;
    this.inputCatalogFile = inputCatalogFile;
    this.mirrorUrl = mirrorUrl;
  }
  
  public void run() {
    try {
      CatalogItemParser parser = new CatalogItemParser();
      ItemToBookConvertor convertor = new ItemToBookConvertor();
      Item parsedItem = parser.parse(inputCatalogFile);
      parsedItem.setMirrorUrl(mirrorUrl);
      Book convertedToBook = convertor.convertToBook(parsedItem);
      indexingQueue.add(convertedToBook);
    } catch (SAXException ex) {
      logger.error("Could not parse a file, SAXException.", ex);
    } catch (MalformedURLException ex) {
      logger.error("Could not download a book, MalformedURLException.", ex);
    } catch (IOException ex) {
      logger.error("Could not parse a catalog file, IOException.", ex);
    }
  }
}
