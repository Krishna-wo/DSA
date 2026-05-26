class Solution {
    int[][] matrix;
    int target;
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0)return false;
        this.matrix=matrix;
        this.target=target;
        return f(0,matrix.length-1,0,matrix[0].length-1);
    }
    boolean f(int is,int il,int js,int jl){
        if(is>il||js>jl)return false;
        if(matrix[is][jl]==target)return true;
        if(target<matrix[is][jl]){
            return f(is,il,js,jl-1);
        }else{
            return f(is+1,il,js,jl);
        }
    }
}