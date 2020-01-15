package com.snake;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class AppleView implements Drawable
{
  private final Point position;
  private final int cellSize;

  public AppleView(int cellSize)
  {
    position = new Point(0, 0);
    this.cellSize = cellSize;
  }

  public void setPosition(Point newPosition)
  {
    position.setLocation(newPosition);
  }

  public Point getPosition()
  {
    return position;
  }

  @Override
  public void draw(Graphics g)
  {
    g.setColor(Color.GREEN);
    g.fillRect((int)position.getX()*cellSize, 
      (int)position.getY()*cellSize, cellSize, cellSize);
  }
}