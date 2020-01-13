package com.snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake
{
  public enum SnakeDirection { UP, RIGHT, DOWN, LEFT };
  private final ArrayList<Point> bodySegments = new ArrayList<>();
  private SnakeDirection direction;
  private final int gridSize;

  public Snake(int gridSize) 
  {
    this.gridSize = gridSize;
    direction = SnakeDirection.UP;
    Point headLocation = new Point(gridSize/2, gridSize/2);
    bodySegments.add(headLocation);
    bodySegments.add(new Point(gridSize/2, gridSize/2 + 1));
    bodySegments.add(new Point(gridSize/2, gridSize/2 + 2));
  }

  public void update()
  {
    for (int i = bodySegments.size() - 1; i > 0; --i) 
    {
      bodySegments.set(i, (Point)bodySegments.get(i - 1).clone());
    }

    Point headLocation = bodySegments.get(0);
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

  public ArrayList<Point> getBodySegments()
  {
    return bodySegments;
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
    return bodySegments.stream().anyMatch((point) -> point.equals(bodySegments.get(0)));
  }
}
