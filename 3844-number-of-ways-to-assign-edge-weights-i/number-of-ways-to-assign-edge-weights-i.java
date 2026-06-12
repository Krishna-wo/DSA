import java.util.*;

class Solution{
    private final int MOD=1_000_000_007;

    public int assignEdgeWeights(int[][] edges){
        int n=edges.length+1;
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int d=dfs(1,0,graph);
        return (int)power(2,d-1,MOD);
    }

    private int dfs(int node,int parent,List<List<Integer>> graph){
        int maxDepth=0;
        for(int neighbor:graph.get(node)){
            if(neighbor!=parent){
                maxDepth=Math.max(maxDepth,1+dfs(neighbor,node,graph));
            }
        }
        return maxDepth;
    }
  
   private long power(long base,int exp,int mod){
    long result=1;
    base=base%mod;
    
    while(exp>0){
        // Check if exp is odd using modulo
        if(exp%2!=0){
            result=(result*base)%mod;
        }
        base=(base*base)%mod; // Square the base
        exp=exp/2; // Divide exp by 2
    }
    return result;
}
}