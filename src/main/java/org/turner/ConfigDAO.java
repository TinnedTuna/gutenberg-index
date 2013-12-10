package org.turner;

import java.io.File;
import java.util.Collection;

/**
 *
 * @author turner
 */
public interface ConfigDAO {
  
  public File getCatalogDirectory();
  public Collection<String> getMirrorList();
  public int getMaxConnectionThreads();
  public int getMaxCatalogThreads();
  
}
