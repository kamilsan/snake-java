package com.snake;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing Apple model
 */
public class Apple
{
  /**
   * Position of an apple
   */
  private final Point position;

  /**
   * A constructor. Sets apple to position (0, 0)
   */
  public Apple()
  {
    this.position = new Point(0, 0);
  }

  /**
   * Places apple in a random place on a grid
   * @param gridSize Size of the grid
   */
  public void selectGridPosition(int gridSize)
  {
    int x = ThreadLocalRandom.current().nextInt(0, gridSize);
    int y = ThreadLocalRandom.current().nextInt(0, gridSize);
    position.setLocation(x, y);
  }

  /**
   * Setter for position
   * @param newPosition Position to be set
   */
  public void setPosition(Point newPosition)
  {
    position.setLocation(newPosition);
  }

  /**
   * Getter for position
   * @return position
   */
  public Point getPosition()
  {
    return position;
  }
}