package com.snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake
{
  public enum SnakeDirection { UP, RIGHT, DOWN, LEFT };
  private final ArrayList<Point> bodySegments = new ArrayList<>();
  private final Point headLocation;
  private SnakeDirection direction;
  private final int gridSize;

  public Snake(int gridSize) 
  {
    headLocation = new Point(gridSize/2, gridSize/2);
    this.gridSize = gridSize;
    bodySegments.add(headLocation);
    direction = SnakeDirection.UP;
  }

  public void update()
  {
    for (int i = bodySegments.size() - 1; i > 0; --i) 
    {
      bodySegments.set(i, bodySegments.get(i - 1));
    }
    bodySegments.set(0, headLocation);

    switch (direction) 
    {
      case UP:
        headLocation.setLocation(headLocation.getX(), headLocation.getY() - 1);
        break;
      case RIGHT:
        headLocation.setLocation(headLocation.getX() + 1, headLocation.getY());
        break;
      case DOWN:
        headLocation.setLocation(headLocation.getX(), headLocation.getY() + 1);
        break;
      case LEFT:
        headLocation.setLocation(headLocation.getX() - 1, headLocation.getY());
        break;
      default:
        break;
    }

    if(headLocation.getX() >= gridSize)
      headLocation.setLocation(0, headLocation.getY());
    else if(headLocation.getX() < 0)
      headLocation.setLocation(gridSize - 1, headLocation.getY());

    if(headLocation.getY() >= gridSize)
      headLocation.setLocation(headLocation.getX(), 0);
    else if(headLocation.getY() < 0)
      headLocation.setLocation(headLocation.getX(), gridSize - 1);
  }

  public Point getPosition()
  {
    return headLocation;
  }

  public SnakeDirection getDirection() 
  {
    return direction;
  }

  public void setDirection(final SnakeDirection direction)
  {
    this.direction = direction;
  }

  public boolean checkForSelfCollision()
  {
    return bodySegments.stream().anyMatch((point) -> point.equals(headLocation));
  }
}
