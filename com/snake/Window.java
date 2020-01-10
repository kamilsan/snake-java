package com.snake;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
  private SnakeView snakeView;

  public Window()
  {
    super();
    setTitle("Snake");
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    snakeView = new SnakeView();
    final JPanel buttons = new JPanel();
    final JButton startBtn = new JButton();
    final JButton pauseBtn = new JButton();

    snakeView.setPreferredSize(new Dimension(500, 500));
    snakeView.setBackground(Color.BLACK);

    startBtn.setText("Start Game!");
    pauseBtn.setText("Pause Game!");

    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.add(startBtn);
    buttons.add(Box.createRigidArea(new Dimension(5, 0)));
    buttons.add(pauseBtn);

    add(snakeView);
    add(Box.createVerticalStrut(10));
    add(buttons);
    add(Box.createVerticalStrut(10));

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setFocusable(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public SnakeView getSnakeView()
  {
    return snakeView;
  }
}
