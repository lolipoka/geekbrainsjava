package ru.geekbrains.lesson1;

public class HomeWork {
    public static void main(String[] args) {
        boolean bl = true;
        char c = 'a';

        byte bt = 120;
        short sh = -312;
        int i = 3;
        long l = 30;

        float f = 1.54f;
        double d = 1.54;

        String name = "Василий";

        int result = computeExpression(i, i, i, i);
        System.out.println(result);

        boolean isInBounds = checkSum(i, i);
        System.out.println(isInBounds);

        checkInteger(sh);

        boolean isNegative = isNegative(sh);
        System.out.println(isNegative);

        printHello(name);
        checkLeapYear(2020);
    }

    static int computeExpression(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    static boolean checkSum(int a, int b) {
        int sum = (a + b);

        boolean isInLowerBound = (sum >= 10);
        boolean isInUpperBound = (sum <= 20);

        return (isInLowerBound && isInUpperBound);
    }

    static void checkInteger(int a) {
        String result = "positive";

        if (a < 0) {
            result = "negative";
        }

        System.out.printf("Integer %1d is %2s.\n", a, result);
    }

    static boolean isNegative(int a) {
        return (a < 0);
    }

    static void printHello(String name) {
        System.out.printf("Привет, %1s!\n", name);
    }

    static void checkLeapYear(int year) {
        boolean isFourth = (year % 4 == 0);
        boolean isCenturyStart = (year % 100 == 0);
        boolean is400th = (year % 400 == 0);

        boolean isLeapYear = (isFourth && !isCenturyStart) || is400th;

        System.out.printf("%1d is a leap year: %2b.\n", year, isLeapYear);
    }
}
