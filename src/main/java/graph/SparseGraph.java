package graph;

import java.util.Arrays;
import java.util.LinkedList;

public class SparseGraph {

    private LinkedList<Integer>[] matrix;


    public SparseGraph(int capacity) {
        matrix = (LinkedList<Integer>[]) new LinkedList[capacity];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new LinkedList<>();
        }
    }

    public void add(int i, int j) {
        matrix[i].add(j);
        matrix[j].add(i);
    }


    public void bfs(int s, int t) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(s);
        boolean[] visited = new boolean[matrix.length];
        int[] prev = new int[matrix.length];
        Arrays.fill(prev, -1);

        while (!list.isEmpty()) {
            Integer remove = list.remove();
            if (remove == t) {
                print(prev, t);
                return;
            }
            visited[remove] = true;
            LinkedList<Integer> list1 = this.matrix[remove];
            for (Integer integer : list1) {
                if (!visited[integer]) {
                    prev[integer] = remove;
                    list.add(integer);
                }
            }
        }
    }


    public void dfs(int s, int t) {
        boolean[] visited = new boolean[matrix.length];
        int[] prev = new int[matrix.length];
        Arrays.fill(prev,-1);
        boolean found = _dfs(visited, prev, s, t);
        if (found) {
            print(prev, t);
        }
    }

    private boolean _dfs(boolean[] visited, int[] prev, int s, int t) {
        if (s == t) {
            return true;
        }
        visited[s] = true;
        LinkedList<Integer> list = this.matrix[s];
        for (Integer integer : list) {
            if (!visited[integer]) {
                prev[integer] = s;
                if (_dfs(visited, prev, integer, t)) {
                    return true;
                }
            }
        }
        return false;
    }


    public void print(int[] prev, int t) {
        if (prev[t] != -1) {
            print(prev, prev[t]);
        }
        System.out.print(t + "->");
    }

    public static void main(String[] args) {
        SparseGraph sparseGraph = new SparseGraph(5);
//        int[] prev = new int[3];
//        prev[2] = 1;
//        prev[1] = 0;
//        prev[0] = -1;
//        sparseGraph.print(prev,0,2);

        sparseGraph.add(0, 1);
        sparseGraph.add(1, 2);
        sparseGraph.add(2, 3);
        sparseGraph.add(2, 4);

        sparseGraph.dfs(0, 4);


    }


}
