package com.snake;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class SnakeView extends JPanel
{
  private final int SIZE = 12;
  private Point2D position;

  public SnakeView()
  {
    super();
    position = new Point(0, 0);
  }

  public Point2D getPosition()
  {
    return position;
  }

  public void setPosition(Point2D newPosition)
  {
    position.setLocation(newPosition);
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.RED);
    g.fillRect((int)position.getX(), (int)position.getY(), SIZE, SIZE);
  }
}