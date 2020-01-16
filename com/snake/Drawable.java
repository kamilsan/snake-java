package com.snake;

import java.awt.Graphics;

/**
 * Common interface for objects that can be drawn
 */
public interface Drawable
{
  /**
   * Draws the object
   * @param g Graphics object used for the drawing
   */
  public void draw(Graphics g);
}