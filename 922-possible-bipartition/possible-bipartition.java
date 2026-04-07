class Solution {
    public boolean bfs(int node, int[] vis, ArrayList<ArrayList<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        vis[node] =0; 
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int neigh : graph.get(curr)) {
                if(vis[neigh]==-1) {
                    vis[neigh]=1-vis[curr];
                    q.offer(neigh);
                } 
                else if(vis[neigh]==vis[curr]) {
                    return false; 
                }
            }
        }

        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        for(int[]d : dislikes) {
            graph.get(d[0]).add(d[1]);
            graph.get(d[1]).add(d[0]);
        }
        int[]vis = new int[n+1];
        Arrays.fill(vis,-1);
        for(int i=1;i<=n;i++) {
            if(vis[i]==-1) {
                if(!bfs(i,vis,graph)) {
                    return false;
                }
            }
        }

        return true;
    }
}