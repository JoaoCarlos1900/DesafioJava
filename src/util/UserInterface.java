package util;

import java.util.Scanner;

public class UserInterface {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("Selecione uma opção:");
        System.out.println("1. Converter moedas");
        System.out.println("2. Sair");
    }

    public static String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    public static void displayResult(String result) {
        System.out.println("Resultado da conversão: " + result);
    }

    public static void closeScanner() {
        scanner.close();
    }
}

