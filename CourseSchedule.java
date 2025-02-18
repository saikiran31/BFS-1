class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses==0)
        return true;

        int indegree[] = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int n=prerequisites.length;
        for(int i=0;i<n;i++)
        {
            int ind = prerequisites[i][1];
            int dep =prerequisites[i][0];
            indegree[dep]++;
            if(!map.containsKey(ind))
            {
                map.put(ind, new ArrayList<>());  
            }
             map.get(ind).add(dep);
        }
        int count=0;
        for(int i=0;i<numCourses;i++)
        {
            if(indegree[i]==0)
            {
                q.add(i);
                count++;
            }
        }
        if(count ==numCourses)
        return true;
        if(q.isEmpty())
        return false;
        while(!q.isEmpty())
        {
            int cur = q.poll();
            List<Integer> children = map.get(cur);
            if(children==null) continue;
            for(Integer child : children)
            {
                indegree[child]--;
                if(indegree[child]==0)
                {
                    q.add(child);
                    count++;
                    if(count == numCourses)
                    return true;
                }
            }
        }
        return count == numCourses;
    }
}

//tc: O(V+E) where V is the number of courses, and E is the number of edges (prerequisites).
//sc: O(V+E)