class Solution {
    public int[] executeInstructions(int n, int[] startPos, String s) {

        int m = s.length();
        int[] ans = new int[m];

        for(int j = 0; j < m; j++) {

            int row = startPos[0];
            int col = startPos[1];
            int count = 0;

            for(int i = j; i < m; i++) {

                char ch = s.charAt(i);

                if(ch == 'R') {
                    if(col + 1 >= n) break;
                    col++;
                }
                else if(ch == 'L') {
                    if(col - 1 < 0) break;
                    col--;
                }
                else if(ch == 'U') {
                    if(row - 1 < 0) break;
                    row--;
                }
                else { // D
                    if(row + 1 >= n) break;
                    row++;
                }

                count++;
            }

            ans[j] = count;
        }

        return ans;
    }
}