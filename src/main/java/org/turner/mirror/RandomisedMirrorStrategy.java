package org.turner.mirror;

import java.util.Random;
import org.turner.ConfigDAO;

/**
 *
 * @author turner
 */
public class RandomisedMirrorStrategy implements MirrorStrategy {

  private String[] mirrorUrls;
  private Random random;
  
  public RandomisedMirrorStrategy(ConfigDAO configDAO) {
    mirrorUrls = configDAO.getMirrorList().toArray(new String[0]);
    random = new Random();
  }
  
  @Override
  public String getMirrorUrl() {
    assert mirrorUrls != null;
    assert mirrorUrls.length > 0;
    assert random != null;
    
    int nextInt = random.nextInt(mirrorUrls.length);
    
    assert nextInt >= 0 && nextInt < mirrorUrls.length;
    return mirrorUrls[nextInt];
  }
  
}
