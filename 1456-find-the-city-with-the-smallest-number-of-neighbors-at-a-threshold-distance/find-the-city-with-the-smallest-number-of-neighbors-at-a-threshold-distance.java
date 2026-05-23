import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 1. Build Adjacency List
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int minNeighbors = n;
        int resCity = -1;

        // 2. Run Dijkstra for each city
        for (int i = 0; i < n; i++) {
            int reachableCount = countReachableCities(i, n, adj, distanceThreshold);
            
            // 3. Update result based on problem constraints
            if (reachableCount <= minNeighbors) {
                minNeighbors = reachableCount;
                resCity = i; // Ties are handled because we iterate 0 to n-1
            }
        }
        return resCity;
    }

    private int countReachableCities(int start, int n, List<int[]>[] adj, int threshold) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dists = new int[n];
        Arrays.fill(dists, Integer.MAX_VALUE);
        
        dists[start] = 0;
        pq.add(new int[]{start, 0});
        int count = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            if (d > dists[u]) continue;

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if (dists[u] + weight <= threshold && dists[u] + weight < dists[v]) {
                    dists[v] = dists[u] + weight;
                    pq.add(new int[]{v, dists[v]});
                }
            }
        }

        // Count how many cities (excluding self) are within the threshold
        for (int i = 0; i < n; i++) {
            if (i != start && dists[i] <= threshold) {
                count++;
            }
        }
        return count;
    }
}