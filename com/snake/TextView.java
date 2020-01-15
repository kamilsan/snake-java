package com.snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;

public class TextView implements Drawable
{
  public enum AnchorType { Left, Center, Right };

  private String message;
  private Point position;
  private boolean visible;
  private AnchorType anchor;

  public TextView(String message, Point position)
  {
    this(message, position, true);
  }

  public TextView(String message, Point position, boolean visible)
  {
    this.message = message;
    this.position = position;
    this.visible = visible;
    this.anchor = AnchorType.Center;
  }

  public void setMessage(String newMessage)
  {
    message = newMessage;
  }

  public void setPosition(Point newPosition)
  {
    position = newPosition;
  }

  public void setVisibility(boolean visibility)
  {
    visible = visibility;
  }

  public void setAnchor(AnchorType newAnchor)
  {
    anchor = newAnchor;
  }

  public String getMessage()
  {
    return message;
  }

  public Point getPosition()
  {
    return position;
  }

  public boolean getVisibility()
  {
    return visible;
  }

  public AnchorType getAnchor()
  {
    return anchor;
  }

  @Override
  public void draw(Graphics g)
  {
    if(visible)
    {
      FontMetrics fontMetrics = g.getFontMetrics();
      int x = position.x;
      int y = position.y + fontMetrics.getHeight() / 2;

      if(anchor == AnchorType.Center)
        x -= fontMetrics.stringWidth(message) / 2;
      else if(anchor == AnchorType.Right)
        x -= fontMetrics.stringWidth(message);

      g.setColor(Color.WHITE);
      g.drawString(message, x, y);
    }
  }
}