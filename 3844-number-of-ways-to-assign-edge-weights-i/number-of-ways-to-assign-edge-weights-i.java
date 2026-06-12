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
  
//  private long power(long base,int exp,int mod){
//     if(exp==0) return 1;
    
//     // Calculate half power down the tree recursively
//     long half=power(base,exp/2,mod);
//     long halfSquare=(half*half)%mod;
    
//     // If exponent is odd, multiply by base one extra time
//     if(exp%2!=0){
//         return (halfSquare*(base%mod))%mod;
//     }
//     return halfSquare;
// }
private long power(long base,int exp,int mod){
    long result=1;
    
    // Just loop 'exp' times and multiply
    for(int i=0;i<exp;i++){
        result=(result*base)%mod;
    }
    
    return result;
}
}