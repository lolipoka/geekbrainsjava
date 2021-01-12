package ru.geekbrains.lesson6;

public class Cat extends Animal {
    private static int count = 0;

    public Cat(String name) {
        super(name, 200, 2, 0.2f);
        count++;
    }

    @Override
    public void swim(int obstacleLengthInMeters) {
        System.out.printf("swim %1d meters: false - Cats can't swim (well, mostly).\n", obstacleLengthInMeters);
    }

    /**
     * @return Quantity of instantiated objects.
     */
    public static int getCount() {
        return count;
    }
}
