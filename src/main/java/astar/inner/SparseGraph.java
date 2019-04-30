package astar.inner;


import dijstra1.Edge;

import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-04-19 10:49
 **/
public class SparseGraph {

    private LinkedList<Edge>[] matrix;
    private Vertex[] vertexes;

    private int v;

    public SparseGraph(int v) {
        this.v = v;
        matrix = (LinkedList<Edge>[]) new LinkedList[v];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new LinkedList<>();
        }
        vertexes = new Vertex[v];
    }

    public void add(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }

    public Vertex get(int id) {
        return vertexes[id];
    }


    public void connect(int v, int w, int weight) {
        LinkedList<Edge> list = this.matrix[v];
        list.add(new Edge(v, w, weight));
    }

    public LinkedList<Edge> adj(int v) {
        return matrix[v];
    }

    public int v() {
        return v;
    }

}
