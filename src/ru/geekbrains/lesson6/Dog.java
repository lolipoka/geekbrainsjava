package ru.geekbrains.lesson6;

public class Dog extends Animal {
    private static int count = 0;

    public Dog(String name) {
        super(name, 500, 0.5f, 0.2f);
        count++;
    }

    /**
     * @return Quantity of instantiated objects.
     */
    public static int getCount() {
        return count;
    }
}
