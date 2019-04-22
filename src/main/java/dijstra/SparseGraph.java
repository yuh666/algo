package dijstra;

import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-04-19 10:49
 **/
public class SparseGraph {

    private LinkedList<Edge>[] matrix;

    private int v;

    public SparseGraph(int v) {
        this.v = v;
        matrix = (LinkedList<Edge>[]) new LinkedList[v];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new LinkedList<>();
        }
    }


    public void connect(int v, int w, int weight) {
        matrix[v].add(new Edge(v, w, weight));
    }

    public LinkedList<Edge> adj(int v) {
        return matrix[v];
    }

    public int v() {
        return v;
    }

}
