package com.snake;

public class Game
{
  public static void main(final String[] args)
  {
    final int VIEW_SIZE = 500;
    final int GRID_SIZE = 50;
    final int CELL_SIZE = VIEW_SIZE / GRID_SIZE;
    
    Window gameWindow = new Window(VIEW_SIZE, CELL_SIZE);
    Snake snakeModel = new Snake(GRID_SIZE);
    Controller controller = new Controller(snakeModel, gameWindow.getSnakeView());
  }
}
