class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m= grid.length;
        
      if (grid[0][0] == 1 || grid[m - 1][m- 1] == 1) return -1;
      if (m == 1) return 1;

        Integer v[][]=new Integer[m][m];
        Queue<int[]> q = new ArrayDeque<>();
       
        q.offer(new int[]{0,0,1});
        v[0][0]=0;
       
        while(!q.isEmpty()){
            int []curr= q.poll();
            int r =curr[0],c=curr[1],di=curr[2];
            int [][] dir={
                {-1,-1},{-1,0},{-1,1},
                {0,-1},       {0,1},
                {1,-1},{1,0},{1,1}
            };
            if(r==m-1 && c ==m-1){
                return di;
            }
            for(int d[] : dir){
                int nr=r+d[0];
                int nc =c+d[1];
                if(nr>=0 && nr <m && nc >=0 && nc <m
                 && grid[nr][nc]==0 && v[nr][nc]==null){
                      v[nr][nc]=0;
                      q.offer(new int[]{nr,nc,di+1});
                }
            }
        }





        return -1;
         
        
    }
}