class Solution {
    String[] strs;
    int n;
    boolean visited[];
    public int numSimilarGroups(String[] strs) {
        int island=0;
        int n=strs.length;
        this.strs=strs;
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                island++;
                dfs(i);
            }
        }
        return island;
    }
    void dfs(int i){
        visited[i]=true;
        for(int j=0;j<strs.length;j++){
            if(!visited[j] && isEqual(strs[i],strs[j])){
                dfs(j);
            }
        }

    }
    boolean isEqual(String a,String b){
        int count=0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)) count++;
        }
        return count==0 || count==2;
    }
}