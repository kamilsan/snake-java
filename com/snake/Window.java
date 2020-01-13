package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame
{
  private SnakeView snakeView;
  private final JButton startBtn;
  private final JButton pauseBtn;

  public Window(int viewSize, int cellSize)
  {
    super();
    setTitle("Snake");
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    snakeView = new SnakeView(viewSize, cellSize);
    startBtn = new JButton();
    pauseBtn = new JButton();
    startBtn.setText("Start Game!");
    pauseBtn.setText("Pause Game!");
    
    final JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.add(startBtn);
    buttons.add(Box.createRigidArea(new Dimension(5, 0)));
    buttons.add(pauseBtn);

    add(snakeView);
    add(Box.createVerticalStrut(10));
    add(buttons);
    add(Box.createVerticalStrut(10));

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public SnakeView getSnakeView()
  {
    return snakeView;
  }

  public void addStartActionListener(ActionListener listener)
  {
    startBtn.addActionListener(listener);
  }

  public void addPauseActionListener(ActionListener listener)
  {
    pauseBtn.addActionListener(listener);
  }
}
