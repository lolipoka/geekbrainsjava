package ru.geekbrains.lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void addFood(int amount) {
        food += amount;
    }

    public void decreaseFood(int n) {
        if (n <= food) {
            food -= n;
        } else {
            food = 0;
        }
    }
    public void info() {
        System.out.println("plate: " + food);
    }
}
