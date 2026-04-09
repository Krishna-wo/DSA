class Solution {
     int m, n;
     int heights[][];
     
    
    
     public List<List<Integer>> pacificAtlantic(int[][] heights) {
         m = heights.length;
         n= heights[0].length;
         this.heights=heights;
         boolean [][] p = new boolean[m][n];
         boolean [][] a = new boolean [m][n];
         for(int j=0;j<n;j++) dfs(0,j,p);
         for(int i=0;i<m;i++) dfs(i,0,p);
         for(int j=0;j<n;j++) dfs(m-1,j,a);
         for(int i=0;i<m;i++) dfs(i,n-1,a);
         List<List<Integer>> res = new ArrayList<>();
         for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(p[i][j] && a[i][j]){
                    res.add(Arrays.asList(i,j));
                }
            }
         }
         return res;
        
     }

    void dfs(int i,int j, boolean [][] visited){
        if(visited[i][j]) return;
        visited[i][j]=true;
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for(int []d:directions){
            int x=d[0]+i;
            int y=d[1]+j;
            if(x<0 || x>=m || y<0 || y>=n) continue;
            if(heights[x][y]<heights[i][j]) continue;
            dfs(x,y,visited);
        }

    }
}