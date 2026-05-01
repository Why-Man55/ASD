//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 0, 30, 50},
                {10, 0, 5, 0, 0},
                {0, 5, 0, 20, 10},
                {30, 0, 20, 0, 6},
                {50, 0, 10, 6, 0}
        };

        int start = 0;

        int[] distances = Dijkstra.findShortestPaths(graph, start);

        for (int i = 0; i < distances.length; i++) {
            System.out.println("От " + start + " до " + i + ": " + distances[i]);
        }
    }
}