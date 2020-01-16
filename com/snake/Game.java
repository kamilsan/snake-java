package com.snake;

/**
 * Main class that initializes the game
 */
public class Game
{
  public static void main(final String[] args)
  {
    final int VIEW_SIZE = 500;
    final int GRID_SIZE = 50;
    final int CELL_SIZE = VIEW_SIZE / GRID_SIZE;
    
    Window gameWindow = new Window(VIEW_SIZE, GRID_SIZE);

    Snake snakeModel = new Snake(GRID_SIZE);
    SnakeView snakeView = new SnakeView(CELL_SIZE);

    Apple appleModel = new Apple();
    AppleView appleView = new AppleView(CELL_SIZE);

    GameView gameView = gameWindow.getGameView();
    gameView.addView(snakeView);
    gameView.addView(appleView);

    new Controller(gameWindow, snakeModel, snakeView, appleModel, appleView);
  }
}
