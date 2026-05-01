class Dijkstra {

    public static int[] findShortestPaths(int[][] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n]; //список расстояний
        boolean[] visited = new boolean[n]; //список посещенных вершин

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[start] = 0;

        for (int i = 0; i < n - 1; i++) {
            int minDist = getMin(dist, visited);
            visited[minDist] = true;

            for (int j = 0; j < n; j++) {
                // если есть ребро и вершина не посещена
                if (!visited[j] && graph[minDist][j] != 0 && dist[minDist] != Integer.MAX_VALUE) {
                    int newDist = dist[minDist] + graph[minDist][j];
                    if (newDist < dist[j]) {
                        dist[j] = newDist;
                    }
                }
            }
        }
        return dist;
    }

    private static int getMin(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }

        return index;
    }
}
