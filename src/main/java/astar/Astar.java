package astar;

import astar.inner.SparseGraph;
import astar.inner.Vertex;
import dijstra1.Edge;
import dijstra1.IndexHeap;

import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-04-22 15:00
 **/
public class Astar {


    private SparseGraph sparseGraph;
    private int v;

    public Astar(SparseGraph sparseGraph) {
        this.sparseGraph = sparseGraph;
        this.v = sparseGraph.v();
    }

    //from tid to sid
    public LinkedList<Integer> path(int tid, int sid) {
        Vertex vertex = sparseGraph.get(sid);
        int[] ws = new int[v];
        IndexHeap<Integer> tIndexHeap = new IndexHeap<Integer>(v);
        LinkedList<Integer> list = new LinkedList<>();
        int[] pre = new int[v];
        tIndexHeap.add(tid, 0);
        ws[tid] = 0;
        while (!tIndexHeap.isEmpty()) {
            int index = tIndexHeap.removeIndex();
            Integer wt = ws[index];
            LinkedList<Edge> adj = sparseGraph.adj(index);
            for (Edge edge : adj) {
                int other = edge.other(index);
                Vertex vertex1 = sparseGraph.get(other);
                int mahattan = mahattan(vertex.getX(), vertex.getY(), vertex1.getX(), vertex1.getY());
                if (tIndexHeap.contains(other)) {
                    Integer byIndex = tIndexHeap.getByIndex(other);
                    if (byIndex > wt + edge.getWeight()+mahattan) {
                        ws[other] = wt + edge.getWeight()+mahattan;
                        tIndexHeap.change(other, wt + edge.getWeight()+mahattan);
                        pre[other] = index;
                    }
                } else {
                    tIndexHeap.add(other, wt + edge.getWeight()+mahattan);
                    ws[other] = wt + edge.getWeight()+mahattan;
                    pre[other] = index;
                }
                if (other == sid) {
                    as(pre, tid, sid, list);
                    return list;
                }
            }
        }
        return null;
    }

    public void as(int[] pre, int tid, int sid, LinkedList<Integer> list) {
        while (tid != sid) {
            list.addFirst(sid);
            sid = pre[sid];
        }
        list.addFirst(tid);
    }
    public int mahattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }


    public static void main(String[] args) {

        SparseGraph sparseGraph = new SparseGraph(4);
        sparseGraph.add(0,0,0);
        sparseGraph.add(1,-1,-1);
        sparseGraph.add(2,1,1);
        sparseGraph.add(3,2,2);

        sparseGraph.connect(0,1,1);
        sparseGraph.connect(1,3,1);
        sparseGraph.connect(0,2,1);
        sparseGraph.connect(2,3,1);

        Astar astar = new Astar(sparseGraph);

        System.out.println(astar.path(0,3));
    }


}
