package ru.geekbrains.lesson3;

import java.util.Random;
import java.util.Scanner;

public class HomeWork {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        System.out.println("Выберите игру:");
        System.out.println("\t1 - Угадай число.");
        System.out.println("\t2 - Угадай фрукт или овощ.");
        System.out.println("\t3 - Выход из меню.");

        while (true) {
            switch (scanner.nextByte()) {
                case 1:
                    playGuessNumber();
                    return;
                case 2:
                    playGuessFood();
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Нужно выбрать один из вариантов.");
            }
        }
    }

    private static void playGuessNumber() {
        while (true) {
            Random random = new Random();
            int guessNumber = random.nextInt(10);
            int playerNumber;
            int attempts = 0;
            int maxAttempts = 3;

            System.out.println("Угадайте число от 0 до 9:");
            do {
                if (++attempts > maxAttempts) {
                    System.out.println("Вы исчерпали количество попыток.");
                    break;
                }

                playerNumber = scanner.nextInt();
                if (playerNumber > guessNumber) {
                    System.out.println("Введённое число больше загаданного.");
                } else if (playerNumber < guessNumber) {
                    System.out.println("Введённое число меньше загаданного.");
                } else {
                    System.out.printf("Вы угадали! Загаданное число - %1d.\n", guessNumber);
                }
            } while (playerNumber != guessNumber);

            System.out.println("Повторить игру ещё раз? 1 - да / 0 - нет");
            byte playerChoice = scanner.nextByte();
            if (playerChoice == 0) {
                System.out.println("Игра завершена.");
                break;
            }
        }
    }

    private static void playGuessFood() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic",
                "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        int guessWordIndex = random.nextInt(words.length);
        String guessWord = words[guessWordIndex];

        String hintWord = "###############";
        char[] hintWordChars = hintWord.toCharArray();

        System.out.println("Угадайте название фрукта или овоща:");

        while (true){
            String playerWord = scanner.next().toLowerCase();
            if (playerWord.equals(guessWord)) {
                System.out.printf("Вы угадали! Загаданное слово - %1s.\nИгра завершена!\n", guessWord);
                break;
            } else {
                int minWordLength = Math.min(guessWord.length(), playerWord.length());
                for (int i = 0; i < minWordLength; i++) {
                    if (guessWord.charAt(i) == playerWord.charAt(i)) {
                        hintWordChars[i] = guessWord.charAt(i);
                    }
                }
                hintWord = new String(hintWordChars);
                System.out.printf("Не угадали! Подсказка: %1s.\n", hintWord);
            }
        }
    }
}
