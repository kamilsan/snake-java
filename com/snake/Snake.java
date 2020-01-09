package com.snake;

import java.awt.*;
import java.util.ArrayList;

public class Snake
{
    public enum SnakeDirection { UP, RIGHT, DOWN, LEFT };
    private final ArrayList<Point> bodySegments = new ArrayList<>();
    private final Point headLocation;
    private SnakeDirection direction;

    public Snake() {
        headLocation = new Point(0, 0);
        direction = SnakeDirection.UP;
    }

    public void update() {
        for (int i = bodySegments.size(); i > 0; ++i) {
            bodySegments.set(i, bodySegments.get(i - 1));
        }
        bodySegments.set(0, headLocation);
        switch (direction) {
        case UP:
            headLocation.y += 1;
            break;
        case RIGHT:
            headLocation.x += 1;
            break;
        case DOWN:
            headLocation.y -= 1;
            break;
        case LEFT:
            headLocation.x -= 1;
            break;
        }
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(final SnakeDirection direction)
    {
        this.direction = direction;
    }

    public boolean checkForSelfCollision()
    {
        return bodySegments.stream().anyMatch((point) -> point.equals(headLocation));
    }
}
