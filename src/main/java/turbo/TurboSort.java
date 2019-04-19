package turbo;

import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-04-19 11:12
 **/
public class TurboSort {


    public static void turboSort1(SparseGraph sparseGraph) {
        int[] arr = new int[sparseGraph.v()];
        for (int i = 0; i < sparseGraph.v(); i++) {
            arr[i] = sparseGraph.adj(i).size();
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                list.add(i);
            }
        }
        while (!list.isEmpty()) {
            Integer pop = list.pop();
            System.out.print(pop + "->");
            for (int i = 0; i < sparseGraph.v(); i++) {
                if (sparseGraph.adj(i).contains(pop)) {
                    if (--arr[i] == 0) {
                        list.add(i);
                    }
                }
            }
        }
    }

    public static void turboSort2(SparseGraph sparseGraph) {
        boolean[] visited = new boolean[sparseGraph.v()];
        for (int i = 0; i < sparseGraph.v(); i++) {
            if (!visited[i]) {
                dfs(i, visited, sparseGraph);
            }
        }
    }

    private static void dfs(int i, boolean[] visited, SparseGraph sparseGraph) {
        visited[i] = true;
        LinkedList<Integer> adj = sparseGraph.adj(i);
        for (Integer c : adj) {
            if (!visited[c]) {
                dfs(c, visited, sparseGraph);
            }
        }
        System.out.print(i + "->");
    }


    public static void main(String[] args) {
        SparseGraph sparseGraph = new SparseGraph(4);
        sparseGraph.connect(0, 1);
        sparseGraph.connect(0, 2);
        sparseGraph.connect(2, 3);
        turboSort2(sparseGraph);
    }
}
