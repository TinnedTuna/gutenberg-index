package org.turner;

import java.io.File;
import java.util.Queue;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.turner.model.catalog.Item;

 
/**
 *
 * @author turner
 */
@RunWith(JUnit4.class)
public class CatalogItemProcessorTest {
  
  @Test
  public void basicTest() {
    File pg1File = new File("src/test/resources/org/turner/pg1.rdf");
    Mockery context = new Mockery();
    final Queue mockQueue = context.mock(Queue.class);
    context.checking(new Expectations() {{
     exactly(1).of(mockQueue).add(any(Item.class));
    }});
    CatalogItemProcessor catalogItemProcessor = new CatalogItemProcessor(mockQueue, pg1File, "http://mirror.ac.uk/");
    catalogItemProcessor.run();
    context.assertIsSatisfied();
    
  }
  
  
}
