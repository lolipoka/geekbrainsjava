package ru.geekbrains.lesson6;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private static final int TIMES_CAN_SWIM_LESS_THAN_RUN = 50;
    private static final int HEIGHT_CALCULATION_MULTIPLIER = 100;

    protected String name;
    protected int maxObstacleLengthInMeters;
    protected float maxObstacleHeightInMeters;

    /**
     * Class constructor.
     *
     * @param name                      Animal's name.
     * @param maxObstacleLengthInMeters Maximum length (in meters) of the obstacle that the animal can overcome.
     * @param maxObstacleHeightInMeters Maximum height (in meters) of the obstacle that the animal can overcome.
     * @param variation                 Coefficient of variation (in fractions of 1) of animal's characteristics.
     */
    public Animal(String name, int maxObstacleLengthInMeters, float maxObstacleHeightInMeters, float variation) {
        this.name = name;
        variateMaxObstacleLength(maxObstacleLengthInMeters, variation);
        variateMaxObstacleHeight(maxObstacleHeightInMeters, variation);
    }

    /**
     * Sets this.maxObstacleHeightInMeters based within given maxObstacleHeightInMeters * ± variation.
     *
     * @param maxObstacleHeightInMeters Basis for calculation of this.maxObstacleHeightInMeters.
     * @param variation                 Coefficient of variation (in fractions of 1).
     */
    private void variateMaxObstacleHeight(float maxObstacleHeightInMeters, float variation) {
        int minHeightBound = (int) (maxObstacleHeightInMeters * (1 - variation) * HEIGHT_CALCULATION_MULTIPLIER);
        int maxHeightBound = (int) (maxObstacleHeightInMeters * (1 + variation) * HEIGHT_CALCULATION_MULTIPLIER);
        this.maxObstacleHeightInMeters = (float) (ThreadLocalRandom.current().nextInt(minHeightBound, maxHeightBound)) / HEIGHT_CALCULATION_MULTIPLIER;
    }

    /**
     * Sets this.maxObstacleLengthInMeters based within given maxObstacleLengthInMeters * ± variation.
     *
     * @param maxObstacleLengthInMeters Basis for calculation of this.maxObstacleLengthInMeters.
     * @param variation                 Coefficient of variation (in fractions of 1).
     */
    private void variateMaxObstacleLength(int maxObstacleLengthInMeters, float variation) {
        int minLengthBound = (int) (maxObstacleLengthInMeters * (1 - variation));
        int maxLengthBound = (int) (maxObstacleLengthInMeters * (1 + variation));
        this.maxObstacleLengthInMeters = ThreadLocalRandom.current().nextInt(minLengthBound, maxLengthBound);
    }

    /**
     * Prints result of check whether the animal can run the given distance.
     *
     * @param obstacleLengthInMeters Length (in meters) of obstacle.
     */
    public void run(int obstacleLengthInMeters) {

        boolean checkResult = (obstacleLengthInMeters <= maxObstacleLengthInMeters);
        String formatString;

        if (checkResult) {
            formatString = "run %d meters: %b - %s can run up to %d meters.\n";
        } else {
            formatString = "run %d meters: %b - %s can run no more than %d meters.\n";
        }

        System.out.printf(formatString, obstacleLengthInMeters, checkResult, name, maxObstacleLengthInMeters);
    }

    /**
     * Prints result of check whether the animal can swim the given distance.
     *
     * @param obstacleLengthInMeters Length (in meters) of obstacle.
     */
    public void swim(int obstacleLengthInMeters) {

        int maxSwimDistance = (maxObstacleLengthInMeters / TIMES_CAN_SWIM_LESS_THAN_RUN);
        boolean checkResult = (obstacleLengthInMeters <= maxSwimDistance);
        String formatString;

        if (checkResult) {
            formatString = "swim %d meters: %b - %s can swim up to %d meters.\n";
        } else {
            formatString = "swim %d meters: %b - %s can swim no more than %d meters.\n";
        }

        System.out.printf(formatString, obstacleLengthInMeters, checkResult, name, maxSwimDistance);
    }

    /**
     * Prints result of check whether the animal can jump over the obstacle with given height.
     *
     * @param obstacleHeightInMeters Height (in meters) of obstacle.
     */
    public void jump(float obstacleHeightInMeters) {

        boolean checkResult = (obstacleHeightInMeters <= maxObstacleHeightInMeters);
        String formatString;

        if (checkResult) {
            formatString = "jump %.2f meters: %b - %s can jump up up to %.2f meters.\n";
        } else {
            formatString = "jump %.2f meters: %b - %s can jump up no higher than %.2f meters.\n";
        }

        System.out.printf(formatString, obstacleHeightInMeters, checkResult, name, maxObstacleHeightInMeters);
    }
}
