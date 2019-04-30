package dijstra1;

import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-04-22 15:00
 **/
public class ShortestPath {


    private SparseGraph1 sparseGraph1;
    private int v;

    public ShortestPath(SparseGraph1 sparseGraph1) {
        this.sparseGraph1 = sparseGraph1;
        this.v = sparseGraph1.v();
    }

    //from tid to sid
    public LinkedList<Integer> path(int tid, int sid) {
        int[] ws = new int[v];
        IndexHeap<Integer> tIndexHeap = new IndexHeap<Integer>(v);
        LinkedList<Integer> list = new LinkedList<>();
        int[] pre = new int[v];
        tIndexHeap.add(tid, 0);
        ws[tid] = 0;
        while (!tIndexHeap.isEmpty()) {
            int index = tIndexHeap.removeIndex();
            if (index == sid) {
                as(pre, tid, sid, list);
                return list;
            }
            Integer wt = ws[index];
            LinkedList<Edge> adj = sparseGraph1.adj(index);
            for (Edge edge : adj) {
                int other = edge.other(index);
                if (tIndexHeap.contains(other)) {
                    Integer byIndex = tIndexHeap.getByIndex(other);
                    if (byIndex > wt + edge.getWeight()) {
                        ws[other] = wt + edge.getWeight();
                        tIndexHeap.change(other, wt + edge.getWeight());
                        pre[other] = index;
                    }
                } else {
                    tIndexHeap.add(other, wt + edge.getWeight());
                    ws[other] = wt + edge.getWeight();
                    pre[other] = index;
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

    public static void main(String[] args) {
        SparseGraph1 sparseGraph1 = new SparseGraph1(4);
        sparseGraph1.connect(0, 1, 1);
        sparseGraph1.connect(0, 2, 2);

        sparseGraph1.connect(1, 3, 20);
        sparseGraph1.connect(2, 3, 1000);

        ShortestPath shortestPath = new ShortestPath(sparseGraph1);
        LinkedList<Integer> path = shortestPath.path(0, 3);
        System.out.println(path);
    }
}
