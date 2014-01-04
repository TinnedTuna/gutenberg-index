package org.turner.mirror;

/**
 * Encapsulates a mirror URL delivery pattern. This could be a single fixed 
 * mirror, one selected at random from a pool, or one that's part of a round 
 * robin scheme, or some other all together.
 * 
 * @author turner
 */
public interface MirrorStrategy {
  
  public String getMirrorUrl();
  
}
