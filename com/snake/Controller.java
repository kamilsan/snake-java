package com.snake;

import java.awt.event.*;
import javax.swing.Timer;

public class Controller implements ActionListener
{
  private Snake model;
  private SnakeView view;
  private Timer timer;

  Controller(Snake model, SnakeView view)
  {
    this.model = model;
    this.view = view;

    this.view.addKeyListener(new KeyListener()
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

    updateViewPosition();

    timer = new Timer(150, this);
    timer.start();    
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
    view.setPosition(model.getPosition());
  }

  private void timerTick()
  {
    model.update();
    updateViewPosition();
  }

  public void actionPerformed(ActionEvent evt) 
  {
    timerTick();
  }
}