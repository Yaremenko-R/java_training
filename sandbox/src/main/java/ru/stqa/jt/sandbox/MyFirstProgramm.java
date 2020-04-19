package ru.stqa.jt.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {
    hello("world!");
    hello("user");
    hello("Alexei");

    Square s = new Square(5);
    System.out.printf("Площадь квалрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.printf("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}