import java.util.*;

class Solution{
    public int largestPathValue(String colors,int[][]edges){
        int n=colors.length(),ans=0;

        ArrayList<ArrayList<Integer>>graph=new ArrayList<>();
        for(int i=0;i<n;i++)graph.add(new ArrayList<>());

        int[][]cnt=new int[n][26];
        int[]indegree=new int[n];

        for(int[]e:edges){
            graph.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }

        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0)q.offer(i);
        }

        int processed=0;

        while(!q.isEmpty()){
            int node=q.poll();
            processed++;

            int col=colors.charAt(node)-'a';
            cnt[node][col]++;
            ans=Math.max(ans,cnt[node][col]);

            for(int nbr:graph.get(node)){
                indegree[nbr]--;
                if(indegree[nbr]==0)q.offer(nbr);

                for(int j=0;j<26;j++){
                    cnt[nbr][j]=Math.max(cnt[nbr][j],cnt[node][j]);
                }
            }
        }

        return processed==n?ans:-1;
    }
}