package com.snake;

import java.awt.Color;
import java.awt.Graphics;

public class ScoreView implements Drawable
{
  private int score;

  ScoreView()
  {
    score = 0;
  }

  public void setScore(int newScore)
  {
    score = newScore;
  }

  public int getScore()
  {
    return score;
  }

  @Override
  public void draw(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.drawString("Score: " + Integer.toString(score), 10, 20);
  }
}