package ru.geekbrains.lesson4;

import java.util.Random;
import java.util.Scanner;

public class NoughtsAndCrosses {
    public static char[][] map;

    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    private static int emptyCellCount = SIZE * SIZE;

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();


    public static void main(String[] args) {
        initializeMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isHumanWin()) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            computerTurn();
            printMap();
            if (isComputerWin()) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void initializeMap() {
        map = new char[SIZE][SIZE];

        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                map[rowIndex][columnIndex] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        printHeader();
        printRows();
    }

    private static void printHeader() {
        for (int columnNumber = 0; columnNumber <= SIZE; columnNumber++) {
            System.out.print(columnNumber + " ");
        }
        System.out.println();
    }

    private static void printRows() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            printRowNumber(rowIndex);
            printColumns(rowIndex);
            System.out.println();
        }
        System.out.println();
    }

    private static void printRowNumber(int rowIndex) {
        System.out.print((rowIndex + 1) + " ");
    }

    private static void printColumns(int rowIndex) {
        for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
            System.out.print(map[rowIndex][columnIndex] + " ");
        }
    }

    public static boolean isMapFull() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                if (map[rowIndex][columnIndex] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void humanTurn() {
        int rowIndex;
        int columnIndex;

        do {
            System.out.println("Введите координаты в формате Ряд Столбец");
            rowIndex = sc.nextInt() - 1;
            columnIndex = sc.nextInt() - 1;
        } while (!isCellValid(rowIndex, columnIndex));

        markHumanTurn(rowIndex, columnIndex);
        emptyCellCount--;
    }

    public static boolean isCellValid(int rowIndex, int columnIndex) {
        if (!isIndexValid(rowIndex) || !isIndexValid(columnIndex)) {
            return false;
        }
        return (map[rowIndex][columnIndex] == DOT_EMPTY);
    }

    private static boolean isIndexValid(int index) {
        return (index >= 0) && (index < SIZE);
    }

    public static void computerTurn() {
        int rowIndex = -1;
        int columnIndex = -1;
        int cellCount = 0;

        /* TODO: переделать, т.к. может быть повторное попадание в ту же ячейку.
           Как вариант, можно создавать копию двухмерного массива map,
           а для проверки обходить пустые ячейки и заполнять уже проверенные. */
        while (cellCount <= emptyCellCount) {
            do {
                rowIndex = rand.nextInt(SIZE);
                columnIndex = rand.nextInt(SIZE);
            } while (!isCellValid(rowIndex, columnIndex));

            cellCount++;

            /* Отмечаем ход игрока и проверяем, является ли он победным.
               Если является, устанавливаем в ячейке символ компьютера,
               иначе очищаем ячейку (устанавливаем символ пустой ячейки). */
            markHumanTurn(rowIndex, columnIndex);
            if (isHumanWin()) {
                markComputerTurn(rowIndex, columnIndex);
                emptyCellCount--;
                return;
            } else {
                clearCell(rowIndex, columnIndex);
            }
        }

        markComputerTurn(rowIndex, columnIndex);
        emptyCellCount--;
    }

    private static void markHumanTurn(int rowIndex, int columnIndex) {
        map[rowIndex][columnIndex] = DOT_X;
    }

    private static void markComputerTurn(int rowIndex, int columnIndex) {
        map[rowIndex][columnIndex] = DOT_O;
        System.out.println("Компьютер походил в точку: ряд " + (rowIndex + 1) + " столбец " + (columnIndex + 1));
    }

    private static void clearCell(int rowIndex, int columnIndex) {
        map[rowIndex][columnIndex] = DOT_EMPTY;
    }

    private static boolean isHumanWin() {
        return checkWin(DOT_X);
    }

    private static boolean isComputerWin() {
        return checkWin(DOT_O);
    }

    public static boolean checkWin(char character) {
        return (isRowWin(character)
                || isColumnWin(character)
                || isDiagonalWin(character)
                || isReverseDiagonalWin(character));
    }

    // Методы проверки взял отсюда: https://stackoverflow.com/a/9746758
    private static boolean isRowWin(char character) {
        int charCount = 0;

        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < (SIZE - (DOTS_TO_WIN - 1)); columnIndex++) {
                while (map[rowIndex][columnIndex] == character) {
                    columnIndex++;
                    charCount++;
                    if (charCount == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isColumnWin(char character) {
        int charCount = 0;

        for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
            for (int rowIndex = 0; rowIndex < (SIZE - (DOTS_TO_WIN - 1)); rowIndex++) {
                while (map[rowIndex][columnIndex] == character) {
                    rowIndex++;
                    charCount++;
                    if (charCount == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(char character) {
        int charCount = 0;
        for (int columnIndex = 0; columnIndex < (SIZE - (DOTS_TO_WIN - 1)); columnIndex++) {
            for (int rowIndex = 0; rowIndex < (SIZE - (DOTS_TO_WIN - 1)); rowIndex++) {
                while (map[rowIndex][columnIndex] == character) {
                    rowIndex++;
                    columnIndex++;
                    charCount++;
                    if (charCount == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isReverseDiagonalWin(char character) {
        int charCount = 0;
        for (int columnIndex = (SIZE - 1); columnIndex > (DOTS_TO_WIN - 2); columnIndex--) {
            for (int rowIndex = 0; rowIndex < (SIZE - (DOTS_TO_WIN - 1)); rowIndex++) {
                while (map[rowIndex][columnIndex] == character) {
                    rowIndex++;
                    columnIndex--;
                    charCount++;
                    if (charCount == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
