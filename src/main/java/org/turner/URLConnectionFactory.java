package org.turner;

import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author turner
 */
public interface URLConnectionFactory {

  public URLConnection getURLConnection(URL url);
  
}
