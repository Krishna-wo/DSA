class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder res=new StringBuilder();
        for(String word : words){
            int totalWeight=0;
            for(char ch : word.toCharArray()){
                totalWeight+=weights[ch-'a'];
            }
             int mod=totalWeight%26;
                char mappedchar=(char)('z'-mod);
                res.append(mappedchar);
        }
        return res.toString();
    }
}