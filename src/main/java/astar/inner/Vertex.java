package astar.inner;

/**
 * 顶点
 *
 * @author yuh
 * @date 2019-04-30 10:02
 **/
public class Vertex {

    private int id;
    private int x;
    private int y;

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
