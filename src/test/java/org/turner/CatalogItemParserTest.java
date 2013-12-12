package org.turner;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turner.model.catalog.Item;
import org.xml.sax.SAXException;

 
/**
 *
 * @author turner
 */
@RunWith(JUnit4.class)
public class CatalogItemParserTest {
  
  private static final Logger logger = LoggerFactory.getLogger(CatalogItemParserTest.class);
  
  @Test
  public void singleTitleTest() throws IOException, SAXException {
    File pg1File = new File("src/test/resources/org/turner/pg1.rdf");
    CatalogItemParser catalogItemParser = new CatalogItemParser();
    Item parsed = catalogItemParser.parse(pg1File);
    Assert.assertNotNull(parsed);
    Assert.assertEquals(Integer.valueOf(1), parsed.getCatalogId());
    Assert.assertNotNull(parsed.getTitle());
    logger.info(parsed.getTitle().toString());
    Assert.assertEquals(1, parsed.getTitle().size());
    Assert.assertEquals("United States Declaration of Independence", parsed.getTitle().get(0));
  }
  
    @Test
  public void singleAuthorTest() throws IOException, SAXException {
    File pg1File = new File("src/test/resources/org/turner/pg1140.rdf");
    CatalogItemParser catalogItemParser = new CatalogItemParser();
    Item parsed = catalogItemParser.parse(pg1File);
    Assert.assertNotNull(parsed);
    Assert.assertEquals(Integer.valueOf(1140), parsed.getCatalogId());
    Assert.assertNotNull(parsed.getAuthors());
    logger.info(parsed.getAuthors().toString());
    Assert.assertEquals(1, parsed.getAuthors().size());
    Assert.assertEquals("Carlyle, Thomas", parsed.getAuthors().get(0));
  }
  
  
}
