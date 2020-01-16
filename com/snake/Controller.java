package com.snake;

import java.awt.event.*;
import java.awt.Point;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

/**
 * Class responisble for realizing Game's logic and communication between models and views.
 */
public class Controller
{
  /**
   * Possible game states
   */
  private enum GameState { Running, Paused, PlayerLosed };

  /**
   * Main view for the game
   */
  private GameView gameView;

  /**
   * Snake's model - stores relevant data
   */
  private Snake snakeModel;

  /**
   * Snake's view - it's graphical representation
   */
  private SnakeView snakeView;

  /**
   * Apple's model - stores it's position
   */
  private Apple appleModel;

  /**
   * Apple's view - it's graphical representation
   */
  private AppleView appleView;

  /**
   * View for displaying user's score
   */
  private TextView scoreView;

  /**
   * View for displaying messages
   */
  private TextView messageView;

  /**
   * Variable signifying if game was updated, since user pressed a key.
   * Used to prevent the user from changing snake's direction twice, between two frames.
   */
  private boolean inputHandled;

  /**
   * Timer responsible for updating the game in regular intervals
   */
  private Timer timer;

  /**
   * State the game is in
   */
  private GameState gameState;

  /**
   * Score counter
   */
  private int score;

  /**
   * Constructs the controller
   * @param window Window that contains the main view for the game
   * @param snakeModel Snake model
   * @param snakeView Snake view
   * @param appleModel Apple model
   * @param appleView Apple view
   */
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

  /**
   * Initializes user's input handling
   */
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

  /**
   * Initializes buttons pressing handling
   * @param window Window that contains the buttons
   */
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
          messageView.setText("Game Paused.");
          messageView.setVisibility(true);
          gameView.repaint();
          gameState = GameState.Paused;
          timer.stop();
        }
      }
    });
  }

  /**
   * Restarts the game the it's inital state
   */
  private void restartGame()
  {
    snakeModel.reset();
    score = 0;
    scoreView.setText("Score: " + Integer.toString(score));
    selectApplesPosition();
    gameState = GameState.Running;
    timer.start();
  }

  /**
   * Selects new apple position after, it was eaten, in a spot that is not occupied by the snake
   */
  private void selectApplesPosition()
  {
    do
    {
      appleModel.selectGridPosition(gameView.getGridSize());
    } while(!isApplePositionIsValid());
    appleView.setPosition(appleModel.getPosition());
  }

  /**
   * Checks if the apple isn't placed on the snake
   * @return Boolean value signifing if apple's position is valid
   */
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

  /**
   * Updates the snake's view with model's data
   */
  private void updateSnakeViewPosition()
  {
    snakeView.setPositions(snakeModel.getBodySegments());
  }

  /**
   * Updates the game
   */
  private void timerTick()
  {
    snakeModel.update();
    inputHandled = true;
    checkIfAppleWasEaten();
    if(snakeModel.isSelfColliding())
    {
      messageView.setText("You Losed! Click the button below to play again.");
      messageView.setVisibility(true);
      gameState = GameState.PlayerLosed;
      timer.stop();
    }
    updateSnakeViewPosition();
    gameView.repaint();
  }

  /**
   * Checks if apple was eaten and handles if it is so
   */
  private void checkIfAppleWasEaten()
  {
    if(appleModel.getPosition().equals(snakeModel.getHeadPosition()))
    {
      score += 1;
      scoreView.setText("Score: " + Integer.toString(score));
      snakeModel.grow();
      selectApplesPosition();
    }
  }

  /**
   * Class representing snake's turning action
   */
  private class MoveAction extends AbstractAction
  {
    /**
     * Snake's new direction of movement
     */
    private Snake.SnakeDirection direction;

    /**
     * Constuctor
     */
    MoveAction(Snake.SnakeDirection direction)
    {
      this.direction = direction;
    }

    /**
     * Action handler - changes snake's movement direction
     */
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