import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;


public class Main {
    public static void main(String[] args) {
        int[] numbers = {84, 6, 40, 33, 60, 52, 24, 32, 13, 5, 90, 85, 59, 86, 46, 22, 42, 45, 37, 44};
        shellSort(numbers);

        for (int i : numbers) {
            System.out.print(i + " ");
        }
    }

    public static void shellSort(int[] numbers) {
        List<Integer> steps = new ArrayList<>();
        int counter = 0;
        while (pow(2, counter) < numbers.length) {
            steps.add((int) pow(2, counter));
            counter++;
        }

        boolean isSorted = false;
        for (int i = steps.size() - 1; i >= 0; i--) {

            if (i == 1) {
                while (!isSorted) {
                    isSorted = true;
                    for (int j = 0; j < numbers.length - 1; j++) {
                        if (numbers[j] > numbers[j + 1]) {
                            changePosition(numbers, j, j + 1);
                            isSorted = false;
                        }
                    }
                }
                break;
            }

            for (int j = 0; j < numbers.length - steps.get(i); j++) {
                if (numbers[j] > numbers[j + steps.get(i)]) {
                    changePosition(numbers, j, j + steps.get(i));
                }
            }
        }
    }

    public static void changePosition(int[] n, int id1, int id2) {
        int holder = n[id1];
        n[id1] = n[id2];
        n[id2] = holder;
    }
}