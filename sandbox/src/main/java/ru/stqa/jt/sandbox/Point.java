package ru.stqa.jt.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point other) {
    return Math.sqrt((other.x - x)*(other.x - x) + (other.y - y)*(other.y - y));
  }

}
