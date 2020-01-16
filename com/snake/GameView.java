package com.snake;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

/**
 * Main view for the game, responsible for drawing other views on the grid
 */
public class GameView extends JPanel
{
  /**
   * Size of the grid
   */
  private final int gridSize;

  /**
   * List of views (drawable objects) that will be drawn
   */
  private ArrayList<Drawable> views;

  /**
   * Constructs the view
   * @param viewSize Size of the canvas
   * @param gridSize Size of the grid on which objects will move
   */
  public GameView(int viewSize, int gridSize)
  {
    super();
    setPreferredSize(new Dimension(viewSize, viewSize));
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    views = new ArrayList<>();
    this.gridSize = gridSize;
  }

  /**
   * Add's a view to the list, so that it can be drawn
   * @param view Drawable object
   */
  public void addView(Drawable view)
  {
    views.add(view);
  }

  /**
   * Getter for the grid size
   * @return Size of the grid
   */
  public int getGridSize()
  {
    return gridSize;
  }

  /**
   * Overriten method responsible for drawing the view
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    for(var view : views)
    {
      view.draw(g);
    }
    
    Toolkit.getDefaultToolkit().sync();
  }
}