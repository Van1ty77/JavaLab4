package lab.iurtaev.generics.methods;
import java.util.*;
import java.util.function.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

public class Methods {

    // Обобщенный метод transform
    public static <T, P> List<P> transform(List<T> inputList, Function<T, P> apply) {
        List<P> resultList = new ArrayList<>();
        for (T item : inputList) {
            resultList.add(apply.apply(item));  // Применяем функцию к каждому элементу списка
        }
        return resultList;
    }

    public static void run1() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Преобразовать строки в длины");
            System.out.println("2. Преобразовать числа в положительные");
            System.out.println("3. Найти максимальные значения массивов");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите строки через пробел (например, qwerty asdfg zx): ");
                    String input = scanner.nextLine();
                    List<String> strings = Arrays.asList(input.split("\\s+"));
                    List<Integer> stringLengths = transform(strings, String::length);
                    System.out.println("Длины строк: " + stringLengths);
                }
                case 2 -> {
                    System.out.print("Введите числа через пробел (например, 1 -3 7): ");
                    String input = scanner.nextLine();
                    List<Integer> numbers = new ArrayList<>();
                    try {
                        for (String num : input.split("\\s+")) {
                            numbers.add(Integer.parseInt(num.trim()));
                        }
                        List<Integer> absoluteNumbers = transform(numbers, Math::abs);
                        System.out.println("Положительные числа: " + absoluteNumbers);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите корректные числа.");
                    }
                }
                case 3 -> {
                    System.out.println("Введите массивы целых чисел, разделенных пробелами. Каждый массив отделяйте символом '|'.");
                    System.out.println("Пример: 1 2 3 | 4 5 6 | 7 8 9");
                    String input = scanner.nextLine();
                    List<int[]> arrays = new ArrayList<>();
                    try {
                        for (String arrayString : input.split("\\|")) {
                            String[] elements = arrayString.trim().split("\\s+");
                            int[] array = Arrays.stream(elements)
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                            arrays.add(array);
                        }
                        List<Integer> maxValues = transform(arrays, arr -> Arrays.stream(arr).max().orElse(Integer.MIN_VALUE));
                        System.out.println("Максимальные значения массивов: " + maxValues);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите массивы корректно.");
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

    // Обобщенный метод filter
    public static <T> List<T> filter(List<T> inputList, Predicate<T> test) {
        List<T> filteredList = new ArrayList<>();
        for (T item : inputList) {
            if (test.test(item)) {
                filteredList.add(item); // Добавляем только те элементы, которые прошли условие
            }
        }
        return filteredList;
    }

    public static void run2() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Фильтровать строки длиной не менее 3 символов");
            System.out.println("2. Удалить все положительные числа");
            System.out.println("3. Оставить массивы без положительных элементов");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите строки через пробел (например, qwerty asdfg zx): ");
                    String input = scanner.nextLine();
                    List<String> strings = Arrays.asList(input.split("\\s+"));
                    List<String> filteredStrings = filter(strings, s -> s.length() >= 3);
                    System.out.println("Отфильтрованные строки: " + filteredStrings);
                }
                case 2 -> {
                    System.out.print("Введите числа через пробел (например, 1 -3 7): ");
                    String input = scanner.nextLine();
                    List<Integer> numbers = new ArrayList<>();
                    try {
                        for (String num : input.split("\\s+")) {
                            numbers.add(Integer.parseInt(num.trim()));
                        }
                        List<Integer> filteredNumbers = filter(numbers, n -> n <= 0);
                        System.out.println("Числа без положительных: " + filteredNumbers);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите корректные числа.");
                    }
                }
                case 3 -> {
                    System.out.println("Введите массивы целых чисел, разделенных пробелами. Каждый массив отделяйте символом '|'.");
                    System.out.println("Пример: 1 -2 -3 | 0 5 -6 | -1 -2 -3");
                    String input = scanner.nextLine();
                    List<int[]> arrays = new ArrayList<>();
                    try {
                        for (String arrayString : input.split("\\|")) {
                            String[] elements = arrayString.trim().split("\\s+");
                            int[] array = Arrays.stream(elements)
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                            arrays.add(array);
                        }
                        List<int[]> filteredArrays = filter(arrays, arr -> Arrays.stream(arr).noneMatch(x -> x > 0));
                        System.out.println("Массивы без положительных чисел: ");
                        for (int[] arr : filteredArrays) {
                            System.out.println(Arrays.toString(arr));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите массивы корректно.");
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

    // Обобщенный метод reduce
    public static <T> T reduce(List<T> inputList, T identity, BinaryOperator<T> reducer) {
        T result = identity; // Начальное значение
        for (T item : inputList) {
            result = reducer.apply(result, item);
        }
        return result;
    }

    // Метод для подсчета общего числа элементов во вложенных списках
    public static <T extends List<?>> int countTotalElements(List<T> inputList) {
        return inputList.stream()
                .mapToInt(List::size)
                .sum();
    }

    public static void run3() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Объединить строки в одну строку");
            System.out.println("2. Суммировать все числа");
            System.out.println("3. Подсчитать общее количество элементов в списках");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите строки через пробел (например, qwerty asdfg zx): ");
                    String input = scanner.nextLine();
                    List<String> strings = Arrays.asList(input.split("\\s+"));
                    String result = reduce(strings, "", (s1, s2) -> s1 + s2);
                    System.out.println("Результат объединения строк: " + result);
                }
                case 2 -> {
                    System.out.print("Введите числа через пробел (например, 1 -3 7): ");
                    String input = scanner.nextLine();
                    List<Integer> numbers = new ArrayList<>();
                    try {
                        for (String num : input.split("\\s+")) {
                            numbers.add(Integer.parseInt(num.trim()));
                        }
                        Integer result = reduce(numbers, 0, Integer::sum);
                        System.out.println("Сумма всех чисел: " + result);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите корректные числа.");
                    }
                }
                case 3 -> {
                    System.out.println("Введите списки целых чисел, разделенные пробелами. Каждый список отделяйте символом '|'.");
                    System.out.println("Пример: 1 -2 3 | 4 5 | 6 7 8 9");
                    String input = scanner.nextLine();
                    List<List<Integer>> lists = new ArrayList<>();
                    try {
                        for (String listString : input.split("\\|")) {
                            String[] elements = listString.trim().split("\\s+");
                            List<Integer> list = new ArrayList<>();
                            for (String num : elements) {
                                list.add(Integer.parseInt(num.trim()));
                            }
                            lists.add(list);
                        }
                        // Используем метод countTotalElements.
                        int totalElements = countTotalElements(lists);
                        System.out.println("Общее количество элементов во всех списках: " + totalElements);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите списки корректно.");
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

    // Универсальный метод коллекционирования
    public static <T, P> P collect(
            List<T> inputList,
            Supplier<P> collectionSupplier,
            BiConsumer<T, P> collectorFunction) {
        P result = collectionSupplier.get();
        for (T item : inputList) {
            collectorFunction.accept(item, result);
        }
        return result;
    }

    public static void run4() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Разделить числа на положительные и отрицательные");
            System.out.println("2. Группировать строки по длине");
            System.out.println("3. Удалить дубликаты из списка строк");
            System.out.println("4. Выйти");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите числа через пробел (например, 1 -3 7): ");
                    String input = scanner.nextLine();
                    try {
                        List<Integer> numbers = Arrays.stream(input.split("\\s+"))
                                .map(Integer::parseInt)
                                .toList();

                        // Разделяем на два подсписка
                        Map<Boolean, List<Integer>> result = collect(
                                numbers,
                                HashMap::new,
                                (num, map) -> map.computeIfAbsent(num > 0, k -> new ArrayList<>()).add(num)
                        );

                        System.out.println("Положительные числа: " + result.get(true));
                        System.out.println("Отрицательные числа: " + result.get(false));
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода! Пожалуйста, введите корректные числа.");
                    }
                }
                case 2 -> {
                    System.out.print("Введите строки через пробел (например, qwerty asdfg zx qw): ");
                    String input = scanner.nextLine();
                    List<String> strings = Arrays.asList(input.split("\\s+"));

                    // Группируем строки по длине
                    Map<Integer, List<String>> result = collect(
                            strings,
                            HashMap::new,
                            (str, map) -> map.computeIfAbsent(str.length(), k -> new ArrayList<>()).add(str)
                    );

                    result.forEach((length, group) ->
                            System.out.println("Строки длиной " + length + ": " + group));
                }
                case 3 -> {
                    System.out.print("Введите строки через пробел (например, qwerty asdfg qwerty qw): ");
                    String input = scanner.nextLine();
                    List<String> strings = Arrays.asList(input.split("\\s+"));

                    // Убираем дубликаты
                    Set<String> result = collect(
                            strings,
                            HashSet::new,
                            (element, collection) -> collection.add(element) // Исправлено
                    );

                    System.out.println("Результат: " + result);
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
