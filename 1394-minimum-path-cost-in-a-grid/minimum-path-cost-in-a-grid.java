class Solution {
    int[][] grid, moveCost, memo;
    int m, n;

    public int minPathCost(int[][] grid, int[][] moveCost) {
        this.grid = grid;
        this.moveCost = moveCost;
        m = grid.length;
        n = grid[0].length;

        // Initialize memo table with -1 (not computed)
        memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int ans = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            ans = Math.min(ans, solve(0, col));
        }
        return ans;
    }

    int solve(int row, int col) {
        // Base case
        if (row == m - 1) return grid[row][col];

        // Already computed? Return cached result
        if (memo[row][col] != -1) return memo[row][col];

        int cellValue = grid[row][col];
        int best = Integer.MAX_VALUE;

        for (int nextCol = 0; nextCol < n; nextCol++) {
            int cost = moveCost[cellValue][nextCol] + solve(row + 1, nextCol);
            best = Math.min(best, cost);
        }

        // Store in memo before returning
        memo[row][col] = cellValue + best;
        return memo[row][col];
    }
}