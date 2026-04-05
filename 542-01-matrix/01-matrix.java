class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int matrix[][] = mat;
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0) q.add(new int[]{i,j});
                else matrix[i][j]=-1;

            }
        }
        while(!q.isEmpty()){
            int [] l = q.poll();
            int r=l[0];
            int c=l[1];
            int [][] dir={{0,1},{1,0},{-1,0},{0,-1}};
            for(int [] d:dir){
                int nr =r+d[0];
                int nc=c+d[1];
                if(nr>=0 &&nc>=0 && nr<m && nc <n){
                    if(matrix[nr][nc]==-1){
                        matrix[nr][nc]=matrix[r][c]+1;
                        q.add(new int []{nr,nc});
                    }
                }
            }
           
        }
         return matrix;
    }
}