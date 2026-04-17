import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;


public class Main {
    public static void main(String[] args) {

        try(BufferedReader read = new BufferedReader(new FileReader("src/tests.txt"))){
            String line;
            while ((line = read.readLine()) != null) {
                long start = System.currentTimeMillis();

                String[] strLine = line.split(" ");
                int[] intLine = new int[strLine.length];
                for (int i = 0; i < strLine.length; i++){
                    intLine[i] = Integer.parseInt(strLine[i]);
                }
                shellSort(intLine);

                for (int i : intLine) {
                    System.out.print(i + " ");
                }

                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;

                try(FileWriter file = new FileWriter("src/res.txt", true)){
                    file.write(timeElapsed + " " + intLine.length);
                    file.append('\n');
                    file.flush();
                }

                System.out.println();
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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