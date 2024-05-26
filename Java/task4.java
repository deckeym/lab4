import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    // Функция для генерации случайного числа в диапазоне от min до max
    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    // Алгоритм "всегда сотрудничать"
    public static boolean alwaysCooperate(int roundNumber, List<Boolean> selfChoices, List<Boolean> enemyChoices) {
        return true;
    }

    // Стратегия уступки: сотрудничать, если противник сотрудничал в предыдущем раунде, иначе предавать
    public static boolean titForTat(int roundNumber, List<Boolean> selfChoices, List<Boolean> enemyChoices) {
        if (roundNumber == 0) {
            return true;
        }
        return enemyChoices.get(roundNumber - 1);
    }

    // Алгоритм со случайными выборами
    public static boolean randomChoice(int roundNumber, List<Boolean> selfChoices, List<Boolean> enemyChoices) {
        return randomNumber(0, 1) == 1;
    }

    // Функция для сравнения алгоритмов
    public static void compareAlgorithms() {
        int numRounds = randomNumber(100, 200);
        List<Boolean> algorithm1Choices = new ArrayList<>();
        List<Boolean> algorithm2Choices = new ArrayList<>();
        List<Boolean> algorithm3Choices = new ArrayList<>();
        int algorithm1Score = 0, algorithm2Score = 0, algorithm3Score = 0;

        for (int round = 0; round < numRounds; round++) {
            boolean algorithm1Choice = randomChoice(round, algorithm1Choices, algorithm2Choices);
            boolean algorithm2Choice = titForTat(round, algorithm2Choices, algorithm1Choices);
            boolean algorithm3Choice = alwaysCooperate(round, algorithm3Choices, algorithm2Choices);

            algorithm1Choices.add(algorithm1Choice);
            algorithm2Choices.add(algorithm2Choice);
            algorithm3Choices.add(algorithm3Choice);

            // Сравниваем все три алгоритма
            if (algorithm1Choice && algorithm2Choice) {
                algorithm1Score += 4;
                algorithm2Score += 4;
            } else if (algorithm1Choice && !algorithm2Choice) {
                algorithm1Score += 0;
                algorithm2Score += 20;
            } else if (!algorithm1Choice && algorithm2Choice) {
                algorithm1Score += 20;
                algorithm2Score += 0;
            } else {
                algorithm1Score += 24;
                algorithm2Score += 24;
            }

            if (algorithm1Choice && algorithm3Choice) {
                algorithm1Score += 4;
                algorithm3Score += 4;
            } else if (algorithm1Choice && !algorithm3Choice) {
                algorithm1Score += 0;
                algorithm3Score += 20;
            } else if (!algorithm1Choice && algorithm3Choice) {
                algorithm1Score += 20;
                algorithm3Score += 0;
            } else {
                algorithm1Score += 24;
                algorithm3Score += 24;
            }

            if (algorithm3Choice && algorithm2Choice) {
                algorithm3Score += 24;
                algorithm2Score += 24;
            } else if (algorithm3Choice && !algorithm2Choice) {
                algorithm3Score += 0;
                algorithm2Score += 20;
            } else if (!algorithm3Choice && algorithm2Choice) {
                algorithm3Score += 20;
                algorithm2Score += 0;
            } else {
                algorithm3Score += 4;
                algorithm2Score += 4;
            }
        }

        System.out.println("randomChoice: " + algorithm1Score);
        System.out.println("titForTat: " + algorithm2Score);
        System.out.println("alwaysCooperate: " + algorithm3Score);
    }

    public static void main(String[] args) {
        compareAlgorithms();
    }
}
