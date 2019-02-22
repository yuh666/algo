package hash.consistent;


import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author yuh
 * @date 2019-02-22 16:17
 **/
public class StorageServer<V> extends TreeMap<Integer,V> {

    private String ip;



    public StorageServer(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }
}
