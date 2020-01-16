package com.snake;

import java.awt.event.*;
import java.awt.Point;
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
  private TextView scoreView;
  private TextView messageView;

  private boolean inputHandled;
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

    scoreView = new TextView("Scores: 0", new Point(15, 15));
    messageView = new TextView("Click the button below to start playing!", 
      new Point(gameView.getWidth()/2, gameView.getHeight() - 15));

    scoreView.setAnchor(TextView.AnchorType.Left);

    gameView.addView(scoreView);
    gameView.addView(messageView);

    initializeInputHandling();
    addButtonActionListeners(window);

    timer = new Timer(100, new ActionListener() 
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        timerTick();
      }
    });

    gameState = GameState.Paused;
    inputHandled = true;
    score = 0;

    selectApplesPosition();
    updateSnakeViewPosition();
  }

  private void initializeInputHandling()
  {
    final int CONDITION = JComponent.WHEN_IN_FOCUSED_WINDOW;
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("W"), "move up");
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("A"), "move left");
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("S"), "move down");
    gameView.getInputMap(CONDITION).put(KeyStroke.getKeyStroke("D"), "move right");

    gameView.getActionMap().put("move up", new MoveAction(Snake.SnakeDirection.UP));
    gameView.getActionMap().put("move left", new MoveAction(Snake.SnakeDirection.LEFT));
    gameView.getActionMap().put("move down", new MoveAction(Snake.SnakeDirection.DOWN));
    gameView.getActionMap().put("move right", new MoveAction(Snake.SnakeDirection.RIGHT));
  }

  private void addButtonActionListeners(Window window)
  {
    window.addStartActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if(gameState == GameState.Paused)
        {
          messageView.setVisibility(false);
          gameState = GameState.Running;
          timer.start();
        }
        else if(gameState == GameState.PlayerLosed)
        {
          messageView.setVisibility(false);
          restartGame();
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
          messageView.setMessage("Game Paused.");
          messageView.setVisibility(true);
          gameView.repaint();
          gameState = GameState.Paused;
          timer.stop();
        }
      }
    });
  }

  private void restartGame()
  {
    snakeModel.reset();
    score = 0;
    scoreView.setMessage("Score: " + Integer.toString(score));
    selectApplesPosition();
    gameState = GameState.Running;
    timer.start();
  }

  private void selectApplesPosition()
  {
    do
    {
      appleModel.selectGridPosition(gameView.getGridSize());
    } while(!isApplePositionIsValid());
    appleView.setPosition(appleModel.getPosition());
  }

  private boolean isApplePositionIsValid()
  {
    var snakeSegments = snakeModel.getBodySegments();
    for(var segmentPosition: snakeSegments)
    {
      if(segmentPosition.equals(appleModel.getPosition()))
        return false;
    }
    return true;
  }

  private void updateSnakeViewPosition()
  {
    snakeView.setPositions(snakeModel.getBodySegments());
  }

  private void timerTick()
  {
    snakeModel.update();
    inputHandled = true;
    checkIfAppleWasEaten();
    if(snakeModel.isSelfColliding())
    {
      messageView.setMessage("You Losed! Click the button below to play again.");
      messageView.setVisibility(true);
      gameState = GameState.PlayerLosed;
      timer.stop();
    }
    updateSnakeViewPosition();
    gameView.repaint();
  }

  private void checkIfAppleWasEaten()
  {
    if(appleModel.getPosition().equals(snakeModel.getHeadPosition()))
    {
      score += 1;
      scoreView.setMessage("Score: " + Integer.toString(score));
      snakeModel.grow();
      selectApplesPosition();
    }
  }

  private class MoveAction extends AbstractAction
  {
    private Snake.SnakeDirection direction;

    MoveAction(Snake.SnakeDirection direction)
    {
      this.direction = direction;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      if(inputHandled && gameState == GameState.Running)
      {
        snakeModel.setDirection(direction);
        inputHandled = false;
      }
    }
  }
}