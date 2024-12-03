package lab.iurtaev.generics.classes;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Box<T> {
    private T item; // Объект, хранящийся в коробке

    // Метод для помещения объекта в коробку
    public void put(T item) {
        if (this.item != null) {
            throw new IllegalStateException("Коробка уже содержит объект!");
        }
        this.item = item;
    }

    // Метод для извлечения объекта из коробки
    public T get() {
        T temp = item;
        item = null; // Освобождаем место
        return temp;
    }

    // Проверка, пуста ли коробка
    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public String toString() {
        return isEmpty() ? "Коробка пуста" : "Коробка содержит: " + item;
    }

    public static void run1() {
        Scanner scanner = new Scanner(System.in);
        Box<Object> box = new Box<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Положить объект в коробку");
            System.out.println("2. Извлечь объект из коробки");
            System.out.println("3. Проверить, пуста ли коробка");
            System.out.println("4. Просмотреть содержимое коробки");
            System.out.println("5. Выйти");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите объект (число, строка и т.д.): ");
                    String input = scanner.nextLine();
                    Object object = parseInput(input);
                    try {
                        box.put(object);
                        System.out.println("Объект успешно помещен в коробку.");
                    } catch (IllegalStateException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
                case 2 -> {
                    Object extracted = box.get();
                    if (extracted != null) {
                        System.out.println("Извлеченный объект: " + extracted);
                    } else {
                        System.out.println("Коробка пуста.");
                    }
                }
                case 3 -> System.out.println("Коробка " + (box.isEmpty() ? "пуста." : "не пуста."));
                case 4 -> System.out.println(box);
                case 5 -> {
                    exit = true;
                    System.out.println("Выход из программы.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

        // Метод для преобразования строки в число, если это возможно
        private static Object parseInput(String input) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Если не удалось преобразовать в число, вернуть строку
                return input;
            }
        }

    // Метод для поиска максимального числа в коробках
    public static double findMaxValue(List<Box<? extends Number>> boxes) {
        double max = Double.NEGATIVE_INFINITY;

        for (Box<? extends Number> box : boxes) {
            if (!box.isEmpty()) {
                double value = box.get().doubleValue();
                max = Math.max(max, value);
            }
        }

        return max;
    }

    private static void createBox(Scanner scanner, List<Box<? extends Number>> boxes) {
        scanner.useLocale(Locale.US); // Устанавливаем локаль для работы с дробными числами
        System.out.println("Выберите тип числа:");
        System.out.println("1. Integer");
        System.out.println("2. Double");
        System.out.println("3. Float");
        System.out.print("Ваш выбор: ");

        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Очистка символа новой строки

        System.out.print("Введите значение: ");
        try {
            switch (typeChoice) {
                case 1 -> {
                    int intValue = scanner.nextInt();
                    Box<Integer> intBox = new Box<>();
                    intBox.put(intValue);
                    boxes.add(intBox);
                    System.out.println("Коробка с Integer создана: " + intValue);
                }
                case 2 -> {
                    double doubleValue = scanner.nextDouble();
                    Box<Double> doubleBox = new Box<>();
                    doubleBox.put(doubleValue);
                    boxes.add(doubleBox);
                    System.out.println("Коробка с Double создана: " + doubleValue);
                }
                case 3 -> {
                    float floatValue = scanner.nextFloat();
                    Box<Float> floatBox = new Box<>();
                    floatBox.put(floatValue);
                    boxes.add(floatBox);
                    System.out.println("Коробка с Float создана: " + floatValue);
                }
                default -> System.out.println("Неверный выбор типа. Коробка не создана.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода. Коробка не создана.");
            scanner.nextLine(); // Пропускаем некорректный ввод
        }
    }

        public static void run2() {
                Scanner scanner = new Scanner(System.in);
                List<Box<? extends Number>> boxes = new ArrayList<>();
                boolean exit = false;

                while (!exit) {
                    System.out.println("\n--- Меню ---");
                    System.out.println("1. Создать новую коробку и добавить число");
                    System.out.println("2. Просмотреть содержимое всех коробок");
                    System.out.println("3. Найти максимальное значение среди всех коробок");
                    System.out.println("4. Выйти");
                    System.out.print("Выберите опцию: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Очистка символа новой строки

                    switch (choice) {
                        case 1 -> createBox(scanner, boxes);
                        case 2 -> {
                            if (boxes.isEmpty()) {
                                System.out.println("Список коробок пуст.");
                            } else {
                                System.out.println("Содержимое коробок:");
                                for (int i = 0; i < boxes.size(); i++) {
                                    System.out.println("Коробка " + (i + 1) + ": " + boxes.get(i));
                                }
                            }
                        }
                        case 3 -> {
                            if (boxes.isEmpty()) {
                                System.out.println("Список коробок пуст. Добавьте коробки перед поиском максимума.");
                            } else {
                                double max = findMaxValue(boxes);
                                System.out.println("Максимальное значение среди всех коробок: " + max);
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
}
