class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n=passingFees.length;
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int []e: edges){
            int u=e[0];
            int v=e[1];
            int t=e[2];
            adj.get(u).add(new int[]{v,t});
            adj.get(v).add(new int[]{u,t});
        }
        int [][] dist = new int[n][maxTime+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        dist[0][0]=passingFees[0];
        PriorityQueue<int[]> pq =new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        // cost ,time ,node , as cost should be minizies and we take count of time whenerve rech  return it 
        pq.add(new int[]{dist[0][0],0,0});
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int cost = curr[0];
            int time = curr[1];
            int node = curr[2];
            if(node==n-1) return cost;
            if(cost>dist[node][time]) continue;
            for(int []nbr :adj.get(node)){
                int next=nbr[0];
                int edjTime=nbr[1];
                int newTime=time+edjTime;
                // very important dont break continue means time ends somewhre do move by other way as possible
                if(newTime>maxTime) continue;
                int newCost=cost+passingFees[next];
                if(newCost<dist[next][newTime]){
                    dist[next][newTime]=newCost;
                    pq.add(new int[]{newCost,newTime,next});
                }
               
            }
        }
       return -1;
    }
}
