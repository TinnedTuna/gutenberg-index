package org.turner;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import org.turner.model.catalog.Item;

/**
 *
 * @author turner
 */
public class CatalogProcessor {
  
  private File catalogDirectory;
  private MirrorStrategy mirrorStrategy;
  private final ExecutorService pool;
  private Queue<Item> catalogQueue;

  public CatalogProcessor(
          File catalogDirectory,
          Queue<Item> catalogQueue,
          ExecutorService pool) {
    this.catalogDirectory = catalogDirectory;
    this.catalogQueue = catalogQueue;
    this.pool = pool;
  }
  
  public void processCatalog() {
    for (File catalogFile : getCatalogDirectory().listFiles()) {
      getPool().submit(new CatalogItemProcessor(
              getCatalogQueue(), 
              catalogFile,
              mirrorStrategy.getMirrorUrl()));
    }
  }
  
  private Queue<Item> getCatalogQueue() {
    assert catalogQueue != null;
    return catalogQueue;
  }
  
  private ExecutorService getPool() {
    assert pool != null;
    return pool;
  }

  public File getCatalogDirectory() {
    assert catalogDirectory != null;
    return catalogDirectory;
  }
  
}
