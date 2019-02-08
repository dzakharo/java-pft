package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistancePositiveValues(){
    Point p1 =new Point(1,1);
    Point p2 =new Point (1,0);
    Assert.assertEquals(p1.distance(p2),1.0);
  }

  @Test
  public void testDistanceNegativeValues(){
    Point p1 =new Point(-1,-2);
    Point p2 =new Point (2,2);
    Assert.assertEquals(p1.distance(p2),5.0);
  }
}
