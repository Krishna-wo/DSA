class Solution {
    public int mySqrt(int x) {
        if(x<2) return x;
        int l=1,r=x/2;
        while(l<=r){
            int m= l+(r-l)/2;
            long sqr = (long)m * m;
            if(sqr==x){
                return m;
            }else if(sqr<x){
                l=m+1;
            }else{
                r=m-1;
            }
        }
        return r;
    }
}