package org.turner.model.catalog;

import java.util.List;

/**
 *
 * @author turner
 */
public class Item {
  
  private Integer catalogId; // set based on the filename.
  
  private List<String> title; // Retrieved from xml catalog files.

  private List<String> authors; // Retrieved from xml cataolog files.
  private String mirrorUrl; // set by the driver.

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public Integer getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(Integer catalogId) {
    this.catalogId = catalogId;
  }

  public String getMirrorUrl() {
    return mirrorUrl;
  }

  public void setMirrorUrl(String mirrorUrl) {
    this.mirrorUrl = mirrorUrl;
  }

  public List<String> getTitle() {
    return title;
  }

  public void setTitle(List<String> title) {
    this.title = title;
  }
}
