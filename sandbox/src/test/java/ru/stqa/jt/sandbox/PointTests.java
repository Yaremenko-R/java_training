package ru.stqa.jt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(3, 9);
    Point p2 = new Point(6, 12);
    Assert.assertEquals(p1.distance(p2), 4.242640687119285);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(10, 200);
    Point p2 = new Point(20, 300);
    Assert.assertEquals(p1.distance(p2), 100.4987562112089);
  }

}