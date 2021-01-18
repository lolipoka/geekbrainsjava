package ru.geekbrains.lesson7;

public class Cat {
    private final String name;
    private int appetite;
    private boolean isFed;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        isFed = false;
    }

    public void eat(Plate p) {
        if (p.getFood() >= appetite) {
            p.decreaseFood(appetite);
            isFed = true;
        }
    }

    public void showFeedStatus() {
        System.out.printf("%s is %sfed.\n", name, isFed ? "" : "not ");
    }
}
