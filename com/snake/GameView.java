package com.snake;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

public class GameView extends JPanel
{
  private final int gridSize;
  private ArrayList<Drawable> views;

  public GameView(int viewSize, int gridSize)
  {
    super();
    setPreferredSize(new Dimension(viewSize, viewSize));
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    views = new ArrayList<>();
    this.gridSize = gridSize;
  }

  public void addView(Drawable view)
  {
    views.add(view);
  }

  public int getGridSize()
  {
    return gridSize;
  }

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