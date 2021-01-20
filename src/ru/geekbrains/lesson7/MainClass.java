package ru.geekbrains.lesson7;

public class MainClass {
    public static void main(String... args) {
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Barsik", 5);
        cats[1] = new Cat("Murzik", 10);
        cats[2] = new Cat("Pushok", 8);
        cats[3] = new Cat("Musya", 6);
        cats[4] = new Cat("Sonya", 5);
        Plate plate = new Plate(22);

        plate.info();
        for (Cat cat : cats) {
            cat.eat(plate);
            cat.showFeedStatus();
        }
        plate.info();
    }
}
