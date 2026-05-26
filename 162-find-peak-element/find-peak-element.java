class Solution {
    public int findPeakElement(int[] arr) {
        int n = arr.length;
        int max = 0;
        for(int i = 0 ; i<n ; i++){
            if(arr[i]>arr[max]){
                max = i;   
            }
        }
        return max;
    }
}