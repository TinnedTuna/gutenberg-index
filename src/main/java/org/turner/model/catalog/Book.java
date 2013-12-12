package org.turner.model.catalog;

import java.util.List;

/**
 *
 * @author turner
 */
public class Book {

  private Integer catalogId;
  private List<String> title;
  private List<String> author;
  private String contents;

  public Book(Integer catalogId, List<String> title, List<String> author, String contents) {
    this.catalogId = catalogId;
    this.title = title;
    this.author = author;
    this.contents = contents;
  }

  public List<String> getAuthor() {
    return author;
  }

  public Integer getCatalogId() {
    return catalogId;
  }

  public String getContents() {
    return contents;
  }

  public List<String> getTitle() {
    return title;
  }
}
