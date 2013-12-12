package org.turner;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turner.model.catalog.Book;
import org.turner.model.catalog.Item;

/**
 *
 * @author turner
 */
public class ItemToBookConvertor {
  
  private static final Logger logger = LoggerFactory.getLogger(ItemToBookConvertor.class);
  

  public Book convertToBook(Item itemToProcess) throws MalformedURLException, IOException {
    URL url = new URL(getFullUrlString(itemToProcess));
    URLConnection connection = url.openConnection();
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName(connection.getContentEncoding())));
    StringBuilder stringBuilder = new StringBuilder(connection.getContentLength());
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
    }
    return new Book(
                      itemToProcess.getCatalogId(), 
                      itemToProcess.getTitle(),
                      itemToProcess.getAuthors(),
                      stringBuilder.toString());
  }
  
  private String getFullUrlString(Item itemToProcess) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
