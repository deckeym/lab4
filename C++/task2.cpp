#include <iostream>
#include <algorithm>
#include <random>

using namespace std;

// Функция для проверки числа на простоту
bool isPrime(int num) {
    if (num <= 1) return false;
    for (int i = 2; i * i <= num; ++i) {
        if (num % i == 0) return false;
    }
    return true;
}

int main() {
    const int n = 10; // Размер массивов
    int arr1[n], arr2[n];

    // Генерация случайных чисел
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(10, 100);

    // Инициализация массивов случайными числами
    for (int i = 0; i < n; ++i) {
        arr1[i] = dis(gen);
        arr2[i] = dis(gen);
    }

    // Вывод простых чисел из массивов
    cout << "Простые числа из диапазона [0, 10] в массиве arr1:\n";
    for (int i = 0; i < n; ++i) {
        if (isPrime(arr1[i]) && arr1[i] >= 0 && arr1[i] <= 10) {
            cout << arr1[i] << " ";
        }
    }
    cout << endl;

    // Сортировка половин массивов
    sort(arr1, arr1 + n/2); // Первая половина arr1 по возрастанию
    sort(arr2 + n/2, arr2 + n, greater<int>()); // Вторая половина arr2 по убыванию

    // Обмен отсортированных и несортированных половин массивов
    swap_ranges(arr1, arr1 + n/2, arr2 + n/2);

    // Создание и инициализация массива случайными символами
    char charArray[n];
    uniform_int_distribution<> charDis('A', 'z');
    for (int i = 0; i < n; ++i) {
        charArray[i] = charDis(gen);
    }

    // Замена заглавных и строчных букв в массиве
    for (int i = 0; i < n; ++i) {
        if (islower(charArray[i])) {
            charArray[i] = toupper(charArray[i]);
        } else if (isupper(charArray[i])) {
            charArray[i] = tolower(charArray[i]);
        }
    }

    // Определение и инициализация массива случайными целыми числами в диапазоне [1000, 2000]
    int intArray[n];
    uniform_int_distribution<> intDis(1000, 2000);
    for (int i = 0; i < n; ++i) {
        intArray[i] = intDis(gen);
    }

    // Сортировка массива целых чисел по убыванию
    sort(intArray, intArray + n, greater<int>());

    // Создание нового массива с повторяющимися цифрами
    int newArray[n];
    int newArrayIndex = 0;
    for (int i = 0; i < n; ++i) {
        int num = intArray[i];
        bool hasRepeatedDigit = false;
        while (num > 0) {
            int digit = num % 10;
            num /= 10;
            if (count_if(intArray, intArray + n, [digit](int x) { return x % 10 == digit; }) > 1) {
                hasRepeatedDigit = true;
                break;
            }
        }
        if (hasRepeatedDigit) {
            newArray[newArrayIndex++] = intArray[i];
        }
    }

    // Вывод результатов
    cout << "Массив charArray после замены регистра:\n";
    for (int i = 0; i < n; ++i) {
        cout << charArray[i] << " ";
    }
    cout << endl;

    cout << "Новый массив с повторяющимися цифрами:\n";
    for (int i = 0; i < newArrayIndex; ++i) {
        cout << newArray[i] << " ";
    }
    cout << endl;

    return 0;
}
