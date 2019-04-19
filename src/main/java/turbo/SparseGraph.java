package turbo;

import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-04-19 10:49
 **/
public class SparseGraph {

    private LinkedList<Integer>[] matrix;

    private int v;

    public SparseGraph(int v) {
        this.v = v;
        matrix = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new LinkedList<>();
        }
    }


    public void connect(int v, int w) {
        matrix[v].add(w);
    }

    public LinkedList<Integer> adj(int v) {
        return matrix[v];
    }

    public int v() {
        return v;
    }
}
