package org.turner.model.catalog;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author turner
 */
@XStreamAlias("pgterms:ebook")
public class Item {
  
  private Integer catalogId; // set based on the filename.
  
  @XStreamAlias("dcterms:title")
  private List<String> title; // Retrieved from xml catalog files.

  @XStreamAlias("pgterms:agent")
  @XStreamImplicit
  private List<Author> author; // Retrieved from xml cataolog files.
  private String mirrorUrl; // set by the driver.

  public List<Author> getAuthor() {
    return author;
  }

  public void setAuthor(List<Author> author) {
    this.author = author;
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
