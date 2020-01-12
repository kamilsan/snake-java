package com.snake;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Snake
{
  public enum SnakeDirection { UP, RIGHT, DOWN, LEFT };
  private final float SPEED = 0.1f;
  private final ArrayList<Point2D> bodySegments = new ArrayList<>();
  private final Point2D headLocation;
  private SnakeDirection direction;

  public Snake() 
  {
    headLocation = new Point.Float(0, 0);
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
        headLocation.setLocation(headLocation.getX(), headLocation.getY() - SPEED);
        break;
      case RIGHT:
        headLocation.setLocation(headLocation.getX() + SPEED, headLocation.getY());
        break;
      case DOWN:
        headLocation.setLocation(headLocation.getX(), headLocation.getY() + SPEED);
        break;
      case LEFT:
        headLocation.setLocation(headLocation.getX() - SPEED, headLocation.getY());
        break;
      default:
        break;
    }

    if(headLocation.getX() > 1.0f)
      headLocation.setLocation(-1.0f, headLocation.getY());
    else if(headLocation.getX() < -1.0f)
      headLocation.setLocation(1.0f, headLocation.getY());

    if(headLocation.getY() > 1.0f)
      headLocation.setLocation(headLocation.getX(), -1.0f);
    else if(headLocation.getY() < -1.0f)
      headLocation.setLocation(headLocation.getX(), 1.0f);
  }

  public Point2D getPosition()
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
