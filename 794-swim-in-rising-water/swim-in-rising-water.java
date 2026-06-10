class Solution {
    // minmum elivation we usse relxation
    // max in one path we use max function 
    //dijkistra again

    public int swimInWater(int[][] grid) {
        int ro= grid.length;
        int co=grid[0].length;
        int [][] elv=new int[ro][co];
        for(int i=0;i<ro;i++){
            Arrays.fill(elv[i],Integer.MAX_VALUE);
        }
        // elv[0][0]=0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        elv[0][0] = grid[0][0];
        minHeap.add(new int[]{grid[0][0],0,0});
        int [][] directions={{1,0},{-1,0},{0,1},{0,-1}};
        while(!minHeap.isEmpty()){
            int []curr=minHeap.poll();
            int el=curr[0];
            int r=curr[1];
            int c=curr[2];
            
            if(el>elv[r][c]) continue;
            if(r==ro-1 && c==co-1) return el;
            for(int dir[] : directions ){
                int nr=r+dir[0];
                int nc=c+dir[1];
                if(nr>=0 && nc>=0 && nr<ro && nc<co){
                    int nel=Math.max(el,grid[nr][nc]);
                    // relaxation
                    if(nel<elv[nr][nc]){
                        elv[nr][nc]=nel;
                        minHeap.add(new int[]{nel,nr,nc});
                    }
                }
            }
        }
       return -1;
         
        
    }
}