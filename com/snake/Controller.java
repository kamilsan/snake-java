package com.snake;

import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

public class Controller
{
  private enum GameState { Running, Paused, PlayerLosed };

  private GameView gameView;
  private Snake snakeModel;
  private SnakeView snakeView;
  private Apple appleModel;
  private AppleView appleView;
  private ScoreView scoreView;

  private Timer timer;
  private GameState gameState;
  private int score;

  Controller(Window window, Snake snakeModel, SnakeView snakeView, Apple appleModel, AppleView appleView)
  {
    gameView = window.getGameView();
    this.snakeModel = snakeModel;
    this.snakeView = snakeView;
    this.appleModel = appleModel;
    this.appleView = appleView;

    scoreView = new ScoreView();
    gameView.addView(scoreView);

    final int CONDITION = JComponent.WHEN_IN_FOCUSED_WINDOW;
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("W"), "move up");
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("A"), "move left");
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("S"), "move down");
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("D"), "move right");

    gameView.getActionMap().put("move up", new MoveAction(Snake.SnakeDirection.UP));
    gameView.getActionMap().put("move left", new MoveAction(Snake.SnakeDirection.LEFT));
    gameView.getActionMap().put("move down", new MoveAction(Snake.SnakeDirection.DOWN));
    gameView.getActionMap().put("move right", new MoveAction(Snake.SnakeDirection.RIGHT));

    timer = new Timer(100, new ActionListener() 
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        timerTick();
      }
    });

    gameState = GameState.Paused;
    score = 0;

    window.addStartActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if(gameState == GameState.Paused)
        {
          gameState = GameState.Running;
          timer.start();
        }
      }
    });

    window.addPauseActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if(gameState == GameState.Running)
        {
          gameState = GameState.Paused;
          timer.stop();
        }
      }
    });

    selectApplesPosition();
    updateSnakeViewPosition();
  }

  private void selectApplesPosition()
  {
    do
    {
      appleModel.selectGridPosition(gameView.getGridSize());
    } while(appleModel.getPosition().equals(snakeModel.getHeadPosition()));
    appleView.setPosition(appleModel.getPosition());
  }

  private void updateSnakeViewPosition()
  {
    snakeView.setPositions(snakeModel.getBodySegments());
  }

  private void timerTick()
  {
    snakeModel.update();
    checkIfAppleWasEaten();
    if(snakeModel.checkForSelfCollision())
    {
      gameState = GameState.PlayerLosed;
      timer.stop();
      return;
    }
    updateSnakeViewPosition();
    gameView.repaint();
  }

  private void checkIfAppleWasEaten()
  {
    if(appleModel.getPosition().equals(snakeModel.getHeadPosition()))
    {
      score += 1;
      scoreView.setScore(score);
      snakeModel.grow();
      selectApplesPosition();
    }
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
      if(gameState == GameState.Running)
        snakeModel.setDirection(direction);
    }
  }
}