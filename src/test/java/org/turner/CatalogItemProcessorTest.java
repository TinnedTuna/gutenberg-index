package org.turner;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turner.model.catalog.Item;

 
/**
 *
 * @author turner
 */
@RunWith(JUnit4.class)
public class CatalogItemProcessorTest {
  
  private static final Logger logger = LoggerFactory.getLogger(CatalogItemProcessorTest.class);
  
  @Test
  public void basicTest() {
    File pg1File = new File("src/test/resources/org/turner/pg1.rdf");
    Mockery context = new Mockery();
    final Queue mockQueue = context.mock(Queue.class);
    context.checking(new Expectations() {{
     exactly(1).of(mockQueue).add(with(any(Item.class)));
    }});
    CatalogItemProcessor catalogItemProcessor = new CatalogItemProcessor(mockQueue, pg1File, "http://mirror.ac.uk/");
    catalogItemProcessor.run();
    context.assertIsSatisfied();
  }
  
  @Test
  public void singleTitleTest() {
    File pg1File = new File("src/test/resources/org/turner/pg1.rdf");
    Queue<Item> itemsQueue = new LinkedList<>();
    CatalogItemProcessor catalogItemProcessor = new CatalogItemProcessor(itemsQueue, pg1File, "http://mirror.ac.uk/");
    catalogItemProcessor.run();
    Assert.assertEquals(1, itemsQueue.size());
    Item parsed = itemsQueue.remove();
    Assert.assertNotNull(parsed);
    Assert.assertEquals(Integer.valueOf(1), parsed.getCatalogId());
    Assert.assertNotNull(parsed.getTitle());
    logger.info(parsed.getTitle().toString());
    Assert.assertEquals(1, parsed.getTitle().size());
    Assert.assertEquals("United States Declaration of Independence", parsed.getTitle().get(0));
  }
  
    @Test
  public void singleAuthorTest() {
    File pg1File = new File("src/test/resources/org/turner/pg1140.rdf");
    Queue<Item> itemsQueue = new LinkedList<>();
    CatalogItemProcessor catalogItemProcessor = new CatalogItemProcessor(itemsQueue, pg1File, "http://mirror.ac.uk/");
    catalogItemProcessor.run();
    Assert.assertEquals(1, itemsQueue.size());
    Item parsed = itemsQueue.remove();
    Assert.assertNotNull(parsed);
    Assert.assertEquals(Integer.valueOf(1140), parsed.getCatalogId());
    Assert.assertNotNull(parsed.getAuthors());
    logger.info(parsed.getAuthors().toString());
    Assert.assertEquals(1, parsed.getAuthors().size());
    Assert.assertEquals("Carlyle, Thomas", parsed.getAuthors().get(0));
  }
  
  
}
