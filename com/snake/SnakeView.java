package com.snake;

import java.awt.*;
import java.awt.Point;
import javax.swing.JPanel;

public class SnakeView extends JPanel
{
  private final int cellSize;
  private Point position;

  public SnakeView(int viewSize, int cellSize)
  {
    super();
    setPreferredSize(new Dimension(viewSize, viewSize));
    setDoubleBuffered(true);
    setBackground(Color.BLACK);
    setFocusable(true);

    position = new Point(viewSize/2, viewSize/2);
    this.cellSize = cellSize;
  }

  public Point getPosition()
  {
    return position;
  }

  public void setPosition(Point newPosition)
  {
    Point positionInGrid = newPosition;
    position.setLocation(positionInGrid.getX()*cellSize, positionInGrid.getY()*cellSize);
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.fillRect((int)position.getX(), 
      (int)position.getY(), cellSize, cellSize);
    
    Toolkit.getDefaultToolkit().sync();
  }
}