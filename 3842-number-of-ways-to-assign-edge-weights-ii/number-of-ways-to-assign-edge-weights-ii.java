import java.util.*;

class Solution{
    private final int MOD=1_000_000_007;
    private final int LOG=18; // 2^17 is > 100,000 nodes

    public int[] assignEdgeWeights(int[][] edges,int[][] queries){
        int n=edges.length+1;
        List<List<Integer>> graph=new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int[] depth=new int[n+1];
        int[][] up=new int[n+1][LOG];
        
        // 1. Build the depth array and 1st-level ancestors
        dfs(1,0,graph,up,depth);
        
        // 2. Precompute the Binary Lifting table
        for(int j=1;j<LOG;j++){
            for(int i=1;i<=n;i++){
                up[i][j]=up[up[i][j-1]][j-1];
            }
        }
        
        int[] ans=new int[queries.length];
        
        // 3. Process every query in O(log N) time
        for(int i=0;i<queries.length;i++){
            int u=queries[i][0];
            int v=queries[i][1];
            
            if(u==v){
                ans[i]=0; // No edges between a node and itself
            }else{
                int lcaNode=getLca(u,v,up,depth);
                
                // Formula to find total edges between u and v
                int d=depth[u]+depth[v]-2*depth[lcaNode];
                
                ans[i]=(int)power(2,d-1,MOD);
            }
        }
        return ans;
    }
    
    private void dfs(int node,int parent,List<List<Integer>> graph,int[][] up,int[] depth){
        up[node][0]=parent;
        for(int neighbor:graph.get(node)){
            if(neighbor!=parent){
                depth[neighbor]=depth[node]+1;
                dfs(neighbor,node,graph,up,depth);
            }
        }
    }
    
    private int getLca(int u,int v,int[][] up,int[] depth){
        // Ensure u is the deeper node to keep the logic simple
        if(depth[u]<depth[v]){
            int temp=u;
            u=v;
            v=temp;
        }
        
        // Step 1: Bring u and v to the exact same depth
        int diff=depth[u]-depth[v];
        for(int j=0;j<LOG;j++){
            if(((diff>>j)&1)==1){
                u=up[u][j];
            }
        }
        
        if(u==v) return u; // If they meet, we found the LCA!
        
        // Step 2: Climb up together in powers of 2 until right below the LCA
        for(int j=LOG-1;j>=0;j--){
            if(up[u][j]!=up[v][j]){
                u=up[u][j];
                v=up[v][j];
            }
        }
        
        // Step 3: Jump one last step to hit the actual LCA
        return up[u][0];
    }
    
    // The exact same math function from Part I
    private long power(long base,int exp,int mod){
        long result=1;
        base=base%mod;
        while(exp>0){
            if((exp&1)==1) result=(result*base)%mod;
            base=(base*base)%mod;
            exp>>=1;
        }
        return result;
    }
}