package ru.geekbrains.lesson7;

public class StringsExample {
    public static void main(String[] args) {
        processString("I like Java!!!");
    }

    public static void processString(String text) {
        System.out.printf("Last char of string '%s' is '%c'.\n", text, text.charAt(text.length() - 1));

        String substring = "!!!";
        System.out.printf("String '%s' ends with '%s': %b.\n", text, substring, text.endsWith(substring));

        substring = "I like";
        System.out.printf("String '%s' starts with '%s': %b.\n", text, substring, text.startsWith(substring));

        substring = "Java";
        System.out.printf("String '%s' contains '%s': %b.\n", text, substring, text.contains(substring));

        int substringIndex = text.indexOf(substring);
        System.out.printf("Position of substring '%s' in string '%s': %d.\n", substring, text, substringIndex);

        System.out.printf("Initial string after replacement of 'a' with 'o': %s.\n", text.replace('a', 'o'));

        System.out.printf("Initial string transformed to upper case: %s.\n", text.toUpperCase());

        System.out.printf("Initial string transformed to lower case: %s.\n", text.toLowerCase());

        System.out.printf("Initial string without '%s': %s.\n", substring, text.substring(0, substringIndex).trim() + text.substring(substringIndex + substring.length()));
    }
}
