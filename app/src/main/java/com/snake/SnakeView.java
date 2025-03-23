package com.snake;

import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Class responsible for graphical representation of the Snake
 */
public class SnakeView implements Drawable
{
  /**
   * Size of the snake's body segment
   */
  private final int cellSize;

  /**
   * List of body segment's positions
   */
  private ArrayList<Point> positions;

  /**
   * Constructs the view
   * @param cellSize Size of snake's body segment
   */
  public SnakeView(int cellSize)
  {
    positions = new ArrayList<>();
    this.cellSize = cellSize;
  }

  /**
   * Setter for snake's position
   * @param newPositions List of body segments' positions
   */
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

  /**
   * Draws the snake
   * @param g Graphics object that will handle the drawing
   */
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