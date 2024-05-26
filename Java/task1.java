import java.util.*;
import java.lang.Math;

public class RootFindingMethods {
    
    // Общая функция, которую мы пытаемся решить
    public static double function(double x) {
        return Math.sin(x + 1) - 0.2 * x;
    }

    // Производная функции
    public static double derivative(double x) {
        return Math.cos(x + 1) - 0.2;
    }

    // Метод половинного деления для поиска корня
    public static double halfDivisionMethod(double a, double b, double epsilon) {
        int iteration = 1;
        double midPoint;

        System.out.println("N\ta\t\tb\t\tb-a");
        System.out.println("---------------------------------");

        if (function(a) * function(b) > 0) {
            System.out.println("Интервал выбран неправильно. В данном сегменте нет решения.");
            return Double.NaN;
        }

        while (Math.abs(b - a) > epsilon) {
            midPoint = (b + a) / 2.0;
            System.out.printf("%d\t%.10f\t%.10f\t%.10f%n", iteration, a, b, b - a);
            if (function(a) * function(midPoint) < 0) {
                b = midPoint;
            } else {
                a = midPoint;
            }
            iteration++;
        }
        return midPoint;
    }

    // Комбинированный метод секущих-хорд
    public static double combinedMethod(double x0, double x1, double epsilon) {
        double x_minus1 = x0;
        double x = x1;
        int iteration = 1;

        System.out.println("N\ta\t\tb\t\tb-a");
        System.out.println("---------------------------------");

        while (Math.abs(x - x_minus1) > epsilon) {

            System.out.printf("%d\t%.10f\t%.10f\t%.10f%n", iteration, x_minus1, x, x - x_minus1);

            double f_min1 = function(x_minus1);
            double f_x = function(x);
            double delta_x = x - x_minus1;
            double delta_f = f_x - f_min1;

            double x_plus1 = x - (x - x_minus1) * f_x / delta_f;

            System.out.printf("%d\t%.10f%n", iteration++, x_plus1);

            x_minus1 = x;
            x = x_plus1;
        }

        return x;
    }

    //метод Хорд: a, b - пределы хорды
    public static double chordMethod(double a0, double b0, double epsilon) {
        double x0 = (a0 + b0) / 2; // Начальное приближение
        int k = 0; // Счетчик итераций

        double M = Math.max(Math.abs(derivative(a0)), Math.abs(derivative(b0))); // Верхняя граница
        double m = Math.min(Math.abs(derivative(a0)), Math.abs(derivative(b0))); // Нижняя граница

        System.out.println("Итерация\tx\t\tf(x)");
        System.out.println("-----------------------------------");

        while (true) {
            double xk = x0 - function(x0) * (b0 - a0) / (function(b0) - function(a0)); // Вычисление xk+1
            System.out.printf("%d\t%.10f\t%.10f%n", k, xk, function(xk));

            if (Math.abs(xk - x0) <= epsilon) { // Проверка условия остановки
                return xk;
            }

            // Определение неподвижного конца отрезка
            if (derivative(a0) * derivative(xk) < 0) {
                b0 = xk;
            } else {
                a0 = xk;
            }

            x0 = xk;
            k++;
        }
    }

    public static void main(String[] args) {
        double a = 0.0; // Начало интервала
        double b = 8.0; // Конец интервала
        double epsilon = 0.0001; // Погрешность

        System.out.println("\nКорни, найденные методом половинного деления:");
        double rootHalfDivision = halfDivisionMethod(a, b, epsilon);
        System.out.println("Root: " + rootHalfDivision);

        System.out.println("\nКорни, найденные комбинированным методом секущих – хорд:");
        double rootIteration = combinedMethod(a, b, epsilon);
        System.out.println("Root: " + rootIteration);

        System.out.println("Корни, найденные методом хорд:");
        double rootSecant = chordMethod(a, b, epsilon);
        System.out.println("Root: " + rootSecant);
    }
}
