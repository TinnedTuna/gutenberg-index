package org.turner.mirror;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.turner.ConfigDAO;

/**
 *
 * @author turner
 */
@RunWith(JUnit4.class)
public class RandomisedMirrorStrategyTest {
  
  @Test
  public void instantiationTest() {
    Mockery context = new Mockery();
    final ConfigDAO mockConfigDAO = context.mock(ConfigDAO.class);
    context.checking(new Expectations() {{
      ignoring(mockConfigDAO);
    }});
    RandomisedMirrorStrategy randomisedMirrorStrategy = new RandomisedMirrorStrategy(mockConfigDAO);
    context.assertIsSatisfied();
    Assert.assertNotNull(randomisedMirrorStrategy);
  }
  
  @Test
  public void testOneMirrorUrlRepeated() {
    Mockery context = new Mockery();
    final ConfigDAO mockConfigDAO = context.mock(ConfigDAO.class);
    context.checking(new Expectations() {{
      exactly(1).of(mockConfigDAO).getMirrorList(); will(returnValue(Arrays.asList("mirrorUrl")));
    }});
    RandomisedMirrorStrategy randomisedMirrorStrategy = new RandomisedMirrorStrategy(mockConfigDAO);
    for (int i = 0; i<30; i++) {
      String mirrorUrl = randomisedMirrorStrategy.getMirrorUrl();
      Assert.assertNotNull(mirrorUrl);
      Assert.assertEquals("mirrorUrl", mirrorUrl);
    }
  }  
  
  @Test
  public void testManyMirrorsRepeated() {
    Mockery context = new Mockery();
    final ConfigDAO mockConfigDAO = context.mock(ConfigDAO.class);
    final Set<String> mirrorList = new HashSet<>(Arrays.asList(
            "mirrorOne", 
            "mirrorTwo", 
            "mirrorThree",
            "mirrorFour"));
    context.checking(new Expectations() {{
      exactly(1).of(mockConfigDAO).getMirrorList(); will(returnValue(mirrorList));
    }});
    RandomisedMirrorStrategy randomisedMirrorStrategy = new RandomisedMirrorStrategy(mockConfigDAO);
    for (int i = 0; i<30; i++) {
      String mirrorUrl = randomisedMirrorStrategy.getMirrorUrl();
      Assert.assertNotNull(mirrorUrl);
      Assert.assertTrue(mirrorList.contains(mirrorUrl));
    }
  }
}
