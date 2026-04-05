class Solution {
    public int[][] highestPeak(int[][] isWater) {
        
        int s =isWater.length;
        int n= isWater[0].length;
        int m[][]=isWater;
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0;i<s;i++){
            for(int j=0;j<n;j++){
                if(m[i][j]==1) {
                    m[i][j]=0;
                    q.add(new int[]{i,j});
                }
                else m[i][j]=-1;
            }
        }

        while(!q.isEmpty()){
            int []l= q.poll();
            int r= l[0];
            int c= l[1];
             int [][] dir={{0,1},{1,0},{-1,0},{0,-1}};
            for(int [] d:dir){
                int nr =r+d[0];
                int nc=c+d[1];
                if(nr>=0 &&nc>=0 && nr<s && nc <n){
                    if(m[nr][nc]==-1){
                        m[nr][nc]=m[r][c]+1;
                        q.add(new int []{nr,nc});
                    }
                }
            }

        }
        return m;


        
    }
}