package com.snake;

import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

public class Controller implements ActionListener
{
  private Snake model;
  private SnakeView view;
  private Timer timer;
  private boolean isGameRunning;

  Controller(Snake model, Window window)
  {
    this.model = model;
    this.view = window.getSnakeView();

    final int CONDITION = JComponent.WHEN_IN_FOCUSED_WINDOW;
    this.view.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("W"), "move up");
    this.view.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("A"), "move left");
    this.view.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("S"), "move down");
    this.view.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("D"), "move right");

    this.view.getActionMap().put("move up", new MoveAction(Snake.SnakeDirection.UP));
    this.view.getActionMap().put("move left", new MoveAction(Snake.SnakeDirection.LEFT));
    this.view.getActionMap().put("move down", new MoveAction(Snake.SnakeDirection.DOWN));
    this.view.getActionMap().put("move right", new MoveAction(Snake.SnakeDirection.RIGHT));

    timer = new Timer(150, this);
    isGameRunning = false;
    window.addStartActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        isGameRunning = true;
        timer.start();
      }
    });

    window.addPauseActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        isGameRunning = false;
        timer.stop();
      }
    });

    updateViewPosition();
  }

  private void updateViewPosition()
  {
    view.setPositions(model.getBodySegments());
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

  class MoveAction extends AbstractAction
  {
    private Snake.SnakeDirection direction;

    MoveAction(Snake.SnakeDirection direction)
    {
      this.direction = direction;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      if(isGameRunning)
        model.setDirection(direction);
    }
  }
}