package org.turner;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import org.turner.model.catalog.Book;

/**
 *
 * @author turner
 */
public class CatalogProcessor {
  
  private File catalogDirectory;
  private MirrorStrategy mirrorStrategy;
  private final ExecutorService pool;
  private BlockingQueue<Book> indexQueue;
  private URLConnectionFactory urlConnectionFactory;

  public CatalogProcessor(
          File catalogDirectory,
          BlockingQueue<Book> catalogQueue,
          ExecutorService pool) {
    this.catalogDirectory = catalogDirectory;
    this.indexQueue = catalogQueue;
    this.pool = pool;
  }
  
  public void processCatalog() {
    for (File catalogFile : getCatalogDirectory().listFiles()) {
      getPool().submit(new CatalogItemProcessor(
              getIndexQueue(),
              getUrlConnectionFactory(),
              catalogFile,
              mirrorStrategy.getMirrorUrl()));
    }
  }
  
  private BlockingQueue<Book> getIndexQueue() {
    assert indexQueue != null;
    return indexQueue;
  }
  
  private ExecutorService getPool() {
    assert pool != null;
    return pool;
  }

  public File getCatalogDirectory() {
    assert catalogDirectory != null;
    return catalogDirectory;
  }

  public URLConnectionFactory getUrlConnectionFactory() {
    assert urlConnectionFactory != null;
    return urlConnectionFactory;
  }
  
}
