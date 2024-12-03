package lab.iurtaev.Main;
import lab.iurtaev.generics.classes.*;
import lab.iurtaev.generics.methods.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Задача 1.1");
            System.out.println("2. Задача 1.2");
            System.out.println("3. Задача 2");
            System.out.println("4. Задача 3.1");
            System.out.println("5. Задача 3.2");
            System.out.println("6. Задача 3.3");
            System.out.println("7. Задача 3.4");
            System.out.println("8. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> Box.run1();
                case 2 -> Storage.run();
                case 3 -> Box.run2();
                case 4 -> Methods.run1();
                case 5 -> Methods.run2();
                case 6 -> Methods.run3();
                case 7 -> Methods.run4();
                case 8 -> {
                    exit = true;
                    System.out.println("Выход из программы.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}