package com.snake;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class Apple
{
  private final Point position;

  public Apple()
  {
    this.position = new Point(0, 0);
  }

  public void selectGridPosition(int gridSize)
  {
    int x = ThreadLocalRandom.current().nextInt(0, gridSize);
    int y = ThreadLocalRandom.current().nextInt(0, gridSize);
    position.setLocation(x, y);
  }

  public void setPosition(Point newPosition)
  {
    position.setLocation(newPosition);
  }

  public Point getPosition()
  {
    return position;
  }
}