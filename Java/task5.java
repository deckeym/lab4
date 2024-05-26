import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void generation(ArrayList<Integer> mod, int X0, int A, int B, int C, int[] repeat) {
        int Xi;
        boolean firstAppear = false;

        for (int i = 0; i < C; i++) {
            Xi = (A * mod.get(i) + B) % C;
            mod.add(Xi);
            if (X0 == Xi && mod.size() > 1 && !firstAppear) {
                repeat[0] = i + 2; // +2 потому что в индекс с нуля и прибавляется знач только на след итерации
                firstAppear = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> mod = new ArrayList<>();
        int[] repeat = new int[1]; // Используем массив для передачи по ссылке
        int X0, A, B, C;

        System.out.println("Enter x0, A, B, C");
        X0 = scanner.nextInt();
        A = scanner.nextInt();
        B = scanner.nextInt();
        C = scanner.nextInt();

        if (!(0 <= A && A <= C) || !(0 <= B && B <= C) || !(0 <= X0 && X0 < C)) {
            System.out.println("error");
            scanner.close();
            return;
        }
        
        mod.add(X0);
        generation(mod, X0, A, B, C, repeat);

        for (int n : mod) {
            System.out.print(n + " ");
        }

        System.out.println();
        System.out.println(repeat[0]);

        scanner.close();
    }
}
