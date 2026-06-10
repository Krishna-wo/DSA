class Solution {
    // yaha pe minimum time krna hai prr apne raaste me max time nahi lekin time addd krte rahana hai
    public int minTimeToReach(int[][] moveTime) {
        int rows = moveTime.length, cols = moveTime[0].length;
        int[][] mint = new int[rows][cols];//yaha pe minimum time 
        PriorityQueue<int []> minHeap= new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        minHeap.add(new int []{0,0,0});
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                mint[i][j]=Integer.MAX_VALUE;
            }
        }
          mint[0][0] = 0;
        int [][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!minHeap.isEmpty()){
            int curr[] = minHeap.poll();
            int t=curr[0];
            int r=curr[1];
            int c=curr[2];
            if(t>mint[r][c]) continue;
            if(r==rows-1 && c==cols-1) return t;
            for( int dir[] : directions ){
                int nr=r+dir[0];
                int nc=c+dir[1];
                if(nr>=0 && nc>=0 && nr<rows && nc<cols){
                    int nt =Math.max(t,moveTime[nr][nc])+1;
                    //relaxation
                    if(nt<mint[nr][nc]){
                    mint[nr][nc]=nt;
                    minHeap.add(new int[]{nt,nr,nc});
                }
                }
               
            }
        }
        return -1;
    }
}