package com.snake;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.event.*;
import javax.swing.Timer;

public class Controller
{
  private Window window;
  private Snake model;
  private SnakeView view;
  private Timer timer;

  Controller(Window window, Snake model)
  {
    this.window = window;
    this.model = model;
    this.view = window.getSnakeView();

    updateViewPosition();

    timer = new Timer(140, new TimerListener());
    timer.start();

    this.window.addKeyListener(new KeyListener()
    {
      public void keyTyped(KeyEvent ev)
      {

      }
  
      public void keyPressed(KeyEvent ev)
      {

      }
      
      public void keyReleased(KeyEvent ev)
      {
        handleKeyPress(ev.getKeyCode());
      }
    });
  }

  private void handleKeyPress(int keyCode)
  {
    switch(keyCode)
    {
      case KeyEvent.VK_W:
        model.setDirection(Snake.SnakeDirection.UP);
        break;
      case KeyEvent.VK_S:
        model.setDirection(Snake.SnakeDirection.DOWN);
        break;
      case KeyEvent.VK_A:
        model.setDirection(Snake.SnakeDirection.LEFT);
        break;
      case KeyEvent.VK_D:
        model.setDirection(Snake.SnakeDirection.RIGHT);
        break;
      default:
        break;
    }
  }

  private void updateViewPosition()
  {
    Point2D positionAbsolute = model.getPosition();
    int x = Math.round(0.5f*view.getWidth()*((float)positionAbsolute.getX() + 1.0f));
    int y = Math.round(0.5f*view.getHeight()*((float)positionAbsolute.getY() + 1.0f));
    view.setPosition(new Point(x, y));
  }

  private void timerTick()
  {
    model.update();
    updateViewPosition();
  }

  class TimerListener implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    {
      timerTick();
    }
  }
}