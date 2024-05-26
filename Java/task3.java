import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    // Функция для заполнения списка случайными целыми числами от 1 до 100
    public static void fillListWithRandom(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < list.size(); i++) {
            list.set(i, random.nextInt(100) + 1);
        }
    }

    // Функция для подсчета количества вхождений элемента в списке
    public static int countOccurrences(List<Integer> list, int element) {
        int count = 0;
        for (int value : list) {
            if (value == element) {
                count++;
            }
        }
        return count;
    }

    // Функция для вычисления среднего значения элементов списка
    public static double mean(List<Integer> list) {
        double sum = 0;
        for (int value : list) {
            sum += value;
        }
        return sum / list.size();
    }

    // Функция для вычисления дисперсии элементов списка
    public static double variance(List<Integer> list) {
        double mean = mean(list);
        double sumSqDiff = 0.0;
        for (int value : list) {
            sumSqDiff += Math.pow(value - mean, 2);
        }
        return sumSqDiff / (list.size() - 1);
    }

    // Функция для вычисления функции нормального распределения (Cumulative Distribution Function - CDF)
    public static double normalCDF(double x) {
        return (1.0 / 2.0) * (1.0 + Math.erf(x / Math.sqrt(2.0)));
    }

    // Функция для проведения теста хи-квадрат на нормальность выборки
    public static double chiSquareTest(List<Integer> list) {
        int size = list.size();
        double chi2 = 0;
        double mean = mean(list);
        double stdDev = Math.sqrt(variance(list));

        for (int i = 1; i <= 100; i++) {
            double expectedFreq = size * (normalCDF((i - mean) / stdDev) - normalCDF((i - 1 - mean) / stdDev));
            double observedFreq = countOccurrences(list, i);
            chi2 += Math.pow(observedFreq - expectedFreq, 2) / expectedFreq;
        }

        return chi2;
    }

    // Функция для вывода результатов теста хи-квадрат
    public static void chiPrint(List<Integer> list, double criticalValue) {
        double chiSquareValue = chiSquareTest(list);
        double meanExpected = mean(list);
        double meanObserved = mean(list);

        System.out.println("Chi-Square P-value: " + chiSquareValue);
        if (chiSquareValue <= criticalValue) {
            System.out.println("Hypothesis of normal distribution is not rejected.");
        } else {
            System.out.println("Hypothesis of normal distribution is rejected.");
        }
        System.out.println("Expected mean: " + meanExpected);
        System.out.println("Observed mean: " + meanObserved);
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> list50 = new ArrayList<>(50);
        List<Integer> list100 = new ArrayList<>(100);
        List<Integer> list1000 = new ArrayList<>(1000);

        for (int i = 0; i < 50; i++) list50.add(0);
        for (int i = 0; i < 100; i++) list100.add(0);
        for (int i = 0; i < 1000; i++) list1000.add(0);

        fillListWithRandom(list50);
        fillListWithRandom(list100);
        fillListWithRandom(list1000);

        double criticalValue = 124.342;
        chiPrint(list50, criticalValue);
        chiPrint(list100, criticalValue);
        chiPrint(list1000, criticalValue);
    }
}
