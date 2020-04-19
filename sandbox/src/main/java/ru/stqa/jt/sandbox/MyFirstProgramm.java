package ru.stqa.jt.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {

    Square s = new Square(5);
    System.out.printf("Площадь квалрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.printf("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(3, 9);
    Point p2 = new Point(6, 12);
    System.out.println("Расстояние между точками " + p1 + " и " + p2 + " = " + p1.distance(p2));
  }

}