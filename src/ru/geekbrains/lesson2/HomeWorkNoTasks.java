package ru.geekbrains.lesson2;

public class HomeWorkNoTasks {
    public static void main(String[] args) {
        // ========== Task 1 ==========
        printHeader(1);

        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArrayElements(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 1 : 0;
        }
        printArrayElements(arr);

        // ========== Task 2 ==========
        printHeader(2);

        int[] arr1 = new int[8];

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i * 3;
        }
        printArrayElements(arr1);

        // ========== Task 3 ==========
        printHeader(3);

        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArrayElements(arr2);

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] < 6) {
                arr2[i] = arr2[i] * 2;
            }
        }
        printArrayElements(arr2);

        // ========== Task 4 ==========
        printHeader(4);

        int arraySize = 8;
        int[][] arr3 = new int[arraySize][arraySize];

        for (int i = 0; i < arr3.length; i++) {
            for (int j = 0; j < arr3[i].length; j++) {
                arr3[i][i] = 1;
            }
        }

        for (int[] ints : arr3) {
            printArrayElements(ints);
        }

        // ========== Task 5 ==========
        printHeader(5);

        int[] arr4 = {5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1, 7};
        printArrayElements(arr4);

        int min = arr4[0];
        int max = arr4[0];

        for (int i : arr4) {
            if (max < i) {
                max = i;
            }

            if (min > i) {
                min = i;
            }
        }
        System.out.printf("Min is %1d.\n", min);
        System.out.printf("Max is %1d.\n", max);

        // ========== Task 6 ==========
        printHeader(6);

        int[] arr5 = {5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1, 7};
        showArrayData(arr5);

        int[] arr6 = {5, 3, 2, 4, 4, 5, 2, 4, 11, 9, 2, 7};
        showArrayData(arr6);

        // ========== Task 7 ==========
        printHeader(7);

        int[] arr7 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Initial array:");
        printArrayElements(arr7);

        int shiftValue = -3;
        System.out.printf("Shift by %1d:\n", shiftValue);
        shiftArrayElements(arr7, shiftValue);
        printArrayElements(arr7);

        shiftValue = 5;
        System.out.printf("Shift of previous result by %1d:\n", shiftValue);
        shiftArrayElements(arr7, shiftValue);
        printArrayElements(arr7);
    }

    private static void printHeader(int taskNumber) {
        System.out.printf("\n========== Task %1d ==========\n", taskNumber);
    }

    private static void printArrayElements(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    private static void showArrayData(int[] arr) {
        boolean isBalanced;
        int balanceIndex;

        isBalanced = checkBalance(arr);
        if (isBalanced) {
            balanceIndex = getBalanceIndex(arr);
            printBalancedArrayElements(arr, balanceIndex);
        } else {
            printArrayElements(arr);
        }

        System.out.printf("Balanced: %1b.\n", checkBalance(arr));
    }

    private static boolean checkBalance(int[] arr) {
        boolean isBalanced = false;

        double totalSum = intArraySum(arr); // there can be fractional value after division
        int currentSum = 0;

        for (int i : arr) {
            currentSum += i;
            if (currentSum == (totalSum / 2)) {
                isBalanced = true;
                break;
            }
        }
        return isBalanced;
    }

    private static int getBalanceIndex(int[] arr) {
        int totalSum = intArraySum(arr);
        int currentSum = 0;
        int balanceIndex = 99;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (currentSum == (totalSum / 2)) {
                balanceIndex = i;
                break;
            }
        }
        return balanceIndex;
    }

    private static void printBalancedArrayElements(int[] arr, int balanceIndex) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
            if (i == balanceIndex) {
                System.out.print("||\t");
            }
        }
        System.out.println();
    }

    private static int intArraySum(int[] arr) {
        int arrElementsSum = 0;

        for (int i : arr) {
            arrElementsSum += i;
        }

        return arrElementsSum;
    }

    private static void shiftArrayElements(int[] arr, int n) {
        /* Суть в следующем: вместо того, чтобы запоминать значения разных элементов,
           нужно запомнить только одно значение, обойти все, смещая на 1 (всегда вправо)
           в количестве раз, равном новому рассчитанному индексу.
           Для рассчета нового индекса, если он находится за пределами массива, нужно использовать
           деление по модулю, т.к. значение смещения может превышать длину массива.
           Первый цикл нужен для обхода итераций от 1 (первое смещение) до нового индекса.
           Второй цикл нужен для смещения значений элементов на 1 вправо.
           При этом присвоение новых значений производится в обратном порядке - от верхней границы к нижней,
           в противном случае невозможно сохранить исходные значения.
           Вначале нужно запомнить значение первого изменяемого элемента (с максимальным индексом).
           При достижении последнего элемента (с индексом 0) нужно присвоить ему запомненное значение.
        */
        int arrLength = arr.length;
        int maxIndex = (arrLength - 1);
        int newIndex;

        boolean isInBounds = ((0 <= n) && (n <= maxIndex));

        if (isInBounds) {
            newIndex = n;
        } else if (n < 0) {
            newIndex = arrLength + (n % arrLength);
        } else {
            newIndex = (n % arrLength);
        }

        for (int i = 1; i <= newIndex; i++) {
            int lastValue = arr[maxIndex];

            for (int j = maxIndex; j > 0; j--) {
                arr[j] = arr[j - 1];
            }

            arr[0] = lastValue;
        }
    }
}
