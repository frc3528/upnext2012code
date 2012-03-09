package com.teamupnext.robot.commands;

/**
 *
 * @author Up Next!
 */
public class Coordinates {

    private int m_x = 0;
    private int m_y = 0;

    public Coordinates() {
    }

    public Coordinates(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public void setX(int x) {
        m_x = x;
    }

    public void setY(int y) {
        m_y = y;
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }
}
