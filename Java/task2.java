import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // Функция для проверки числа на простоту
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        final int n = 10; // Размер массивов
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        // Генерация случайных чисел
        Random rand = new Random();

        // Инициализация массивов случайными числами
        for (int i = 0; i < n; ++i) {
            arr1[i] = rand.nextInt(91) + 10; // [10, 100]
            arr2[i] = rand.nextInt(91) + 10; // [10, 100]
        }

        // Вывод простых чисел из массивов
        System.out.println("Простые числа из диапазона [0, 10] в массиве arr1:");
        for (int i = 0; i < n; ++i) {
            if (isPrime(arr1[i]) && arr1[i] >= 0 && arr1[i] <= 10) {
                System.out.print(arr1[i] + " ");
            }
        }
        System.out.println();

        // Сортировка половин массивов
        Arrays.sort(arr1, 0, n / 2); // Первая половина arr1 по возрастанию
        Arrays.sort(arr2, n / 2, n, Collections.reverseOrder()); // Вторая половина arr2 по убыванию

        // Обмен отсортированных и несортированных половин массивов
        for (int i = 0; i < n / 2; ++i) {
            int temp = arr1[i];
            arr1[i] = arr2[n / 2 + i];
            arr2[n / 2 + i] = temp;
        }

        // Создание и инициализация массива случайными символами
        char[] charArray = new char[n];
        for (int i = 0; i < n; ++i) {
            charArray[i] = (char) (rand.nextInt(58) + 'A'); // [A, z]
        }

        // Замена заглавных и строчных букв в массиве
        for (int i = 0; i < n; ++i) {
            if (Character.isLowerCase(charArray[i])) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            } else if (Character.isUpperCase(charArray[i])) {
                charArray[i] = Character.toLowerCase(charArray[i]);
            }
        }

        // Определение и инициализация массива случайными целыми числами в диапазоне [1000, 2000]
        int[] intArray = new int[n];
        for (int i = 0; i < n; ++i) {
            intArray[i] = rand.nextInt(1001) + 1000; // [1000, 2000]
        }

        // Сортировка массива целых чисел по убыванию
        Arrays.sort(intArray);
        intArray = Arrays.stream(intArray).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        // Создание нового массива с повторяющимися цифрами
        List<Integer> newArray = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int num = intArray[i];
            boolean hasRepeatedDigit = false;
            while (num > 0) {
                int digit = num % 10;
                num /= 10;
                if (Arrays.stream(intArray).filter(x -> x % 10 == digit).count() > 1) {
                    hasRepeatedDigit = true;
                    break;
                }
            }
            if (hasRepeatedDigit) {
                newArray.add(intArray[i]);
            }
        }

        // Вывод результатов
        System.out.println("Массив charArray после замены регистра:");
        for (int i = 0; i < n; ++i) {
            System.out.print(charArray[i] + " ");
        }
        System.out.println();

        System.out.println("Новый массив с повторяющимися цифрами:");
        for (int i = 0; i < newArray.size(); ++i) {
            System.out.print(newArray.get(i) + " ");
        }
        System.out.println();
    }
}
