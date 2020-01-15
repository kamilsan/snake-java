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
    reset();
  }

  public void reset()
  {
    bodySegments.clear();
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

    Point headPosition = bodySegments.get(0);
    switch (direction) 
    {
      case UP:
        headPosition.setLocation(headPosition.getX(), headPosition.getY() - 1);
        break;
      case RIGHT:
        headPosition.setLocation(headPosition.getX() + 1, headPosition.getY());
        break;
      case DOWN:
        headPosition.setLocation(headPosition.getX(), headPosition.getY() + 1);
        break;
      case LEFT:
        headPosition.setLocation(headPosition.getX() - 1, headPosition.getY());
        break;
      default:
        break;
    }

    if(headPosition.getX() >= gridSize)
      headPosition.setLocation(0, headPosition.getY());
    else if(headPosition.getX() < 0)
      headPosition.setLocation(gridSize - 1, headPosition.getY());

    if(headPosition.getY() >= gridSize)
      headPosition.setLocation(headPosition.getX(), 0);
    else if(headPosition.getY() < 0)
      headPosition.setLocation(headPosition.getX(), gridSize - 1);
  }

  public void grow()
  {
    bodySegments.add((Point)bodySegments.get(bodySegments.size() - 1).clone());
  }

  public Point getHeadPosition()
  {
    return bodySegments.get(0);
  }

  public ArrayList<Point> getBodySegments()
  {
    return bodySegments;
  }

  public SnakeDirection getDirection() 
  {
    return direction;
  }

  public void setDirection(SnakeDirection direction)
  {
    if((this.direction == SnakeDirection.LEFT && direction != SnakeDirection.RIGHT) ||
      (this.direction == SnakeDirection.RIGHT && direction != SnakeDirection.LEFT) ||
      (this.direction == SnakeDirection.UP && direction != SnakeDirection.DOWN) || 
      (this.direction == SnakeDirection.DOWN && direction != SnakeDirection.UP))
      this.direction = direction;
  }

  public boolean isSelfColliding()
  {
    var headPosition = bodySegments.get(0);
    return bodySegments.stream().anyMatch((point) -> point.equals(headPosition) && point != headPosition);
  }
}
