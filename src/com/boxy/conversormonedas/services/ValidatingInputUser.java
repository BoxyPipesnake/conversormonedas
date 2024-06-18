package com.boxy.conversormonedas.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidatingInputUser {

    public int getValidMenuChoice(Scanner scanner, int maxChoice) {
        int choice = -1;
        while (choice < 1 || choice > maxChoice) {
            try {
                System.out.print("Ingrese su elección: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > maxChoice) {
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }

    public double getValidAmount(Scanner scanner) {
        double amount = -1;
        while (amount <= 0) {
            try {
                System.out.print("Ingrese el monto: ");
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("El monto debe ser positivo. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número válido.");
                scanner.next(); // Clear the invalid input
            }
        }
        return amount;
    }
}
