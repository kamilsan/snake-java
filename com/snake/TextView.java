package com.snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;

/**
 * Class responsible for displaying the text
 */
public class TextView implements Drawable
{
  /**
   * Posible anchor types
   */
  public enum AnchorType { Left, Center, Right };

  /**
   * Text to be displayd
   */
  private String text;

  /**
   * Position of the anchor point
   */
  private Point position;

  /**
   * Tells if the text should be visible
   */
  private boolean visible;

  /**
   * Anchor type
   */
  private AnchorType anchor;

  /**
   * Constructs the view
   * @param text Text to be displayed
   * @param position Position of the text
   */
  public TextView(String text, Point position)
  {
    this(text, position, true);
  }

  /**
   * Constructs the view
   * @param text Text to be displayed
   * @param position Position of the text
   * @param visible Tells if the text should be visible
   */
  public TextView(String text, Point position, boolean visible)
  {
    this.text = text;
    this.position = position;
    this.visible = visible;
    this.anchor = AnchorType.Center;
  }

  /**
   * Setter for the text
   * @param newText Text to be set
   */
  public void setText(String newText)
  {
    text = newText;
  }

  /**
   * Setter for the position
   * @param newPosition Position to be set
   */
  public void setPosition(Point newPosition)
  {
    position = newPosition;
  }

  /**
   * Setter for the visibility
   * @param visibility New visibility
   */
  public void setVisibility(boolean visibility)
  {
    visible = visibility;
  }

  /**
   * Setter for the anchor type
   * @param newAnchor New anchor type
   */
  public void setAnchor(AnchorType newAnchor)
  {
    anchor = newAnchor;
  }

  /**
   * Getter for the text
   * @return text
   */
  public String getText()
  {
    return text;
  }

  /**
   * Getter for the position
   * @return position
   */
  public Point getPosition()
  {
    return position;
  }

  /**
   * Getter for the visibility
   * @return visibility
   */
  public boolean getVisibility()
  {
    return visible;
  }

  /**
   * Getter for the anchor type
   * @return anchor type
   */
  public AnchorType getAnchor()
  {
    return anchor;
  }

  /**
   * Draws the text
   * @param g Graphics object that will handle the drawing
   */
  @Override
  public void draw(Graphics g)
  {
    if(visible)
    {
      FontMetrics fontMetrics = g.getFontMetrics();
      int x = position.x;
      int y = position.y + fontMetrics.getHeight() / 2;

      if(anchor == AnchorType.Center)
        x -= fontMetrics.stringWidth(text) / 2;
      else if(anchor == AnchorType.Right)
        x -= fontMetrics.stringWidth(text);

      g.setColor(Color.WHITE);
      g.drawString(text, x, y);
    }
  }
}