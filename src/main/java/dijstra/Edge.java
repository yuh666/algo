package dijstra;

/**
 * @author yuh
 * @date 2019-04-22 14:38
 **/
public class Edge {

    private int tid;
    private int sid;
    private int weight;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(int tid, int sid, int weight) {
        this.tid = tid;
        this.sid = sid;
        this.weight = weight;
    }

    public Edge() {
    }

    public int other(int id) {
        return id == tid ? sid : tid;
    }


}
