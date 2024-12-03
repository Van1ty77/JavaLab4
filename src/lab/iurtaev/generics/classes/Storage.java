package lab.iurtaev.generics.classes;

import java.util.Scanner;

public class Storage<T> {
    private final T value; // Значение в хранилище

    // Конструктор
    public Storage(T value) {
        this.value = value; // Хранит null или объект
    }

    // Метод для получения значения с альтернативой
    public T getOrElse(T alternative) {
        // Если значение в хранилище null, то вернуть альтернативное значение
        if (value == null) {
            return alternative;
        }
        return value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "Хранилище содержит: null";  // Обрабатываем null в хранилище для вывода
        }
        return "Хранилище содержит: " + value;
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Storage<?> storage = null; // Хранилище с параметризацией
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Создать хранилище");
            System.out.println("2. Извлечь значение с альтернативным значением");
            System.out.println("3. Просмотреть содержимое хранилища");
            System.out.println("4. Выйти");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите тип данных для хранилища (1 - Integer, 2 - String): ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // Очистка символа новой строки

                    System.out.print("Введите значение для хранилища (или 'null'): ");
                    String input = scanner.nextLine();

                    boolean validInput = false;
                    Object value = null;

                    // Валидация ввода для каждого типа
                    while (!validInput) {
                        if (typeChoice == 1) {
                            value = parseInput(input, Integer.class);
                            if (value != null || input.equalsIgnoreCase("null")) {
                                validInput = true;
                            } else {
                                System.out.println("Ошибка ввода. Пожалуйста, введите корректное число.");
                                input = scanner.nextLine();
                            }
                        } else if (typeChoice == 2) {
                            value = parseInput(input, String.class);
                            if (value != null || input.equalsIgnoreCase("null")) {
                                validInput = true;
                            } else {
                                System.out.println("Ошибка ввода. Пожалуйста, введите корректную строку.");
                                input = scanner.nextLine();
                            }
                        } else {
                            System.out.println("Неверный выбор типа. Попробуйте снова.");
                            break;
                        }
                    }

                    // Создаем хранилище только если ввод был корректен
                    if (validInput) {
                        // Определяем тип хранилища
                        if (typeChoice == 1) {
                            storage = new Storage<>((Integer) value);
                        } else {
                            storage = new Storage<>((String) value);
                        }
                        System.out.println("Хранилище создано.");
                    }
                }
                case 2 -> {
                    if (storage == null) {
                        System.out.println("Хранилище еще не создано. Сначала создайте его.");
                        break;
                    }
                    System.out.print("Введите альтернативное значение: ");
                    String altInput = scanner.nextLine();

                    // Проверка типа данных в хранилище
                    if (storage.value instanceof Integer || storage.value == null) {
                        Integer alternative = (Integer) parseInput(altInput, Integer.class);
                        Integer extracted = ((Storage<Integer>) storage).getOrElse(alternative);
                        System.out.println("Извлеченное значение: " + extracted);
                    } else if (storage.value instanceof String) {
                        String alternative = (String) parseInput(altInput, String.class);
                        String extracted = ((Storage<String>) storage).getOrElse(alternative);
                        System.out.println("Извлеченное значение: " + extracted);
                    } else {
                        System.out.println("Тип значения в хранилище неизвестен.");
                    }
                }
                case 3 -> {
                    if (storage == null) {
                        System.out.println("Хранилище еще не создано.");
                    } else {
                        System.out.println(storage);
                    }
                }
                case 4 -> {
                    exit = true;
                    System.out.println("Выход из программы.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    // Метод для преобразования строки в указанный тип или null, если это указано
    private static Object parseInput(String input, Class<?> type) {
        if (input.equalsIgnoreCase("null")) {
            return null;
        }
        try {
            if (type == Integer.class) {
                return Integer.parseInt(input);
            } else if (type == String.class) {
                return input;
            }
        } catch (NumberFormatException e) {
            // Если формат неправильный, возвращаем null
            return null;
        }
        return null;
    }
}
