package org.turner.model.catalog;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author turner
 */
@XStreamAlias("pgterms:agent")
public class Author {
  
  @XStreamAlias("pgterms:name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
