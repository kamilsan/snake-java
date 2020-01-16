package com.snake;

import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

public class SnakeView implements Drawable
{
  private final int cellSize;
  private ArrayList<Point> positions;

  public SnakeView(int viewSize, int cellSize)
  {
    positions = new ArrayList<>();
    this.cellSize = cellSize;
  }

  public void setPositions(ArrayList<Point> newPositions)
  {
    positions.clear();
    for(var newPosition : newPositions)
    {
      Point positionInGrid = newPosition;
      positions.add(new Point((int)positionInGrid.getX()*cellSize, 
        (int)positionInGrid.getY()*cellSize));
    }
  }

  @Override
  public void draw(Graphics g) 
  {  
    g.setColor(Color.RED);
    g.fillRect((int)positions.get(0).getX(), 
        (int)positions.get(0).getY(), cellSize, cellSize);
    
    g.setColor(Color.WHITE);
    for(int i = 1; i < positions.size(); ++i)
    {
      g.fillRect((int)positions.get(i).getX(), 
        (int)positions.get(i).getY(), cellSize, cellSize);
    }
  }
}