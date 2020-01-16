package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Game window class
 */
public class Window extends JFrame
{
  /**
   * Main view for the game graphics
   */
  private GameView gameView;

  /**
   * Start button
   */
  private final JButton startBtn;

  /**
   * Pause button
   */
  private final JButton pauseBtn;

  /**
   * Constructs the window with specified window and game grid sizes
   * @param viewSize Size of the window
   * @param gridSize Size of the game grid
   */
  public Window(int viewSize, int gridSize)
  {
    super();
    setTitle("Snake");
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    gameView = new GameView(viewSize, gridSize);
    startBtn = new JButton();
    pauseBtn = new JButton();
    startBtn.setText("Start Game!");
    pauseBtn.setText("Pause Game!");
    
    final JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.add(startBtn);
    buttons.add(Box.createRigidArea(new Dimension(5, 0)));
    buttons.add(pauseBtn);

    add(gameView);
    add(Box.createVerticalStrut(10));
    add(buttons);
    add(Box.createVerticalStrut(10));

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Getter for contained game view
   */
  public GameView getGameView()
  {
    return gameView;
  }

  /**
   * Adds start button action listener
   * @param listener Action listener
   */
  public void addStartActionListener(ActionListener listener)
  {
    startBtn.addActionListener(listener);
  }

  /**
   * Adds pause button action listener
   * @param listener Action listener
   */
  public void addPauseActionListener(ActionListener listener)
  {
    pauseBtn.addActionListener(listener);
  }
}
