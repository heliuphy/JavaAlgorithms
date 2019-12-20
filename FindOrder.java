import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindOrder {
    /*
     * @param numCourses: a total of n courses
     * 
     * @param prerequisites: a list of prerequisite pairs
     * 
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
        if (numCourses == 0 || prerequisites == null) {
            return new int[0];
        }
        int n = numCourses;
        int[] inNum = new int[n]; // 入度

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n); // 图
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            inNum[prerequisites[i][0]]++;
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n];
        int flag = 0;

        for (int i = 0; i < n; i++) {
            if (inNum[i] == 0) {
                result[flag++] = i;
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : graph.get(node)) {
                inNum[neighbor]--;
                if (inNum[neighbor] == 0) {
                    result[flag++] = neighbor;
                    q.offer(neighbor);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (inNum[i] != 0) {
                return new int[0];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindOrder fo = new FindOrder();
        int n = 2;
        int[][] p = new int[][] { { 1, 0 } };
        System.out.println(fo.findOrder(n, p));

    }
}
