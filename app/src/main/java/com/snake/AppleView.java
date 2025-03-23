package com.snake;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class responsible for graphical representation of the Apple
 */
public class AppleView implements Drawable
{
  /**
   * Position of an apple
   */
  private final Point position;

  /**
   * Size of an apple
   */
  private final int size;

  /**
   * Constructor that places the apple to (0, 0) and sets it's size to a specified one.
   * @param size Apple's size
   */
  public AppleView(int size)
  {
    position = new Point(0, 0);
    this.size = size;
  }

  /**
   * Setter for the position
   * @param newPosition Position to be set
   */
  public void setPosition(Point newPosition)
  {
    position.setLocation(newPosition);
  }

  /**
   * Getter for the position
   * @return position
   */
  public Point getPosition()
  {
    return position;
  }

  /**
   * Draws the apple
   * @param g Graphics object that will handle the drawing
   */
  @Override
  public void draw(Graphics g)
  {
    g.setColor(Color.GREEN);
    g.fillRect((int)position.getX()*size, 
      (int)position.getY()*size, size, size);
  }
}