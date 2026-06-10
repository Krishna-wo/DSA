import java.util.*;

class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        if(m==1 && n==1) return 0;
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> q = new LinkedList<>();
        // {row, col, remainingK}
        q.offer(new int[]{0, 0, k});
        visited[0][0][k] = true;
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                int rem = curr[2];
                if (r==m-1 && c==n-1){
                    return steps;
                }
                for(int[]dir : dirs){
                    int nr=r+dir[0];
                    int nc=c+dir[1];

                    if(nr<0 || nr>=m || nc<0 || nc>=n)
                        continue;

                    if(grid[nr][nc] == 0){
                        if(!visited[nr][nc][rem]){
                            visited[nr][nc][rem]=true;
                            q.offer(new int[]{nr, nc, rem});
                        }

                    } else {
                        if(rem>0 && !visited[nr][nc][rem-1]) {
                            visited[nr][nc][rem - 1] = true;
                            q.offer(new int[]{nr, nc, rem - 1});
                        }
                    }
                }
            }

            steps++;
        }
        return -1;
    }
}