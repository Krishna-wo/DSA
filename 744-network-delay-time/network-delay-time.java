class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int []>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
         for(int []e : times){
            int u=e[0];
            int v=e[1];
            int w=e[2];
            adj.get(u).add(new int[]{v,w});
           
        }
        int dist[] = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k]=0;
         PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[]{0, k});
        while(!pq.isEmpty()){
              int [] curr = pq.poll();
            int d= curr[0];
            int u= curr[1];
            if(d> dist[u]){
                continue;
            }
            for(int [] ni :adj.get(u)){
                int v= ni[0];
                int weight=ni[1];
                if(dist[u]+weight<dist[v]){
                    dist[v]=dist[u]+weight;
                    pq.add(new int[]{dist[v],v});
                }
            }
        }
        for(int i=1;i<=n;i++){
            if(dist[i]==Integer.MAX_VALUE) return -1;
            
        }
        Arrays.sort(dist);
        return dist[n-1];
        
    }
}