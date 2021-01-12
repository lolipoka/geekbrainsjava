package ru.geekbrains.lesson6;

public class AnimalsExample {
    public static void main(String[] args) {

        Cat poppy = new Cat("Poppy");
        poppy.run(500);
        poppy.swim(100);
        poppy.jump(1.5f);
        System.out.println();

        Cat willy = new Cat("Willy");
        willy.run(150);
        willy.swim(50);
        willy.jump(1.8f);
        System.out.println();

        Dog oscar = new Dog("Oscar");
        oscar.run(400);
        oscar.swim(5);
        oscar.jump(1f);
        System.out.println();

        System.out.printf("Number of cats: %d\n", Cat.getCount());
        System.out.printf("Number of dogs: %d\n", Dog.getCount());
    }
}
