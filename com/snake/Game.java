package com.snake;

public class Game
{
  public static void main(final String[] args)
  {
    Window gameWindow = new Window();
    Snake snakeModel = new Snake();
    Controller controller = new Controller(gameWindow, snakeModel);
  }
}
